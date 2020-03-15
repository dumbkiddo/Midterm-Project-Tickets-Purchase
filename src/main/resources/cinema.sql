CREATE TABLE IF NOT EXISTS movies  (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    morning time,
    afternoon time,
    evening time
);

CREATE TABLE IF NOT EXISTS tickets  (
    id INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(255),
    morningPrice int,
    afternoonPrice int,
    eveningPrice int
);

CREATE TABLE IF NOT EXISTS purchases  (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ticketAmount int,
    total int
);

INSERT INTO movies VALUES
(1, 'Onward', '10:00','13:30','17:00'),
(2, 'Uncut gems', '11:30','15:00','18:10' ),
(3, 'The way back', '9:30','14:20','19:00' ),
(4, 'Only yesterday', '10:00','12:50','17:40'),
(5, 'The Stranger', '9:00','13:00','19:10');

INSERT INTO tickets VALUES
(1, 'Adult', 900, 1200, 1500),
(2, 'Student', 700, 1000, 1300),
(3, 'Child', 500, 800, 1100 );


