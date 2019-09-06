package com.srsystems.challenge.csv.city.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ExistentIbgeId extends RuntimeException {

    public ExistentIbgeId(String message) {
        super(message);
    }
}
