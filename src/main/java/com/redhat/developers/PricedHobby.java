package com.redhat.developers;

import javax.json.bind.annotation.JsonbCreator;
import java.util.UUID;


public sealed class PricedHobby extends BasicHobby permits CompleteHobby {
    public String randomId = UUID.randomUUID()
            .toString().substring(0, 4);
    public double price;

    @JsonbCreator
    public static PricedHobby empty() {
        return new PricedHobby();
    }
}
