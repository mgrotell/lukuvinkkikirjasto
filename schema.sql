CREATE TABLE IF NOT EXISTS tips (
    id INTEGER PRIMARY KEY,
    header TEXT UNIQUE,
    description TEXT,
    creator TEXT,
    url url,
    tags TEXT,
    type TEXT,
    courses TEXT,
    comment TEXT);