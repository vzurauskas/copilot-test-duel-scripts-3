# Fight History Captures Relevant Details of the Duel — Rich Domain Description

## The Scribe of the Duel

In the arena, sand hangs in the air as the turn begins. Two fighters, Alice and Bob, breathe in tempo. They are focused, but they are not alone. Beside them stands an invisible scribe: the FightHistory. It watches without blinking. It does not judge. It remembers.

The FightHistory is not there to simply list strikes. It is there to carry the duel forward, to give the fighters context and the viewer clarity. It captures each exchange with enough precision that both a human reader and a cold, tactical script can learn from it.

## The First Exchange — What Gets Remembered

The referee signals. Both fighters commit in the same heartbeat, guided by their CombatScripts:

- Alice raises her guard to parry her torso and throws a strike at Bob's head.
- Bob sets his defense to parry his head and snaps a strike toward Alice's legs.

The moment splits into two outcomes:

- Alice's head strike is absorbed by Bob's head parry. No damage.
- Bob's leg strike connects cleanly. Damage is applied to Alice's legs according to their damage multiplier. Alice's total hit points fall.

The FightHistory writes this exchange down. Not as a vague sentence, but as a structured memory of the turn:

- turn: 1
- alice: { parry: torso, strike: { target: head, result: parried, damage: 0 } }
- bob: { parry: head, strike: { target: legs, result: hit, damage: X } }
- after: { aliceHp: 100 - X, bobHp: 100 }

This is enough for the viewer to understand the scene and for scripts to reason about what to do next.

## Between Turns — How Scripts Learn and Adapt

Between exchanges, the fighters’ CombatScripts consult the FightHistory.

- Alice’s script notes: “Bob parried head. My head strike was parried. My legs were hit.” From this, it infers that Bob might continue to protect his head and that Alice may need to shift targets to the torso or legs to land damage.
- Bob’s script notes: “Alice parried torso. My leg strike hit. Her head strike was parried.” From this, it infers that continuing to attack the legs may be efficient, but that Alice might adapt and defend low.

The FightHistory enables these reflections because it preserves exactly what matters for tactics:

- The parry location each fighter chose.
- The strike target each fighter chose.
- Whether the strike was parried or hit.
- The effective damage dealt (after multipliers and parry outcomes).
- The evolving state after the exchange (hit points per fighter).

With this, scripts can ask meaningful questions:

- “What did my opponent parry last turn?”
- “What body part has taken the most damage so far?”
- “How often has my target been parried in the last N exchanges?”
- “What was the last successful hit I landed, and where?”

These are not speculations; they are derivable from what the FightHistory remembers.

## The Second Exchange — A Narrative the Viewer Can Read

The next signal comes. Both adjust.

- Alice shifts to target Bob’s torso while keeping her parry on legs, wary of repeated low attacks.
- Bob, encouraged by success, targets Alice’s legs again and keeps his parry high on head, expecting another high strike.

The outcomes:

- Alice’s torso strike lands. Bob’s parry was on head; no defense on torso. Damage is applied to Bob’s torso; his hit points decrease.
- Bob’s leg strike is deflected this time by Alice’s low parry. No damage.

The FightHistory records the second exchange:

- turn: 2
- alice: { parry: legs, strike: { target: torso, result: hit, damage: Y } }
- bob: { parry: head, strike: { target: legs, result: parried, damage: 0 } }
- after: { aliceHp: 100 - X, bobHp: 100 - Y }

For a human reader, this can be presented as a clean, readable log:

- Turn 1 — Alice→Bob: head [parried], Bob→Alice: legs [hit X]. HP: Alice 100→100−X, Bob 100→100
- Turn 2 — Alice→Bob: torso [hit Y], Bob→Alice: legs [parried]. HP: Alice 100−X→100−X, Bob 100→100−Y

The purpose is clarity without losing tactical fidelity.

## What the FightHistory Knows (and Why)

The FightHistory is a disciplined observer. For every exchange it captures:

- Who attacked whom.
- Each fighter’s chosen parry location.
- Each fighter’s chosen strike target.
- Whether each strike was parried or landed.
- The effective damage for each strike that landed.
- Cumulative damage by body part per fighter (derivable or cached).
- Current hit points after the exchange for each fighter.

This knowledge supports two audiences:

- Viewer: wants a narrative that is truthful, sequential, and easy to skim.
- Script: wants precise data to detect tendencies and adapt.

## How Scripts Use It — Practical Tactical Questions

With a complete FightHistory, scripts can implement simple adaptive tactics:

- Identify opponent parry tendencies: “Over the last 3 turns, where did they parry most?”
- Identify personal target effectiveness: “Which of my targets have been parried most often?”
- Identify opponent vulnerability: “Where have I landed the most damage on them?”
- React to streaks: “They’ve parried head three turns in a row — shift to torso.”
- Risk management: “My last two leg strikes were parried — try head despite higher risk.”

None of this requires predicting the future; it only requires remembering the past precisely.

## The Endgame — A History That Tells the Whole Fight

As the duel progresses, the FightHistory becomes the authoritative chronicle of what happened and when. If the duel ends because Bob’s torso can no longer absorb damage, the history makes that trajectory obvious: a trail of hits to the torso, missed opportunities, and adaptive shifts.

For the viewer, the story is compelling and coherent. For the scripts, the data is actionable. For the system, the FightHistory is the shared truth that both sides can trust.

## Purpose of This Iteration

Expand the FightHistory so that it:

- Records exchanges in a structured way (turn-by-turn, per-fighter decisions and outcomes, post-exchange state).
- Presents a human-readable summary of the duel so far (string rendering that is concise and accurate).
- Exposes queryable information for CombatScripts to adapt (recent parries, successful targets, damage by body part, last outcomes).

When this is in place, fighters won’t fight in the dark. They will fight with memory.