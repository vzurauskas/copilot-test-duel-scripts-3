package com.vzurauskas.duelscripts3;

public final class Turn {
    private static final String PARRY_KEY = "parry";
    private static final String TARGET_KEY = "target";
    private static final String WEAPON_KEY = "weapon";
    private static final String OUTCOME_KEY = "outcome";
    private static final String DAMAGE_KEY = "damage";
    private static final String CRITICAL_KEY = "critical";

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
        Fighter fighter, String weaponName, String targetId, String outcome
    ) {
        ensureOrder(fighter);
        remember(key(fighter, WEAPON_KEY), weaponName);
        remember(key(fighter, OUTCOME_KEY), outcome);
        if (!desc.knows(key(fighter, TARGET_KEY))) {
            remember(key(fighter, TARGET_KEY), targetId);
        }
    }

    public void recordDamage(Fighter fighter, int damage) {
        ensureOrder(fighter);
        remember(key(fighter, DAMAGE_KEY), String.valueOf(damage));
    }

    public void recordCriticalHit(Fighter fighter) {
        ensureOrder(fighter);
        remember(key(fighter, CRITICAL_KEY), "true");
    }

    public String humanReadable() {
        String header = String.format("Turn %d:", number);
        String firstDamageText = (first != null && isCritical(first))
            ? String.format("%s critical damage", value(first, DAMAGE_KEY))
            : String.format("%s damage", value(first, DAMAGE_KEY));
        String secondDamageText = (second != null && isCritical(second))
            ? String.format("%s critical damage", value(second, DAMAGE_KEY))
            : String.format("%s damage", value(second, DAMAGE_KEY));
        String firstLine = String.format(
            "    %s parries %s, strikes with %s at %s's %s [%s], "
            + "deals %s.",
            first,
            value(first, PARRY_KEY),
            value(first, WEAPON_KEY),
            second,
            value(first, TARGET_KEY),
            value(first, OUTCOME_KEY),
            firstDamageText
        );
        String secondLine = String.format(
            "    %s parries %s, strikes with %s at %s's %s [%s], "
            + "deals %s.",
            second,
            value(second, PARRY_KEY),
            value(second, WEAPON_KEY),
            first,
            value(second, TARGET_KEY),
            value(second, OUTCOME_KEY),
            secondDamageText
        );

        boolean firstDead = first.describe().intValue("hp") <= 0;
        boolean secondDead = second.describe().intValue("hp") <= 0;

        String finalHpLine = (firstDead || secondDead)
            ? String.format(
                "    final hp: %s=%d, %s=%d",
                first, first.describe().intValue("hp"),
                second, second.describe().intValue("hp")
            )
            : "";

        String bothDiedLine = (firstDead && secondDead)
            ? "    both died"
            : "";

        String[] lines = new String[] {
            header,
            firstLine,
            secondLine,
            finalHpLine,
            bothDiedLine
        };

        StringBuilder readable = new StringBuilder();
        for (String line : lines) {
            if (!line.isEmpty()) {
                readable.append(line).append("\n");
            }
        }
        return readable.toString();
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

    private boolean isCritical(Fighter fighter) {
        return desc.knows(key(fighter, CRITICAL_KEY))
            && "true".equals(value(fighter, CRITICAL_KEY));
    }
}
