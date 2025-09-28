package com.vzurauskas.duelscripts3;

public final class Turn {
    private static final String PARRY_KEY = "parry";
    private static final String TARGET_KEY = "target";
    private static final String OUTCOME_KEY = "outcome";
    private static final String DAMAGE_KEY = "damage";

    private final int number;
    private Fighter first;
    private Fighter second;
    private final Description desc;

    public Turn(int number) {
        this.number = number;
        this.desc = new Description();
    }

    public void recordParry(Fighter fighter, String parryId) {
        ensureOrder(fighter);
        remember(key(fighter, PARRY_KEY), parryId);
    }

    public void recordTarget(Fighter fighter, String targetId) {
        ensureOrder(fighter);
        remember(key(fighter, TARGET_KEY), targetId);
    }

    public void recordOutcome(
        Fighter fighter, String targetId, String outcome
    ) {
        ensureOrder(fighter);
        remember(key(fighter, OUTCOME_KEY), outcome);
        if (!desc.knows(key(fighter, TARGET_KEY))) {
            remember(key(fighter, TARGET_KEY), targetId);
        }
    }

    public void recordDamage(Fighter fighter, int damage) {
        ensureOrder(fighter);
        remember(key(fighter, DAMAGE_KEY), String.valueOf(damage));
    }

    public String humanReadable() {
        String header = String.format("Turn %d:", number);
        String firstLine = String.format(
            "    %s parry=%s, strike=%s [%s], damage=%s",
            first,
            value(first, PARRY_KEY),
            value(first, TARGET_KEY),
            value(first, OUTCOME_KEY),
            value(first, DAMAGE_KEY)
        );
        String secondLine = String.format(
            "    %s parry=%s, strike=%s [%s], damage=%s",
            second,
            value(second, PARRY_KEY),
            value(second, TARGET_KEY),
            value(second, OUTCOME_KEY),
            value(second, DAMAGE_KEY)
        );
        StringBuilder sb = new StringBuilder();
        sb.append(header).append("\n");
        sb.append(firstLine).append("\n");
        sb.append(secondLine).append("\n");
        boolean firstDead = first.describe().intValue("hp") <= 0;
        boolean secondDead = second.describe().intValue("hp") <= 0;
        if (firstDead && secondDead) {
            sb.append("    both died\n");
        }
        return sb.toString();
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

    private String value(Fighter fighter, String what) {
        return desc.stringValue(key(fighter, what));
    }

    private String key(Fighter fighter, String what) {
        return fighter.toString() + "." + what;
    }

    private void remember(String key, String val) {
        desc.remember(key, val);
    }
}
