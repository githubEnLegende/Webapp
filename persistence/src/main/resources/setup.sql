// Default H2 collation does not match default MySQL connection
SET COLLATION ENGLISH STRENGTH PRIMARY;

DROP SCHEMA IF EXISTS `newro-factory-db` CASCADE;
CREATE SCHEMA IF NOT EXISTS `newro-factory-db`;
SET SCHEMA `newro-factory-db`;

CREATE TABLE `newro-factory-db`.promotion
(
    id   BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE `newro-factory-db`.intern
(
    id             BIGINT NOT NULL AUTO_INCREMENT,
    first_name     VARCHAR(255),
    last_name      VARCHAR(255),
    arrival        TIMESTAMP NULL,
    formation_over TIMESTAMP NULL,
    promotion_id   BIGINT DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (promotion_id) REFERENCES `newro-factory-db`.promotion (id)
        ON DELETE RESTRICT ON UPDATE RESTRICT
);
CREATE INDEX ix_intern_promotion_1 ON `newro-factory-db`.intern (promotion_id);

CREATE TABLE `newro-factory-db`.chapter
(
    `id`          INT          NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(100) NOT NULL,
    `parent_path` VARCHAR(500) NOT NULL DEFAULT '/',
    PRIMARY KEY (`id`)
);

CREATE TABLE `newro-factory-db`.question
(
    `id`         INT          NOT NULL AUTO_INCREMENT,
    `title`      VARCHAR(255) NOT NULL,
    `statement`  TEXT         NOT NULL,
    `chapter_id` INT          NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`chapter_id`) REFERENCES `newro-factory-db`.chapter (`id`)
);

CREATE TABLE `newro-factory-db`.answer
(
    `id`           INT          NOT NULL AUTO_INCREMENT,
    `label`        VARCHAR(1)   NOT NULL,
    `text`         VARCHAR(255) NOT NULL,
    `valid_answer` BOOLEAN      NOT NULL,
    `question_id`  INT          NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`question_id`) REFERENCES `newro-factory-db`.question (`id`)
);

INSERT INTO `newro-factory-db`.promotion (id, name)
VALUES (1, 'Février 2011'),
       (2, 'Mars 2011'),
       (3, 'Avril 2011'),
       (4, 'Février 2012');

INSERT INTO `newro-factory-db`.intern (first_name, last_name, arrival, formation_over, promotion_id)
VALUES ('Abel', 'Boulle', null, null, 1),
       ('Abélard', 'Grandjean', null, null, 1),
       ('Abelin', 'Bourguignon', null, null, 1),
       ('Abraham', 'Delannoy', null, null, 1),
       ('Achille', 'Charbonnier', null, null, 1),
       ('Adam', 'Bousquet', null, null, 1),
       ('Adélie', 'Bellegarde', null, null, 1),
       ('Adélie', 'Lavaud', null, null, 1),
       ('Adélie', 'Rouzet', null, null, 1),
       ('Adrienne', 'Geiger', null, null, 1),
       ('Adrienne', 'Mazet', null, null, 1),
       ('Agnès', 'Courtial', null, null, 1),
       ('Alain', 'Delcroix', null, null, 1),
       ('Alberte', 'Dimont', null, null, 1),
       ('Alceste', 'Marchant', null, null, 2),
       ('Alceste', 'Serre', null, null, 2),
       ('Alexandre', 'Bethune', null, null, 2),
       ('Alfred', 'Lacan', null, null, 2),
       ('Alix', 'Arsenault', null, null, 2),
       ('Alix', 'Gardet', null, null, 2),
       ('Amand', 'Baschet', null, null, 2),
       ('Amand', 'Silvestre', null, null, 2),
       ('Amanda', 'Morel', null, null, 2),
       ('Anaïs', 'Monteil', null, null, 2),
       ('Andrée', 'Philidor', null, null, 2),
       ('Angèle', 'Beauchamp', null, null, 2),
       ('Angèle', 'Cochet', null, null, 2),
       ('Angeline', 'Cuvier', null, null, 2),
       ('Anne-Laure', 'Bertillon', null, null, 2),
       ('Anne-Marie', 'Brochard', null, null, 2),
       ('Anne-Sophie', 'Berger', null, null, 2),
       ('Annick', 'Desmarais', null, null, 2),
       ('Annick', 'Gérald', null, null, 3),
       ('Annick', 'Picard', null, null, 3),
       ('Antoine', 'Berger', null, null, 3),
       ('Ariane', 'Magnier', null, null, 3),
       ('Armel', 'Ardouin', null, null, 3),
       ('Armelle', 'Ouvrard', null, null, 3),
       ('Arsène', 'Simon', null, null, 3),
       ('Arsène', 'Trouvé', null, null, 4),
       ('Aubin', 'Guilloux', null, null, 4),
       ('Aude', 'Besson', null, null, 4),
       ('Audrey', 'Bélanger', null, null, 4),
       ('Augustin', 'Chaney', null, null, 4),
       ('Augustine', 'Niel', null, null, 4),
       ('Aurélia', 'Allard', null, null, 4),
       ('Axelle', 'Chuquet', null, null, 4),
       ('Axelle', 'Thiers', null, null, 4),
       ('Aymeric', 'Moreau', null, null, 4),
       ('Barbe', 'Droz', null, null, 4);

INSERT INTO `newro-factory-db`.chapter (id, name, parent_path)
VALUES (5, 'OCA', '/');

INSERT INTO `newro-factory-db`.question (id, title, statement, chapter_id)
VALUES (364, 'OCA Final Exam - 1', '<p>What is the output if this class is run with java Indexing cars carts?&nbsp;</p><p>&nbsp;</p><pre><code class="language-java">public class Indexing {
	public static void main(String... books) {
		StringBuilder sb = new StringBuilder();
		for (String book : books)
			sb.insert(sb.indexOf("c"), book);
		System.out.println(sb);
	}
}</code></pre>', 5),
       (365, 'OCA Final Exam - 2',
        'Fill in the blanks: The operators +=,__________ ,__________ ,__________ ,__________ , and ++ are listed in increasing or the same level of operator precedence. (Choose two.)',
        5),
       (366, 'OCA Final Exam - 3', '<p>Which of the following are valid JavaBean signatures? (Choose three.)</p>', 5),
       (367, 'OCA Final Exam - 4', '<p>Which of the following are true? (Choose two.)&nbsp;</p><p>&nbsp;</p><pre><code class="language-java">20: int[] crossword [] = new int[10][20];
21: for (int i = 0; i &lt; crossword.length; i++)
22: for (int j = 0; j &lt; crossword.length; j++)
23: crossword[i][j] = ''x'';
24: System.out.println(crossword.size());</code></pre>', 5),
       (368, 'OCA Final Exam - 5',
        '<p>Which of the following statements about java.lang.Error are most accurate? (Choose two.)</p>', 5),
       (369, 'OCA Final Exam - 6', '<p>Given a class that uses the following import statements, which class would be automatically accessible without using its full package name? (Choose three.)&nbsp;</p><p>&nbsp;</p><pre><code class="language-java">import forest.Bird;
import jungle.tree.*;
import savana.*;</code></pre>', 5),
       (370, 'OCA Final Exam - 7', '<p>How many of the following variables represent immutable objects?&nbsp;</p><p>&nbsp;</p><pre><code class="language-java">ArrayList l = new ArrayList();
String s = new String();
StringBuilder sb = new StringBuilder();
LocalDateTime t = LocalDateTime.now();</code></pre>', 5),
       (371, 'OCA Final Exam - 8', '<p>What is the output of the following?&nbsp;</p><p>&nbsp;</p><pre><code class="language-java">StringBuilder builder = new StringBuilder("Leaves growing");
do { builder.delete(0, 5); }
while (builder.length() &gt; 5);
System.out.println(builder);</code></pre>', 5),
       (372, 'OCA Final Exam - 9', '<p>What is the output of the following application?&nbsp;</p><p>&nbsp;</p><pre><code class="language-java">package reality;
public class Equivalency {
	public static void main(String[] edges) {
		final String ceiling = "up";
		String floor = new String("up");
		final String wall = new String(floor);
		System.out.print((ceiling==wall) +" "+(floor==wall) +" "+ceiling.equals(wall));
	}
}</code></pre>', 5),
       (373, 'OCA Final Exam - 10', '<p>How many times does the following code print true?&nbsp;</p><p>&nbsp;</p><pre><code class="language-java">1: public class Giggles {
2: public static void main(String[] args) {
3: String lol = "lol";
4: System.out.println(lol.toUpperCase() == lol);
5: System.out.println(lol.toUpperCase() == lol.toUpperCase());
6: System.out.println(lol.toUpperCase().equals(lol));
7: System.out.println(lol.toUpperCase().equals(lol.toUpperCase()));
8: System.out.println(lol.toUpperCase().equalsIgnoreCase(lol));
9: System.out.println(lol.toUpperCase()
10: .equalsIgnoreCase(lol.toUpperCase()));
11: } }</code></pre>', 5),
       (374, 'OCA Final Exam - 11', '<p>Which lines can be removed together without stopping the code from compiling and while printing the same output? (Choose three.)&nbsp;</p><p>&nbsp;</p><pre><code class="language-java">14: String race = "";
15: outer:
16: do {
17: inner:
18: do {
19: race += "x";
20: } while (race.length() &lt;= 4);
21: } while (race.length() &lt; 4);
22: System.out.println(race);</code></pre>', 5),
       (375, 'OCA Final Exam - 12',
        '<p>Which of the following do not compile when filling in the blank? (Choose two.)&nbsp;</p><p>&nbsp;</p><p>long bigNum = ____________;</p>',
        5),
       (376, 'OCA Final Exam - 13', '<p>How many lines does this program print?&nbsp;</p><p>&nbsp;</p><pre><code class="language-java">import java.time.*;
public class OnePlusOne {
	public static void main(String... nums) {
		LocalTime time = LocalTime.of(1, 11);
		while (time.getHour() &lt; 1) {
			time.plusHours(1);
			System.out.println("in loop");
		}
	}
}</code></pre>', 5),
       (377, 'OCA Final Exam - 14', '<p>What is the data of running the following program?&nbsp;</p><p>&nbsp;</p><pre><code class="language-java">1: package fun;
2: public class Sudoku {
3: static int[][] game;
4:
5: public static void main(String args[]) {
6: game[3][3] = 6;
7: Object[] obj = game;
8: obj[3] = ''X'';
9: System.out.println(game[3][3]);
10: }
11: }</code></pre>', 5),
       (378, 'OCA Final Exam - 15',
        '<p>Which of the following use generics and compile without warnings? (Choose two.)</p>', 5),
       (379, 'OCA Final Exam - 16', '<p>Which of the following are true right before the main() method ends? (Choose two.)</p><p>&nbsp;</p><pre><code class="language-java">public static void main(String[] args) {
	String shoe1 = new String("sandal");
	String shoe2 = new String("flip flop");
	String shoe3 = new String("croc");
	shoe1 = shoe2;
	shoe2 = shoe3;
	shoe3 = shoe1;
}</code></pre>', 5),
       (380, 'OCA Final Exam - 17', '<p>How many lines of the following application do not compile?&nbsp;</p><p>&nbsp;</p><pre><code class="language-java">package ocean;
class BubbleException extends Exception {}
class Fish {
	Fish getFish() throws BubbleException {
		throw new RuntimeException("fish!");
	}
}
public final class Clownfish extends Fish {
	public final Clownfish getFish() {
		throw new RuntimeException("clown!");
	}
	public static void main(String[] bubbles) {
		final Fish f = new Clownfish();
		f.getFish();
		System.out.println("swim!");
	}
}</code></pre>', 5),
       (381, 'OCA Final Exam - 18', '<p>How many lines does this code output?&nbsp;</p><p>&nbsp;</p><pre><code class="language-java">import java.util.*;
import java.util.function.*;
public class PrintNegative {
	public static void main(String[] args) {
		List list= new ArrayList&lt;&gt;();
		list.add(-5);
		list.add(0);
		list.add(5);
		print(list, e -&gt; e &lt; 0);
	}
	public static void print(List list, Predicate p) {
		for (Integer num : list)
			if (p.test(num))
				System.out.println(num);
	}
}</code></pre>', 5),
       (382, 'OCA Final Exam - 19',
        '<p>Which keywords are required with a try statement?&nbsp;</p><p>&nbsp;</p><p>I. finalize&nbsp;</p><p>II. catch&nbsp;</p><p>III. throws&nbsp;</p><p>IV. finally</p>',
        5),
       (383, 'OCA Final Exam - 20', '<p>What is the output of the following?&nbsp;</p><p>&nbsp;</p><pre><code class="language-java">12: int data = 8;
13: loop: while (data &gt; 7) {
14: data++;
15: do {
16: data--;
17: } while (data &gt; 5);
18: break loop;
19: }
20: System.out.println(data);</code></pre>', 5),
       (384, 'OCA Final Exam - 21', '<p>What is the data of compiling and executing the following application?&nbsp;</p><p>&nbsp;</p><pre><code class="language-java">package reptile;
public class Alligator {
	static int teeth;
	double scaleToughness;
	public Alligator() { teeth++; }
	public void snap(int teeth) {
		System.out.print(teeth+" ");
		teeth--;
	}
	public static void main(String[] unused) {
		new Alligator().snap(teeth);
		new Alligator().snap(teeth);
	}
}</code></pre>', 5),
       (385, 'OCA Final Exam - 22', '<p>What is the output of the following?&nbsp;</p><p>&nbsp;</p><pre><code class="language-java">public class Costume {
	public static void main(String[] black) {
		String witch = "b";
		String tail = "lack";
		witch.concat(tail);
		System.out.println(witch);
	}
}</code></pre>', 5),
       (386, 'OCA Final Exam - 23',
        '<p>Which modifiers can be independently applied to an interface method? (Choose three.)</p>', 5),
       (387, 'OCA Final Exam - 24', '<p>What is the output of the following?&nbsp;</p><p>&nbsp;</p><pre><code class="language-java">public class Shoelaces {
	public static void main(String[] args) {
		String tie = null;
		while (tie = null)
			tie = "shoelace";
		System.out.print(tie);
	}
}</code></pre>', 5),
       (388, 'OCA Final Exam - 25', '<p>What statements are true about compiling a Java class file? (Choose two.)</p>',
        5),
       (389, 'OCA Final Exam - 26', '<p>What is the output of the following application?&nbsp;</p><p>&nbsp;</p><pre><code class="language-java">package woods;
interface Plant {
	default String grow() {
		return "Grow!";
	}
}
interface Living {
	public default String grow() {
		return "Growing!";
	}
}
public class Tree implements Plant, Living { // m1
	public String grow(int height) {
		return "Super Growing!";
	}
	public static void main(String[] leaves) {
		Plant p = new Tree(); // m2
		System.out.print(((Living)p).grow()); // m3
	}
}</code></pre>', 5),
       (390, 'OCA Final Exam - 27', '<p>What is the data of the following?&nbsp;</p><p>&nbsp;</p><pre><code class="language-java">public static void main(String... args) {
	String name = "Desiree";
	int _number = 694;
	boolean profit$$$;
	System.out.println(name + " won. " + _number + " profit? " + profit$$$);
}</code></pre>', 5),
       (391, 'OCA Final Exam - 28',
        '<p>Fill in the blanks: Given a variable x, __________ decreases the value of x by 1 and returns the original value, while __________ increases the value of x by 1 and returns the new value.</p>',
        5),
       (392, 'OCA Final Exam - 29', '<p>Given the following two classes in the same package, which constructors contain compiler errors? (Choose three.)</p><p>&nbsp;</p><pre><code class="language-java">public class Big {
	public Big(boolean stillIn) {
		super();
	}
}
public class Trouble extends Big {
	public Trouble() {}
	public Trouble(int deep) {
		super(false);
		this();
	}
	public Trouble(String now, int... deep) {
		this(3);
	}
	public Trouble(long deep) {
		this("check",deep);
	}
	public Trouble(double test) {
		super(test&gt;5 ? true : false);
	}
}</code></pre>', 5),
       (393, 'OCA Final Exam - 30', '<p>Which of the following can replace the comment so this code outputs 100? (Choose two.)&nbsp;</p><p>&nbsp;</p><pre><code class="language-java">public class Stats {
	// INSERT CODE
	public static void main(String[] math) {
		System.out.println(max - min);
	}
}</code></pre>', 5),
       (394, 'OCA Final Exam - 31',
        '<p>Which of the following statements are true about Java operators and statements? (Choose two.)</p>', 5),
       (395, 'OCA Final Exam - 32', '<p>What is the output of the following?&nbsp;</p><p>&nbsp;</p><pre><code class="language-java">1: public class Legos {
2: 	public static void main(String[] args) {
3: 		StringBuilder sb = new StringBuilder();
4: 		sb.append("red");
5: 		sb.deleteCharAt(0);
6: 		sb.delete(1, 1);
7: 		System.out.println(sb);
8: 	}
9: }</code></pre>', 5),
       (396, 'OCA Final Exam - 33', '<p>Which of the following is a valid method name in Java? (Choose two.)</p>', 5),
       (397, 'OCA Final Exam - 34',
        '<p>Which of the following statements about inheritance are true? (Choose two.)</p>', 5),
       (398, 'OCA Final Exam - 35',
        '<p>Which of the following statements about Java are true?&nbsp;</p><p>I. The java command uses . to separate packages.&nbsp;</p><p>II. Java supports functional programming.&nbsp;</p><p>III. Java is object oriented.&nbsp;</p><p>IV. Java supports polymorphism.</p>',
        5),
       (399, 'OCA Final Exam - 36', '<p>What is the output of the following?&nbsp;</p><p>&nbsp;</p><pre><code class="language-java">String[][] listing = new String[][] { { "Book", "34.99" }, { "Game", "29.99" }, { "Pen", ".99" } };
System.out.println(listing.length + " " + listing[0].length);</code></pre>', 5),
       (400, 'OCA Final Exam - 37',
        '<p>Which of the following variable types is permitted in a switch statement? (Choose three.)</p>', 5),
       (401, 'OCA Final Exam - 38', '<p>What does the following do?&nbsp;</p><p>&nbsp;</p><pre><code class="language-java">public class Shoot {
	interface Target {
		boolean needToAim(double angle);
	}
	static void prepare(double angle, Target t) {
		boolean ready = t.needToAim(angle); // k1
		System.out.println(ready);
	}
	public static void main(String[] args) {
		prepare(45, d =&gt; d &gt; 5 || d &lt; -5); // k2
	}
}</code></pre>', 5),
       (402, 'OCA Final Exam - 39', '<p>Which of the following is a valid code comment in Java? (Choose three.)</p>',
        5),
       (403, 'OCA Final Exam - 40', '<p>Given the following two classes, each in a different package, which lines allow the second class to compile when inserted independently? (Choose two.)&nbsp;</p><p>&nbsp;</p><pre><code class="language-java">package food;
public class Grass {
	public static int seeds = 10;
	public static Grass getGrass() {return new Grass();}
}
package woods;
// INSERT CODE HERE
public class Deer {
	public void eat() {
		getGrass();
		System.out.print(seeds);
	}
}</code></pre>', 5);

INSERT INTO `newro-factory-db`.answer (id, label, text, valid_answer, question_id)
VALUES (3, 'A', 'cars', 0, 364),
       (4, 'B', 'cars carts', 0, 364),
       (5, 'C', 'ccars arts', 0, 364),
       (6, 'D', 'The code does not compile', 0, 364),
       (7, 'E', 'The code compiles but throws an exception at runtime', 1, 364),
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
       (41, 'F', 'None of the above - this code doesn''t compile', 0, 370),
       (42, 'A', 'Leaves growing', 0, 371),
       (43, 'B', 'ing', 0, 371),
       (44, 'C', 'wing', 1, 371),
       (45, 'D', 'The code does not compile', 0, 371),
       (46, 'E', 'The code compiles but throws an exception at runtime', 0, 371),
       (47, 'A', 'false false false', 0, 372),
       (48, 'B', 'true true true', 0, 372),
       (49, 'C', 'false true true', 0, 372),
       (50, 'D', 'false false true', 1, 372),
       (51, 'E', 'It does not compile', 0, 372),
       (52, 'A', 'One', 0, 373),
       (53, 'B', 'Two', 0, 373),
       (54, 'C', 'Three', 1, 373),
       (55, 'D', 'Four', 0, 373),
       (56, 'E', 'Five', 0, 373),
       (57, 'F', 'None. The code does not compile', 0, 373),
       (58, 'A', 'Lines 15 and 17', 1, 374),
       (59, 'B', 'Lines 15, 16 and 21', 1, 374),
       (60, 'C', 'Line 17', 1, 374),
       (61, 'D', 'Lines 17, 18 and 20', 0, 374),
       (62, 'E', 'Line 20', 0, 374),
       (63, 'F', 'Line 21', 0, 374),
       (64, 'A', '1234', 0, 375),
       (65, 'B', '1234.0', 1, 375),
       (66, 'C', '1234.0L', 1, 375),
       (67, 'D', '1234l', 0, 375),
       (68, 'E', '1234L', 0, 375),
       (69, 'F', '1_234', 0, 375),
       (70, 'A', 'None', 1, 376),
       (71, 'B', 'One', 0, 376),
       (72, 'C', 'Two', 0, 376),
       (73, 'D', 'This is an infinite loop', 0, 376),
       (74, 'E', 'The code does not compile', 0, 376),
       (75, 'A', 'A', 0, 377),
       (76, 'B', '6', 0, 377),
       (77, 'C', 'The code does not compile', 0, 377),
       (78, 'D', 'The code compiles but throws a NullPointerException at runtime', 1, 377),
       (79, 'E', 'The code compiles but throws a different exception at runtime', 0, 377),
       (80, 'F', 'The output is not guaranteed', 0, 377),
       (81, 'A', 'List a = new ArrayList();', 0, 378),
       (82, 'B', 'List b = new ArrayList();', 0, 378),
       (83, 'C', 'List c = new ArrayList();', 1, 378),
       (84, 'D', 'List d = new ArrayList();', 0, 378),
       (85, 'E', 'List e = new ArrayList();', 1, 378),
       (86, 'F', 'List f = new ArrayList();', 0, 378),
       (87, 'A', 'No objects are eligible for garbage collection.', 0, 379),
       (88, 'B', 'One object is eligible for garbage collection.', 1, 379),
       (89, 'C', 'Two objects are eligible for garbage collection.', 0, 379),
       (90, 'D', 'No objects are guaranteed to be garbage collected.', 1, 379),
       (91, 'E', 'One object is guaranteed to be garbage collected', 0, 379),
       (92, 'F', 'Two objects are guaranteed to be garbage collected.', 0, 379),
       (93, 'A', 'None. The code compiles and prints swim!', 0, 380),
       (94, 'B', 'None. The code compiles and prints a stack trace', 0, 380),
       (95, 'C', 'One', 1, 380),
       (96, 'D', 'Two', 0, 380),
       (97, 'E', 'Three', 0, 380),
       (98, 'A', 'One', 1, 381),
       (99, 'B', 'Two', 0, 381),
       (100, 'C', 'Three', 0, 381),
       (101, 'D', 'None. It doesn''t compile', 0, 381),
       (102, 'E', 'None. It throws an exception at runtime', 0, 381),
       (103, 'A', 'I only', 0, 382),
       (104, 'B', 'II only', 0, 382),
       (105, 'C', 'III only', 0, 382),
       (106, 'D', 'IV only', 0, 382),
       (107, 'E', 'I or II, or both', 0, 382),
       (108, 'F', 'None of the above', 1, 382),
       (109, 'A', '5', 1, 383),
       (110, 'B', '7', 0, 383),
       (111, 'C', '8', 0, 383),
       (112, 'D', 'The code does not compile', 0, 383),
       (113, 'E', 'The code compiles but throws an exception at runtime', 0, 383),
       (114, 'A', '0 1', 0, 384),
       (115, 'B', '1 1', 0, 384),
       (116, 'C', '1 2', 1, 384),
       (117, 'D', '2 2', 0, 384),
       (118, 'E', 'The code does not compile', 0, 384),
       (119, 'F', 'The code compiles but produces an exception at runtime', 0, 384),
       (120, 'A', 'b', 1, 385),
       (121, 'B', 'black', 0, 385),
       (122, 'C', 'lack', 0, 385),
       (123, 'D', 'The code does not compile', 0, 385),
       (124, 'E', 'The code compiles but throws an exception at runtime', 0, 385),
       (125, 'A', 'default', 1, 386),
       (126, 'B', 'protected', 0, 386),
       (127, 'C', 'static', 1, 386),
       (128, 'D', 'private', 0, 386),
       (129, 'E', 'final', 0, 386),
       (130, 'F', 'abstract', 1, 386),
       (131, 'A', 'null', 0, 387),
       (132, 'B', 'shoelace', 0, 387),
       (133, 'C', 'shoelaceshoelace', 0, 387),
       (134, 'D', 'The code does not compile', 1, 387),
       (135, 'E', 'This is an infinite loop', 0, 387),
       (136, 'F', 'The code compiles but throws an exception at runtime', 0, 387),
       (137, 'A',
        'If the file does not contain a package statement, then the compiler considers the class part of the java.lang package.',
        0, 388),
       (138, 'B', 'The compiler assumes every class implicitly imports the java.lang.* package.', 1, 388),
       (139, 'C', 'The compiler assumes every class implicitly imports the java.util.* package.', 0, 388),
       (140, 'D', 'Java requires every file to declare a package statement.', 0, 388),
       (141, 'E', 'Java requires every file to declare at least one import statement.', 0, 388),
       (142, 'F',
        'If the class declaration does not extend another class, then it implicitly extends the java.lang.Object class.',
        1, 388),
       (143, 'A', 'Grow!', 0, 389),
       (144, 'B', 'Growing!', 0, 389),
       (145, 'C', 'Super Growing!', 0, 389),
       (146, 'D', 'It does not compile because of line m1', 1, 389),
       (147, 'E', 'It does not compile because of line m2', 0, 389),
       (148, 'F', 'It does not compile because of line m3', 0, 389),
       (149, 'A', 'The declaration of name does not compile', 0, 390),
       (150, 'B', 'The declaration of _number does not compile', 0, 390),
       (151, 'C', 'The declaration of profit$$$ does not compile', 0, 390),
       (152, 'D', 'The println statement does not compile', 1, 390),
       (153, 'E', 'The code compiles and runs successfully', 0, 390),
       (154, 'F', 'The code compiles and throws an exception at runtime', 0, 390),
       (155, 'A', 'x--, ++x', 1, 391),
       (156, 'B', 'x--, x++', 0, 391),
       (157, 'C', '--x, x++', 0, 391),
       (158, 'D', '--x, ++x', 0, 391),
       (159, 'A', 'public Big(boolean stillIn)', 0, 392),
       (160, 'B', 'public Trouble()', 1, 392),
       (161, 'C', 'public Trouble(int deep)', 1, 392),
       (162, 'D', 'public Trouble(String now, int... deep)', 0, 392),
       (163, 'E', 'public Trouble(long deep)', 1, 392),
       (164, 'F', 'public Trouble(double test)', 0, 392),
       (165, 'A', 'final int min, max = 100;', 0, 393),
       (166, 'B', 'final int min = 0, max = 100;', 0, 393),
       (167, 'C', 'int min, max = 100;', 0, 393),
       (168, 'D', 'int min = 0, max = 100;', 0, 393),
       (169, 'E', 'static int min, max = 100;', 1, 393),
       (170, 'F', 'static int min = 0, max = 100;', 1, 393),
       (171, 'A', 'Both right-hand sides of the ternary expression will be evaluated at runtime.', 0, 394),
       (172, 'B', 'A switch statement may contain at most one default statement', 1, 394),
       (173, 'C', 'A single if-then statement can have multiple else statements.', 0, 394),
       (174, 'D', 'The | and || operator are interchangeable, always producing the same results at runtime.', 0, 394),
       (175, 'E', 'The ! operator may not be applied to numeric expressions.', 1, 394),
       (176, 'A', 'r', 0, 395),
       (177, 'B', 'e', 0, 395),
       (178, 'C', 'ed', 1, 395),
       (179, 'D', 'red', 0, 395),
       (180, 'E', 'The code does not compile', 0, 395),
       (181, 'F', 'The code compiles but throws an exception at runtime', 0, 395),
       (182, 'A', '_____()', 1, 396),
       (183, 'B', '%run()', 0, 396),
       (184, 'C', 'check-Activity()', 0, 396),
       (185, 'D', '$Hum2()', 1, 396),
       (186, 'E', 'sing\\\\3()', 0, 396),
       (187, 'F', 'po#ut ()', 0, 396),
       (188, 'A', 'Inheritance is better than using static methods for accessing data in other classes.', 0, 397),
       (189, 'B',
        'Inheritance allows a method to be overridden in a subclass, possibly changing the expected behavior of other methods in a superclass',
        1, 397),
       (190, 'C', 'Inheritance allows objects to inherit commonly used attributes and methods', 1, 397),
       (191, 'D', 'It is possible to create a Java class that does not inherit from any other', 0, 397),
       (192, 'E', 'Inheritance tends to make applications more complicated', 0, 397),
       (193, 'A', 'I only', 0, 398),
       (194, 'B', 'II only', 0, 398),
       (195, 'C', 'II and III', 0, 398),
       (196, 'D', 'I, III and IV', 0, 398),
       (197, 'E', 'I, II, III, and IV', 1, 398),
       (198, 'F', 'None are true', 0, 398),
       (199, 'A', '2 2', 0, 399),
       (200, 'B', '2 3', 0, 399),
       (201, 'C', '3 2', 1, 399),
       (202, 'D', '3 3', 0, 399),
       (203, 'E', 'The code does not compile', 0, 399),
       (204, 'F', 'The code compiles but throws an exception at runtime', 0, 399),
       (205, 'A', 'Character', 1, 400),
       (206, 'B', 'Byte', 1, 400),
       (207, 'C', 'Double', 0, 400),
       (208, 'D', 'long', 0, 400),
       (209, 'E', 'String', 1, 400),
       (210, 'F', 'Object', 0, 400),
       (211, 'A', 'It prints true', 0, 401),
       (212, 'B', 'It prints false', 0, 401),
       (213, 'C', 'It doesn''t compile due to line k1', 0, 401),
       (214, 'D', 'It doesn''t compile due to line k2', 1, 401),
       (215, 'E', 'It doesn''t compile due to another line', 0, 401),
       (216, 'A', '/** Insert */ in next method **/', 0, 402),
       (217, 'B', '/****** Find the kitty cat */', 1, 402),
       (218, 'C', '// Is this a bug ?', 1, 402),
       (219, 'D', '/ Begin method - performStart() /', 0, 402),
       (220, 'E', '/*** TODO: Call grandma ***/', 1, 402),
       (221, 'F', '# Updated code by Patti', 0, 402),
       (222, 'A', 'import static food.Grass.getGrass;
    import static food.Grass.seeds', 1, 403),
       (223, 'B', 'import static food.*;', 0, 403),
       (224, 'C', 'static import food.Grass.*;', 0, 403),
       (225, 'D', 'import food.Grass.*;', 0, 403),
       (226, 'E', 'static import food.Grass.getGrass;
    static import food.Grass.seeds;', 0, 403),
       (227, 'F', 'import static food.Grass.*;', 1, 403);
