run-backend:
	cd backend && mvn spring-boot:run

run-frontend:
	cd frontend && python3 -m http.server 5500

test:
	cd backend && mvn test
