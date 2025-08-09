package com.vzurauskas.duelscripts3;

public final class BodyPart {
    private final String name;
    private final Fighter owner;
    private final double damageMultiplier;
    private int damage;

    public BodyPart(String name, Fighter owner, double damageMultiplier) {
        this.name = name;
        this.owner = owner;
        this.damageMultiplier = damageMultiplier;
        this.damage = 0;
    }

    public void receiveStrike(int baseDamage, Fighter striker) {
        if (owner.isParrying(this)) {
            return;
        }
        this.damage += baseDamage * damageMultiplier;
    }

    public String id() {
        return name;
    }

    public int damage() {
        return damage;
    }
}


