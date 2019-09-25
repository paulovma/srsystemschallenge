package com.srsystems.challenge.csv.city.factory;

import com.srsystems.challenge.csv.city.domain.CsvHeaderEnum;
import com.srsystems.challenge.csv.domain.FileWriter;
import com.srsystems.challenge.entity.City;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CsvFileWriter implements FileWriter<City> {

    @Override
    public Optional<MultipartFile> write(MultipartFile file) {
        try {
            InputStreamReader input = new InputStreamReader(file.getInputStream());
            CSVParser csvParser = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(input);
            List<String[]> listLine = new ArrayList<>();
            listLine.add(new String[] {"ibge_id", "name", "uf", "capital", "lon", "lat", "no_accents", "alternative_names", "microregion", "mesoregion"});
            csvParser.forEach(record -> {
                listLine.add(new String[] {
                        record.get(CsvHeaderEnum.IBGE_ID.getHeader()),
                        record.get(CsvHeaderEnum.NAME.getHeader()),
                        record.get(CsvHeaderEnum.UF.getHeader()).trim(),
                        record.get(CsvHeaderEnum.CAPITAL.getHeader()).trim(),
                        record.get(CsvHeaderEnum.LON.getHeader()),
                        record.get(CsvHeaderEnum.LAT.getHeader()),
                        record.get(CsvHeaderEnum.NO_ACCENTS.getHeader()),
                        record.get(CsvHeaderEnum.ALTERNATIVE_NAMES.getHeader()),
                        record.get(CsvHeaderEnum.MICROREGION.getHeader()),
                        record.get(CsvHeaderEnum.MESOREGION.getHeader())
                });
            });

            File csvOutputFile = new File(file.getOriginalFilename());
            try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
                listLine.stream().map(this::convertToCSV).forEach(pw::println);
            }

            return Optional.of(file);
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    private String convertToCSV(String[] data) {
        return Stream.of(data)
                .collect(Collectors.joining(","));
    }
}
