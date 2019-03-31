package com.azmath.hms.common.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;


@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceAlreadyExistsException extends ApplicationException {

    public ResourceAlreadyExistsException(String code, String identifier) {
        super(HttpStatus.CONFLICT, code, Collections.singletonMap("value", identifier));
    }
}
