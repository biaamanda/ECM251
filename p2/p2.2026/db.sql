CREATE TABLE subject (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    grade_test_1 FLOAT CHECK (grade_test_1 BETWEEN 0 AND 10),
    grade_test_2 FLOAT CHECK (grade_test_2 BETWEEN 0 AND 10),
    grade_test_3 FLOAT CHECK (grade_test_3 BETWEEN 0 AND 10),
    grade_test_4 FLOAT CHECK (grade_test_4 BETWEEN 0 AND 10),

    grade_assignment_1 FLOAT CHECK (grade_assignment_1 BETWEEN 0 AND 10),
    grade_assignment_2 FLOAT CHECK (grade_assignment_2 BETWEEN 0 AND 10),

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE my_grades (
    id INT PRIMARY KEY,
    subject_id INT,
    FOREIGN KEY (subject_id) REFERENCES subject(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;