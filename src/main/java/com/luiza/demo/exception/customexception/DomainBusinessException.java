package com.luiza.demo.exception.customexception;


import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class DomainBusinessException extends IllegalArgumentException {
    public DomainBusinessException(String message){
        super(message);
    }
}
