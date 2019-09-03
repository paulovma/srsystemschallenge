package com.srsystems.challenge.state.domain;

import com.srsystems.challenge.city.domain.response.StateCityQuantityResponse;

import java.util.List;

public interface StateService {

    List<StateCityQuantityResponse> getStatesWithHighestAndLowestAmountOfCity();

    public List<StateCityQuantityResponse> getStatesWithCityCount();
}
