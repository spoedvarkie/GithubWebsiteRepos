INSERT INTO `mydb`.`user`
(`USER_ID`,
`FIRST_NAME`,
`LAST_NAME`,
`CONTACT_NUM`,
`EMAIL`)
VALUES
(null,
'Leandri',
'du Toit',
'0821112233',
'ldt@gmail.com');

INSERT INTO `mydb`.`user`
(`USER_ID`,
`FIRST_NAME`,
`LAST_NAME`,
`CONTACT_NUM`,
`EMAIL`)
VALUES
(null,
'Christo',
'du Plessis',
'0834442233',
'cdp@gmail.com');

INSERT INTO `mydb`.`user`
(`USER_ID`,
`FIRST_NAME`,
`LAST_NAME`,
`CONTACT_NUM`,
`EMAIL`)
VALUES
(null,
'Salman',
'Dukanwala',
'0812342233',
'salman@gmail.com');

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
'Database Systems',
'12',
'9781305627482',
'900.00',
'Textbook still in excellent condition. No pen marks or damaged pages.',
'1');

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
'Elementary Statistical Methods',
'4',
'9765874523658',
'250.00',
'Textbook still in good condition. It has no pen marks, but has minor damages.',
'2');

INSERT INTO `mydb`.`author`
(`AUTHOR_ID`,
`AUTHOR_INITIALS`,
`AUTHOR_L_NAME`)
VALUES
(null,
'C',
'Coronel');

INSERT INTO `mydb`.`author`
(`AUTHOR_ID`,
`AUTHOR_INITIALS`,
`AUTHOR_L_NAME`)
VALUES
(null,
'L',
'Santana');

INSERT INTO `mydb`.`textbook_author`
(`TEXTBOOK_AUTHOR_ID`,
`TEXTBOOK_ID`,
`AUTHOR_ID`)
VALUES
(null,
'1',
'1');

INSERT INTO `mydb`.`textbook_author`
(`TEXTBOOK_AUTHOR_ID`,
`TEXTBOOK_ID`,
`AUTHOR_ID`)
VALUES
(null,
'2',
'2');
