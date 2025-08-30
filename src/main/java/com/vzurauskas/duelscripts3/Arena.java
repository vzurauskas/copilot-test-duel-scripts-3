package com.vzurauskas.duelscripts3;

public final class Arena {
    private final Fighter first;
    private final Fighter second;
    private final FightHistory history;

    public Arena(Fighter first, Fighter second) {
        this(first, second, new FightHistory());
    }

    public Arena(Fighter first, Fighter second, FightHistory history) {
        this.first = first;
        this.second = second;
        this.history = history;
    }

    public void nextTurn() {
        first.decideParryAgainst(second);
        second.decideParryAgainst(first);
        first.strike(second);
        second.strike(first);
    }
}


