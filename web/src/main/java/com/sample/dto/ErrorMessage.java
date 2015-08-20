package com.sample.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by popikyardo on 06.08.15.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

    private List<String> errors;

    public ErrorMessage(String error) {
        this(Collections.singletonList(error));
    }

    public ErrorMessage(String ... errors) {
        this(Arrays.asList(errors));
    }
}