# CRC Cards - Two Fighters Combat System

**Fighter**
- **Responsibilities:**
  - ID self
  - Describe self
  - Determine overall health status
  - Have body parts available for striking
  - Strike opponent at chosen body part
  - Parry strikes to chosen body part
- **Knowledge Sources:**
  - I know my name and max hit points (fields)
  - I have my body parts (fields)
  - Opponent fighter to strike is given with request (parameters)
  - I can ask my CombatScript for decisions (collaborator)
  - I can ask my body parts for their damage (collaborator)
- **Collaborators:**
  - BodyPart
  - CombatScript

**BodyPart**
- **Responsibilities:**
  - ID self
  - Take damage
  - Report damage taken
- **Knowledge Sources:**
  - I know my owner fighter (collaborator)
  - I know my type and damage multiplier (fields)
  - I know my current damage total (field)
  - Base damage amount given with attack (parameters)
- **Collaborators:**
  - Fighter (owner)

**CombatScript**
- **Responsibilities:**
  - Analyze combat situation
  - Make tactical decisions
  - Choose strike target
  - Choose parry location
- **Knowledge Sources:**
  - I know my tactical preferences (fields)
  - Fight situation given with requests (parameters)
  - I can ask FightHistory about patterns (collaborator)
- **Collaborators:**
  - FightHistory

**FightHistory**
- **Responsibilities:**
  - Observe combat events
  - Record combat events as they happen
  - Describe past exchanges
  - Track patterns and trends
- **Knowledge Sources:**
  - I know all recorded combat events (fields)
  - I observe combat events as they occur (parameters)
- **Collaborators:**
  - Arena

**Arena**
- **Responsibilities:**
  - Coordinate fighter interactions
  - Manage turn sequence
  - Determine fight conclusion
  - Notify observers of combat events
- **Knowledge Sources:**
  - I know the fighters in combat (fields)
  - I know my fight observers (fields)
  - Turn actions given by fighters (collaborators)
  - I can ask fighters about their health status (collaborator)
- **Collaborators:**
  - Fighter
  - FightHistory
