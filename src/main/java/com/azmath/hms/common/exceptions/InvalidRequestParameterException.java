package com.azmath.hms.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRequestParameterException extends ApplicationException {

    public InvalidRequestParameterException(String code, String identifier) {
        super(HttpStatus.BAD_REQUEST, code, Collections.singletonMap("Invalid parameter", identifier));
    }

    public InvalidRequestParameterException(String code) {
        this(code, null);
    }
}
