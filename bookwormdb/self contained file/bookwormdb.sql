-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bookwormdb
-- ------------------------------------------------------
-- Server version	8.0.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `addressID` int NOT NULL AUTO_INCREMENT,
  `customerID` int NOT NULL,
  `line1` varchar(60) NOT NULL,
  `line2` varchar(60) DEFAULT NULL,
  `city` varchar(40) NOT NULL,
  `district` varchar(2) NOT NULL,
  `zipCode` varchar(10) NOT NULL,
  PRIMARY KEY (`addressID`)
) ENGINE=InnoDB AUTO_INCREMENT=367 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (363,15511,'1280 Grove Streen','Ocen view 106','San Andreas','CA','30094'),(364,5280,'Holly Hill','Blue Meadow','conyers','Fl','8112'),(365,30655,'505 Riverbend Parkway','Holly Hill Dr','Athens','GA','30605'),(366,59743,'Univeristy hill','Apple st','Athens','GA','30605');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `billingaddress`
--

DROP TABLE IF EXISTS `billingaddress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `billingaddress` (
  `billingAddressID` int NOT NULL AUTO_INCREMENT,
  `billingAddress1` varchar(60) NOT NULL,
  `billingAddress2` varchar(60) NOT NULL,
  `billingCity` varchar(60) NOT NULL,
  `billingState` varchar(60) NOT NULL,
  `billingZipCode` varchar(60) NOT NULL,
  `customerID` int DEFAULT NULL,
  PRIMARY KEY (`billingAddressID`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `billingaddress`
--

LOCK TABLES `billingaddress` WRITE;
/*!40000 ALTER TABLE `billingaddress` DISABLE KEYS */;
INSERT INTO `billingaddress` VALUES (35,'Franklin Avenue 104','Ocen view 106','bradley beach','NJ','0776',15511),(36,'505 Riverbend Parkway','Holly Hill Dr','Athens','GA','30605',5280),(37,'','','','','',30655),(38,'505 Riverbend Parkway','Holly Hill Dr','Athens','GA','30605',59743);
/*!40000 ALTER TABLE `billingaddress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookcategory`
--

DROP TABLE IF EXISTS `bookcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookcategory` (
  `bookID` int NOT NULL,
  `categoryID` int NOT NULL,
  KEY `bookID` (`bookID`),
  KEY `categoryID` (`categoryID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookcategory`
--

LOCK TABLES `bookcategory` WRITE;
/*!40000 ALTER TABLE `bookcategory` DISABLE KEYS */;
/*!40000 ALTER TABLE `bookcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookgenre`
--

DROP TABLE IF EXISTS `bookgenre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookgenre` (
  `bookgenID` int NOT NULL AUTO_INCREMENT,
  `genreID` int NOT NULL,
  `bookID` int NOT NULL,
  PRIMARY KEY (`bookgenID`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookgenre`
--

LOCK TABLES `bookgenre` WRITE;
/*!40000 ALTER TABLE `bookgenre` DISABLE KEYS */;
INSERT INTO `bookgenre` VALUES (1,6,1),(2,6,2),(3,6,3),(4,6,4),(5,6,5),(6,2,6),(7,2,7),(8,2,8),(9,4,8),(10,2,9),(11,2,10),(12,4,10),(13,3,11),(14,3,12),(15,3,13),(16,4,13),(17,3,14),(18,3,15),(19,7,16),(20,7,17),(21,4,17),(22,7,18),(23,7,19),(24,7,20),(25,8,21),(26,8,22),(27,8,23),(28,4,23),(29,8,24),(30,8,25),(31,5,26),(32,5,27),(33,5,28),(34,5,29),(35,5,30),(36,3,31),(37,6,32),(38,11,33),(39,12,34),(40,11,35),(41,12,36),(42,11,37),(43,12,38),(44,14,39),(45,13,40);
/*!40000 ALTER TABLE `bookgenre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `bookID` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `descrip` text NOT NULL,
  `releaseYear` int NOT NULL,
  `isbn` varchar(60) NOT NULL,
  `bookPrice` decimal(10,2) NOT NULL,
  `rating` decimal(2,1) NOT NULL,
  PRIMARY KEY (`bookID`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'The Home Cook: Recipes to Know by Heart: A Cookbook','Alex Guarnaschelli','The all-in-one cooking bible for a new generation with 300 recipes for everything from simple vinaigrettes and roast chicken to birthday cake and cocktails.',2017,'030795658X',14.00,4.7),(2,'The Great British Baking Show: Love to Bake','Paul Hollywood, Prue Leith','Love to Bake is The Great British Bake Off\'s best collection yet - recipes to remind us that baking is the ultimate expression of thanks, togetherness, celebration and love.',2020,'751583057',10.00,4.8),(3,'The Bob\'s Burger Book: Real Recipes for Joke Burgers','Loren Bouchard','Fully illustrated with all-new art in the series\'s signature style, The Bob\'s Burgers Burger Book showcases the entire Belcher family as well as beloved characters including Teddy, Jimmy Pesto Jr., and Aunt Gayle.',2021,'1368071066',16.00,4.9),(4,'A Table: Recipes for Cooking and Eating the French Way','Rebekah Peppler, Joann Pai','Mastering the Art of French Cooking meets Dinner: Changing the Game in a beautifully photographed, fresh approach to French cooking and gathering, with 125 simple recipes.',2021,'1797202235',10.00,4.6),(5,'5-Ingredient Cooking for Two: 100 Recipes Portioned for Pairs','Robin Donovan','With easy-to-follow instructions, smart shopping lists, and step-by-step meal planning, the 100+ recipes in 5-Ingredient Cooking for Two helps minimize prep time while maximizing flavor and your budget.',2020,'1646110986',15.00,4.5),(6,'Gone Girl','Gillian Flynn','#1 NEW YORK TIMES BESTSELLER. The “mercilessly entertaining” (Vanity Fair) instant classic \"about the nature of identity and the terrible secrets that can survive and thrive in even the most intimate relationships\" (Lev Grossman, Time).',2014,'307588378',10.00,4.0),(7,'The President\'s Daughter','Bill Clinton, James Patterson','A madman abducts Keating\'s teenage daughter, Melanie—turning every parent\'s deepest fear into a matter of national security. As the world watches in real time, Keating embarks on a one-man special-ops mission that tests his strengths: as a leader, a warrior, and a father.',2021,'031627853X',15.00,4.7),(8,'Later','Stephen King','The son of a struggling single mother, Jamie Conklin just wants an ordinary childhood. But Jamie is no ordinary child. Born with an unnatural ability his mom urges him to keep secret, Jamie can see what no one else can see and learn what no one else can learn. But the cost of using this ability is higher than Jamie can imagine – as he discovers when an NYPD detective draws him into the pursuit of a killer who has threatened to strike from beyond the grave. ',2021,'1789096499',13.00,4.5),(9,'The Da Vinci Code','Robert Langdon','While in Paris, Harvard symbologist Robert Langdon is awakened by a phone call in the dead of the night. The elderly curator of the Louvre has been murdered inside the museum, his body covered in baffling symbols. As Langdon and gifted French cryptologist Sophie Neveu sort through the bizarre riddles, they are stunned to discover a trail of clues hidden in the works of Leonardo da Vinci—clues visible for all to see and yet ingeniously disguised by the painter.',2009,'307474275',10.00,4.6),(10,'Joyland','Stephen King','Set in a small-town North Carolina amusement park in 1973, Joyland tells the story of the summer in which college student Devin Jones comes to work as a carny and confronts the legacy of a vicious murder, the fate of a dying child, and the ways both will change his life forever.',2013,'9781781162',12.50,4.6),(11,'The Hitchhiker\'s Guide to the Galaxy (Hitchhiker\'s Guide Series #1)','Douglas Adams','It\'s an ordinary Thursday morning for Arthur Dent . . . until his house gets demolished. The Earth follows shortly after to make way for a new hyperspace express route, and Arthur\'s best friend has just announced that he\'s an alien. After that, things get much, much worse.',1995,'345391802',8.00,4.6),(12,'The Hobbit','J R. R. Tolkien','A great modern classic and the prelude to The Lord of the Rings. Bilbo Baggins is a hobbit who enjoys a comfortable, unambitious life, rarely traveling any farther than his pantry or cellar. But his contentment is disturbed when the wizard Gandalf and a company of dwarves arrive on his doorstep one day to whisk him away on an adventure.',1937,'054792822X',15.00,4.8),(13,'Coraline','Neil Gaiman, Dave McKean','When Coraline steps through a door to find another house strangely similar to her own (only better), things seem marvelous. But there\'s another mother there, and another father, and they want her to stay and be their little girl. They want to change her and never let her go.',2012,'380807343',12.00,4.8),(14,'Harry Potter and the Sorcerer\'s Stone','J.K. Rowling','Harry Potter has no idea how famous he is. That\'s because he\'s being raised by his miserable aunt and uncle who are terrified Harry will learn that he\'s really a wizard, just as his parents were. But everything changes when Harry is summoned to attend an infamous school for wizards, and he begins to discover some clues about his illustrious birthright.',1998,'439708184',10.00,4.8),(15,'Dune','Frank Herbert','Set on the desert planet Arrakis, Dune is the story of the boy Paul Atreides, heir to a noble family tasked with ruling an inhospitable world where the only thing of value is the “spice” melange, a drug capable of extending life and enhancing consciousness. Coveted across the known universe, melange is a prize worth killing for....',2005,'441013597',9.00,4.7),(16,'Frankenstein','Mary Shelley','The original 1818 version of Frankenstein; or the Modern Prometheus by Mary Wollstonecraft Shelley is presented here in this stunning new paperback edition. Enjoy the original vision of Frankenstein again or for the very first time.',2005,'1687768641',9.00,4.7),(17,'Carrie','Stephen King','Carrie White may be picked on by her classmates, but she has a gift. She can move things with her mind. Doors lock. Candles fall. This is her power and her problem. Then, an act of kindness, as spontaneous as the vicious taunts of her classmates, offers Carrie a chance to be a normal...until an unexpected cruelty turns her gift into a weapon of horror and destruction that no one will ever forget.',1818,'307743667',10.00,4.7),(18,'Parasite','Darcy Coates','When a guard discovers an unusual lifeform on her remote moon outpost, she disregards protocol to investigate―with catastrophic consequences. It soon becomes clear she\'s stuck in isolation with an alien capable of incredible depravity. The parasite wears its victims skins and adopts their personalities. It mimics the way they talk, the way they look, the way they act. It\'s the perfect disguise.',1974,'1728221803',12.00,4.3),(19,'The Silence of the Lambs','Thomas Harris','As part of the search for a serial murderer nicknames \"Buffalo Bill,\" FBI trainee Clarice Starling is given an assignment. She must visit a man confined to a high-security facility for the criminally insane and interview him. That man, Dr. Hannibal Lecter, is a former psychiatrist with unusual tastes and an intense curiosity about the darker corners of the mind.',1988,'312924585',10.00,4.8),(20,'The Haunting of Hill House','Shirley Jackson','First published in 1959, Shirley Jackson\'s The Haunting of Hill House has been hailed as a perfect work of unnerving terror. It is the story of four seekers who arrive at a notoriously unfriendly pile called Hill House: Dr. Montague, an occult scholar looking for solid evidence of a \"haunting\"; Theodora, his lighthearted assistant; Eleanor, a friendless, fragile young woman well acquainted with poltergeists; and Luke, the future heir of Hill House. ',1959,'143039989',10.00,4.4),(21,'The Paleo Manifesto: Ancient Wisdom for Lifelong Health','John Durant','In The Paleo Manifesto: Ancient Wisdom for Lifelong Health, John Durant argues for an evolutionary – and revolutionary – approach to health. All animals, human or otherwise, thrive when they mimic key elements of life in their natural habitat. From diet to movement to sleep, this evolutionary perspective sheds light on some of our most pressing health concerns. ',2014,'307889181',13.00,4.6),(22,'How the Word Is Passed: A Reckoning with the History of Slavery Across America','Clint Smith','A deeply researched and transporting exploration of the legacy of slavery and its imprint on centuries of American history, How the Word Is Passed illustrates how some of our country\'s most essential stories are hidden in plain view—whether in places we might drive by on our way to work, holidays such as Juneteenth, or entire neighborhoods like downtown Manhattan, where the brutal history of the trade in enslaved men, women, and children has been deeply imprinted.',2021,'316492930',15.00,4.9),(23,'All the Light We Cannot See','Anthony Doerr','Marie-Laure lives in Paris near the Museum of Natural History, where her father works. When she is twelve, the Nazis occupy Paris and father and daughter flee to the walled citadel of Saint-Malo. In a mining town in Germany, Werner Pfennig, an orphan, grows up with his younger sister, enchanted by a crude radio they find that brings them news and stories from places they have never seen or imagined. Deftly interweaving the lives of Marie-Laure and Werner, Doerr illuminates the ways, against all odds, people try to be good to one another.',2017,'1501173219',10.00,4.6),(24,'A History of the World in 6 Glasses','Tom Standage','Beer, wine, spirits, coffee, tea, and Coca-Cola: In Tom Standage’s deft, innovative account of world history, these six beverages turn out to be much more than just ways to quench thirst. They also represent six eras that span the course of civilization―from the adoption of agriculture, to the birth of cities, to the advent of globalization.',2006,'802715524',12.00,4.6),(25,'Sapiens: A Brief History of Humankind','Yuval Noah Harari','From a renowned historian comes a groundbreaking narrative of humanity\'s creation and evolution - a number one international best seller - that explores the ways in which biology and history have defined us and enhanced our understanding of what it means to be \"human\".',2018,'9780062310',11.00,4.6),(26,'Dance Away With Me','Suzanne Philips','When life throws her one setback too many, midwife and young widow Tess Hartsong takes off for Runaway Mountain. In this small town high in the Tennessee mountains, surrounded by nature, she hopes to outrun her heartbreak and find the solace she needs to heal.',2020,'006297307X',9.00,4.5),(27,'One Last Stop','Casey McQuiston','For cynical twenty-three-year-old August, moving to New York City is supposed to prove her right: that things like magic and cinematic love stories don’t exist, and the only smart way to go through life is alone. She can’t imagine how waiting tables at a 24-hour pancake diner and moving in with too many weird roommates could possibly change that. And there’s certainly no chance of her subway commute being anything more than a daily trudge through boredom and electrical failures.',2021,'1250244498',6.00,4.0),(28,'Red, White and Royal Blue','Casey McQuiston','When his mother became President, Alex Claremont-Diaz was promptly cast as the American equivalent of a young royal. Handsome, charismatic, genius―his image is pure millennial-marketing gold for the White House. There\'s only one problem: Alex has a beef with the actual prince, Henry, across the pond. And when the tabloids get hold of a photo involving an Alex-Henry altercation, U.S./British relations take a turn for the worse.',2019,'1250316774',10.00,4.5),(29,'People We Meet On Vacation','Emily Henry','Poppy and Alex. Alex and Poppy. They have nothing in common. She\'s a wild child; he wears khakis. She has insatiable wanderlust; he prefers to stay home with a book. And somehow, ever since a fateful car share home from college many years ago, they are the very best of friends. For most of the year they live far apart—she\'s in New York City, and he’s in their small hometown—but every summer, for a decade, they have taken one glorious week of vacation together.',2021,'1984806750',9.00,4.7),(30,'The Ex Talk','Rachel Lynn Solomon','Shay Goldstein has been a producer at her Seattle public radio station for nearly a decade, and she can\'t imagine working anywhere else. But lately it\'s been a constant clash between her and her newest colleague, Dominic Yun, who\'s fresh off a journalism master\'s program and convinced he knows everything about public radio. ',2021,'593200128',8.00,4.3),(31,'Once Upon a Time in Hollywood','Quentin Tarantino','Quentin Tarantino\'s long-awaited first work of fiction - at once hilarious, delicious, and brutal — is the always surprising, sometimes shocking new novel based on his Academy Award- winning film.',2021,'63112523',16.00,4.5),(32,'American Marxism','Mark R. Levin','In American Marxism, Levin explains how the core elements of Marxist ideology are now pervasive in American society and culture.',2021,'150113597X',16.00,4.0),(33,'The Last Thing He Told Me','Laura Dave','A \"gripping\" (Entertainment Weekly) mystery about a woman who thinks she’s found the love of her life—until he disappears.',2021,'1501171348',16.00,4.3),(34,'The Body Keeps The Score','Bessel Van Der Kolk M.D','Essential reading for anyone interested in understanding and treating traumatic stress and the scope of its impact on society.',2015,'143127748',10.00,4.8),(35,'The Midnight Library','Matt Haig','Somewhere out beyond the edge of the universe there is a library that contains an infinite number of books, each one the story of another reality.',2020,'525559477',14.00,4.4),(36,'Atomic Habits','James Clear','Tiny Changes, Remarkable Results',2018,'735211299',12.00,4.8),(37,'The Four Winds','Kristin Hannah','A powerful American epic about love and heroism and hope, set during the Great Depression',2021,'1250178606',16.00,4.5),(38,'The Four Agreements','Don Miguel Ruez','Ruiz reveals the source of self-limiting beliefs that rob us of joy and create needless suffering.',2018,'9781878424310',10.00,4.7),(39,'The Boy, The Mole, The Fox, and The Horse','Charlie Mackesy','The tale of a curious boy, a greedy mole, a wary fox and a wise horse who find themselves together in sometimes difficult terrain, sharing their greatest fears and biggest discoveries about vulnerability, kindness, hope, friendship and love.',2019,'62976583',11.00,4.9),(40,'Untamed','Glennon Doyle','In her most revealing and powerful memoir yet, the activist, speaker, bestselling author, and \"patron saint of female empowerment\"',2020,'1984801252',14.00,4.6),(41,'The hound','konon doyle','sherlock holmes mystery',2015,'3094893204809',0.00,4.5),(43,'','konon doyle','null',0,'3094893204809',0.00,4.5);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booksordered`
--

DROP TABLE IF EXISTS `booksordered`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booksordered` (
  `booksOrderedID` int NOT NULL AUTO_INCREMENT,
  `orderID` int NOT NULL,
  `bookID` int NOT NULL,
  `quantity` int NOT NULL,
  `priceEach` decimal(10,2) NOT NULL,
  PRIMARY KEY (`booksOrderedID`),
  KEY `bookID` (`bookID`),
  KEY `orderID` (`orderID`),
  CONSTRAINT `booksordered_ibfk_1` FOREIGN KEY (`bookID`) REFERENCES `books` (`bookID`),
  CONSTRAINT `booksordered_ibfk_2` FOREIGN KEY (`orderID`) REFERENCES `orders` (`orderID`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booksordered`
--

LOCK TABLES `booksordered` WRITE;
/*!40000 ALTER TABLE `booksordered` DISABLE KEYS */;
INSERT INTO `booksordered` VALUES (10,6,37,4,16.00),(11,6,37,4,16.00),(12,6,37,4,16.00),(13,6,37,4,16.00),(14,6,39,3,11.00),(15,6,39,3,11.00),(16,6,39,3,11.00),(17,7,36,5,12.00),(18,7,36,5,12.00),(19,7,36,5,12.00),(20,7,36,5,12.00),(21,7,36,5,12.00),(22,7,35,4,14.00),(23,7,35,4,14.00),(24,7,35,4,14.00),(25,7,35,4,14.00),(26,8,37,1,16.00),(27,9,38,1,10.00),(28,10,38,1,10.00),(29,11,38,1,10.00),(30,11,38,1,10.00),(31,11,38,1,10.00),(32,12,38,1,10.00),(33,12,38,1,10.00),(34,12,38,1,10.00),(35,12,39,1,11.00),(36,13,36,1,12.00),(37,14,36,1,12.00),(38,14,37,1,16.00),(39,15,36,1,12.00),(40,15,37,1,16.00),(41,15,36,1,12.00),(42,16,36,1,12.00),(43,16,37,1,16.00),(44,16,36,1,12.00),(45,17,38,1,10.00),(46,18,36,1,12.00),(47,19,36,1,12.00),(48,20,37,1,16.00),(49,21,36,1,12.00),(50,22,39,1,11.00),(51,23,39,1,11.00),(52,24,38,1,10.00),(53,25,38,1,10.00),(54,26,38,1,10.00),(55,28,40,1,14.00),(56,28,33,3,16.00),(57,28,33,3,16.00),(58,28,38,1,10.00),(59,29,39,3,11.00),(60,29,39,3,11.00),(61,29,39,3,11.00),(62,30,39,1,11.00),(63,31,39,1,11.00);
/*!40000 ALTER TABLE `booksordered` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cardtype`
--

DROP TABLE IF EXISTS `cardtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cardtype` (
  `cardID` int NOT NULL AUTO_INCREMENT,
  `cardTypeInfo` varchar(60) NOT NULL,
  PRIMARY KEY (`cardID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cardtype`
--

LOCK TABLES `cardtype` WRITE;
/*!40000 ALTER TABLE `cardtype` DISABLE KEYS */;
INSERT INTO `cardtype` VALUES (1,'VISA'),(2,'MASTERCARD'),(3,'DISCOVER'),(4,'AMERICAN EXPRESS');
/*!40000 ALTER TABLE `cardtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `cartID` int NOT NULL AUTO_INCREMENT,
  `bookID` int NOT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`cartID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `categoryID` int NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(255) NOT NULL,
  PRIMARY KEY (`categoryID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Action'),(2,'Mystery'),(3,'Fantasy'),(4,'Thriller'),(5,'Romance'),(6,'Horror'),(7,'History'),(8,'CookBooks'),(9,'Fiction'),(10,'NonFiction'),(11,'Show All');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `customerID` int NOT NULL AUTO_INCREMENT,
  `pswd` varchar(60) NOT NULL,
  `firstName` varchar(60) NOT NULL,
  `lastName` varchar(60) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phoneNumber` varchar(12) DEFAULT NULL,
  `userType` int NOT NULL,
  `promoSubscription` tinyint(1) DEFAULT NULL,
  `resetPasswordToken` varchar(60) NOT NULL,
  PRIMARY KEY (`customerID`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=98821 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (5280,'$2a$10$o.uhgEuUq36PCXdo.7RzwetiAn1XWiRw9btzLa9lH3LJSbhk9cEGm','Eliza','Sillesky','Liz@gmail.com','4793940328',1,0,'null'),(15511,'$2a$10$1rPvVigsyVvGJovfliyPP.Wv9n58wA9bMb9j0JmXJ2jup13KiEYZe','Gabriel','St Jean','Gabe@gmail.com','4171287889',0,1,'null'),(30655,'$2a$10$j5qpStiYWjWz19bzr3I9uOfnWEHojazW13vxomLknta9DPAqwcUQ.','Holly','Northern','North124@gmail.com','4703232323',2,0,'null'),(59743,'$2a$10$096zEJ10g9NYFeaGbaITvOOUz0xR/gO6wY7P/2SUQA.bAEkKaLini','Shazia','badbur','Shaz192@gmail.com','4389248324',0,1,'null');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genre` (
  `genreID` int NOT NULL AUTO_INCREMENT,
  `genreName` varchar(60) NOT NULL,
  PRIMARY KEY (`genreID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` VALUES (1,'Action'),(2,'Mystery'),(3,'Fantasy'),(4,'Thriller'),(5,'Romance'),(6,'Other'),(7,'Horror'),(8,'History'),(11,'Fiction'),(12,'Self-Help'),(13,'Biography'),(14,'Comics');
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventory` (
  `inventoryID` int NOT NULL AUTO_INCREMENT,
  `bookID` int NOT NULL,
  PRIMARY KEY (`inventoryID`),
  KEY `bookID` (`bookID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `orderID` int NOT NULL AUTO_INCREMENT,
  `customerID` int NOT NULL,
  `paymentID` int NOT NULL,
  `addressID` int NOT NULL,
  `total` decimal(10,2) NOT NULL,
  `orderDate` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`orderID`),
  KEY `customerID` (`customerID`),
  KEY `paymentID` (`paymentID`),
  KEY `addressID` (`addressID`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`customerID`) REFERENCES `customers` (`customerID`),
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`paymentID`) REFERENCES `payments` (`paymentID`),
  CONSTRAINT `orders_ibfk_3` FOREIGN KEY (`addressID`) REFERENCES `address` (`addressID`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (6,21941,127,345,97.00,'2021/08/02 11:40:05'),(7,21941,127,345,116.00,'2021/08/02 13:26:54'),(8,21941,127,345,16.00,'2021/08/02 16:18:14'),(9,21941,127,345,10.00,'2021/08/02 16:28:14'),(10,21941,127,345,30.00,'2021/08/02 16:34:33'),(11,21941,127,345,90.00,'2021/08/02 16:36:33'),(12,21941,127,345,41.00,'2021/08/02 16:40:17'),(13,21941,127,345,12.00,'2021/08/02 16:42:24'),(14,21941,127,345,28.00,'2021/08/02 16:44:02'),(15,21941,127,345,40.00,'2021/08/02 16:44:27'),(16,21941,127,345,120.00,'2021/08/02 16:45:02'),(17,21941,127,345,30.00,'2021/08/02 17:08:05'),(18,21941,127,345,12.00,'2021/08/02 17:11:15'),(19,21941,127,345,12.00,'2021/08/02 17:12:22'),(20,21941,127,345,48.00,'2021/08/02 17:12:46'),(21,21941,127,345,11.76,'2021/08/02 17:17:49'),(22,21941,127,345,11.00,'2021/08/02 17:21:44'),(23,21941,127,345,11.00,'2021/08/02 17:23:11'),(24,21941,127,345,10.00,'2021/08/02 17:24:17'),(25,21941,127,345,10.00,'2021/08/02 17:38:14'),(26,21941,127,345,9.80,'2021/08/02 18:53:53'),(28,46062,137,352,54.88,'2021/08/02 20:13:43'),(29,46062,137,352,14.52,'2021/08/02 20:23:32'),(30,60612,157,360,11.00,'2021/08/17 22:24:31'),(31,98820,158,362,11.00,'2021/08/17 23:12:47');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payments`
--

DROP TABLE IF EXISTS `payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payments` (
  `paymentID` int NOT NULL AUTO_INCREMENT,
  `nameOnCard` varchar(255) NOT NULL,
  `cardType` varchar(60) NOT NULL,
  `customerID` int NOT NULL,
  `year` int NOT NULL,
  `month` int NOT NULL,
  `cardNumber` varchar(16) DEFAULT NULL,
  `paymentOptionNum` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`paymentID`),
  KEY `customerID` (`customerID`),
  CONSTRAINT `payments_ibfk_1` FOREIGN KEY (`customerID`) REFERENCES `customers` (`customerID`)
) ENGINE=InnoDB AUTO_INCREMENT=163 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payments`
--

LOCK TABLES `payments` WRITE;
/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
INSERT INTO `payments` VALUES (159,'Jack','2',15511,2022,1,'8923182382913291',0),(160,'','0',5280,2021,1,'',0),(161,'','0',30655,2021,1,'',0),(162,'john','3',59743,2022,1,'9438294324923492',0);
/*!40000 ALTER TABLE `payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promos`
--

DROP TABLE IF EXISTS `promos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promos` (
  `promoID` int NOT NULL AUTO_INCREMENT,
  `promoCode` varchar(6) NOT NULL,
  `discountAmount` decimal(10,0) NOT NULL,
  `startDate` varchar(60) NOT NULL,
  `endDate` varchar(60) NOT NULL,
  `promoName` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`promoID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promos`
--

LOCK TABLES `promos` WRITE;
/*!40000 ALTER TABLE `promos` DISABLE KEYS */;
INSERT INTO `promos` VALUES (1,'834533',10,'2012-01-08','2012-01-08','Valentines Day'),(12,'AZ7E13',30,'2012-01-25','2012-21-26','Turkey Day');
/*!40000 ALTER TABLE `promos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statustype`
--

DROP TABLE IF EXISTS `statustype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `statustype` (
  `statusID` int NOT NULL AUTO_INCREMENT,
  `statusType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`statusID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statustype`
--

LOCK TABLES `statustype` WRITE;
/*!40000 ALTER TABLE `statustype` DISABLE KEYS */;
/*!40000 ALTER TABLE `statustype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usertype`
--

DROP TABLE IF EXISTS `usertype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usertype` (
  `typeID` int NOT NULL AUTO_INCREMENT,
  `typeName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`typeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usertype`
--

LOCK TABLES `usertype` WRITE;
/*!40000 ALTER TABLE `usertype` DISABLE KEYS */;
/*!40000 ALTER TABLE `usertype` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-18  0:02:39
