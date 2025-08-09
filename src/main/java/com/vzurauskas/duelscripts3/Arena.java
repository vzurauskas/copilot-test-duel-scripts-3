package com.vzurauskas.duelscripts3;

public final class Arena {
    private final Fighter first;
    private final Fighter second;

    public Arena(Fighter first, Fighter second) {
        this.first = first;
        this.second = second;
    }

    public void nextTurn() {
        first.decideParryAgainst(second);
        second.decideParryAgainst(first);

        // Simultaneous strikes; ordering doesn't matter for this simple model
        first.strike(second);
        second.strike(first);
    }
}


