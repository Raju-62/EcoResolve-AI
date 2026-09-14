CREATE TABLE IF NOT EXISTS users (
 id BIGSERIAL PRIMARY KEY, name VARCHAR(100) NOT NULL, email VARCHAR(255) NOT NULL UNIQUE,
 password_hash VARCHAR(255) NOT NULL, role VARCHAR(20) NOT NULL DEFAULT 'STUDENT', created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
 CHECK (role IN ('STUDENT','ADMIN'))
);
CREATE TABLE IF NOT EXISTS locations (
 id BIGSERIAL PRIMARY KEY, name VARCHAR(150) NOT NULL, building VARCHAR(100), floor VARCHAR(50)
);
CREATE TABLE IF NOT EXISTS complaints (
 id BIGSERIAL PRIMARY KEY, user_id BIGINT NOT NULL REFERENCES users(id), location_id BIGINT NOT NULL REFERENCES locations(id),
 description TEXT NOT NULL, image_url VARCHAR(500), ai_category VARCHAR(50), ai_subcategory VARCHAR(100), ai_priority VARCHAR(20),
 final_category VARCHAR(50), final_priority VARCHAR(20), ai_summary TEXT, ai_recommendation TEXT, status VARCHAR(30) NOT NULL DEFAULT 'PENDING',
 resolution_note TEXT, created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, resolved_at TIMESTAMP,
 CHECK (ai_priority IS NULL OR ai_priority IN ('LOW','MEDIUM','HIGH')),
 CHECK (final_priority IS NULL OR final_priority IN ('LOW','MEDIUM','HIGH')),
 CHECK (status IN ('PENDING','REVIEWED','ASSIGNED','IN_PROGRESS','RESOLVED'))
);
CREATE TABLE IF NOT EXISTS ai_analysis (
 id BIGSERIAL PRIMARY KEY, complaint_id BIGINT NOT NULL REFERENCES complaints(id) ON DELETE CASCADE, model VARCHAR(100), category VARCHAR(50), subcategory VARCHAR(100), priority VARCHAR(20), summary TEXT, recommendation TEXT, created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS complaint_history (
 id BIGSERIAL PRIMARY KEY, complaint_id BIGINT NOT NULL REFERENCES complaints(id) ON DELETE CASCADE, changed_by BIGINT REFERENCES users(id), old_status VARCHAR(30), new_status VARCHAR(30) NOT NULL, note TEXT, created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX IF NOT EXISTS idx_complaints_user ON complaints(user_id);
CREATE INDEX IF NOT EXISTS idx_complaints_location ON complaints(location_id);
CREATE INDEX IF NOT EXISTS idx_complaints_status ON complaints(status);
CREATE INDEX IF NOT EXISTS idx_complaints_created_at ON complaints(created_at);
CREATE INDEX IF NOT EXISTS idx_ai_analysis_complaint ON ai_analysis(complaint_id);
CREATE INDEX IF NOT EXISTS idx_history_complaint ON complaint_history(complaint_id);
