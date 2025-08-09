package com.vzurauskas.duelscripts3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.function.BiFunction;

public final class DuelTest {

    @Test
    void unparriedStrikeDealsDamage() {
        CombatScript aragornScript = new FixedScript(
            (self, opponent) -> self.torso(),
            (self, opponent) -> opponent.torso()
        );
        CombatScript boromirScript = new FixedScript(
            (self, opponent) -> self.head(),
            (self, opponent) -> opponent.legs()
        );

        Fighter aragorn = new Fighter("Aragorn", aragornScript);
        Fighter boromir = new Fighter("Boromir", boromirScript);

        Arena arena = new Arena(aragorn, boromir);
        arena.nextTurn();

        Description description = boromir.describe();
        assertTrue(description.knows("damage"));
        assertTrue(description.intValue("damage") > 0);
    }

    private static final class FixedScript implements CombatScript {
        private final BiFunction<Fighter, Fighter, BodyPart> parrySelector;
        private final BiFunction<Fighter, Fighter, BodyPart> strikeSelector;

        private FixedScript(
            BiFunction<Fighter, Fighter, BodyPart> parrySelector,
            BiFunction<Fighter, Fighter, BodyPart> strikeSelector
        ) {
            this.parrySelector = parrySelector;
            this.strikeSelector = strikeSelector;
        }

        @Override
        public BodyPart chooseParryLocation(Fighter self, Fighter opponent) {
            return parrySelector.apply(self, opponent);
        }

        @Override
        public BodyPart chooseStrikeTarget(Fighter self, Fighter opponent) {
            return strikeSelector.apply(self, opponent);
        }
    }
}


