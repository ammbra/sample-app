package com.redhat.developers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.json.bind.annotation.JsonbCreator;

@JsonIgnoreProperties(ignoreUnknown = true)
public sealed class BasicHobby permits PricedHobby {

    public String key;
    public String activity;
    public String type;
    public int participants;

    @JsonbCreator
    public static BasicHobby empty() {
        return new BasicHobby();
    }
}
