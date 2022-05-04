package com.ecabs.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class AbstractResponse {

    protected int status = HttpStatus.OK.value();
    protected LocalDateTime timestamp = LocalDateTime.now();
    protected List<String> errorMessage;
    protected List<String> warningMessage;
    protected List<String> infoMessage;
}
