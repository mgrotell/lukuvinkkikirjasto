CREATE TABLE Tips (
	id SERIAL PRIMARY KEY,
    header TEXT,
    description TEXT,
	creator TEXT,
    url TEXT,
    tags TEXT,
    type TEXT,
    course_id INTEGER REFERENCES Courses ON DELETE CASCADE,
    comment TEXT
);
CREATE TABLE Courses (
	id SERIAL PRIMARY KEY,
	name TEXT,
	url TEXT
);
