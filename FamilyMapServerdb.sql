

INSERT INTO Events (eventID, descendant, personID, latitude, longitude, country, city, eventtype, year)
                        VALUES ('hello', 'Descendant', 'jacob',
                        'Latitude', 'Longitude', 'Country() ',
                        'City', 'EventType', 'EventYear' );

select * from Events

update Events
SET eventID='updateEvent.getEventID() ',
descendant='updateEvent.getDescendant()',
personid='updateEvent.getPersonID()',
latitude=' updateEvent.getLatitude() ',
longitude=' updateEvent.getLongitude() ',
country=' updateEvent.getCountry() ',
city=' updateEvent.getCity() ',
eventtype=' updateEvent.getEventType() ',
year=' updateEvent.getEventYear() '
where eventID='hello';

"update Users\n" +
                    "SET username='" + user.getUsername() + "',"
                    + "password='" + user.getPassword() + "',"
                    + "email='" + user.getEmail() + "',"
                    + "first_name='" + user.getFirstName() + "',"
                    + "last_name='" + user.getLastName() + "',"
                    + "gender='" + user.getGender() + "',"
                    + "person_id='" + user.getPersonID() + " "
                    + "where personID='" + user.getPersonID() + "';";



DROP TABLE Persons
DROP TABLE Users
DROP TABLE AuthTokens
DROP TABLE Events
select * from User
CREATE TABLE Persons
(
	personID varchar(255) NOT NULL PRIMARY KEY,
	descendant varchar(255),
	firstname varchar(255) NOT NULL,
	lastname varchar(255) NOT NULL,
	gender varchar(1) NOT NULL,
	father varchar(255),
	mother varchar(255),
	spouse varchar(255),
	CONSTRAINT ck_gender CHECK (gender in ('m', 'm'))
);

CREATE TABLE Events
(
	eventID varchar(255) NOT NULL PRIMARY KEY UNIQUE,
	descendant varchar(255),
	personID varchar(255) NOT NULL,
	latitude real not null,
	longitude real not null,
	country varchar(255) NOT NULL,
	city varchar(255) NOT NULL,
	eventtype varchar(255) NOT NULL,
	year int NOT NULL,
	FOREIGN KEY(personID) REFERENCES Person(personID)
);


CREATE TABLE AuthTokens
(
	username varchar(255) NOT NULL,
	authtoken varchar(255) NOT NULL,
	currenttime DATETIME NOT NULL,
	FOREIGN KEY(username) REFERENCES User(username)
);

CREATE TABLE Users
(
	username varchar(255) NOT NULL UNIQUE,
	password varchar(255) NOT NULL,
	email varchar(255) NOT NULL UNIQUE,
 	firstname varchar(255) NOT NULL,
	lastname varchar(255) NOT NULL,
	gender varchar(1),
	personID varchar(255),
	CONSTRAINT ck_gender CHECK (gender in ('m', 'f')),
	FOREIGN KEY(personID) REFERENCES Person(personID)
);

select * from Users;

select * from Persons;
select * from Events;
select * from AuthTokens;

delete from AuthTokens where username='pjacobr';

insert into AuthTokens (username, authtoken, currenttime)values ('pjacobr','3434343','01:45:55');

select username, password, email, firstname, lastname, gender, personID  from Users;

insert into AuthTokens(username, authtoken, currenttime) values ('pjacobr','123',datetime('now','localtime'));

select username, authtoken, currenttime from AuthTokens

select * from AuthTokens where username='pjacobr'

insert into AuthTokens(username, authtoken, currenttime) values ('pjacobr','asdf',datetime('now','localtime'));
