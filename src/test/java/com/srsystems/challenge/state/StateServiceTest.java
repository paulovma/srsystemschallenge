package com.srsystems.challenge.state;

import com.google.common.collect.Iterables;
import com.srsystems.challenge.city.domain.response.StateCityQuantityResponse;
import com.srsystems.challenge.state.domain.StateRepository;
import com.srsystems.challenge.state.service.StateServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class StateServiceTest {

    @Mock
    private StateRepository stateRepository;

    @InjectMocks
    private StateServiceImpl stateService;

    @Test
    public void test_getStatesWithHighestAndLowestAmountOfCity_expectingEmptyList() {
        Mockito.when(stateRepository.getAmountOfCitiesByState()).thenReturn(new ArrayList<>());
        var response = stateService.getStatesWithHighestAndLowestAmountOfCity();

        Assertions.assertTrue(response.isEmpty());
    }

    @Test
    public void test_getStatesWithHighestAndLowestAmountOfCity() {
        var expectedList = makeStateCityQuantityResponseList();
        Mockito.when(stateRepository.getAmountOfCitiesByState()).thenReturn(expectedList);

        var response = stateService.getStatesWithHighestAndLowestAmountOfCity();

        Assertions.assertEquals(2, response.size());
        Assertions.assertEquals("SP", expectedList.get(0).getName());
        Assertions.assertEquals("RJ", Iterables.getLast(expectedList).getName());
    }

    private List<StateCityQuantityResponse> makeStateCityQuantityResponseList() {
        return List.of(
            StateCityQuantityResponse.builder().name("SP").amount(20L).build(),
            StateCityQuantityResponse.builder().name("AL").amount(18L).build(),
            StateCityQuantityResponse.builder().name("RJ").amount(2L).build()
        );
    }
}
