DROP TABLE IF EXISTS USERTABLE;  
CREATE TABLE USERTABLE (  
Id INT AUTO_INCREMENT PRIMARY KEY,
Email VARCHAR(50),  
Name varchar(100) not null,
Password VARCHAR(50) NOT NULL,  
Phone VARCHAR(8) NOT NULL,
City VARCHAR(100) NOT NULL,
State VARCHAR(100) not null,
Zip VARCHAR(100) not null,
AppUserRole varchar not null,
Locked boolean not null,
Enable Boolean not null
);  
DROP TABLE IF EXISTS TOKEN;  
CREATE TABLE TOKEN (  
Id INT AUTO_INCREMENT PRIMARY KEY, 
token varchar(100) not null,
confirmDate  date,  
expiredTime date NOT NULL,
userId VARCHAR(100) NOT NULL
);  
	