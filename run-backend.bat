@echo off
cd /d "%~dp0backend"
set ADMIN_EMAIL=admin@ecoresolve.local
set ADMIN_PASSWORD=Admin@12345
set ADMIN_NAME=EcoResolve Admin
call mvnw.cmd spring-boot:run
pause
