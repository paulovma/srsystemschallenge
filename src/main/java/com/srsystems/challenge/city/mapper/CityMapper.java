package com.srsystems.challenge.city.mapper;

import com.srsystems.challenge.csv.city.batch.BatchImportCity;
import com.srsystems.challenge.entity.City;
import com.srsystems.challenge.city.domain.request.CityRequest;
import com.srsystems.challenge.city.domain.response.CityResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper {

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "state.id", source = "stateId"),
        @Mapping(target = "state.uf", source = "state")
    })
    City requestToCity(CityRequest cityRequest);

    List<City> requestsToCities(List<CityRequest> cityRequest);

//    @Mapping(target = "state", source = "state.uf")
    List<CityResponse> citiesToResponseList(List<City> cities);

    @Mapping(target = "state", source = "state.uf")
    CityResponse cityToRequest(City city);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "state.id", source = "stateId"),
            @Mapping(target = "state.uf", source = "state")
    })
    City cityFromBatchImportCity(BatchImportCity batchImportCity);
}
