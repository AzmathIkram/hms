package com.azmath.hms.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ApplicationInternalException extends ApplicationException {

    public ApplicationInternalException(String code, String identifier) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, code, Collections.singletonMap("value", identifier));
    }


    public ApplicationInternalException(String code) {
        this(code, null);
    }
}
