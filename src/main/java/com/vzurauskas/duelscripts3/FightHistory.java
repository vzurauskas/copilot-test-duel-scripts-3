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

    public void strikeOccurred(
        Fighter attacker, BodyPart target, int damageDealt
    ) {
        currentTurn.recordOutcome(
            attacker, target.id(), damageDealt == 0 ? "parried" : "hit"
        );
        currentTurn.recordDamage(attacker, damageDealt);
    }

    public void turnCompleted() {
        completedTurns.add(currentTurn);
        nextTurnNumber++;
        currentTurn = new Turn(nextTurnNumber);
    }

    public String describeTurn(int turnNumber) {
        return completedTurns.get(turnNumber - 1).humanReadable();
    }

    public int turnsPassed() {
        return completedTurns.size();
    }

    public String fullStory() {
        StringBuilder sb = new StringBuilder();
        for (Turn t : completedTurns) {
            sb.append(t.humanReadable());
        }
        return sb.toString();
    }

    public String lastParryOf(Fighter fighter) {
        return completedTurns.getLast().parryOf(fighter);
    }

    public String lastTargetOf(Fighter fighter) {
        return completedTurns.getLast().targetOf(fighter);
    }

    public Map<BodyPart, Integer> targetFrequencyOverLastN(
        Fighter attacker, int n
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
            BodyPart part = opponent.bodyPart(targetId);
            freq.put(part, freq.getOrDefault(part, 0) + 1);
        }
        return freq;
    }

    public Map<BodyPart, Integer> parryFrequencyOverLastN(
        Fighter fighter, int n
    ) {
        Map<BodyPart, Integer> freq = new HashMap<>();
        int size = completedTurns.size();
        int start = Math.max(0, size - n);
        for (int i = start; i < size; i++) {
            Turn t = completedTurns.get(i);
            if (!t.involves(fighter)) {
                continue;
            }
            String parryId = t.parryOf(fighter);
            BodyPart part = fighter.bodyPart(parryId);
            freq.put(part, freq.getOrDefault(part, 0) + 1);
        }
        return freq;
    }
}
