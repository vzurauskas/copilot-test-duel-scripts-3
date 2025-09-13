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
    void parryNegationRecordedAsZeroDamage() {
        CombatScript aliceScript = new FixedScript()
            .parry(Fighter::torso)
            .strike(Fighter::head);
        CombatScript bobScript = new FixedScript()
            .parry(Fighter::head)
            .strike(Fighter::legs);

        FightHistory history = new FightHistory();
        Fighter alice = new Fighter("Alice", aliceScript, history);
        Fighter bob = new Fighter("Bob", bobScript, history);
        Arena arena = new Arena(alice, bob, history);

        arena.nextTurn();

        assertEquals(
            """
            Turn 1:
                Alice parry=torso, strike=head [parried], damage=0
                Bob parry=head, strike=legs [hit], damage=2
            """,
            history.describeTurn(1)
        );
    }

    @Test
    void exposesRecentOpponentParryForScript() {
        CombatScript aliceScript = new FixedScript()
            .parry(Fighter::torso)
            .strike(Fighter::head);
        CombatScript bobScript = new FixedScript()
            .parry(Fighter::head)
            .strike(Fighter::legs);

        FightHistory history = new FightHistory();
        Fighter alice = new Fighter("Alice", aliceScript, history);
        Fighter bob = new Fighter("Bob", bobScript, history);
        Arena arena = new Arena(alice, bob, history);

        arena.nextTurn();

        assertEquals("head", history.lastParryOf(bob));
        assertEquals("torso", history.lastParryOf(alice));
    }

    @Test
    void exposesTargetFrequencyOverLastN() {
        CombatScript aliceScript = new FixedScript()
            .parry(Fighter::torso)
            .strike(Fighter::head);
        CombatScript bobScript = new FixedScript()
            .parry(Fighter::head)
            .strike(Fighter::legs);

        FightHistory history = new FightHistory();
        Fighter alice = new Fighter("Alice", aliceScript, history);
        Fighter bob = new Fighter("Bob", bobScript, history);
        Arena arena = new Arena(alice, bob, history);

        arena.nextTurn();
        arena.nextTurn();

        assertEquals(2, history.targetFrequencyOverLastN(bob, 2).get(alice.legs()));
        assertEquals(2, history.targetFrequencyOverLastN(alice, 2).get(bob.head()));
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
    void humanReadableSummaryRendersTwoTurns() {
        CombatScript aliceScript = new FixedScript()
            .parry(Fighter::torso)
            .strike(Fighter::head);
        CombatScript bobScript = new FixedScript()
            .parry(Fighter::head)
            .strike(Fighter::legs);

        FightHistory history = new FightHistory();
        Fighter alice = new Fighter("Alice", aliceScript, history);
        Fighter bob = new Fighter("Bob", bobScript, history);
        Arena arena = new Arena(alice, bob, history);

        arena.nextTurn();
        arena.nextTurn();

        assertEquals(
            """
            Turn 1:
                Alice parry=torso, strike=head [parried], damage=0
                Bob parry=head, strike=legs [hit], damage=2
            Turn 2:
                Alice parry=torso, strike=head [parried], damage=0
                Bob parry=head, strike=legs [hit], damage=2
            """,
            history.describeTurn(1) + history.describeTurn(2)
        );
    }
    @Test
    void recordsTurnWithParriesTargetsAndOutcomes() {
        CombatScript aliceScript = new FixedScript()
            .parry(Fighter::torso)
            .strike(Fighter::head);
        CombatScript bobScript = new FixedScript()
            .parry(Fighter::head)
            .strike(Fighter::legs);

        FightHistory history = new FightHistory();
        Fighter alice = new Fighter("Alice", aliceScript, history);
        Fighter bob = new Fighter("Bob", bobScript, history);
        Arena arena = new Arena(alice, bob, history);
        arena.nextTurn();

        assertEquals(
            """
            Turn 1:
                Alice parry=torso, strike=head [parried], damage=0
                Bob parry=head, strike=legs [hit], damage=2
            """,
            history.describeTurn(1)
        );
    }
}


