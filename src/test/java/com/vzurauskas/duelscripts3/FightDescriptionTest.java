package com.vzurauskas.duelscripts3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        arena.nextTurn();

        assertEquals(
            """
            Turn 1:
                Alice parries torso, strikes with fists at Bob's head [parried], deals 0 damage.
                Bob parries head, strikes with fists at Alice's legs [hit], deals 2 damage.
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
        arena.nextTurn();

        assertEquals(
            """
            Turn 1:
                Alice parries torso, strikes with fists at Bob's head [parried], deals 0 damage.
                Bob parries head, strikes with fists at Alice's legs [hit], deals 2 damage.
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
                Alice parries torso, strikes with fists at Bob's head [parried], deals 0 damage.
                Bob parries head, strikes with fists at Alice's legs [hit], deals 2 damage.
            Turn 2:
                Alice parries torso, strikes with fists at Bob's head [parried], deals 0 damage.
                Bob parries head, strikes with fists at Alice's legs [hit], deals 2 damage.
            """,
            history.fullStory()
        );
    }

    @Test
    void finalTurnMentionsBothDiedAndBothStrikesRecordedBeforeConclusion() {
        CombatScript aliceScript = new FixedScript()
            .parry(Fighter::torso)
            .strike(Fighter::head);
        CombatScript bobScript = new FixedScript()
            .parry(Fighter::torso)
            .strike(Fighter::head);

        FightHistory history = new FightHistory();
        Fighter alice = new Fighter("Alice", 5, aliceScript, history);
        Fighter bob = new Fighter("Bob", 5, bobScript, history);
        Arena arena = new Arena(alice, bob, history);

        arena.beginFight();

        assertEquals(1, history.turnsPassed());
        String turn = history.describeTurn(1);
        assertTrue(turn.contains("Alice parries torso, strikes with fists at Bob's head [hit], deals 5 damage."));
        assertTrue(turn.contains("Bob parries torso, strikes with fists at Alice's head [hit], deals 5 damage."));
        int idxFinalHp = turn.indexOf("final hp:");
        int idxBothDied = turn.indexOf("both died");
        assertTrue(idxFinalHp >= 0 && idxBothDied >= 0);
        assertTrue(idxBothDied > idxFinalHp);
    }

    @Test
    void finalOutcomeIncludesFinalHpAndIsNotFirstTurn() {
        CombatScript aliceScript = new FixedScript()
            .parry(Fighter::torso)
            .strike(Fighter::head);
        CombatScript bobScript = new FixedScript()
            .parry(Fighter::head)
            .strike(Fighter::legs);

        FightHistory history = new FightHistory();
        Fighter alice = new Fighter("Alice", 10, aliceScript, history);
        Fighter bob = new Fighter("Bob", 7, bobScript, history);
        Arena arena = new Arena(alice, bob, history);

        arena.beginFight();

        assertTrue(history.turnsPassed() > 1);
        int last = history.turnsPassed();
        String lastTurn = history.describeTurn(last);
        String expected = String.format(
            """
            Turn 5:
                Alice parries torso, strikes with fists at Bob's head [parried], deals 0 damage.
                Bob parries head, strikes with fists at Alice's legs [hit], deals 2 damage.
                final hp: Alice=0, Bob=7
            """
        );
        assertEquals(expected, lastTurn);
    }

    @Test
    void fightHistoryCapturesCriticalStrikesInNarrative() {
        Weapon critSword = new Weapon("sword", 8, 1.0, 2.0);
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

        String turnDescription = history.describeTurn(1);
        assertTrue(turnDescription.contains("critical"));
    }
}
