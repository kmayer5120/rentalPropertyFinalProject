
CREATE TABLE Tenants
(
    tenantID INT NOT NULL,
    firstName varchar(50) NOT NULL,
    lastName varchar(50) NOT NULL,
    emailAddress varchar(255) NOT NULL,
    billingAddress varchar(255) NOT NULL,
    PRIMARY KEY(tenantID)
);

CREATE TABLE Properties
(
    propertyID varchar(50) NOT NULL,
    propertyAddress varchar(255) NOT NULL,
    propertyDescription varchar(255) NOT NULL,
    available varchar (5) NOT NULL,
    rentalFee float(2)
    moveInDate date,
    leaseTerm INT NOT NULL,
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
