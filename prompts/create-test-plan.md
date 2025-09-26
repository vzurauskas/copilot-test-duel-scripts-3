You are in the DESIGN PHASE for this project. Create a TDD test plan grounded in the narrative domain description and CRC cards.

Inputs:
- ITERATION_FILE: {path-to-iteration-domain-doc}
- CRC_FILE: design/crc.md
- PLAN_FILE: plan.md

Task:
- Read ITERATION_FILE (a narrative “thick description”) to extract observable behaviors and end conditions.
- Read CRC_FILE to align behaviors with object responsibilities and collaborations.
- Find and read existing unit tests in this project to see what responsibilities are already implemented.
- Write or update PLAN_FILE to include a concise list of tests to implement next, each test anchored to observable signals described in the narrative (descriptions, history entries, HP changes) and to specific responsibilities in CRC.

Format to preserve in PLAN_FILE:
- Keep existing headings and structure. Populate the "Tests to Implement (TDD)" section with a checklist.
- One test per bullet. Use short, descriptive names in the problem domain language.
- Under each test name, optionally add one sub-bullet stating the observable cue you will assert (e.g., “history states ‘both died’”).

Canonical PLAN_FILE structure (convert bulletpoints to headings and subheadings):
- Implementation Plan — Iteration: [Name of the iteration here]
  - Goals
  - Scenario description
  - Design
    - Diagram
    - Implementation details
  - Tests to Implement (TDD)
  - Notes

 If PLAN_FILE does not exist, create it with the above skeleton before adding content.

Heuristics for deriving tests:
- Each test should come from one of:
  - A decisive rule/end condition in the narrative, unless a test for that already exists.
  - A responsibility in CRC cards, unless a test for that already exists.
- Ensure every test references an observable output, not hidden internals.
- Keep the first pass lean (5–9 tests). More can be added in subsequent iterations.

Output:
- Return the fully updated PLAN_FILE content only. No commentary outside the file content.

Checklist before returning:
- [ ] Tests are phrased in domain language and tied to observable cues
- [ ] Each test maps to a responsibility in CRC or a rule in the narrative
- [ ] Formatting and existing sections of PLAN_FILE are preserved
 - [ ] If PLAN_FILE was missing, it was created using the canonical structure above
