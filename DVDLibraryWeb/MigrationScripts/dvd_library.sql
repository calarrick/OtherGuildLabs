CREATE TABLE IF NOT EXISTS Dvds
(
dvd_id INT(11) NOT NULL auto_increment,
title varchar(255) NOT NULL,
release_date DATE,
mpaa_rating varchar(5),
director varchar(100),
studio varchar(255),
PRIMARY KEY (dvd_id)
)ENGINE = InnoDB auto_increment 100;

CREATE TABLE IF NOT EXISTS dvd_notes
(
dvd_id INT(11) NOT NULL,
note_id INT(11) NOT NULL,
added_date DATE,
note TEXT NOT NULL
)
ENGINE = InnoDB;

ALTER TABLE dvd_notes
MODIFY note_id INT(11) NOT NULL auto_increment PRIMARY KEY;

ALTER TABLE dvd_notes
ADD CONSTRAINT fk_dvd_id FOREIGN KEY (dvd_id)
	REFERENCES Dvds(dvd_id);

CREATE INDEX title
ON Dvds(title);

CREATE INDEX director
ON Dvds(director);

ALTER TABLE Dvds
ADD column note TEXT;

  

