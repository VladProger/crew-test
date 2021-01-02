package com.company.dto;

public enum Degree {
    ASSISTANT(1),
    ASSOCIATE_PROFESSOR(2),
    PROFESSOR(3);

    private int value;

    Degree(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
