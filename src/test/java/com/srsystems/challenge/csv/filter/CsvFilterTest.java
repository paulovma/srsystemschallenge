package com.srsystems.challenge.csv.filter;

import com.srsystems.challenge.csv.city.exception.FileExtensionNotAllowed;
import com.srsystems.challenge.csv.city.filter.CsvFilter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

@ExtendWith(MockitoExtension.class)
public class CsvFilterTest {

    @InjectMocks
    private CsvFilter csvFilter;

    @Test()
    public void test_filter_expectingFileExtensionNotAllowedException_becauseFileIsTextPlain() {
        var file = makeFIle("teste.txt", "text/plain");
        Assertions.assertThrows(FileExtensionNotAllowed.class, () -> {csvFilter.filter(file);});
    }

    @Test()
    public void test_filter_expectingFileExtensionNotAllowedException_becauseFileIsPdf() {
        var file = makeFIle("teste.pdf", "application/pdf");
        Assertions.assertThrows(FileExtensionNotAllowed.class, () -> {csvFilter.filter(file);});
    }

    @Test()
    public void test_filter_expectingFileExtensionNotAllowedException_becauseFileIsPng() {
        var file = makeFIle("teste.pdf", "image/png");
        Assertions.assertThrows(FileExtensionNotAllowed.class, () -> {csvFilter.filter(file);});

    }

    @Test
    public void test_filter_noExceptionExpected_becauseItIsACsvFile() {
        var file = makeFIle("teste.csv", "text/csv");
        csvFilter.filter(file);
    }

    private MockMultipartFile makeFIle(String filename, String contentType) {
        return new MockMultipartFile("teste", filename, contentType, "some_text".getBytes());
    }
}
