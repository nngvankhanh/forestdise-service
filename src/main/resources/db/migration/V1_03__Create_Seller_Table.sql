CREATE TABLE IF NOT EXISTS seller (
    ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    SELLER_NAME VARCHAR(50) NOT NULL,
    EMAIL VARCHAR(100) NOT NULL,
    PASSWORD VARCHAR(400) NOT NULL,
    BALANCE DOUBLE,
    ROLE VARCHAR(50)
)