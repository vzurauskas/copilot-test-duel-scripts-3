package com.vzurauskas.duelscripts3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class FightHistoryQueriesTest {

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
}
