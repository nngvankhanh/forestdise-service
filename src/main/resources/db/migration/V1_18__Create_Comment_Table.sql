CREATE TABLE IF NOT EXISTS comment (
    ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    CONTENT TEXT NOT NULL,
    REVIEW_ID INT NOT NULL,
    SELLER_ID INT NOT NULL,
    FOREIGN KEY (REVIEW_ID) REFERENCES review(ID),
    FOREIGN KEY (SELLER_ID) REFERENCES seller(ID)
    )