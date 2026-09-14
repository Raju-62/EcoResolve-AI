# Complaint Analysis Skill

## Goal
Assist with the EcoResolve complaint-analysis workflow.

## Workflow
1. Inspect the Complaint entity, DTOs and AIService.
2. Identify the complaint's sustainability category from the supported enum.
3. Produce a structured analysis containing category, subcategory, priority, summary and recommendation.
4. Preserve human oversight: never treat the AI recommendation as the final institutional decision.
5. Run relevant tests and report any uncertainty.
