CREATE TABLE IF NOT EXISTS products (
	id INTEGER IDENTITY NOT NULL,
	name VARCHAR(256) NOT NULL,
	CONSTRAINT pk_roles PRIMARY KEY (id)
);