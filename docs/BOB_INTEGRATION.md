# IBM BOB Integration Plan

The supplied IBM BOB training summary describes BOB as an AI-powered code assistant with reusable Skills, MCP connectivity, Modes (Agent, Plan and Ask), global Rules, and repository context through `agent.md`. The project therefore incorporates BOB into the development workflow rather than pretending BOB is the runtime complaint-analysis API.

## Repository assets

- `agent.md` — project/codebase context file.
- `dobb/skills/complaint-analysis/` — reusable complaint-analysis development workflow.
- `dobb/skills/sustainability-insights/` — reusable sustainability-insight workflow.
- `dobb/skills/api-testing/` — reusable API-testing workflow.
- `dobb/rules.md` — project-wide development rules.

## BOB usage examples

### Plan mode
Use Plan to review a feature request and produce an implementation plan before code changes.

### Agent mode
Use Agent to implement an approved plan, run tests, and update project files.

### Ask mode
Use Ask to understand unfamiliar code, explain errors, or review an implementation.

## Skills
Skills should be kept concise and reusable. Each skill documents a repeatable workflow rather than embedding a one-off conversation.

## MCP
MCP can be added where a real external system connection is available and useful, such as a development database or project-management system. Do not add a fake MCP configuration merely to claim integration. The exact MCP server configuration should follow the IBM BOB environment/documentation available to the student.

## Human review
BOB-generated code and artifacts should be manually reviewed before being accepted into the project. This mirrors the development workflow described in the training summary.

## Important naming note
The supplied training notes mention both `instruction.md` and `skill.md` conventions. This repository includes both filenames inside each skill folder with the same workflow content for compatibility; verify the exact convention required by the BOB environment being used before final submission.
