CREATE TABLE users (
    id CHAR(36) NOT NULL PRIMARY KEY DEFAULT (UUID()),
    name VARCHAR(60) NOT NULL,
    username VARCHAR(30) NOT NULL UNIQUE,
    password VARCHAR(30) NOT NULL,
    grade DECIMAL(3, 1) NOT NULL DEFAULT 0.0,
    absences INT NOT NULL DEFAULT 0,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_accessed_at DATETIME NULL,
    CONSTRAINT check_grade_range CHECK (grade >= 0.0 AND grade <= 10.0),
    CONSTRAINT check_absences_not_negative CHECK (absences >= 0)
);
