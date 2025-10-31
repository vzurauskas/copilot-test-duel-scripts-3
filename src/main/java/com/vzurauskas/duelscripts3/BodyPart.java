package com.vzurauskas.duelscripts3;

public final class BodyPart {
    private final String name;
    private final Fighter owner;
    private final double damageMultiplier;
    private final FightHistory history;
    private int damage;

    public BodyPart(
        String name, Fighter owner, double damageMultiplier,
        FightHistory history
    ) {
        this.name = name;
        this.owner = owner;
        this.damageMultiplier = damageMultiplier;
        this.history = history;
        this.damage = 0;
    }

    public void receiveStrike(Weapon weapon, Fighter striker) {
        boolean isParried = owner.isParrying(this);
        int weaponDamage = weapon.calculateDamage(striker, isParried);
        int dealt = isParried ? 0 : (int) (weaponDamage * damageMultiplier);
        this.damage += dealt;
        history.strikeOccurred(striker, weapon, this, dealt);
    }

    public String id() {
        return name;
    }

    public int damage() {
        return damage;
    }
}
