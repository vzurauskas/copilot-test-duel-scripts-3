package com.vzurauskas.duelscripts3;

public final class Fighter {
    private final String name;
    private final CombatScript script;
    private final BodyPart head;
    private final BodyPart torso;
    private final BodyPart legs;
    private BodyPart parryLocation;

    public Fighter(String name, CombatScript script) {
        this.name = name;
        this.script = script;
        this.head = new BodyPart("head", this, 3);
        this.torso = new BodyPart("torso", this, 2);
        this.legs = new BodyPart("legs", this, 1);
        this.parryLocation = this.torso; // default non-null
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
    }

    public void strike(Fighter opponent) {
        BodyPart target = script.chooseStrikeTarget(this, opponent);
        target.receiveStrike(1, this);
    }

    public boolean isParrying(BodyPart bodyPart) {
        return bodyPart == parryLocation;
    }

    public Description describe() {
        int totalDamage = head.damage() + torso.damage() + legs.damage();
        Description description = new Description();
        description.remember("damage", String.valueOf(totalDamage));
        return description;
    }
}


