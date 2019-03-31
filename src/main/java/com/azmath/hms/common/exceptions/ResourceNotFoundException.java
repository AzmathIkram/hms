package com.azmath.hms.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends ApplicationException {

    public ResourceNotFoundException(String code, String identifier) {
        super(HttpStatus.NOT_FOUND, code, Collections.singletonMap("value", identifier));
    }

    public ResourceNotFoundException(String code) {
        this(code, null);
    }
}
