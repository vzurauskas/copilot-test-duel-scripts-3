package com.vzurauskas.duelscripts3;

import java.util.function.BiFunction;
import java.util.function.Function;

public final class FixedScript implements CombatScript {
    private BiFunction<Fighter, Fighter, BodyPart> parrySelector =
        (self, opponent) -> self.torso();
    private BiFunction<Fighter, Fighter, BodyPart> strikeSelector =
        (self, opponent) -> opponent.torso();

    public FixedScript parry(Function<Fighter, BodyPart> selfSelector) {
        this.parrySelector = (self, opponent) -> selfSelector.apply(self);
        return this;
    }

    public FixedScript strike(Function<Fighter, BodyPart> opponentSelector) {
        this.strikeSelector = 
            (self, opponent) -> opponentSelector.apply(opponent);
        return this;
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
