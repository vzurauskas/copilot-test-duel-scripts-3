You are in the DESIGN PHASE for this project. Follow the design discipline in .cursor/rules/110_design_phase.mdc.

Inputs:
- ITERATION_FILE: {path-to-iteration-domain-doc}
- VISION_FILE: VISION.md
- CRC_FILE: design/crc.md

Task:
- Read VISION_FILE and CRC_FILE to understand the project’s purpose and current object model.
- Expand ITERATION_FILE into a rich domain “thick description” for this iteration — layered, contextual narrative that allows the reader to vicariously experience the domain. Favor stories over outlines, per the notion of "thick description" (Geertz) as discussed by David West [Deep Theory, Part Two](https://profwest.substack.com/p/deep-theory-8d4).
- Use the project’s ubiquitous language; avoid implementation details.

Style guidance for thick description:
- Write in narrative form. Prefer paragraphs and embedded mini-stories over rigid headings.
- Show, don’t enumerate: reveal rules and invariants through concrete moments in play.
- Layer context: highlight motivations, expectations, and how actors interpret events.
- Include revealing asides when helpful (e.g., “This matters because …”). Keep them concise.
- Make simultaneity, end conditions, and observability clear in-story rather than as bullet lists.
- Surface 2–4 illustrative scenarios naturally within the prose (they can be short vignettes).
- Keep the focus on behavior and outcomes; avoid APIs, classes, or method names.

Scope and formatting rules:
- Edit ITERATION_FILE only.
- Preserve existing content and formatting (indentation, bullets, headers). Append and refine; do not delete original meaning.
- Keep language in the problem domain; do not prescribe APIs, classes, or method names.
- Align with the current CRC model; if your narrative implies new responsibilities, mention them briefly in-line (as an aside) so they can be added to CRC later. Do not modify CRC here.

Heuristics:
- Nouns → potential objects; Verbs → responsibilities.
- When simultaneity or end conditions matter, state them explicitly somewhere in the narrative.
- Ensure outcomes are observable via descriptions/history so tests can later anchor to them.
- Prioritize clarity and coherence over completeness; prefer depth of a few key moments.

Output:
- Return the fully updated ITERATION_FILE content only. No commentary outside the file content.

Checklist before returning:
- [ ] Narrative reads as a coherent thick description (not a rigid outline)
- [ ] Key end conditions and simultaneity (if relevant) are made clear in-story
- [ ] 2–4 concrete moments/scenarios appear naturally in the prose
- [ ] Observable signals exist for later tests (history/description cues)
- [ ] Language aligns with existing CRC vocabulary; no implementation details
- [ ] Original formatting preserved

