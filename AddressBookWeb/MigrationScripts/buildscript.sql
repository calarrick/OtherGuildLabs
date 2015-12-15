CREATE TABLE Addresses(
	address_id INT NOT NULL auto_increment,
	last_name varchar(255) NOT NULL,
	street_num varchar(255) NOT NULL,
	street_name varchar(255) NOT NULL,
	apt_num varchar(255) NOT NULL,
	city_name varchar(255) NOT NULL,
	state_name varchar(255) NOT NULL,
	zip_code varchar(15) NOT NULL,
	PRIMARY KEY(address_id) 

)ENGINE = InnoDB auto_increment 100;

CREATE TABLE First_names(
	f_name_id INT NOT NULL auto_increment,
	first_name varchar(255) NOT NULL,
	address_id INT NOT NULL,
	PRIMARY KEY(f_name_id),
CONSTRAINT fk_fname_address_id FOREIGN KEY (address_id)
	REFERENCES Addresses(address_id)

)ENGINE = InnoDB auto_increment 1;
	
CREATE TABLE Phone_numbers(
		address_id INT NOT NULL auto_increment,
		phone_num varchar(20) NOT NULL,
CONSTRAINT fk_phonenum_address_id FOREIGN KEY (address_id)
	REFERENCES Addresses(address_id)

)ENGINE = InnoDB;

CREATE TABLE Email_addresses(
	email_id INT NOT NULL auto_increment,
	email_address varchar(255) NOT NULL,
	address_id INT NOT NULL,
	PRIMARY KEY(email_id),
	CONSTRAINT fk_email_address_id FOREIGN KEY (address_id)
		REFERENCES Addresses(address_id)

)ENGINE = InnoDB;

CREATE INDEX l_name_index
ON Addresses(last_name);

CREATE INDEX c_name_index
ON Addresses(city_name);

CREATE INDEX z_code_index
ON Addresses(zip_code);

