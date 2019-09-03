package com.srsystems.challenge.api.city;

import com.srsystems.challenge.city.domain.request.CityRequest;
import com.srsystems.challenge.city.domain.request.CityRequestFilter;
import com.srsystems.challenge.city.domain.response.CityCountResponse;
import com.srsystems.challenge.city.domain.response.CityResponse;
import com.srsystems.challenge.city.domain.CityService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/city")
public class CityAPI {

    @Autowired
    private CityRequestFilter cityRequestFilter;

    @Autowired
    private CityService cityService;

    @GetMapping("")
    @ApiOperation(value = "Returns all cities")
    public List<CityResponse> findAll() {
        return cityService.findAll();
    }

    @PostMapping("")
    @ApiOperation(value = "Insert a city")
    public boolean insert(@RequestBody @Valid final CityRequest cityRequest) {
        cityRequestFilter.filter(cityRequest);
        return cityService.insert(cityRequest);
    }

    @GetMapping("/capital")
    @ApiOperation(value = "Returns the cities which are capital")
    public List<CityResponse> getCapitalCities() {
        return cityService.getCapitalCities();
    }

    @GetMapping("/{ibgeId}")
    @ApiOperation(value = "Returns a city based on given IBGE ID")
    public CityResponse getCityByIbgeId(@PathVariable final Long ibgeId) {
        return cityService.getCityByIbgeId(ibgeId);
    }

    @DeleteMapping("/{ibgeId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Deletes a city based on given IBGE ID", code = 204)
    public void deleteCityByIbgeId(@PathVariable final Long ibgeId) {
        cityService.deleteCityByIbgeId(ibgeId);
    }

    @GetMapping("/state/{state}")
    @ApiOperation(value = "Returns the cities based on given state")
    public List<CityResponse> getCityByState(@PathVariable final String state) {
        cityRequestFilter.filter(CityRequest.builder().state(state).build());
        return cityService.getCityByState(state);
    }

    @GetMapping("/total")
    @ApiOperation(value = "Returns the cities which are capital")
    public CityCountResponse getTotal() {
        return cityService.getTotal();
    }

}
