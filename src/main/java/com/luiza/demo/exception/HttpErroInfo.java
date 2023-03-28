package com.luiza.demo.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class HttpErroInfo {
    private final int code;
    private final String description;
}
