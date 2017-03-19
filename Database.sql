CREATE TABLE Hotel(
  id INTEGER PRIMARY KEY,
  name VARCHAR(255),
  streetAddress VARCHAR(255),
  city VARCHAR(255),
  postalCode VARCHAR(255),
  country VARCHAR(255),
  starCount INT
);

CREATE TABLE Room(
  id int PRIMARY KEY,
  hotelId int,
  numSingleBeds int,
  numDoubleBeds int,
  bathroom boolean,
  costPerNight int,
  FOREIGN KEY(hotelId) REFERENCES Hotel(id)
);

INSERT INTO Hotel VALUES(
  666,
  'Hótel Saga',
  'Hagatorg',
  'Reykjavík',
  '107',
  'Ísland',
  4
);

INSERT INTO Room VALUES(
  101,
  666,
  0,
  1,
  1,
  10000
);
