package com.luiza.demo.exception.customexception;


import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends IllegalArgumentException {
    public ResourceNotFoundException(String message){
        super(message);
    }
}
