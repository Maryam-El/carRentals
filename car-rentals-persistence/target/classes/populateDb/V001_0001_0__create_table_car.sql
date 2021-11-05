-- /*==============================================================*/
-- /* Table : car                                              */
-- /*==============================================================*/
CREATE TABLE car (
  car_id VARCHAR(255)  PRIMARY KEY,
  client_id VARCHAR(255) NULL,
  rented BOOLEAN NOT NULL,
  model VARCHAR(255) NULL,
  registration_number VARCHAR(255) NOT NULL
);
