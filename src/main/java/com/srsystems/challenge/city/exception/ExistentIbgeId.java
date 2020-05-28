package com.srsystems.challenge.city.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "The given IBGE ID is already linked to another city.")
public class ExistentIbgeId extends RuntimeException {
}
