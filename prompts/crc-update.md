You are in the DESIGN PHASE for this project. Follow the CRC design discipline in .cursor/rules/110_design_phase.mdc.

Inputs:
- DOMAIN_FILE: {path-to-rich-domain-description}
- CRC_FILE: design/crc.md

Task:
- Read DOMAIN_FILE carefully.
- Update CRC_FILE by extracting NEW objects and responsibilities from the domain description.
- Next to EACH new object and EACH new responsibility, add a parenthetical excerpt with the exact short phrase you used from DOMAIN_FILE (e.g., ‘from "attack is completely deflected, dealing no damage"’).
- Only add what is explicitly supported by the domain description. Do not speculate.
- Keep existing content intact unless the domain text clearly requires refinement; when refining, also include a parenthetical excerpt for the refined line.

Scope and formatting rules:
- Edit CRC_FILE only.
- Preserve existing indentation, bullet styles, section order, and spacing exactly.
- Add new objects as new sections matching the file’s existing pattern:
  **ObjectName**
  - **Responsibilities:**
    - Responsibility text (from "domain excerpt")
  - **Knowledge Sources:** … (figure out what knowledge the object needs to perform the new responsibility. Use "Creating CRC Cards" guidelines from Design Phase rules.)
  - **Collaborators:** … (figure out if the object needs new collaborators to perform the new responsibility.)
- For existing objects, append new responsibilities/knowledge/collaborators under the correct subsection; include the parenthetical excerpt.
- Use short, precise excerpts; quote exactly from DOMAIN_FILE.
- Do not add commentary outside the CRC structure. No extra headers or prose.

Heuristics:
- Nouns → potential Objects; Verbs → Responsibilities.
- Only include collaborators/knowledge sources if they are needed for the object to perform the new responsibility.
- Prefer minimal, incremental edits.

Output:
- Return the fully updated CRC_FILE content with the new/modified lines annotated with their domain excerpts.
- Do not include explanations outside the file content.

Checklist before returning:
- [ ] All new objects include parenthetical domain excerpts on their header line or first responsibility
- [ ] Every new responsibility has a parenthetical excerpt
- [ ] Existing formatting preserved exactly
- [ ] No speculative additions; each change is justified by an excerpt from DOMAIN_FILE