package com.srsystems.challenge.state.service;

import com.google.common.collect.Iterables;
import com.srsystems.challenge.city.domain.response.StateCityQuantityResponse;
import com.srsystems.challenge.state.domain.StateRepository;
import com.srsystems.challenge.state.domain.StateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class StateServiceImpl implements StateService {

    private StateRepository stateRepository;

    public StateServiceImpl(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public List<StateCityQuantityResponse> getStatesWithHighestAndLowestAmountOfCity() {
        log.info("Method=getStatesWithHighestAndLowestAmountOfCity");
        List<StateCityQuantityResponse> dtos = stateRepository.getAmountOfCitiesByState();

        if (dtos.isEmpty()) {
            return new ArrayList<>();
        }

        return Arrays.asList(dtos.get(0), Iterables.getLast(dtos));
    }

    @Override
    public List<StateCityQuantityResponse> getStatesWithCityCount() {
        log.info("Method=getStatesWithCityCount");
        return stateRepository.getAmountOfCitiesByState();
    }
}
