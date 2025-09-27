package com.vzurauskas.duelscripts3;

public final class Fighter {
    private final String name;
    private final Description description;
    private final CombatScript script;
    private final int maxHp;
    private final BodyPart head;
    private final BodyPart torso;
    private final BodyPart legs;
    private BodyPart parryLocation;
    private FightHistory history;

    public Fighter(String name, CombatScript script, FightHistory history) {
        this(name, 100, script, history);
    }

    public Fighter(
        String name, int maxHp, CombatScript script, FightHistory history
    ) {
        this.name = name;
        this.description = new Description();
        this.script = script;
        this.history = history;
        this.maxHp = maxHp;
        this.head = new BodyPart("head", this, 1.7, history);
        this.torso = new BodyPart("torso", this, 1, history);
        this.legs = new BodyPart("legs", this, 0.7, history);
        this.parryLocation = this.torso;
    }

    public BodyPart head() {
        return head;
    }

    public BodyPart torso() {
        return torso;
    }

    public BodyPart legs() {
        return legs;
    }

    public BodyPart bodyPart(String id) {
        return switch (id) {
            case "head" -> head();
            case "torso" -> torso();
            case "legs" -> legs();
            default -> throw new IllegalArgumentException(
                "Unknown body part id: " + id
            );
        };
    }

    public void decideParryAgainst(Fighter opponent) {
        this.parryLocation = script.chooseParryLocation(this, opponent);
        history.parryChosen(this, parryLocation);
    }

    public void strike(Fighter opponent) {
        BodyPart target = script.chooseStrikeTarget(this, opponent);
        history.strikeTargetChosen(this, target);
        target.receiveStrike(3, this);
    }

    public boolean isParrying(BodyPart bodyPart) {
        return bodyPart == parryLocation;
    }

    public Description describe() {
        int totalDamage = head.damage() + torso.damage() + legs.damage();
        description.remember("damage", String.valueOf(totalDamage));
        description.remember("hp", String.valueOf(maxHp - totalDamage));
        return description;
    }

    @Override
    public String toString() {
        return name;
    }
}
