DROP DATABASE IF EXISTS eshop;
CREATE DATABASE eshop;
USE eshop;

CREATE TABLE users (UserID INT NOT NULL AUTO_INCREMENT, UserFullName VARCHAR(100), UserEmail VARCHAR(50) NOT NULL, UserPassword VARCHAR(130) NOT NULL, UserType VARCHAR(15) NOT NULL, PRIMARY KEY (UserID));

CREATE TABLE products (ProductID INT NOT NULL AUTO_INCREMENT, ProductName VARCHAR(50), ProductCategory VARCHAR(50), ProductPrice FLOAT(2), PRIMARY KEY (ProductID));

ALTER TABLE users AUTO_INCREMENT = 45;
ALTER TABLE products AUTO_INCREMENT = 1010; 

INSERT INTO users (UserFullName, UserEmail, UserPassword, UserType) VALUES ("Test", "test@test.com", md5(12345), "Individual");

INSERT INTO products (ProductName, ProductCategory, ProductPrice) VALUES ("Mouse Pad", "Accessories", 9.50);
INSERT INTO products (ProductName, ProductCategory, ProductPrice) VALUES ("Headphone Stand", "Accessories", 33.80);
INSERT INTO products (ProductName, ProductCategory, ProductPrice) VALUES ("Pencil", "Consumables", 0.99);
INSERT INTO products (ProductName, ProductCategory, ProductPrice) VALUES ("A4 Paper", "Consumables", 0.99);
INSERT INTO products (ProductName, ProductCategory, ProductPrice) VALUES ("Laptop", "Electronics", 499.99);
INSERT INTO products (ProductName, ProductCategory, ProductPrice) VALUES ("Monitor", "Electronics", 155.00);
INSERT INTO products (ProductName, ProductCategory, ProductPrice) VALUES ("Headphones", "Peripherals", 15.00);
INSERT INTO products (ProductName, ProductCategory, ProductPrice) VALUES ("USB Stick", "Peripherals", 14.35);
INSERT INTO products (ProductName, ProductCategory, ProductPrice) VALUES ("Power Cable", "Peripherals", 1.00);
