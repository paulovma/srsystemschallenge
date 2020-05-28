package com.srsystems.challenge.city;


import com.srsystems.challenge.city.domain.CityRepository;
import com.srsystems.challenge.city.domain.request.CityRequest;
import com.srsystems.challenge.city.domain.response.CityResponse;
import com.srsystems.challenge.city.mapper.CityMapper;
import com.srsystems.challenge.city.service.CityServiceImpl;
import com.srsystems.challenge.csv.city.domain.StateIdMap;
import com.srsystems.challenge.entity.City;
import com.srsystems.challenge.entity.State;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ExtendWith(MockitoExtension.class)
public class CityServiceTest {

    @Mock
    private CityRepository cityRepository;

    @Mock
    private CityMapper cityMapper;

    @InjectMocks
    private CityServiceImpl cityService;

    @Test
    public void test_getAllCities() {
        List<City> cityList = getCityList();

        Mockito.when(cityRepository.findAll()).thenReturn(cityList);
        Mockito.when(cityMapper.citiesToResponseList(cityList)).thenReturn(getCityResponseList());

        List<CityResponse> response = cityService.findAll();
        Assertions.assertEquals(1, response.size());
        Assertions.assertEquals(cityList.get(0).getIbgeId(), response.get(0).getIbgeId());
    }

    @Test
    public void test_getCapitalCities() {
        List<City> cityList = getCityList();

        Mockito.when(cityRepository.findAllByCapitalOrderByName(Boolean.TRUE)).thenReturn(cityList);
        Mockito.when(cityMapper.citiesToResponseList(cityList)).thenReturn(getCityResponseList());

        List<CityResponse> response = cityService.getCapitalCities();
        Assertions.assertEquals(1, response.size());
        Assertions.assertEquals(cityList.get(0).getIbgeId(), response.get(0).getIbgeId());
    }

    @Test
    public void test_getCityByIbgeId() {
        var city = makeCity();
        var cityResponse = makeCityResponse();

        Mockito.when(cityRepository.findByIbgeId(1L)).thenReturn(city);
        Mockito.when(cityMapper.cityToRequest(city)).thenReturn(cityResponse);

        CityResponse cityActualResponse = cityService.getCityByIbgeId(1L);

        Assertions.assertEquals(cityResponse.getIbgeId(), cityActualResponse.getIbgeId());
    }

    @Test
    public void test_getCityByState() {
        List<City> cityList = getCityList();
        var state = "SP";

        Mockito.when(cityRepository.findAllByStateUf(state)).thenReturn(cityList);
        Mockito.when(cityMapper.citiesToResponseList(cityList)).thenReturn(getCityResponseList());

        List<CityResponse> response = cityService.getCityByState(state);

        Assertions.assertEquals(1, response.size());
        Assertions.assertEquals(cityList.get(0).getIbgeId(), response.get(0).getIbgeId());
    }

    private List<CityResponse> getCityResponseList() {
        return List.of(makeCityResponse());
    }

    private List<City> getCityList() {
        return List.of(makeCity());
    }

    private CityResponse makeCityResponse() {
        var city = makeCity();
        return CityResponse.builder()
                .ibgeId(city.getIbgeId())
                .name(city.getName())
                .state(city.getState().getUf())
                .capital(city.getCapital())
                .latitude(city.getLatitude())
                .longitude(city.getLongitude())
                .accent(city.getAccent())
                .alternativeNames(city.getAlternativeNames())
                .microRegion(city.getMicroRegion())
                .mesoRegion(city.getMesoRegion())
                .build();
    }

    private City makeCity() {
        return City.builder()
                .id(1L)
                .ibgeId(1L)
                .name("teste")
                .state(new State(1L, "uf"))
                .capital(false)
                .latitude(Double.longBitsToDouble(1L))
                .longitude(Double.longBitsToDouble(1L))
                .accent("acc")
                .alternativeNames("alt")
                .microRegion("micro")
                .mesoRegion("meso")
                .build();
    }

    private CityRequest makeCityRequest() {
        return CityRequest.builder()
                .ibgeId(1L)
                .name("teste")
                .state("uf")
                .capital(false)
                .latitude(Double.longBitsToDouble(1L))
                .longitude(Double.longBitsToDouble(1L))
                .accent("acc")
                .alternativeNames("alt")
                .microRegion("micro")
                .mesoRegion("meso")
                .build();
    }

}
