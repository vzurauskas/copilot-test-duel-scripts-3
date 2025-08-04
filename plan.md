# Implementation Plan

## Scenario description

Arena coordinates the exchange: Both fighters simultaneously decide which body part to parry (by asking their scripts) and then strike their opponents - target body parts are also chosen by scripts. Alice chooses to parry her torso, and Bob chooses to parry his head. Alice strikes Bob's head, and Bob strikes Alice's legs. Alice's head strike is completely negated by Bob's successful parry, while Bob's leg strike connects and deals damage to Alice's legs according to the leg's damage multiplier. This exchange is recorder in combat history, making it available for future tactical decisions.