package com.srsystems.challenge.csv.city.service;

import com.srsystems.challenge.city.mapper.CityMapper;
import com.srsystems.challenge.city.domain.CityRepository;
import com.srsystems.challenge.csv.city.factory.CityRequestFactory;
import com.srsystems.challenge.csv.domain.CsvService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class CityCsvService implements CsvService {

    private CityMapper cityMapper;

    private CityRepository cityRepository;

    @Autowired
    private CityRequestFactory cityFactory;

    public CityCsvService(
            CityMapper cityMapper,
            CityRepository cityRepository
    ) {
        this.cityMapper = cityMapper;
        this.cityRepository = cityRepository;
    }

    @Override
    @Transactional
    public void process(MultipartFile file) throws IOException {
        log.info("Method=process, file={}", file);
        cityRepository.saveAll(cityMapper.requestsToCities(cityFactory.make(file)));
    }
}
