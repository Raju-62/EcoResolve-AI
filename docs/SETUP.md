# Setup

## Easiest local run

Backend defaults to H2, so PostgreSQL is not required for the first demo.

```bash
cd backend
mvn spring-boot:run
```

Then in another terminal:

```bash
cd frontend
python -m http.server 5500
```

Open `http://localhost:5500`.

## Admin account

Before starting the backend, set:

- `ADMIN_EMAIL`
- `ADMIN_PASSWORD`
- optional `ADMIN_NAME`

Example values are intentionally not embedded in production configuration.

## PostgreSQL

Set:

- `SPRING_PROFILE=postgres`
- `DB_URL=jdbc:postgresql://localhost:5432/ecoresolve`
- `DB_USERNAME=postgres`
- `DB_PASSWORD=<your password>`

The SQL schema and seed scripts are under `db/`.

## Docker

`docker-compose.yml` provides PostgreSQL and a backend container. Change the sample admin/JWT secrets before using it beyond a local demo.
