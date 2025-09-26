package com.vzurauskas.duelscripts3;

public final class Arena {
    private final Fighter first;
    private final Fighter second;
    private final FightHistory history;
    private boolean concluded;

    public Arena(Fighter first, Fighter second) {
        this(first, second, new FightHistory());
    }

    public Arena(Fighter first, Fighter second, FightHistory history) {
        this.first = first;
        this.second = second;
        this.history = history;
        this.concluded = false;
    }

    public void nextTurn() {
        if (concluded) {
            return;
        }
        first.decideParryAgainst(second);
        second.decideParryAgainst(first);
        first.strike(second);
        second.strike(first);
        history.turnCompleted();
        concludeIfOver();
    }

    public void concludeIfOver() {
        boolean firstDead = first.describe().intValue("hp") <= 0;
        boolean secondDead = second.describe().intValue("hp") <= 0;
        if (firstDead || secondDead) {
            concluded = true;
        }
    }

    public boolean isConcluded() {
        return concluded;
    }
}


