DROP TABLE IF EXISTS subjects;

CREATE TABLE subjects (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    p1 REAL NOT NULL CHECK (p1 >= 0 AND p1 <= 10) DEFAULT 0.0,
    p2 REAL NOT NULL CHECK (p2 >= 0 AND p2 <= 10) DEFAULT 0.0,
    t1 REAL NOT NULL CHECK (t1 >= 0 AND t1 <= 10) DEFAULT 0.0,
    p3 REAL NOT NULL CHECK (p3 >= 0 AND p3 <= 10) DEFAULT 0.0,
    p4 REAL NOT NULL CHECK (p4 >= 0 AND p4 <= 10) DEFAULT 0.0,
    t2 REAL NOT NULL CHECK (t2 >= 0 AND t2 <= 10) DEFAULT 0.0
);

INSERT INTO subjects (name) VALUES ('Materia 1'), ('Materia 2'), ('Materia 3'), ('Materia 4');