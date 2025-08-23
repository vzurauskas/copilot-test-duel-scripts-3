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

    public Fighter(String name, CombatScript script) {
        this.name = name;
        this.description = new Description();
        this.script = script;
        this.head = new BodyPart("head", this, 1.7);
        this.torso = new BodyPart("torso", this, 1);
        this.legs = new BodyPart("legs", this, 0.7);
        this.parryLocation = this.torso;
        this.history = new FightHistory();
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
        int before = target.damage();
        target.receiveStrike(3, this);
        int dealt = target.damage() - before;
        history.recordStrike(this, target, dealt);
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

    public void observeWith(FightHistory history) {
        this.history = history;
    }

    @Override
    public String toString() {
        return name;
    }
}


