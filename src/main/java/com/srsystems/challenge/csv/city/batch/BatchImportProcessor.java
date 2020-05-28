//package com.srsystems.challenge.csv.city.batch;
//
//import com.srsystems.challenge.city.mapper.CityMapper;
//import com.srsystems.challenge.entity.City;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//@StepScope
//public class BatchImportProcessor implements ItemProcessor<BatchImportCity, City> {
//
//    @Autowired
//    private CityMapper cityMapper;
//
//    @Override
//    public City process(BatchImportCity batchImportCity) throws Exception {
//        log.info("M=process, BatchImportCity={}", batchImportCity);
//        return cityMapper.cityFromBatchImportCity(batchImportCity);
//    }
//}
