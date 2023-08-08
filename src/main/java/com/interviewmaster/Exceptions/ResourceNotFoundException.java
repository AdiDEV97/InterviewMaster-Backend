package com.interviewmaster.Exceptions;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

@Getter
@Setter
@ToString
public class ResourceNotFoundException extends RuntimeException{

    private String resourceName;

    private String fieldName;

    private int fieldValue;

    //Constructor
    public ResourceNotFoundException(String resourceName, String fieldName, int fieldValue) {
        super(String.format("The %s is not found with %s : %s", resourceName, fieldName, fieldValue));

        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

}
