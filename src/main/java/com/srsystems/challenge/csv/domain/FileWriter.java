package com.srsystems.challenge.csv.domain;

import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface FileWriter<T> {

    Optional<MultipartFile> write(MultipartFile file);
}
