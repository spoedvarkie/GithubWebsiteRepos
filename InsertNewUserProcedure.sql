DELIMITER $$

CREATE PROCEDURE InsertNewUser(
firstName varchar(45),
lastName varchar(45),
username varchar(45),
userPassword varchar(45),
contactNum varchar(10),
email varchar(45))
BEGIN
INSERT INTO `mydb`.`user`
(`USER_ID`,
`FIRST_NAME`,
`LAST_NAME`,
`CONTACT_NUM`,
`EMAIL`,
`USERNAME`,
`U_PASSWORD`)
VALUES
(null,
firstName,
lastName,
contactNum,
email,
username,
userPassword);
END;