package com.redhat.developers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public non-sealed class CompleteHobby extends PricedHobby {
    public String link;
    public double accessibility;
}


