package com.srsystems.challenge.csv.city.batch;

import com.srsystems.challenge.csv.city.service.CityCsvService;
import com.srsystems.challenge.entity.City;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@StepScope
@Component
public class BatchImportWriter implements ItemWriter<City> {

    @Autowired
    private CityCsvService cityCsvService;

    @Override
    public void write(List<? extends City> items) throws Exception {
        log.info("M=write, Total items={}", items.size());
        items.forEach(cityCsvService::save);
    }
}
