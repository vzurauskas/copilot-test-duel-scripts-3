package com.vzurauskas.duelscripts3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class DuelConclusionTest {

    @Test
    void endsAfterSingleDeathAtEndOfTurn() {
        CombatScript aliceScript = new FixedScript()
            .parry(Fighter::torso)
            .strike(Fighter::head);
        CombatScript bobScript = new FixedScript()
            .parry(Fighter::torso)
            .strike(Fighter::legs);

        FightHistory history = new FightHistory();

        Fighter alice = new Fighter("Alice", 10, aliceScript, history);
        Fighter bob = new Fighter("Bob", 5, bobScript, history);
        Arena arena = new Arena(alice, bob, history);

        arena.nextTurn();

        assertTrue(
            arena.isFightOver(),
            "Fight should be over after lethal turn ends");
        assertTrue(
            bob.describe().intValue("hp") <= 0,
            "Bob should be dead (HP <= 0)");
        assertTrue(
            alice.describe().intValue("hp") > 0,
            "Alice should be alive (HP > 0)");

        arena.nextTurn();
        assertThrows(
            IndexOutOfBoundsException.class,
            () -> history.describeTurn(2)
        );
    }

    @Test
    void bothDieInTheSameTurn() {
        CombatScript aliceScript = new FixedScript()
            .parry(Fighter::torso)
            .strike(Fighter::head);
        CombatScript bobScript = new FixedScript()
            .parry(Fighter::torso)
            .strike(Fighter::head);

        FightHistory history = new FightHistory();

        Fighter alice = new Fighter("Alice", 15, aliceScript, history);
        Fighter bob = new Fighter("Bob", 15, bobScript, history);
        Arena arena = new Arena(alice, bob, history);

        arena.beginFight();

        assertTrue(
            arena.isFightOver(),
            "Fight should be over after lethal turn ends");
        assertTrue(
            alice.describe().intValue("hp") <= 0,
            "Alice should be dead (HP <= 0)");
        assertTrue(
            bob.describe().intValue("hp") <= 0,
            "Bob should be dead (HP <= 0)");
        assertTrue(history.turnsPassed() == 3);
    }

    @Test
    void endsAfterSingleDeathAtEndOfTurnAfterMultipleTurns() {
        CombatScript aliceScript = new FixedScript()
            .parry(Fighter::torso)
            .strike(Fighter::head);
        CombatScript bobScript = new FixedScript()
            .parry(Fighter::torso)
            .strike(Fighter::legs);

        FightHistory history = new FightHistory();

        Fighter alice = new Fighter("Alice", 10, aliceScript, history);
        Fighter bob = new Fighter("Bob", 7, bobScript, history);
        Arena arena = new Arena(alice, bob, history);

        arena.beginFight();

        assertTrue(
            arena.isFightOver(),
            "Fight should be over after lethal turn ends");
        assertTrue(
            bob.describe().intValue("hp") <= 0,
            "Bob should be dead (HP <= 0)");
        assertTrue(
            alice.describe().intValue("hp") > 0,
            "Alice should be alive (HP > 0)");
        assertTrue(history.turnsPassed() == 2);
    }
}
