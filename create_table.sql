CREATE TABLE IF NOT EXISTS users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    pseudo VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    user_type ENUM('user', 'volunteer', 'moderator') NOT NULL
);

-- Création de la table mission
CREATE TABLE IF NOT EXISTS missions (
    mission_id INT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    user_id INT,
    mission_state ENUM('waiting', 'valid', 'realized', 'refused') NOT NULL,
    motif VARCHAR(255) DEFAULT '',
    mission_type ENUM('help', 'need_help') NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Création de la table connection
CREATE TABLE IF NOT EXISTS connections (
    connection_id INT AUTO_INCREMENT PRIMARY KEY,
    mission_id INT,
    user_id INT,
    connection_type ENUM('help', 'need_help') NOT NULL,
    FOREIGN KEY (mission_id) REFERENCES missions(mission_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);