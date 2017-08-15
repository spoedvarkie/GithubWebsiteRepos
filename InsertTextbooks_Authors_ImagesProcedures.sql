DELIMITER $$

CREATE PROCEDURE InsertNewAuthor(
author_initials varchar(45),
author_surname VARCHAR(45))
BEGIN
	INSERT INTO `mydb`.`author`
(`AUTHOR_ID`,
`AUTHOR_INITIALS`,
`AUTHOR_L_NAME`)
VALUES
(null,
author_initials,
author_surname);
END;

DELIMITER $$

CREATE PROCEDURE InsertNewTextbook(
username varchar(45),
title VARCHAR(45),
edition varchar(45),
isbn varchar(45),
price decimal(9,2),
comments mediumtext)
BEGIN
INSERT INTO `mydb`.`textbook`
(`TEXTBOOK_ID`,
`TEXTBOOK_TITLE`,
`TEXTBOOK_EDITION`,
`TEXTBOOK_ISBN`,
`TEXTBOOK_PRICE`,
`TEXTBOOK_COMMENTS`,
`USER_ID`)
VALUES
(null,
title,
edition,
isbn,
price,
comments,
(SELECT U.USER_ID FROM mydb.USER U WHERE U.USERNAME = username));
END;

DELIMITER $$

CREATE PROCEDURE InsertNewTextbook_Author()
BEGIN
	INSERT INTO `mydb`.`textbook_author`
(`TEXTBOOK_AUTHOR_ID`,
`TEXTBOOK_ID`,
`AUTHOR_ID`)
VALUES
(null,
(SELECT MAX(TEXTBOOK_ID) FROM mydb.TEXTBOOK),
(SELECT MAX(AUTHOR_ID) FROM mydb.AUTHOR));
END;

DELIMITER $$

CREATE PROCEDURE InsertNewImage(
image longblob)
BEGIN
INSERT INTO `mydb`.`image`
(`IMAGE_ID`,
`IMAGE_FILE`,
`TEXTBOOK_ID`)
VALUES
(null,
image,
(SELECT MAX(TEXTBOOK_ID) FROM mydb.TEXTBOOK));
END;

