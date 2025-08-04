# Two Fighters Strike Each Other - Rich Domain Description

## The Arena Scene

In the dusty arena, two seasoned fighters face each other under the blazing sun. Alice, a nimble warrior with 100 hit points and formidable strength, stands across from Bob, equally matched with his own 100 hit points and powerful build. Neither carries a weapon—this is a test of pure combat skill, fist against fist.

## The Fighters' Mindset

Alice studies her opponent carefully. She knows her own capabilities: her current health, her striking power, her ability to read combat situations. She must decide where to strike Bob and which part of her own body to protect. Her combat script—the ingrained fighting instincts developed through years of training—guides her tactical decisions.

Bob mirrors this assessment. He too understands his own condition and capabilities. His eyes scan Alice, trying to anticipate her next move while his own combat script processes the tactical situation. Like Alice, he must choose both an offensive target and a defensive stance.

## The Moment of Combat

The referee signals the beginning of the turn. Time seems to slow as both fighters make their simultaneous decisions:

**Alice's Decision Process:**
- She chooses to strike at Bob's head, knowing it will deal the most damage if it connects
- She anticipates Bob might target her torso, so she raises her guard to parry strikes to that area
- Her combat script processes these choices based on her knowledge of effective fighting tactics

**Bob's Decision Process:**
- He decides to target Alice's legs, aiming to impair her mobility with a lower but more reliable strike
- Expecting Alice to go for his head, he positions himself to parry head strikes
- His own combat script guides these tactical choices

## The Exchange

In a split second, both fighters act:

Alice's fist shoots toward Bob's head with devastating intent. Simultaneously, Bob's strike aims low at Alice's legs while his arms protect his head from the incoming blow.

The collision of strategy unfolds: Alice's powerful head strike meets Bob's prepared defense. His parry is perfectly timed and positioned—the attack is completely deflected, dealing no damage to Bob.

Meanwhile, Bob's leg strike finds its mark. Alice, focused on her offensive move and protecting her torso, left her legs undefended. The impact connects solidly, reducing Alice's hit points according to Bob's strength and the inherent damage potential of a leg strike.

## The Aftermath

Bob remains at full strength, his successful defense having protected him completely. Alice, though wounded, maintains her fighting spirit. Her hit points have decreased, but she remains very much in the fight, already analyzing what happened and preparing for the next exchange.

Both fighters now possess additional knowledge: they have seen each other's tactical preferences, experienced each other's combat scripts in action, and gained insight into their opponent's fighting style. This information will influence their decisions in subsequent turns.

## The Continuing Duel

The fight is far from over. Each fighter will continue to make tactical decisions, their combat scripts adapting based on the growing history of their exchanges. They will strike, parry, take damage, and learn from each encounter until one fighter's hit points are depleted and victory is achieved.

## Domain Concepts Revealed

This scenario reveals several key domain concepts:

- **Fighters** as autonomous decision-makers with internal state and capabilities
- **Body parts** as discrete targets and defensive positions with different damage characteristics
- **Combat scripts** as decision-making algorithms that process fight information
- **Simultaneous turns** where both fighters act at once, creating tactical interactions
- **Parrying mechanics** that can completely negate attacks when properly executed
- **Damage calculation** based on fighter strength and target selection
- **Fight history** as accumulating knowledge that influences future decisions
- **Combat state** tracking hit points and fighter condition over time

The elegance lies in the simultaneous nature of the combat—neither fighter has perfect information about their opponent's immediate intentions, making each exchange a calculated risk based on prediction, pattern recognition, and tactical analysis.

# Objects and their responsibilities

## Extracted Objects (Nouns → Objects, Verbs → Responsibilities)

**Fighter** (from "two seasoned fighters face each other")
- ID self
- Describe self (name, hit points, strength, combat condition) (from "Alice, a nimble warrior with 100 hit points and formidable strength")
- Strike opponent at chosen body part (from "Alice's fist shoots toward Bob's head")
- Parry strikes to chosen body part (from "Bob's strike aims low at Alice's legs while his arms protect his head")
- Take damage and reduce hit points (from "reducing Alice's hit points according to Bob's strength")
- Analyze combat situation (from "Alice studies her opponent carefully")
- Make tactical decisions (from "She must decide where to strike Bob and which part of her own body to protect")

**BodyPart** (from "Target one of three body parts—Head (highest damage), Torso (medium), or Legs (lowest)")
- ID self (Head, Torso, Legs) (from "Head (highest damage), Torso (medium), or Legs (lowest)")
- Take damage (from "Head (highest damage), Torso (medium), or Legs (lowest)")

**CombatScript** (from "Her combat script—the ingrained fighting instincts")
- Describe decision-making approach (from "the ingrained fighting instincts developed through years of training")
- Choose strike target (from "Her combat script processes these choices based on her knowledge")
- Choose parry location (from "His own combat script guides these tactical choices")

**FightHistory** (from "the growing history of their exchanges")
- Describe past exchanges (from "they have seen each other's tactical preferences")
- Record combat events (from "based on the growing history of their exchanges")
- Track patterns and trends (from "This information will influence their decisions in subsequent turns")

**Arena** (from "In the dusty arena, two seasoned fighters face each other")
- Coordinate fighter interactions (from "The referee signals the beginning of the turn")
- Manage turn sequence (from "Each turn, both fighters perform two actions")
- Determine fight conclusion (from "until one fighter's hit points are depleted and victory is achieved")

**HitPoints** (from "100 hit points")
- Track current health (from "Alice, a nimble warrior with 100 hit points")
- Reduce health on damage (from "reducing Alice's hit points according to Bob's strength")
- Check for defeat condition (from "until one fighter's hit points are depleted and victory is achieved")

## Other potential objects
- Strike
- Parry
- Attack
- Defense
- Combat / Fight / Duel
- Turn
- Exchange