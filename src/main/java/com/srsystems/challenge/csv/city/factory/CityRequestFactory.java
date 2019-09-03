package com.srsystems.challenge.csv.city.factory;

import com.srsystems.challenge.city.domain.request.CityRequest;
import com.srsystems.challenge.csv.city.domain.CsvHeaderEnum;
import com.srsystems.challenge.csv.domain.FileConverter;
import com.srsystems.challenge.csv.city.domain.StateIdMap;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.monitor.FileEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class CityRequestFactory implements FileConverter<CityRequest> {

    @Autowired
    private StateIdMap stateIdMap;

    @Override
    public List<CityRequest> make(MultipartFile file) throws IOException {
        InputStreamReader input = new InputStreamReader(file.getInputStream());
        CSVParser csvParser = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(input);
        List<CityRequest> cities = new ArrayList<>();
        csvParser.forEach(record -> {
            CityRequest cityRequest =
                    new CityRequest(
                            Long.parseLong(record.get(CsvHeaderEnum.IBGE_ID.getHeader())),
                            record.get(CsvHeaderEnum.NAME.getHeader()),
                            stateIdMap.getMap().get(record.get(CsvHeaderEnum.UF.getHeader()).trim()),
                            record.get(CsvHeaderEnum.UF.getHeader()).trim(),
                            record.get(CsvHeaderEnum.CAPITAL.getHeader()).trim().equalsIgnoreCase("true"),
                            Double.parseDouble(record.get(CsvHeaderEnum.LON.getHeader())),
                            Double.parseDouble(record.get(CsvHeaderEnum.LAT.getHeader())),
                            record.get(CsvHeaderEnum.NO_ACCENTS.getHeader()),
                            record.get(CsvHeaderEnum.ALTERNATIVE_NAMES.getHeader()),
                            record.get(CsvHeaderEnum.MICROREGION.getHeader()),
                            record.get(CsvHeaderEnum.MESOREGION.getHeader())
                    );
            cities.add(cityRequest);
        });

        return cities;
    }
}
