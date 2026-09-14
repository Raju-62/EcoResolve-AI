# REST API quick reference

Base URL: `http://localhost:8080/api`

## Public
- `POST /auth/register`
- `POST /auth/login`
- `GET /locations`

## Authenticated student
- `GET /complaints?page=0&size=10`
- `POST /complaints`
- `GET /complaints/{id}`

## Admin
- `GET /admin/dashboard`
- `GET /admin/complaints?page=0&size=20`
- `GET /admin/complaints/{id}`
- `PUT /admin/complaints/{id}`
- `GET /admin/analytics`
- `POST /admin/insights`

All protected endpoints use `Authorization: Bearer <JWT>`.
