package com.srsystems.challenge.csv.city.factory;

import com.srsystems.challenge.city.domain.request.CityRequest;
import com.srsystems.challenge.csv.city.domain.CsvHeaderEnum;
import com.srsystems.challenge.csv.city.domain.StateIdMap;
import com.srsystems.challenge.csv.domain.FileConverter;
import com.srsystems.challenge.entity.City;
import com.srsystems.challenge.entity.State;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class CityRequestFactory implements FileConverter<City> {

    @Autowired
    private StateIdMap stateIdMap;

    @Override
    public List<City> make(String filename) throws IOException {
        List<City> cities = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                cities.add(
                        City.builder()
                                .ibgeId(Long.parseLong(values[0]))
                                .name(values[1])
                                .state(
                                        State.builder()
                                                .id(stateIdMap.getMap().get(values[2].trim()))
                                                .uf(values[2])
                                                .build()
                                )
                                .capital(values[3].trim().equalsIgnoreCase("true"))
                                .longitude(Double.parseDouble(values[4]))
                                .latitude(Double.parseDouble(values[5]))
                                .accent(values[6])
                                .alternativeNames(values[7])
                                .microRegion(values[8])
                                .mesoRegion(values[9])
                                .build()
                );
            }
        }

        return cities;
    }

}
