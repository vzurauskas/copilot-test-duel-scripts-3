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

        Fighter aragorn = new Fighter("Aragorn", aragornScript);
        Fighter boromir = new Fighter("Boromir", boromirScript);

        Arena arena = new Arena(aragorn, boromir);
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

        Fighter aragorn = new Fighter("Aragorn", aragornScript);
        Fighter boromir = new Fighter("Boromir", boromirScript);

        Arena arena = new Arena(aragorn, boromir);
        arena.nextTurn();

        Description description = boromir.describe();
        assertTrue(description.knows("damage"));
        assertEquals(0, description.intValue("damage"));
    }

    @Test
    void fightHistoryRecordsSimultaneousExchange() {
        CombatScript aliceScript = new FixedScript()
            .parry(Fighter::torso)
            .strike(Fighter::head);
        CombatScript bobScript = new FixedScript()
            .parry(Fighter::head)
            .strike(Fighter::legs);

        Fighter alice = new Fighter("Alice", aliceScript);
        Fighter bob = new Fighter("Bob", bobScript);

        FightHistory history = new FightHistory();
        Arena arena = new Arena(alice, bob, history);
        arena.nextTurn();

        assertEquals(2, history.strikes().size());
    }

    @Test
    void damageIsCalculatedCorrectly() {
        CombatScript aragornScript = new FixedScript()
            .parry(Fighter::torso)
            .strike(Fighter::head);
        CombatScript boromirScript = new FixedScript()
            .parry(Fighter::legs)
            .strike(Fighter::legs);

        Fighter aragorn = new Fighter("Aragorn", aragornScript);
        Fighter boromir = new Fighter("Boromir", boromirScript);

        Arena arena = new Arena(aragorn, boromir);
        arena.nextTurn();

        assertEquals(5, boromir.head().damage());
        assertEquals(2, aragorn.legs().damage());
    }
}


