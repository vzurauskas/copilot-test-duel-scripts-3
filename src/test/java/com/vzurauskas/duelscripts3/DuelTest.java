package com.vzurauskas.duelscripts3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class DuelTest {

    @Test
    void unparriedStrikeDealsDamage() {
        CombatScript aragornScript = new FixedScript()
            .parry(Fighter::torso)
            .strike(Fighter::torso);
        CombatScript boromirScript = new FixedScript()
            .parry(Fighter::head)
            .strike(Fighter::legs);

        FightHistory history = new FightHistory();
        Fighter aragorn = new Fighter("Aragorn", aragornScript, history);
        Fighter boromir = new Fighter("Boromir", boromirScript, history);

        Arena arena = new Arena(aragorn, boromir, history);
        arena.nextTurn();

        Description description = boromir.describe();
        assertTrue(description.knows("damage"));
        assertTrue(description.intValue("damage") > 0);
    }

    @Test
    void parriedStrikeDealsNoDamage() {
        CombatScript aragornScript = new FixedScript()
            .parry(Fighter::legs)
            .strike(Fighter::head);
        CombatScript boromirScript = new FixedScript()
            .parry(Fighter::head)
            .strike(Fighter::legs);

        FightHistory history = new FightHistory();
        Fighter aragorn = new Fighter("Aragorn", aragornScript, history);
        Fighter boromir = new Fighter("Boromir", boromirScript, history);

        Arena arena = new Arena(aragorn, boromir, history);
        arena.nextTurn();

        Description description = boromir.describe();
        assertTrue(description.knows("damage"));
        assertEquals(0, description.intValue("damage"));
    }

    @Test
    void damageIsCalculatedCorrectly() {
        CombatScript aragornScript = new FixedScript()
            .parry(Fighter::torso)
            .strike(Fighter::head);
        CombatScript boromirScript = new FixedScript()
            .parry(Fighter::legs)
            .strike(Fighter::legs);

        FightHistory history = new FightHistory();
        Fighter aragorn = new Fighter("Aragorn", aragornScript, history);
        Fighter boromir = new Fighter("Boromir", boromirScript, history);

        Arena arena = new Arena(aragorn, boromir, history);
        arena.nextTurn();

        assertEquals(5, boromir.head().damage());
        assertEquals(2, aragorn.legs().damage());
    }

    @Test
    void swordDealsModerateDamage() {
        Weapon sword = new Weapon("sword", 8, 0.0, 1.0);
        CombatScript aliceScript = new FixedScript()
            .parry(Fighter::head)
            .strike(Fighter::torso);
        CombatScript bobScript = new FixedScript()
            .parry(Fighter::head)
            .strike(Fighter::legs);

        FightHistory history = new FightHistory();
        Fighter alice = new Fighter("Alice", sword, aliceScript, history);
        Fighter bob = new Fighter("Bob", bobScript, history);

        Arena arena = new Arena(alice, bob, history);
        arena.nextTurn();

        assertEquals(8, bob.torso().damage());
    }

    @Test
    void axeDealsHighestDamage() {
        Weapon axe = new Weapon("axe", 12, 0.0, 1.0);
        CombatScript aliceScript = new FixedScript()
            .parry(Fighter::head)
            .strike(Fighter::torso);
        CombatScript bobScript = new FixedScript()
            .parry(Fighter::head)
            .strike(Fighter::legs);

        FightHistory history = new FightHistory();
        Fighter alice = new Fighter("Alice", axe, aliceScript, history);
        Fighter bob = new Fighter("Bob", bobScript, history);

        Arena arena = new Arena(alice, bob, history);
        arena.nextTurn();

        assertEquals(12, bob.torso().damage());
    }

    @Test
    void spearDealsModerateDamage() {
        Weapon spear = new Weapon("spear", 8, 0.0, 1.0);
        CombatScript aliceScript = new FixedScript()
            .parry(Fighter::head)
            .strike(Fighter::torso);
        CombatScript bobScript = new FixedScript()
            .parry(Fighter::head)
            .strike(Fighter::legs);

        FightHistory history = new FightHistory();
        Fighter alice = new Fighter("Alice", spear, aliceScript, history);
        Fighter bob = new Fighter("Bob", bobScript, history);

        Arena arena = new Arena(alice, bob, history);
        arena.nextTurn();

        assertEquals(8, bob.torso().damage());
    }

    @Test
    void weaponWithFullCritChanceProducesCriticalHit() {
        Weapon critSword = new Weapon("test-sword", 8, 1.0, 2.0);
        CombatScript aliceScript = new FixedScript()
            .parry(Fighter::head)
            .strike(Fighter::torso);
        CombatScript bobScript = new FixedScript()
            .parry(Fighter::head)
            .strike(Fighter::legs);

        FightHistory history = new FightHistory();
        Fighter alice = new Fighter("Alice", critSword, aliceScript, history);
        Fighter bob = new Fighter("Bob", bobScript, history);

        Arena arena = new Arena(alice, bob, history);
        arena.nextTurn();

        assertEquals(16, bob.torso().damage());
    }

    @Test
    void weaponWithZeroCritChanceNeverProducesCriticalHits() {
        Weapon axe = new Weapon("axe", 12, 0.0, 1.0);
        CombatScript aliceScript = new FixedScript()
            .parry(Fighter::head)
            .strike(Fighter::torso)
            .parry(Fighter::head)
            .strike(Fighter::torso)
            .parry(Fighter::head)
            .strike(Fighter::torso);
        CombatScript bobScript = new FixedScript()
            .parry(Fighter::head)
            .strike(Fighter::legs)
            .parry(Fighter::head)
            .strike(Fighter::legs)
            .parry(Fighter::head)
            .strike(Fighter::legs);

        FightHistory history = new FightHistory();
        Fighter alice = new Fighter("Alice", axe, aliceScript, history);
        Fighter bob = new Fighter("Bob", bobScript, history);

        Arena arena = new Arena(alice, bob, history);
        arena.nextTurn();
        arena.nextTurn();
        arena.nextTurn();

        String fullHistory = history.fullStory();
        assertTrue(!fullHistory.contains("critical"));
    }
}
