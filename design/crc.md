# CRC Cards - Two Fighters Combat System

**Fighter**
- **Responsibilities:**
  - ID self
  - Describe self
  - Determine overall health status
  - Have body parts available for striking
  - Strike opponent at chosen body part
  - Parry strikes to chosen body part
  - Report chosen parry and strike to observers
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
  - Take damage (0 if parried)
  - Report damage taken
  - Notify combat observers of events
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
  - Mark explicit end-of-fight outcome, including both died when applicable (from "both died.")
- **Knowledge Sources:**
  - I know all events that happened so far (fields)
  - I observe combat events and turn boundaries as they occur (parameters)
  - I maintain derived aggregates per fighter and body part (fields or computed)
- **Collaborators:**
  - Turn
  - Fighter (decisions, HP changes)
  - BodyPart (strike outcomes and damage)

**Turn**
- **Responsibilities:**
  - Record events that happened in this turn
  - Describe self (i.e. what happened this turn)
  - Present a human-readable summary of the turn
- **Knowledge Sources:**
  - Events are given to me via method parameters as they happen
  - I know everything that happened this turn (Description field)
- **Collaborators:**
  - Description

**Arena**
- **Responsibilities:**
  - Coordinate fighter interactions
  - Manage turn sequence
  - Determine fight conclusion
- **Knowledge Sources:**
  - I know the fighters in combat (fields)
  - I know my fight observers (fields)
  - Turn actions given by fighters (collaborators)
  - I can ask fighters about their health status (collaborator)
- **Collaborators:**
  - Fighter
  - FightHistory