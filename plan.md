# Implementation Plan — Iteration: Turn-by-turn History and Script Insights

## Goals
- Present a clear, human-readable turn-by-turn account of the duel for the user
- Record a structured history of each turn so `CombatScript` can make tactical decisions
- Expose queries on `FightHistory` needed by scripts (recent parries, outcomes, damage by body part, last successful hit)


## Scenario description
- Turn 1: Alice parries torso, strikes Bob’s head [parried]. Bob parries head, strikes Alice’s legs [hit X].
- Turn 2: Alice parries legs, strikes Bob’s torso [hit Y]. Bob parries head, strikes Alice’s legs [parried].
- The user can read this as sequential lines; scripts can query exact data points that produced it.


## Design

### Diagram

No diagram for this iteration since there is no new objects or responsibilities.

### Implementation details
- Enhance `FightHistory` to capture per-turn structured entries:
  - For each fighter: chosen parry body part; chosen strike target; strike result (parried/hit); effective damage (0 if parried)
  - Maintain cumulative damage per fighter and per body part (derivable or cached)
- Provide a concise, human-readable summary rendering of the duel so far (ordered by turn)
- Provide query methods for scripts (examples below)
- Keep `Arena` as orchestrator: collect both parry decisions first, then both strikes

### Queries needed by `CombatScript`
- Recent opponent parry location (e.g., last turn)
- Last successful hit target for a given attacker
- Parry frequency for a fighter over the last N turns
- Target body part frequency for a given attacker over the last N turns


## Tests to Implement (TDD)
- [x] recordsTurnWithParriesTargetsAndOutcomes
  - Setup two fighters with `FixedScript` to choose: Alice parry torso/strike head; Bob parry head/strike legs.
  - After one `Arena.nextTurn()`, `FightHistory` contains a structured entry for turn 1 with both fighters’ parry, target, result, and damage fields (0 when parried).

- [x] parryNegationRecordedAsZeroDamage
  - A strike into a correctly parried body part is recorded with result=parried and damage=0.

- [x] humanReadableSummaryRendersTwoTurns
  - After two `nextTurn()` calls (with deterministic scripts), `FightHistory` summary returns two lines in order summarizing both exchanges for a human reader.

- [x] exposesRecentOpponentTargetForScript
  - A query on `FightHistory` returns the opponent’s last target location for the requesting fighter.

- [x] exposesParryFrequencyOverLastN
  - Over the last N turns, a query returns how often a given target was parried.

## Notes
- Keep the API surface minimal; prefer derived data where possible.
- The human-readable summary should be stable and skimmable, not a debug dump.

## Next