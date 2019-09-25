package com.srsystems.challenge.csv.city.service;

import com.srsystems.challenge.city.domain.CityRepository;
import com.srsystems.challenge.csv.city.batch.BatchImportCity;
import com.srsystems.challenge.csv.city.exception.ExistentIbgeId;
import com.srsystems.challenge.csv.city.factory.CityRequestFactory;
import com.srsystems.challenge.csv.domain.CsvService;
import com.srsystems.challenge.csv.domain.FileWriter;
import com.srsystems.challenge.csv.receiver.CsvQueueReceiver;
import com.srsystems.challenge.entity.City;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class CityCsvService implements CsvService {

    private CityRepository cityRepository;

    @Autowired
    private FileWriter<City> fileWriter;

    @Autowired
    private CityRequestFactory cityFactory;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private CsvQueueReceiver csvQueueReceiver;

    public CityCsvService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    @Transactional
    public void process(MultipartFile file) throws IOException, ExistentIbgeId {
        log.info("Method=process, file={}", file);

        fileWriter.write(file).ifPresent(
            r -> rabbitTemplate.convertAndSend("amq.fanout", "", r.getOriginalFilename().getBytes())
        );


//        List<CityRequest> cities = cityFactory.make(file);
//        cities.forEach((city) -> {
//            City existentCity = cityRepository.findByIbgeId(city.getIbgeId());
//            if (existentCity != null) {
//                throw new ExistentIbgeId("The given IBGE ID " + existentCity.getIbgeId() + " is already in use.");
//            }
//        });
//        cityRepository.saveAll(cityMapper.requestsToCities(cities));
    }

    @Transactional
    public void save(City city) {
        cityRepository.save(city);
    }

}
