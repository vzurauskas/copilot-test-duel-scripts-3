package com.vzurauskas.duelscripts3;

import java.util.LinkedHashMap;
import java.util.Map;

public final class Turn {
    private static final String PARRY_KEY = "parry";
    private static final String TARGET_KEY = "target";
    private static final String OUTCOME_KEY = "outcome";

    private final int number;
    private Fighter first;
    private Fighter second;
    private final Description desc;
    private final Map<String, String> values;

    public Turn(int number) {
        this.number = number;
        this.desc = new Description();
        this.values = new LinkedHashMap<>();
    }

    public void recordParry(Fighter fighter, String parryId) {
        ensureOrder(fighter);
        remember(key(fighter, PARRY_KEY), parryId);
    }

    public void recordTarget(Fighter fighter, String targetId) {
        ensureOrder(fighter);
        remember(key(fighter, TARGET_KEY), targetId);
    }

    public void recordOutcome(Fighter fighter, String targetId, String outcome) {
        ensureOrder(fighter);
        remember(key(fighter, OUTCOME_KEY), outcome);
        if (!values.containsKey(key(fighter, TARGET_KEY))) {
            remember(key(fighter, TARGET_KEY), targetId);
        }
    }

    public boolean isComplete() {
        return first != null && second != null
            && knows(first, PARRY_KEY) && knows(first, TARGET_KEY) && knows(first, OUTCOME_KEY)
            && knows(second, PARRY_KEY) && knows(second, TARGET_KEY) && knows(second, OUTCOME_KEY);
    }

    public String humanReadable() {
        String header = String.format("Turn %d:", number);
        String firstLine = String.format(
            "    %s parry=%s, strike=%s [%s]",
            first,
            value(first, PARRY_KEY),
            value(first, TARGET_KEY),
            value(first, OUTCOME_KEY)
        );
        String secondLine = String.format(
            "    %s parry=%s, strike=%s [%s]",
            second,
            value(second, PARRY_KEY),
            value(second, TARGET_KEY),
            value(second, OUTCOME_KEY)
        );
        return header + "\n" + firstLine + "\n" + secondLine + "\n";
    }

    public boolean involves(Fighter fighter) {
        return fighter == first || fighter == second;
    }

    public Fighter opponentOf(Fighter fighter) {
        return fighter == first ? second : fighter == second ? first : null;
    }

    public String parryOf(Fighter fighter) {
        return involves(fighter) ? value(fighter, PARRY_KEY) : null;
    }

    public String targetOf(Fighter fighter) {
        return involves(fighter) ? value(fighter, TARGET_KEY) : null;
    }

    private void ensureOrder(Fighter fighter) {
        if (first == null) {
            first = fighter;
        } else if (second == null && fighter != first) {
            second = fighter;
        }
    }

    private boolean knows(Fighter fighter, String what) {
        return values.containsKey(key(fighter, what));
    }

    private String value(Fighter fighter, String what) {
        return values.get(key(fighter, what));
    }

    private String key(Fighter fighter, String what) {
        return fighter.toString() + "." + what;
    }

    private void remember(String key, String val) {
        desc.remember(key, val);
        values.put(key, val);
    }
}


