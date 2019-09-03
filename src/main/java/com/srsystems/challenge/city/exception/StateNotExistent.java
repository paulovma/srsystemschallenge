package com.srsystems.challenge.city.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Given state does not exist.")
public class StateNotExistent extends RuntimeException {

}
