CREATE TABLE Item
(
  Serial_number INT NOT NULL AUTO_INCREMENT,
  Name VARCHAR(50) NOT NULL,
  Price FLOAT NOT NULL,
  Quantity INT NOT NULL,
  Description VARCHAR(200) NOT NULL,
  Image longblob NOT NULL,
  No_of_sold_items INT NOT NULL,
  Category VARCHAR(50) NOT NULL,
  PRIMARY KEY (Serial_number)
);


CREATE TABLE Customer
(
  Username VARCHAR(50) NOT NULL,
  Name VARCHAR(50) NOT NULL,
  Password VARCHAR(50) NOT NULL,
  Credit_card INT NOT NULL,
  Cash_balance FLOAT NOT NULL,
  PRIMARY KEY (Username)
  
);

CREATE TABLE Admin
( 
  Username VARCHAR(50) NOT NULL,
  Name VARCHAR(50) NOT NULL,
  Password VARCHAR(50) NOT NULL,
  PRIMARY KEY (Username)
);

CREATE TABLE Customer_Address
(
  Address VARCHAR(150) NOT NULL,
  Username VARCHAR(50) NOT NULL,
  PRIMARY KEY (Address, Username),
  FOREIGN KEY (Username) REFERENCES Customer(Username)
  ON UPDATE CASCADE
  ON DELETE CASCADE
);

CREATE TABLE Customer_Phone
(
  Phone VARCHAR(20) NOT NULL,
  Username VARCHAR(50) NOT NULL,
  PRIMARY KEY (Phone, Username),
  FOREIGN KEY (Username) REFERENCES Customer(Username)
  ON UPDATE CASCADE
  ON DELETE CASCADE
);

CREATE TABLE Order_
(
  Order_id INT NOT NULL,
  Order_Date DATE NOT NULL,
  Delivery_Date DATE NOT NULL,
  Paid_amount FLOAT NOT NULL,
  Username VARCHAR(50),
  PRIMARY KEY (Order_id),
  FOREIGN KEY (Username) REFERENCES Customer(Username)
  ON UPDATE CASCADE
  ON DELETE RESTRICT
);

CREATE TABLE contain
(
  Sold_quantity INT NOT NULL,
  Order_id INT NOT NULL AUTO_INCREMENT,
  Serial_number INT NOT NULL,
  PRIMARY KEY (Order_id, Serial_number),
  FOREIGN KEY (Order_id) REFERENCES Order_(Order_id)
  ON UPDATE CASCADE
  ON DELETE CASCADE,
  FOREIGN KEY (Serial_number) REFERENCES Item(Serial_number)
  ON UPDATE CASCADE
  ON DELETE CASCADE
  );