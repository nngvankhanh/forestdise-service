CREATE TABLE IF NOT EXISTS VIDEO (
                       ID INT AUTO_INCREMENT PRIMARY KEY,
                       VIDEO_PATH VARCHAR(255),
                       VARIANT_ID INT,
                       STORE_CATEGORY_ID INT,
                       FOREIGN KEY (VARIANT_ID) REFERENCES VARIANT (ID),
                       FOREIGN KEY (STORE_CATEGORY_ID) REFERENCES STORE_CATEGORY (ID)

    );