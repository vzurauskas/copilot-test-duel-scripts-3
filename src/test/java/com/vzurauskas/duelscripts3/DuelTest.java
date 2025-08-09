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

    // Test-local FixedScript removed in favor of production FixedScript
}


