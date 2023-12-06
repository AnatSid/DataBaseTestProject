CREATE TABLE City
(
	id SERIAL PRIMARY KEY,
	name VARCHAR(255) NOT NULL
)
INSERT INTO City (name)
VALUES
('Gomel'),
('Minsk'),
('Mogilev'),
('Vitebsk'),
('Grodno'),
('Brest');
-----------------------------------------------------------
CREATE TABLE Athletes
(
    id SERIAL PRIMARY KEY,
	first_name VARCHAR(255) NOT NULL,
	surname VARCHAR(255) NOT NULL,
	date_birth INTEGER NOT NULL,
	city_id INTEGER,
	FOREIGN KEY (city_id) REFERENCES City(id)
)

INSERT INTO Athletes (first_name,surname,date_birth,city_id)
VALUES
('Ivan','Ivanov', 1995, 1),
('Semen','Semenov', 1989, 2),
('Egor','Egorov', 2001, 3),
('Petr','Petrov', 1994, 1),
('Alexandr','Alexanrov', 1999, 4),
('Makar','Makarenko', 1995, 5),
('Vlad','Topalov', 1990, 2);

-----------------------------------------------------------------------------------

CREATE TABLE Race
(
	id SERIAL PRIMARY KEY,
	style VARCHAR(255) NOT NULL,
	distance INTEGER NOT NULL
)

INSERT INTO Race (style,distance)
VALUES
('Butterfly',50),
('Butterfly',100),
('Butterfly',200),
('Freestyle ',50),
('Freestyle ',100),
('Freestyle ',200),
('Freestyle ',400);
-----------------------------------------------------------------

CREATE TABLE Competitions
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    city_id INTEGER NOT NULL,
	FOREIGN KEY (city_id) REFERENCES City(id)
);

INSERT INTO Competitions (name, date, city_id)
VALUES
('Swimming Championship', '2023-08-15', 2),
('Aquatic Games', '2022-09-20', 1),
('Summer Swim Fest', '2021-07-10', 5),
('Golden Finale', '2020-11-05', 2),
('Sprint Showdown', '2019-06-25', 4),
('Winter Swim Cup', '2022-01-25', 6);

----------------------------------------------------------------------------------------

CREATE TABLE Race_result
(
	id_race INTEGER,
	id_athlete INTEGER,
	result_time INTERVAL,
	id_competition INTEGER,
	points INTEGER,
	FOREIGN KEY (id_race) REFERENCES Race(id),
	FOREIGN KEY (id_athlete) REFERENCES Athletes(id),
	FOREIGN KEY (id_competition) REFERENCES Competitions(id)
)

INSERT INTO Race_result (id_race,id_athlete,result_time,id_competition,points)
VALUES
(4,1,'00:00:22.50',1,950),
(4,2,'00:00:24.50',1,810),
(4,3,'00:00:25.50',1,720),
(4,4,'00:00:22.55',1,945),
(4,5,'00:00:23.23',1,880),
(4,6,'00:00:23.65',1,870),
(4,7,'00:00:22.90',1,920);

INSERT INTO Race_result (id_race,id_athlete,result_time,id_competition,points)
VALUES
(1,1,'00:00:23.50',1,850),
(1,2,'00:00:25.50',1,710),
(1,3,'00:00:26.50',1,620),
(1,4,'00:00:23.55',1,845),
(1,5,'00:00:24.23',1,780),
(1,6,'00:00:24.65',1,770),
(1,7,'00:00:23.90',1,820);

INSERT INTO RaceResult (id_race,id_athlete,result_time,id_competition,points)
VALUES
(5,1,'00:01:23.50',1,750),
(5,2,'00:01:25.50',1,610),
(5,3,'00:01:26.50',1,520),
(5,4,'00:01:23.55',1,545),
(5,5,'00:01:24.23',1,680),
(5,6,'00:01:24.65',1,670),
(5,7,'00:01:23.90',1,720);

-- (другие соревнований, id_competition = 2)
INSERT INTO Race_result (id_race,id_athlete,result_time,id_competition,points)
VALUES
(4,1,'00:00:22.55',2,960),
(4,2,'00:00:24.56',2,820),
(4,3,'00:00:25.33',2,730),
(4,4,'00:00:22.56',2,955),
(4,5,'00:00:23.54',2,890),
(4,6,'00:00:23.87',2,880),
(4,7,'00:00:22.95',2,930);

INSERT INTO Race_result (id_race,id_athlete,result_time,id_competition,points)
VALUES
(1,1,'00:00:22.55',2,960),
(1,2,'00:00:24.56',2,820),
(1,3,'00:00:25.33',2,730),
(1,4,'00:00:22.56',2,955),
(1,5,'00:00:23.54',2,890),
(1,6,'00:00:23.87',2,880),
(1,7,'00:00:22.95',2,930);


