package com.vzurauskas.duelscripts3;

public final class Weapon {
    private final String name;
    private final int baseDamage;
    private final double criticalChance;
    private final double criticalMultiplier;
    private final FightHistory observer;

    public Weapon(
        String name,
        int baseDamage,
        double criticalChance,
        double criticalMultiplier
    ) {
        this(name, baseDamage, criticalChance, criticalMultiplier, null);
    }

    public Weapon(
        String name,
        int baseDamage,
        double criticalChance,
        double criticalMultiplier,
        FightHistory observer
    ) {
        this.name = name;
        this.baseDamage = baseDamage;
        this.criticalChance = criticalChance;
        this.criticalMultiplier = criticalMultiplier;
        this.observer = observer;
    }

    public int calculateDamage(Fighter attacker, boolean willBeParried) {
        if (Math.random() < criticalChance) {
            int damage = (int) (baseDamage * criticalMultiplier);
            if (!willBeParried && observer != null) {
                observer.observeCriticalHit(attacker);
            }
            return damage;
        }
        return baseDamage;
    }

    public String name() {
        return name;
    }

    Weapon withObserver(FightHistory observer) {
        return new Weapon(
            name,
            baseDamage,
            criticalChance,
            criticalMultiplier,
            observer
        );
    }
}

