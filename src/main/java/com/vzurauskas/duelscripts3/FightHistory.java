package com.vzurauskas.duelscripts3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public final class FightHistory {
    private static final String PARRY_KEY = "parry";
    private static final String TARGET_KEY = "target";
    private static final String OUTCOME_KEY = "outcome";
    private final List<String> turnSummaries;
    private final TurnBuffer turn;
    private int turnCounter;
    private final Map<Fighter, String> lastParryByFighter;

    public FightHistory() {
        this.turnSummaries = new ArrayList<>();
        this.turn = new TurnBuffer();
        this.turnCounter = 1;
        this.lastParryByFighter = new HashMap<>();
    }

    public void parryChosen(Fighter fighter, BodyPart parryLocation) {
        turn.recordParry(fighter, parryLocation.id());
        lastParryByFighter.put(fighter, parryLocation.id());
    }

    public void strikeTargetChosen(Fighter attacker, BodyPart target) {
        turn.recordTarget(attacker, target.id());
    }

    public void strikeOccurred(Fighter attacker, BodyPart target, int damageDealt) {
        turn.recordOutcome(attacker, target.id(), damageDealt);
        if (turn.isComplete()) {
            turnSummaries.add(turn.toSummary(turnCounter));
            turn.clear();
            turnCounter++;
        }
    }

    public String describeTurn(int turnNumber) {
        return turnSummaries.get(turnNumber - 1);
    }

    public String lastParryOf(Fighter fighter) {
        return lastParryByFighter.get(fighter);
    }

    private static final class TurnBuffer {
        private final Map<Fighter, Map<String, String>> decisions;

        private TurnBuffer() {
            this.decisions = new LinkedHashMap<>();
        }

        private void recordParry(Fighter fighter, String parryId) {
            decisions.computeIfAbsent(fighter, f -> new LinkedHashMap<>())
                .put(PARRY_KEY, parryId);
        }

        private void recordTarget(Fighter fighter, String targetId) {
            Map<String, String> decision = decisions.computeIfAbsent(fighter, f -> new LinkedHashMap<>());
            decision.put(TARGET_KEY, targetId);
        }

        private void recordOutcome(Fighter fighter, String targetId, int damage) {
            Map<String, String> decision = decisions.computeIfAbsent(fighter, f -> new LinkedHashMap<>());
            decision.put(TARGET_KEY, targetId);
            decision.put(OUTCOME_KEY, damage == 0 ? "parried" : "hit");
        }

        private boolean isComplete() {
            return decisions.size() == 2 && decisions.values().stream()
                .allMatch(d -> d.containsKey(PARRY_KEY) && d.containsKey(TARGET_KEY) && d.containsKey(OUTCOME_KEY));
        }

        private String toSummary(int turnNumber) {
            Iterator<Fighter> fighters = decisions.keySet().iterator();
            Fighter first = fighters.next();
            Fighter second = fighters.next();
            Map<String, String> firstDecision = decisions.get(first);
            Map<String, String> secondDecision = decisions.get(second);

            String header = String.format("Turn %d:", turnNumber);
            String firstLine = String.format(
                "    %s parry=%s, strike=%s [%s]",
                first,
                firstDecision.get(PARRY_KEY),
                firstDecision.get(TARGET_KEY),
                firstDecision.get(OUTCOME_KEY)
            );
            String secondLine = String.format(
                "    %s parry=%s, strike=%s [%s]",
                second,
                secondDecision.get(PARRY_KEY),
                secondDecision.get(TARGET_KEY),
                secondDecision.get(OUTCOME_KEY)
            );

            return header + "\n" + firstLine + "\n" + secondLine + "\n";
        }

        private void clear() {
            decisions.clear();
        }
    }
}

 