# EcoResolve AI — Windows Quick Start

## Requirements
- Java 21 (already required by the project)
- Internet connection for the first Maven/dependency download

## Start the backend — no Maven installation required
Open PowerShell in the `backend` folder and run:

```powershell
.\mvnw.cmd spring-boot:run
```

The included wrapper launcher downloads Apache Maven 3.9.16 into your user `.m2` folder if needed. Maven does not need to be added to PATH.

Or from the project root, double-click `run-backend.bat`.

## Start the frontend
Open `frontend/index.html` with VS Code Live Server, or open the file in a browser.

Backend API: `http://localhost:8080/api`

Demo admin:
- Email: `admin@ecoresolve.local`
- Password: `Admin@12345`

## If the first run fails
Send the complete terminal error. Do not delete the `.m2` folder unless instructed.

### Demo admin account
Email: `admin@ecoresolve.local`
Password: `Admin@12345`

If you changed backend environment variables previously, restart the backend after updating the project.
