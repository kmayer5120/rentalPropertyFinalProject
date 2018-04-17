DROP TABLE Tenants; 
DROP TABLE Properties;
DROP TABLE RentedProperties;

CREATE TABLE Tenants
(
    tenantID INT NOT NULL GENERATED ALWAYS AS IDENTITY, 
    firstName varchar(50) NOT NULL, 
    lastName varchar(50) NOT NULL,
    PRIMARY KEY (tenantID)
);

CREATE TABLE Properties
(
    propertyID varchar(50) NOT NULL,
    propertyAddress varchar(255) NOT NULL,
    propertyDescription varchar(255) NOT NULL,
    isAvailable char(1) NOT NULL,
    isLate char(1), NOT NULL,
    isEvicted char(1), NOT NULL,
    isPaid char(1), NOT NULL,
    leaseTerm INT NOT NULL,
    rentalFee float(2)
    moveInDate date,
    PRIMARY KEY (propertyID)
);

CREATE TABLE RentedProperties
(
    propertyID INT NOT NULL,
    tenantID INT NOT NULL,
    FOREIGN KEY (propertyID) REFERENCES Properties (propertyID),
    FOREIGN KEY (tenantID) REFERENCES Tenants (tenantID)
);

CREATE TABLE People
(
    personID INT NOT NULL,
    tenantID INT NOT NULL,
    firstName varchar(50) NOT NULL, 
    lastName varchar(50) NOT NULL,
    age INT NOT NULL,
    PRIMARY KEY (personID),
    FOREIGN KEY (tenantID) REFERENCES Tenants (tenantID)
);

INSERT INTO Tenants(firstName, lastName)
VALUES
    ('Kyle', 'Mayer');

INSERT INTO Properties(propertyID, propertyAddress, propertyDescription, isAvailable, 
            isLate, isEvicted, isPaid, leaseTerm, rentalFee, moveInDate)
        VALUES('SABQ123', '123 Street St.', 'Has chairs', 'T', 'F'. 'F', 'T'
                3, 3.14, 2018-12-20);

INSERT INTO People(personID, tenantID, firstName, lastName, age)
        VALUES(1, 1, 'Daniel', 'Garcia', 26);


