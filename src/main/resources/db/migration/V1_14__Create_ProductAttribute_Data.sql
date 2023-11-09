CREATE TABLE IF NOT EXISTS productattribute (
                                  ID INT AUTO_INCREMENT PRIMARY KEY,
                                  NAME VARCHAR(255),
                                  VALUE VARCHAR(255),
                                  PRODUCT_ID INT,
                                  FOREIGN KEY (PRODUCT_ID) REFERENCES product(ID)
);