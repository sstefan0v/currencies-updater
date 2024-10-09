CREATE TABLE currencies
(
    id           SERIAL PRIMARY KEY,
    gold         INTEGER,
    code         VARCHAR(3) NOT NULL,
    ratio        INTEGER,
    reverse_rate DOUBLE PRECISION,
    rate         DOUBLE PRECISION,
    f_star       INTEGER,
    curr_date    DATE
);

CREATE TABLE languages
(
    id           SERIAL PRIMARY KEY,
    gold         INTEGER,
    lang_code    VARCHAR(2)  NOT NULL,
    name         VARCHAR(50) NOT NULL,
    code         VARCHAR(10) NOT NULL,
    ratio        VARCHAR(50),
    reverse_rate VARCHAR(50),
    rate         VARCHAR(50),
    extra_info   TEXT,
    curr_date    VARCHAR(10),
    title        TEXT,
    f_star       INTEGER
);

CREATE TABLE languages_currencies
(
    currency_id BIGINT NOT NULL,
    language_id BIGINT NOT NULL,
    PRIMARY KEY (currency_id, language_id),
    FOREIGN KEY (currency_id) REFERENCES currencies (id) ON DELETE CASCADE,
    FOREIGN KEY (language_id) REFERENCES languages (id) ON DELETE CASCADE
);

CREATE TABLE currencies_2 AS TABLE currencies;
CREATE TABLE languages_2 AS TABLE languages;