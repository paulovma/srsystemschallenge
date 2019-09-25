package com.srsystems.challenge.city.filter;

import com.srsystems.challenge.city.domain.request.CityRequest;
import com.srsystems.challenge.city.domain.request.CityRequestFilter;
import com.srsystems.challenge.city.exception.StateNotExistent;
import com.srsystems.challenge.csv.city.domain.StateIdMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotExistentStateFilter implements CityRequestFilter {

    @Autowired
    private StateIdMap stateIdMap;

    @Override
    public void filter(CityRequest cityRequest) {
        if (null == stateIdMap.getMap().get(cityRequest.getState())) {
            throw new StateNotExistent();
        }
    }
}
