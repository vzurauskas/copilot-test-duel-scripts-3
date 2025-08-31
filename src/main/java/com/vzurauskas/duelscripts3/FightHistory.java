package com.vzurauskas.duelscripts3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public final class FightHistory {
    private int nextTurnNumber;
    private Turn currentTurn;
    private final List<Turn> completedTurns;

    public FightHistory() {
        this.nextTurnNumber = 1;
        this.currentTurn = new Turn(nextTurnNumber);
        this.completedTurns = new ArrayList<>();
    }

    public void parryChosen(Fighter fighter, BodyPart parryLocation) {
        currentTurn.recordParry(fighter, parryLocation.id());
    }

    public void strikeTargetChosen(Fighter attacker, BodyPart target) {
        currentTurn.recordTarget(attacker, target.id());
    }

    public void strikeOccurred(Fighter attacker, BodyPart target, int damageDealt) {
        currentTurn.recordOutcome(attacker, target.id(), damageDealt == 0 ? "parried" : "hit");
        if (currentTurn.isComplete()) {
            completedTurns.add(currentTurn);
            nextTurnNumber++;
            currentTurn = new Turn(nextTurnNumber);
        }
    }

    public String describeTurn(int turnNumber) {
        return completedTurns.get(turnNumber - 1).humanReadable();
    }

    public String lastParryOf(Fighter fighter) {
        for (int i = completedTurns.size() - 1; i >= 0; i--) {
            String parry = completedTurns.get(i).parryOf(fighter);
            if (parry != null) {
                return parry;
            }
        }
        return null;
    }

    public Map<BodyPart, Integer> targetFrequencyOverLastN(
        Fighter attacker,
        int n
    ) {
        Map<BodyPart, Integer> freq = new HashMap<>();
        int size = completedTurns.size();
        int start = Math.max(0, size - n);
        for (int i = start; i < size; i++) {
            Turn t = completedTurns.get(i);
            if (!t.involves(attacker)) {
                continue;
            }
            Fighter opponent = t.opponentOf(attacker);
            String targetId = t.targetOf(attacker);
            BodyPart part = resolveBodyPartById(opponent, targetId);
            if (part == null) {
                continue;
            }
            freq.put(part, freq.getOrDefault(part, 0) + 1);
        }
        return freq;
    }

    private BodyPart resolveBodyPartById(
        Fighter fighter,
        String id
    ) {
        if ("head".equals(id)) {
            return fighter.head();
        }
        if ("torso".equals(id)) {
            return fighter.torso();
        }
        if ("legs".equals(id)) {
            return fighter.legs();
        }
        return null;
    }
}

 