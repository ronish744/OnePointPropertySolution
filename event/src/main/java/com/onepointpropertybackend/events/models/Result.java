package com.onepointpropertybackend.events.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Result<T> {

        private int code;
        private String message;
        private T data;



}
