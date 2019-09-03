package com.srsystems.challenge.city.domain.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CityRequest {

    private Long ibgeId;

    private String name;

    @JsonIgnore
    private Long stateId;

    private String state;

    private boolean capital;

    private Double latitude;

    private Double longitude;

    private String accent;

    private String alternativeNames;

    private String microRegion;

    private String mesoRegion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityRequest that = (CityRequest) o;
        return name.equals(that.name) &&
                state.equals(that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }
}
