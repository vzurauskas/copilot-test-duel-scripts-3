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

Canonical PLAN_FILE structure (headings and subheadings):
- Implementation Plan — Iteration: [Name of the iteration here]
  - Goals
  - Scenario description
  - Design
    - Diagram
    - Implementation details
  - Tests to Implement (TDD)
  - Notes

 If PLAN_FILE does not exist, create it with the above skeleton before adding content. Preserve indentation, bullet style, and spacing exactly as shown when populating sections.

Heuristics for deriving tests:
- Map each decisive rule/end condition in the narrative to at least one test.
- Prefer tests that assert end-of-turn outcomes over intermediate mechanics.
- Use simultaneity and parry absoluteness to generate edge-case tests (e.g., double death, fully blocked turn, lethal-but-still-strikes).
- Ensure every test references an observable output (description text, history line, or HP totals), not hidden internals.
- Keep the first pass lean (5–9 tests). More can be added in subsequent iterations.

Output:
- Return the fully updated PLAN_FILE content only. No commentary outside the file content.

Checklist before returning:
- [ ] Tests are phrased in domain language and tied to observable cues
- [ ] Each test maps to a responsibility in CRC or a rule in the narrative
- [ ] Simultaneity and end conditions are covered (single death, both died)
- [ ] A no-damage exchange is covered (both parries succeed)
- [ ] Formatting and existing sections of PLAN_FILE are preserved
 - [ ] If PLAN_FILE was missing, it was created using the canonical structure above
