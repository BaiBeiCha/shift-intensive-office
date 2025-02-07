CREATE TABLE IF NOT EXISTS departments
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS employees
(
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(255) NOT NULL ,
    salary        NUMERIC(10, 2) DEFAULT 0.0,
    isManager       BOOLEAN DEFAULT FALSE,
    department_id BIGINT NOT NULL ,
    FOREIGN KEY (department_id) REFERENCES departments (id)
);