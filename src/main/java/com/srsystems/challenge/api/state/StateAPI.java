package com.srsystems.challenge.api.state;

import com.srsystems.challenge.city.domain.response.StateCityQuantityResponse;
import com.srsystems.challenge.state.domain.StateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/state")
public class StateAPI {

    @Autowired
    private StateService stateService;

    @GetMapping("/highest-lowest")
    @ApiOperation(value = "Returns the states with highest and lowest quantity of cities")
    public List<StateCityQuantityResponse> getAmountOfCitiesByState() {
        return stateService.getStatesWithHighestAndLowestAmountOfCity();
    }

    @GetMapping("/amount")
    @ApiOperation(value = "Returns the states and its cities")
    public List<StateCityQuantityResponse> getStatesWithCityCount() {
        return stateService.getStatesWithCityCount();
    }
}
