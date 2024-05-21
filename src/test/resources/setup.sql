SET COLLATION ENGLISH STRENGTH PRIMARY;

DROP SCHEMA IF EXISTS `newro-factory-db` CASCADE;
CREATE SCHEMA IF NOT EXISTS `newro-factory-db`;
SET SCHEMA `newro-factory-db`;

CREATE USER IF NOT EXISTS "testnewro" PASSWORD 'Test123!';
GRANT ALL PRIVILEGES ON SCHEMA `newro-factory-db` TO "testnewro";

CREATE TABLE `newro-factory-db`.promotion
(
    id   IDENTITY NOT NULL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE `newro-factory-db`.intern
(
    id             IDENTITY NOT NULL PRIMARY KEY,
    first_name     VARCHAR(255),
    last_name      VARCHAR(255),
    arrival        TIMESTAMP NULL,
    formation_over TIMESTAMP NULL,
    promotion_id   BIGINT DEFAULT NULL,
    FOREIGN KEY (promotion_id) REFERENCES `newro-factory-db`.promotion (id)
        ON DELETE RESTRICT ON UPDATE RESTRICT
);
CREATE INDEX ix_intern_promotion_1 ON `newro-factory-db`.intern (promotion_id);

CREATE TABLE `newro-factory-db`.chapter
(
    id          INT          NOT NULL AUTO_INCREMENT,
    name        VARCHAR(100) NOT NULL,
    parent_path VARCHAR(500) NOT NULL DEFAULT '/',
    PRIMARY KEY (id)
);

CREATE TABLE `newro-factory-db`.question
(
    id         INT          NOT NULL AUTO_INCREMENT,
    title      VARCHAR(255) NOT NULL,
    statement  TEXT         NOT NULL,
    chapter_id INT          NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (chapter_id) REFERENCES `newro-factory-db`.chapter (id)
);

CREATE TABLE `newro-factory-db`.answer
(
    id           INT          NOT NULL AUTO_INCREMENT,
    label        VARCHAR(1)   NOT NULL,
    text         VARCHAR(255) NOT NULL,
    valid_answer BOOLEAN      NOT NULL,
    question_id  INT          NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (question_id) REFERENCES `newro-factory-db`.question (id)
);

INSERT INTO `newro-factory-db`.promotion (id, name)
VALUES  (1, 'Février 2011'),
        (2, 'Mars 2011'),
        (3, 'Avril 2011'),
        (4, 'Février 2012');
ALTER TABLE `newro-factory-db`.promotion ALTER COLUMN id RESTART WITH 5;

INSERT INTO `newro-factory-db`.intern (id, first_name, last_name, arrival, formation_over, promotion_id)
VALUES  (1, 'Abel', 'Boulle', null, null, 1),
        (2, 'Abélard', 'Grandjean', null, null, 1),
        (3, 'Abelin', 'Bourguignon', null, null, 1),
        (4, 'Abraham', 'Delannoy', null, null, 1),
        (5, 'Coucou', 'UwU', '2011-02-09', '2011-03-09', 1);
ALTER TABLE `newro-factory-db`.intern ALTER COLUMN id RESTART WITH 51;

INSERT INTO `newro-factory-db`.chapter (id, name, parent_path)
VALUES  (5, 'OCA', '/');
ALTER TABLE `newro-factory-db`.chapter ALTER COLUMN id RESTART WITH 6;

INSERT INTO `newro-factory-db`.question (id, title, statement, chapter_id)
VALUES  (364, 'cc', 'cc2', 5),
        (365, 'OCA Final Exam - 2', 'Fill in the blanks: The operators +=,__________ ,__________ ,__________ ,__________ , and ++ are listed in increasing or the same level of operator precedence. (Choose two.)', 5),
        (366, 'OCA Final Exam - 3', '<p>Which of the following are valid JavaBean signatures? (Choose three.)</p>', 5),
        (367, 'OCA Final Exam - 4', '<p>Which of the following are true? (Choose two.)&nbsp;</p><p>&nbsp;</p><pre><code class="language-java">20: int[] crossword [] = new int[10][20];
21: for (int i = 0; i &lt; crossword.length; i++)
22: for (int j = 0; j &lt; crossword.length; j++)
23: crossword[i][j] = ''x'';
24: System.out.println(crossword.size());</code></pre>', 5),
        (368, 'OCA Final Exam - 5', '<p>Which of the following statements about java.lang.Error are most accurate? (Choose two.)</p>', 5),
        (369, 'OCA Final Exam - 6', '<p>Given a class that uses the following import statements, which class would be automatically accessible without using its full package name? (Choose three.)&nbsp;</p><p>&nbsp;</p><pre><code class="language-java">import forest.Bird;
import jungle.tree.*;
import savana.*;</code></pre>', 5),
        (370, 'OCA Final Exam - 7', '<p>How many of the following variables represent immutable objects?&nbsp;</p><p>&nbsp;</p><pre><code class="language-java">ArrayList l = new ArrayList();
String s = new String();
StringBuilder sb = new StringBuilder();
LocalDateTime t = LocalDateTime.now();</code></pre>', 5);
ALTER TABLE `newro-factory-db`.question ALTER COLUMN id RESTART WITH 404;

INSERT INTO `newro-factory-db`.answer (id, label, text, valid_answer, question_id)
VALUES  (3, 'A', 'cars', 0, 364),
        (4, 'B', 'cars carts', 0, 364),
        (8, 'A', '-, +, =, --', 0, 365),
        (9, 'B', '%, *, /, +', 0, 365),
        (10, 'C', '=, +, /, *', 1, 365),
        (11, 'D', '^, +, -, ==', 0, 365),
        (12, 'E', '*, /, %, --', 1, 365),
        (13, 'A', 'public byte getNose(String nose)', 0, 366),
        (14, 'B', 'public void setHead(int head)', 1, 366),
        (15, 'C', 'public String getShoulders()', 1, 366),
        (16, 'D', 'public long isMouth()', 0, 366),
        (17, 'E', 'public void gimmeEars()', 0, 366),
        (18, 'F', 'public boolean isToes()', 1, 366),
        (19, 'A', 'One line needs to be changed for this code to compile', 1, 367),
        (20, 'B', 'Two lines need to be changed for this code to compile', 0, 367),
        (21, 'C', 'Three lines need to be changed for this code to compile', 0, 367),
        (22, 'D', 'If the code is fixed to compile, none of the cells in the 2D array have a value of 0.', 0, 367),
        (23, 'E', 'If the code is fixed to compile, half of the cells in the 2D array have a value of 0.', 1, 367),
        (24, 'F', 'If the code is fixed to compile, all of the cells in the 2D array have a value of 0.', 0, 367),
        (25, 'A', 'An Error should be thrown if a file system resource becomes temporarily unavailable.', 0, 368),
        (26, 'B', 'An application should never catch an Error.', 1, 368),
        (27, 'C', 'Error is a subclass of Exception, making it a checked exception.', 0, 368),
        (28, 'D', 'It is possible to catch and handle an Error thrown in an application.', 1, 368),
        (29, 'E', 'An Error should be thrown if a user enters invalid input.', 0, 368),
        (30, 'A', 'forest.Bird', 1, 369),
        (31, 'B', 'savana.sand.Wave', 0, 369),
        (32, 'C', 'jungle.tree.Huicongo', 1, 369),
        (33, 'D', 'java.lang.Object', 1, 369),
        (34, 'E', 'forest.Sloth', 0, 369),
        (35, 'F', 'forest.ape.bonobo', 0, 369),
        (36, 'A', 'None', 0, 370),
        (37, 'B', 'One', 0, 370),
        (38, 'C', 'Two', 1, 370),
        (39, 'D', 'Three', 0, 370),
        (40, 'E', 'Four', 0, 370),
        (41, 'F', 'None of the above - this code doesn''t compile', 0, 370);
ALTER TABLE `newro-factory-db`.answer ALTER COLUMN id RESTART WITH 227;
