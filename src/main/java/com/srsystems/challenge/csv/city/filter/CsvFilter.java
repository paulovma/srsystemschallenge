package com.srsystems.challenge.csv.city.filter;

import com.srsystems.challenge.csv.city.exception.FileExtensionNotAllowed;
import com.srsystems.challenge.csv.domain.FileFilter;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public class CsvFilter implements FileFilter {

    private List<String> extensions = List.of("csv");

    @Override
    public void filter(MultipartFile file) throws FileExtensionNotAllowed {
        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (!extensions.contains(fileExtension)) {
            throw new FileExtensionNotAllowed();
        }
    }
}
