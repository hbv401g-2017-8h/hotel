DROP TABLE IF EXISTS Room;
DROP TABLE IF EXISTS Amenities;
DROP TABLE IF EXISTS Hotel;
DROP TABLE IF EXISTS Guest;
DROP TABLE IF EXISTS BookingNight;

CREATE TABLE Hotel(
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  name VARCHAR(255) COLLATE NOCASE,
  streetAddress VARCHAR(255) COLLATE NOCASE,
  city VARCHAR(255) COLLATE NOCASE,
  postalCode VARCHAR(255),
  country VARCHAR(255) COLLATE NOCASE,
  starCount INTEGER
);

CREATE TABLE Room(
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  hotelId INTEGER,
  numSingleBeds INTEGER,
  numDoubleBeds INTEGER,
  bathroom BOOLEAN,
  costPerNight INTEGER,
  FOREIGN KEY(hotelId) REFERENCES Hotel(id)
);

CREATE TABLE Guest(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(255) COLLATE NOCASE,
    email VARCHAR(255) COLLATE NOCASE,
    phoneNumber VARCHAR(255) COLLATE NOCASE,
    numberOfAdults INTEGER,
    numberOfChildren INTEGER
);

CREATE TABLE BookingNight(
  roomId INTEGER NOT NULL,
  guestId INTEGER NOT NULL,
  date DATE NOT NULL,
  PRIMARY KEY(roomId, date)
  FOREIGN KEY(roomId) REFERENCES Room(id),
  FOREIGN KEY(guestId) REFERENCES Guest(id)
);

CREATE TABLE Amenities(
    amenity VARCHAR(255) COLLATE NOCASE,
    hotelId INTEGER,
    PRIMARY KEY(amenity, hotelId),
    FOREIGN KEY(hotelId) REFERENCES Hotel(id)
);

INSERT INTO Hotel VALUES(
  0001,
  'Hótel Saga',
  'Hagatorg',
  'Reykjavík',
  '107',
  'Ísland',
  4
);

INSERT INTO Hotel VALUES(
  0002,
  'Grand hótel Reykjavík',
  'Sigtún 38',
  'Reykjavík',
  '105',
  'Ísland',
  4
);

INSERT INTO Hotel VALUES(
  0003,
  'Stracta Hótel',
  'Rangárflötum 4',
  'Hellu',
  '850',
  'Ísland',
  3
);

INSERT INTO Hotel VALUES(
  0004,
  'Hilton Reykjavík Nordica',
  'Suðurlandsbraut 2',
  'Reykjavík',
  '108',
  'Ísland',
  4
);

INSERT INTO Hotel VALUES(
  0005,
  'Hótel Glymur',
  'Hvalfirði',
  'Akranesi',
  '301',
  'Ísland',
  2
);

INSERT INTO Hotel VALUES(
  0006,
  'Hótel Akureyri',
  'Hafnarstræti 67',
  'Akureyri',
  '600',
  'Ísland',
  3
);

INSERT INTO Hotel VALUES(
  0007,
  'Hótel Höfn',
  'Víkurbraut 20',
  'Höfn í Hornafirði',
  '780',
  'Ísland',
  4
);

INSERT INTO Room VALUES(
  1,
  0001,
  1,
  0,
  0,
  10000
);

INSERT INTO Room VALUES(
  2,
  0001,
  1,
  0,
  1,
  13000
);

INSERT INTO Room VALUES(
  3,
  0001,
  2,
  0,
  1,
  15000
);

INSERT INTO Room VALUES(
  4,
  0001,
  4,
  0,
  0,
  15000
);

INSERT INTO Room VALUES(
  5,
  0001,
  0,
  1,
  1,
  17000
);

INSERT INTO Room VALUES(
  6,
  0002,
  1,
  0,
  0,
  10000
);

INSERT INTO Room VALUES(
  7,
  0002,
  1,
  0,
  1,
  13000
);

INSERT INTO Room VALUES(
  8,
  0002,
  2,
  0,
  1,
  15000
);

INSERT INTO Room VALUES(
  9,
  0002,
  4,
  0,
  0,
  15000
);

INSERT INTO Room VALUES(
  10,
  0002,
  0,
  1,
  1,
  17000
);

INSERT INTO Room VALUES(
  11,
  0003,
  1,
  0,
  0,
  10000
);

INSERT INTO Room VALUES(
  12,
  0003,
  1,
  0,
  1,
  13000
);

INSERT INTO Room VALUES(
  13,
  0003,
  2,
  0,
  1,
  15000
);

INSERT INTO Room VALUES(
  14,
  0003,
  4,
  0,
  0,
  15000
);

INSERT INTO Room VALUES(
  15,
  0003,
  0,
  1,
  1,
  17000
);

INSERT INTO Room VALUES(
  16,
  0004,
  1,
  0,
  0,
  10000
);

INSERT INTO Room VALUES(
  17,
  0004,
  1,
  0,
  1,
  13000
);

INSERT INTO Room VALUES(
  18,
  0004,
  2,
  0,
  1,
  15000
);

INSERT INTO Room VALUES(
  19,
  0004,
  4,
  0,
  0,
  15000
);

INSERT INTO Room VALUES(
  20,
  0004,
  0,
  1,
  1,
  17000
);

INSERT INTO Room VALUES(
  21,
  0005,
  1,
  0,
  0,
  10000
);

INSERT INTO Room VALUES(
  22,
  0005,
  1,
  0,
  1,
  13000
);

INSERT INTO Room VALUES(
  23,
  0005,
  2,
  0,
  1,
  15000
);

INSERT INTO Room VALUES(
  24,
  0005,
  4,
  0,
  0,
  15000
);

INSERT INTO Room VALUES(
  25,
  0005,
  0,
  1,
  1,
  17000
);

INSERT INTO Room VALUES(
  26,
  0006,
  1,
  0,
  0,
  10000
);

INSERT INTO Room VALUES(
  27,
  0006,
  1,
  0,
  1,
  13000
);

INSERT INTO Room VALUES(
  28,
  0006,
  2,
  0,
  1,
  15000
);

INSERT INTO Room VALUES(
  29,
  0006,
  4,
  0,
  0,
  15000
);

INSERT INTO Room VALUES(
  30,
  0006,
  0,
  1,
  1,
  17000
);

INSERT INTO Room VALUES(
  31,
  0007,
  1,
  0,
  0,
  10000
);

INSERT INTO Room VALUES(
  32,
  0007,
  1,
  0,
  1,
  13000
);

INSERT INTO Room VALUES(
  33,
  0007,
  2,
  0,
  1,
  15000
);

INSERT INTO Room VALUES(
  34,
  0007,
  4,
  0,
  0,
  15000
);

INSERT INTO Room VALUES(
  35,
  0007,
  0,
  1,
  1,
  17000
);

INSERT INTO Guest VALUES(
	1,
	'Jón Jónsson',
	'jon@jon.is',
	'5540122',
	2,
	0
);

INSERT INTO BookingNight VALUES(
	10,
	1,
	'2017-05-01'
);

INSERT INTO Amenities VALUES(
	'Wifi',
	0001
);

INSERT INTO Amenities VALUES(
	'Wifi',
	0002
);

INSERT INTO Amenities VALUES(
	'Wifi',
	0003
);

INSERT INTO Amenities VALUES(
	'Wifi',
	0004
);

INSERT INTO Amenities VALUES(
	'Wifi',
	0006
);

INSERT INTO Amenities VALUES(
	'Wifi',
	0007
);

INSERT INTO Amenities VALUES(
	'Continental breakfast',
	0001
);

INSERT INTO Amenities VALUES(
	'Continental breakfast',
	0002
);

INSERT INTO Amenities VALUES(
	'Continental breakfast',
	0004
);

INSERT INTO Amenities VALUES(
	'Continental breakfast',
	0005
);

INSERT INTO Amenities VALUES(
	'Continental breakfast',
	0007
);

INSERT INTO Amenities VALUES(
	'Cable TV',
	0004
);

INSERT INTO Amenities VALUES(
	'Cable TV',
	0002
);

INSERT INTO Amenities VALUES(
	'Cable TV',
	0007
);

INSERT INTO Amenities VALUES(
	'Room service',
	0001
);

INSERT INTO Amenities VALUES(
	'Room service',
	0002
);

INSERT INTO Amenities VALUES(
	'Room service',
	0004
);

INSERT INTO Amenities VALUES(
	'Room service',
	0005
);

INSERT INTO Amenities VALUES(
	'Room service',
	0006
);

INSERT INTO Amenities VALUES(
	'Room service',
	0007
);

INSERT INTO Amenities VALUES(
	'24 hour service desk',
	0001
);

INSERT INTO Amenities VALUES(
	'24 hour service desk',
	0003
);

INSERT INTO Amenities VALUES(
	'24 hour service desk',
	0004
);

INSERT INTO Amenities VALUES(
	'24 hour service desk',
	0006
);

INSERT INTO Amenities VALUES(
	'24 hour service desk',
	0007
);
