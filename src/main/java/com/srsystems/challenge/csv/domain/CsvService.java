package com.srsystems.challenge.csv.domain;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CsvService {

    public void process(MultipartFile file) throws IOException;
}
