package com.srsystems.challenge.csv.domain;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileConverter<T> {

    public List<T> make(MultipartFile file) throws IOException;
}
