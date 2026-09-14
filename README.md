# EcoResolve AI

AI-assisted sustainability complaint and insight platform built as a working prototype for the AI for Sustainability Virtual Internship.

## What is included

- Spring Boot 3.5 backend, Java 21
- PostgreSQL target database + H2 development profile
- JWT authentication and BCrypt password hashing
- Student complaint submission and tracking
- AI classification/summarization/recommendation MVP (deterministic rule-based adapter)
- Admin complaint review and human override
- Complaint history/audit trail
- Analytics and sustainability insights
- Responsive HTML/CSS/JavaScript frontend
- IBM BOB development assets: `agent.md`, `dobb/skills`, rules and workflow documentation
- Project documentation mapped to the internship guidelines

## Important AI note

The included AI adapter is intentionally a local, deterministic MVP so the project runs without an external AI credential. Replace `AIService` with the approved IBM AI/Granite/BOB-connected runtime workflow when credentials and access are available. IBM BOB is treated as a development assistant in this repository, consistent with the supplied BOB training summary; it is not represented as a fake runtime API.

## Run backend

1. Install Java 21 and Maven.
2. From `backend/`, run `mvn spring-boot:run`.
3. Default profile is H2, stored under `backend/data/`.
4. For PostgreSQL set `SPRING_PROFILE=postgres`, `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD`.
5. Optional admin bootstrap: set `ADMIN_EMAIL`, `ADMIN_PASSWORD`, and optionally `ADMIN_NAME` before starting the server.

## Run frontend

From `frontend/`:

```bash
python -m http.server 5500
```

Open `http://localhost:5500`.

## Demo flow

1. Register a student.
2. Login.
3. Submit a complaint such as: `The tap beside Lab 2 has been leaking since yesterday.`
4. View the AI category/priority/recommendation.
5. Create an admin using environment variables and login as admin.
6. Review complaints, analytics and generated insights.

## Security

- Never commit real JWT secrets, DB passwords or admin credentials.
- Change `app.jwt.secret` in production.
- The included H2 profile is for local demonstration only.

## Internship alignment

Primary SDG: SDG 11 (Sustainable Cities and Communities), with secondary relevance to SDG 6, SDG 7 and SDG 12. The project uses AI for classification, summarization, pattern/insight generation and decision support, while preserving human administrative review.

## Fast Windows Demo

1. Install Java 21, Maven 3.9+, and Python 3.
2. Double-click `run-backend.bat`.
3. Double-click `run-frontend.bat`.
4. Open `http://localhost:5500`.

Demo admin: `admin@ecoresolve.local` / `Admin@12345`

For the full walkthrough, see `QUICKSTART-WINDOWS.md`.
