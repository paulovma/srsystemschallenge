package com.srsystems.challenge.csv.city.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "You must provide a .csv file")
public class FileExtensionNotAllowed extends RuntimeException {
}
