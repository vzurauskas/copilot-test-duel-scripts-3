package com.vzurauskas.duelscripts3;

public interface CombatScript {
    BodyPart chooseParryLocation(Fighter self, Fighter opponent);
    BodyPart chooseStrikeTarget(Fighter self, Fighter opponent);
}


