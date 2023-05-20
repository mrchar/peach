package net.mrchar.peach.authorization.domain.common.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    MALE("male"),
    FEMALE("female");

    @JsonValue
    public final String VALUE;

    Gender(String VALUE) {
        this.VALUE = VALUE;
    }

    @JsonCreator
    public static Gender formValue(String value) {
        for (Gender gender : Gender.values()) {
            if (gender.VALUE.equals(value)) {
                return gender;
            }
        }

        return null;
    }
}