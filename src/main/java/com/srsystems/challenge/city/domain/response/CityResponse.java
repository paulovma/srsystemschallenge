package com.srsystems.challenge.city.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CityResponse {

    private Long ibgeId;

    private String name;

    private String state;

    private boolean capital;

    private Double latitude;

    private Double longitude;

    private String accent;

    private String alternativeNames;

    private String microRegion;

    private String mesoRegion;
}
