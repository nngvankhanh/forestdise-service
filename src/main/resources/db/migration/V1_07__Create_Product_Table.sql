CREATE TABLE IF NOT EXISTS product (
                         ID INT AUTO_INCREMENT NOT NULL PRIMARY KEY ,
                         TITLE VARCHAR(255),
                         DESCRIPTION TEXT,
                         MAIN_PICTURE VARCHAR(255),
                         STATUS VARCHAR(50),
                         CREATE_AT DATETIME,
                         UPDATED_AT DATETIME,
                         CATEGORY_ID INT,
                         STORE_CATEGORY_ID INT,
                         STORE_ID INT,
                         FOREIGN KEY (CATEGORY_ID) REFERENCES category (ID),
                         FOREIGN KEY (STORE_CATEGORY_ID) REFERENCES store_category (ID),
                         FOREIGN KEY (STORE_ID) REFERENCES store (ID)


    );