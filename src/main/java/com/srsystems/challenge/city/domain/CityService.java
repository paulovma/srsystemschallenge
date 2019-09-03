package com.srsystems.challenge.city.domain;

import com.srsystems.challenge.city.domain.request.CityRequest;
import com.srsystems.challenge.city.domain.response.CityCountResponse;
import com.srsystems.challenge.city.domain.response.CityResponse;

import java.util.List;

public interface CityService {

    public List<CityResponse> findAll();

    public boolean insert(CityRequest cityRequest);

    public List<CityResponse> getCapitalCities();

    CityResponse getCityByIbgeId(final Long ibgeId);

    List<CityResponse> getCityByState(String state);

    void deleteCityByIbgeId(Long ibgeId);

    CityCountResponse getTotal();
}
