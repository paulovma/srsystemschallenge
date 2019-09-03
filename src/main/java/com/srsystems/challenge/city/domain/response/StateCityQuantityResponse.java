package com.srsystems.challenge.city.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StateCityQuantityResponse {

    private String name;

    private Long amount;
}
