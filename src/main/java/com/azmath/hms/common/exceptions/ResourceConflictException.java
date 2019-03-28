package com.azmath.hms.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceConflictException extends ApplicationException {

    public ResourceConflictException(String code, String identifier) {
        super(HttpStatus.CONFLICT, code, Collections.singletonMap("value", identifier));
    }

    public ResourceConflictException(String code) {
        this(code, null);
    }
}
