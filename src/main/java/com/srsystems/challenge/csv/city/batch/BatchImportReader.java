package com.srsystems.challenge.csv.city.batch;

import com.srsystems.challenge.csv.city.domain.StateIdMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@StepScope
public class BatchImportReader implements ItemReader<BatchImportCity>, InitializingBean {

    @Autowired
    private StateIdMap stateIdMap;

    @Value("#{jobParameters[fileName]}")
    private String filename;

    private CSVParser csvParser;

    private Iterator<CSVRecord> it;


    @Override
    public BatchImportCity read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        log.info("M=read");
        CSVRecord next = getNext();
        if (next != null) {
            return BatchImportCity.builder()
                    .ibgeId(Long.parseLong(next.get("ibge_id")))
                    .name(next.get("name"))
                    .state(next.get("uf"))
                    .stateId(stateIdMap.getMap().get(next.get("uf").trim()))
                    .capital(next.get("capital").equalsIgnoreCase("true"))
                    .accent(next.get("no_accents"))
                    .latitude(next.get("lat"))
                    .longitude(next.get("lon"))
                    .alternativeNames(next.get("alternative_names"))
                    .accent(next.get("no_accents"))
                    .build();
        }

        csvParser.close();
        return null;
    }

    private CSVRecord getNext() {
        log.info("M=getNext");
        return it.hasNext() ? it.next() : null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("M=afterPropertiesSet");
        Reader reader = Files.newBufferedReader(Paths.get(new FileSystemResource(filename).getURI()));

        CSVFormat format = CSVFormat.DEFAULT
                .withHeader("ibge_id", "name", "uf", "capital", "lon", "lat", "no_accents", "alternative_names")
                .withFirstRecordAsHeader();

        csvParser = new CSVParser(reader, format);

        it = csvParser.iterator();
    }
}
