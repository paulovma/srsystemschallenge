package com.srsystems.challenge.csv.city.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class StateIdMap {

    private Map<String, Long> map = new HashMap<>();

    public StateIdMap() {
        for (StateEnum state : StateEnum.values()) {
            map.put(state.getStateKey(), state.getStateId());
        }
    }
}
