# EcoResolve AI — Agent Context

EcoResolve AI is a Java 21 / Spring Boot sustainability complaint platform with a static HTML/CSS/JS frontend and PostgreSQL target database. H2 is provided as the default local development profile.

Core backend packages:
- entity: User, Location, Complaint, AIAnalysis, ComplaintHistory
- enums: Role, Priority, ComplaintStatus, ComplaintCategory
- repository: Spring Data JPA repositories
- service: authentication, complaint workflow and AI adapter
- controller: authentication, locations, student complaints and admin APIs
- security: JWT filter/service and Spring Security configuration

Runtime workflow:
Student submits complaint -> backend validates and saves it -> AIService generates structured analysis -> analysis is stored -> student can track the complaint -> admin can review/override -> status history is recorded -> analytics and sustainability insights are generated.

IBM BOB role:
BOB is used as a development/code assistant through project context, reusable skills, modes and rules. It is not represented as the runtime complaint-analysis model. The local AIService is a deterministic MVP adapter so the application can run without external credentials. Replace that adapter with the approved IBM runtime AI/Granite workflow when access is available.

Primary SDG: SDG 11. Secondary relevance: SDG 6, 7 and 12.

Responsible AI requirements: fairness, transparency, ethics, privacy and human oversight.
