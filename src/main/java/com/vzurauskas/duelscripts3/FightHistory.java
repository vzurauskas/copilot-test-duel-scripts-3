package com.vzurauskas.duelscripts3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class FightHistory {
    private final List<String> strikes;

    public FightHistory() {
        this.strikes = new ArrayList<>();
    }

    public void strikeOccured(String strike) {
        strikes.add(strike);
    }

    public List<String> strikes() {
        return Collections.unmodifiableList(strikes);
    }
}

 