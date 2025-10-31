You are in the DESIGN PHASE for this project. Follow the CRC design discipline in .cursor/rules/110_design_phase.mdc. Your role is Object Thinking CRC Designer reviewing the quality of the object model.

Inputs:
- CRC_FILE: design/crc.md
- VISION_FILE: VISION.md (optional)
- ITERATION_FILES: design/*.md (optional, for context)

Task:
- Read CRC_FILE carefully and evaluate each object against Object Thinking principles.
- For each object, assess:
  1. **Responsibility Distribution**: Are responsibilities well-defined, behavioral, and cohesive? Do they avoid passive "know something" responsibilities? Does the object delegate appropriately (lazy principle)?
  2. **Knowledge Sources**: Does the object have sufficient knowledge to fulfill its responsibilities? Is knowledge obtained appropriately (fields, parameters, or collaborators)? Is there too much knowledge (suggesting the object is doing too much)?
  3. **Collaborators**: Are all necessary collaborators listed? Are listed collaborators actually needed given the object's responsibilities and knowledge sources? Is the object trying to control/manage other objects inappropriately?
  4. **Autonomy**: Can the object act as a self-sufficient organism? Is it cohesive and consistent across contexts?
  5. **Anthropomorphism**: Can you imagine this object as a living entity with clear purpose and boundaries?

Review heuristics:
- An object does not control/manage any object other than itself.
- Avoid passive responsibilities (e.g., "know something" should be reframed as what the object *does* with that knowledge).
- Objects are lazy; they delegate hard work to collaborators.
- Collections separate individual vs collective behaviors.
- Knowledge sources should align with responsibilities (if a responsibility needs data X, there must be a knowledge source for X).
- Collaborators should align with responsibilities and knowledge sources (if knowledge source says "I can ask X", X should be in collaborators).
- Each responsibility should be testable via observable behavior (avoid internal-only responsibilities).

Review priorities (focus here):
1. Responsibilities that are on wrong objects
2. Missing collaborators for knowledge sources that say "I can ask..."
3. Responsibilities that are too passive or controlling
4. Knowledge sources that don't support declared responsibilities
5. Collaborators that aren't referenced in knowledge sources or responsibilities
6. Objects with too many or too few responsibilities (cohesion check)

When the review is done, modify the CRC cards to fix the issues you found.