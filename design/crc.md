# CRC Cards - Two Fighters Combat System

**Fighter**
- **Responsibilities:**
  - ID self
  - Describe self
  - Determine overall health status
  - Have body parts available for striking
  - Strike opponent at chosen body part
  - Parry strikes to chosen body part
  - Report chosen parry and strike, including damage, to observers
  - Know strength / base strike power (from "formidable strength" and "reducing Alice's hit points according to Bob's strength")
  - Maintain hit points via HitPoints (from "100 hit points" and "until one fighter's hit points are depleted")
- **Knowledge Sources:**
  - I know my name and max hit points (fields)
  - I know my current parry location (field)
  - I have my body parts (fields)
  - Opponent fighter to strike is given with request (parameters)
  - I can ask my CombatScript for decisions (collaborator)
  - I can ask my body parts for their damage (collaborator)
  - I know my observers (collaborator)
- **Collaborators:**
  - BodyPart
  - CombatScript
  - FightHistory (observer)

**BodyPart**
- **Responsibilities:**
  - ID self
  - Receive strike attempts
  - Check if strike is parried
  - Take damage
  - Report damage taken
  - Notify combat observers of events
  - Compute damage using striker power × body-part multiplier (from "head ... will deal the most damage" and "legs ... lower")
- **Knowledge Sources:**
  - I know my owner fighter (collaborator)
  - I know my type and damage multiplier (fields)
  - I know my current damage total (field)
  - I know my combat observers (collaborators)
  - Base damage amount given with attack (parameters)
  - Striking fighter given with attack (parameters)
  - I can ask my owner Fighter if they're parrying me (collaborator)
- **Collaborators:**
  - Fighter (owner)
  - Observers (FightHistory)

**CombatScript**
- **Responsibilities:**
  - Analyze combat situation using FightHistory
  - Make tactical decisions, possibly adapting to opponent tendencies
  - Choose strike target
  - Choose parry location
- **Knowledge Sources:**
  - I know my tactical preferences (fields)
  - Fight situation given with requests (parameters)
  - I can query FightHistory for: recent parries, last outcomes, damage by body part, target effectiveness (collaborator)
- **Collaborators:**
  - FightHistory

**FightHistory**
- **Responsibilities:**
  - Observe combat events (parry choices, strike targets, outcomes, damage)
  - Record exchanges turn-by-turn in a structured form
  - Present a human-readable summary of the duel so far, turn by turn
  - Expose queryable insights (recent parries, last outcomes, damage by body part, target effectiveness)
  - Track cumulative and per-body-part damage over time
  - Expose post-exchange state (hit points per fighter) after each turn
  - Record parry-caused negations explicitly (from "attack is completely deflected, dealing no damage")
- **Knowledge Sources:**
  - I know all recorded exchanges (fields)
  - I observe combat events and turn boundaries as they occur (parameters)
  - I maintain derived aggregates per fighter and body part (fields or computed)
- **Collaborators:**
  - Arena (turn sequencing, boundaries)
  - Fighter (decisions, HP changes)
  - BodyPart (strike outcomes and damage)
  - CombatScript (consumer of queries)

**Arena**
- **Responsibilities:**
  - Coordinate fighter interactions
  - Manage turn sequence
  - Determine fight conclusion
  - Orchestrate simultaneous decisions per turn (from "both fighters make their simultaneous decisions")
  - Apply parry resolution before damage (from "completely deflected, dealing no damage")
- **Knowledge Sources:**
  - I know the fighters in combat (fields)
  - I know my fight observers (fields)
  - Turn actions given by fighters (collaborators)
  - I can ask fighters about their health status (collaborator)
- **Collaborators:**
  - Fighter
  - FightHistory

**HitPoints** (from "100 hit points" and "until one fighter's hit points are depleted")
- **Responsibilities:**
  - Track current and maximum health (from "100 hit points")
  - Reduce health when damage is applied (from "reducing Alice's hit points")
  - Signal defeat when health reaches zero (from "until one fighter's hit points are depleted")

**Exchange** (from "The referee signals the beginning of the turn" and "both fighters act")
- **Responsibilities:**
  - Capture one simultaneous turn with both parry choices and strike targets (from "simultaneous decisions")
  - Apply parry rules that can completely negate attacks (from "attack is completely deflected, dealing no damage")
  - Calculate damage using striker strength and target body-part characteristics (from "according to Bob's strength" and "head ... most damage / legs ... lower")
  - Produce an outcome and update FightHistory (from "gained insight ... will influence their decisions in subsequent turns")
