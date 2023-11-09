CREATE TABLE IF NOT EXISTS video (
                       ID INT AUTO_INCREMENT PRIMARY KEY,
                       VIDEO_PATH VARCHAR(255),
                       VARIANT_ID INT,
                       STORE_CATEGORY_ID INT,
                       FOREIGN KEY (VARIANT_ID) REFERENCES variant (ID),
                       FOREIGN KEY (STORE_CATEGORY_ID) REFERENCES store_category (ID)

    );