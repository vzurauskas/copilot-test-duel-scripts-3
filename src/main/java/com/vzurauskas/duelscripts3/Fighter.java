package com.vzurauskas.duelscripts3;

public final class Fighter {
    private final String name;
    private final Description description;
    private final CombatScript script;
    private final BodyPart head;
    private final BodyPart torso;
    private final BodyPart legs;
    private BodyPart parryLocation;
    private FightHistory history;

    public Fighter(String name, CombatScript script, FightHistory history) {
        this.name = name;
        this.description = new Description();
        this.script = script;
        this.history = history;
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
        description.remember(
            "damage",
            String.valueOf(head.damage() + torso.damage() + legs.damage())
        );
        return description;
    }

    @Override
    public String toString() {
        return name;
    }
}


