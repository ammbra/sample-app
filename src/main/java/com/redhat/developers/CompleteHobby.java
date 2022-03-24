package com.redhat.developers;

import javax.json.bind.annotation.JsonbCreator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public non-sealed class CompleteHobby extends PricedHobby {
    public String link;
    public double accessibility;

    @JsonbCreator
    public static CompleteHobby empty() {
        return new CompleteHobby();
    }
}


