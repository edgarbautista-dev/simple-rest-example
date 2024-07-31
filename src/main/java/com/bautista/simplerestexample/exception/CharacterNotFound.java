package com.bautista.simplerestexample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND,reason = "Character not found")
public class CharacterNotFound  extends RuntimeException{
    private static final long serialVersionUID= -882897689043048002L;

}
