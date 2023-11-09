CREATE TABLE IF NOT EXISTS optionvalue (
                             ID INT AUTO_INCREMENT PRIMARY KEY,
                             VALUE VARCHAR(255),
                             OPTION_ID INT,
                             FOREIGN KEY (OPTION_ID) REFERENCES option_table(ID)


);