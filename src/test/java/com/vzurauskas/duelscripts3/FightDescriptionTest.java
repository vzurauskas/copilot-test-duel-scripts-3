package com.vzurauskas.duelscripts3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class FightDescriptionTest {

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
}
