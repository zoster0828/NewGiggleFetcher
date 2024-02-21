CREATE TABLE Articles (
    id VARCHAR(64) NOT NULL,
    title VARCHAR(128) NOT NULL,
    url VARCHAR(256) NOT NULL,
    site VARCHAR(32) NOT NULL,
    likes BIGINT,
    views BIGINT,
    sourceLikes BIGINT,
    sourceViews BIGINT,
    sourceDate DATETIME,
    thumbnailUrl VARCHAR(256),
    PRIMARY KEY (id),
    INDEX (sourceDate)
);