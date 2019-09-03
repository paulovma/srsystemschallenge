package com.srsystems.challenge.csv.domain;

import com.srsystems.challenge.csv.city.exception.FileExtensionNotAllowed;
import org.springframework.web.multipart.MultipartFile;

public interface FileFilter {

    public void filter(MultipartFile file) throws FileExtensionNotAllowed;
}
