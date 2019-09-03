package com.srsystems.challenge.city.service;

import com.srsystems.challenge.city.domain.response.CityCountResponse;
import com.srsystems.challenge.csv.city.domain.StateIdMap;
import com.srsystems.challenge.entity.City;
import com.srsystems.challenge.city.domain.request.CityRequest;
import com.srsystems.challenge.city.domain.response.CityResponse;
import com.srsystems.challenge.city.domain.CityService;
import com.srsystems.challenge.city.mapper.CityMapper;
import com.srsystems.challenge.city.domain.CityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    private final CityMapper cityMapper;

    @Autowired
    private StateIdMap stateIdMap;

    public CityServiceImpl(CityRepository cityRepository, CityMapper cityMapper) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CityResponse> findAll() {
        log.info("Method=findAll");
        return cityMapper.citiesToResponseList(cityRepository.findAll());
    }

    @Override
    @Transactional
    public boolean insert(CityRequest cityRequest) {
        log.info("Method=insert, cityRequest={}", cityRequest);
        var map = stateIdMap.getMap();
        var state = map.get(cityRequest.getState());
        cityRequest.setStateId(state);
        City city = cityMapper.requestToCity(cityRequest);
        cityRepository.save(city);
        return city.getId() != null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CityResponse> getCapitalCities() {
        log.info("Method=getCapitalCities");
        List<City> cities = cityRepository.findAllByCapitalOrderByName(Boolean.TRUE);
        return cityMapper.citiesToResponseList(cities);
    }

    @Override
    @Transactional(readOnly = true)
    public CityResponse getCityByIbgeId(Long ibgeId) {
        log.info("Method=getCityByIbgeId");
        return cityMapper.cityToRequest(cityRepository.findByIbgeId(ibgeId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CityResponse> getCityByState(String state) {
        log.info("Method=getCityByState");
        return cityMapper.citiesToResponseList(cityRepository.findAllByStateUf(state));
    }

    @Override
    @Transactional
    public void deleteCityByIbgeId(Long ibgeId) {
        log.info("Method=deleteCityByIbgeId, ibgeId={}", ibgeId);
        cityRepository.deleteByIbgeId(ibgeId);
    }

    @Override
    public CityCountResponse getTotal() {
        log.info("Method=getTotal");
        return new CityCountResponse(cityRepository.count());
    }

}
