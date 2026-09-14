@echo off
setlocal EnableExtensions EnableDelayedExpansion

rem EcoResolve Maven Wrapper launcher
set "MAVEN_VERSION=3.9.16"
set "MAVEN_HOME_DIR=%USERPROFILE%\.m2\wrapper\dists\apache-maven-%MAVEN_VERSION%"
set "MAVEN_BIN=%MAVEN_HOME_DIR%\apache-maven-%MAVEN_VERSION%\bin\mvn.cmd"
set "MAVEN_ZIP=%USERPROFILE%\.m2\wrapper\dists\apache-maven-%MAVEN_VERSION%-bin.zip"
set "MAVEN_URL=https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/%MAVEN_VERSION%/apache-maven-%MAVEN_VERSION%-bin.zip"

if exist "%MAVEN_BIN%" goto runMaven

echo.
echo Maven %MAVEN_VERSION% is not installed locally. Downloading it now...
echo This is a one-time download and requires an internet connection.
echo.

if not exist "%MAVEN_HOME_DIR%" mkdir "%MAVEN_HOME_DIR%" >nul 2>&1

powershell -NoProfile -ExecutionPolicy Bypass -Command "try { Invoke-WebRequest -Uri '%MAVEN_URL%' -OutFile '%MAVEN_ZIP%' -UseBasicParsing; exit 0 } catch { Write-Host $_.Exception.Message; exit 1 }"
if errorlevel 1 (
  echo.
  echo Could not download Maven. Please check your internet connection and try again.
  exit /b 1
)

powershell -NoProfile -ExecutionPolicy Bypass -Command "try { Expand-Archive -Path '%MAVEN_ZIP%' -DestinationPath '%MAVEN_HOME_DIR%' -Force; exit 0 } catch { Write-Host $_.Exception.Message; exit 1 }"
if errorlevel 1 (
  echo Could not extract Maven.
  exit /b 1
)

if not exist "%MAVEN_BIN%" (
  echo Maven was downloaded but the expected launcher was not found:
  echo %MAVEN_BIN%
  exit /b 1
)

del /q "%MAVEN_ZIP%" >nul 2>&1

:runMaven
call "%MAVEN_BIN%" %*
exit /b %ERRORLEVEL%
