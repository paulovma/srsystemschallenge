package com.srsystems.challenge.api.csv;

import com.srsystems.challenge.csv.city.filter.CsvFilter;
import com.srsystems.challenge.csv.domain.CsvService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/import/city")
public class CityCsvAPI {

    @Autowired
    private CsvService csvService;

    @Autowired
    private CsvFilter csvFilter;

    @PostMapping(consumes = { "multipart/form-data" })
    @ApiOperation(value = "Imports CSV file to database", consumes = "multipart/form-data")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void importFile(@RequestPart final MultipartFile file) throws IOException {
        csvFilter.filter(file);
        csvService.process(file);
    }


}
