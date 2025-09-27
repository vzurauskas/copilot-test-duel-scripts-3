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

        // Alice has more HP; Bob is fragile enough to die from a single head
        // hit.
        Fighter alice = new Fighter("Alice", 10, aliceScript, history);
        Fighter bob = new Fighter("Bob", 5, bobScript, history);
        Arena arena = new Arena(alice, bob, history);

        arena.nextTurn();

        assertTrue(
                   arena.isConcluded(),
                   "Fight should be concluded after lethal turn ends");
        assertTrue(
                   bob.describe().intValue("hp") <= 0,
                   "Bob should be dead (HP <= 0)");
        assertTrue(
                   alice.describe().intValue("hp") > 0,
                   "Alice should be alive (HP > 0)");

        arena.nextTurn();
        assertThrows(
                     IndexOutOfBoundsException.class,
                     () -> history.describeTurn(2));
    }
}
