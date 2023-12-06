---- Вывод итоговой таблицы (общий протокол) со всеми данными
SELECT
    r.distance || ' ' || r.style AS distance,
    ath.surname,
    ath.first_name,
    ath.date_birth,
    c.name,
    rr.result_time,
    comp.name,
    rr.points

FROM
	Race_result rr
JOIN
    Race r ON rr.id_race = r.id
JOIN
    Athletes ath ON rr.id_athlete = ath.id
JOIN
    Competitions comp ON rr.id_race = comp.id
JOIN
    City c ON ath.city_id = c.id
ORDER BY result_time


---+Топ 3 спортсмена, которые набрали больше всего очков на конкретных соревнованиях
SELECT
    a.first_name,
    a.surname,
    a.date_birth,
    SUM(rr.points) AS total_points
FROM
    Race_result rr
JOIN
    Athletes a ON rr.id_athlete = a.id
WHERE
    rr.id_competition = 1
GROUP BY
    a.id
ORDER BY
    total_points DESC
LIMIT 3;

----Cписок участников на конкретных соревнованиях, и на конкретной дистанции
SELECT ath.surname, ath.first_name, r.style,r.distance, comp.name AS Competition
FROM Race_result rr
JOIN Athletes ath ON rr.id_athlete = ath.id
JOIN Race r ON rr.id_race = r.id
JOIN Competitions comp ON rr.id_competition = comp.id
WHERE rr.id_competition = 1 AND rr.id_race = 4
GROUP BY ath.id,r.style,r.distance,comp.name

------список результатов ( ФИО + дистанция + результат)
SELECT
ath.surname,
ath.first_name,
r.style,
r.distance,
rr.result_time,
comp.name AS Competition
FROM
Race_result rr
JOIN
Athletes ath ON rr.id_athlete = ath.id
JOIN
Race r ON rr.id_race = r.id
JOIN
Competitions comp ON rr.id_competition = comp.id
WHERE
rr.id_competition = 1 AND rr.id_race = 4
GROUP BY
ath.id,r.style,r.distance, rr.result_time,comp.name
ORDER BY
result_time

---------+топ 3 города, которые набрали больше всего очков на конкретных соревнованиях
SELECT
    c.name AS city,
    SUM(rr.points) AS total_points
FROM
    Race_result rr
JOIN
    Athletes a ON a.id = rr.id_athlete
JOIN
    City c ON a.city_id = c.id
WHERE
    rr.id_competition = 1
GROUP BY
   c.id
ORDER BY
    total_points DESC
LIMIT 3;