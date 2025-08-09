package com.vzurauskas.duelscripts3;

public final class BodyPart {
    private final String id;
    private final Fighter owner;
    private final int damageMultiplier;
    private int damage;

    public BodyPart(String id, Fighter owner, int damageMultiplier) {
        this.id = id;
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
        return id;
    }

    public int damage() {
        return damage;
    }
}


