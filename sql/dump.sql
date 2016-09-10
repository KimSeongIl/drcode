-- MySQL dump 10.13  Distrib 5.7.12, for osx10.9 (x86_64)
--
-- Host: allan.kr    Database: drcode
-- ------------------------------------------------------
-- Server version	5.6.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(11) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-10 12:12:20


-- MySQL dump 10.13  Distrib 5.7.12, for osx10.9 (x86_64)
--
-- Host: allan.kr    Database: drcode
-- ------------------------------------------------------
-- Server version	5.6.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `auth` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_number` varchar(255) DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'student','ksj@nate.com','$2a$10$ZRIaXzpNgmRVuLsRmIjiduzx2Eovnv7WBaAttXm.arotQtINVz5Je','김성일','201132006',1),(3,'student','ooo__ooo@nate.com','$2a$10$mPjrbBvpDvRSr7RA9N7QGOZVyD.JfXHLQjzcZn/2iKhosrZUBSZvu','노태웅','201132014',1),(11,'admin','taoh95@hanmail.net','$2a$10$cPAkaGrGVLM17OIJ3Dg0Cux3hOPEbDG1DRzK0QRFKSURYrUtkVM1W','관리자','0',1),(23,'student','taoh95@hanmail.net','$2a$10$Q/HGgexG1LqLl8GYzV1U1ewNvpHeoXBnOKWtYd4xOxLlalsqaEqv.','최태은','201232038',NULL),(24,'student','taoh95@hanmail.net','$2a$10$cEfdtvvm06FT65/n2yl54.NGzNjyn915QNW1dd44Z2Fq7/joW8QlO','최학생','2012',NULL),(27,'professor','moon_100@naver.com','$2a$10$hO.IFkLu4qTWtplN5BYoQOXjht8KlgofmH8cvzw8nW3a04/v12m/q','이승진','lsj',1),(28,'professor','moon_100@naver.com','$2a$10$BQyPF4.r3Z7LKX.H.5c4.OJlOh6VoOdttmhIMWwDp5.5dELrIY5L.','유상신','yss',1),(29,'professor','moon_100@naver.com','$2a$10$.1xyC2afhdAI8ASO9Izfsu33YvGZimjM0lJi97/aexW0E29UYTFGW','노은하','neh',1),(30,'student','dlsgur1447@gmail.com','$2a$10$hkKJaX4HlJzljHdh07pXN.0GAYGQGwzW4kwIUo3FcPgszqhSACig.','최인혁','201232036',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-10 12:12:20

-- MySQL dump 10.13  Distrib 5.7.12, for osx10.9 (x86_64)
--
-- Host: allan.kr    Database: drcode
-- ------------------------------------------------------
-- Server version	5.6.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `user_setting`
--

DROP TABLE IF EXISTS `user_setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_setting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `font` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `theme` varchar(255) DEFAULT NULL,
  `user_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_setting`
--

LOCK TABLES `user_setting` WRITE;
/*!40000 ALTER TABLE `user_setting` DISABLE KEYS */;
INSERT INTO `user_setting` VALUES (2,'12px','java','sqlserver','201132006'),(3,'20px','java','twilight','201132014'),(4,'13px','java','monokai','201232038'),(5,NULL,NULL,NULL,'prof'),(6,NULL,NULL,NULL,'001'),(7,NULL,NULL,NULL,''),(8,NULL,NULL,NULL,''),(9,NULL,NULL,NULL,'2'),(10,NULL,NULL,NULL,''),(11,NULL,NULL,NULL,'0'),(12,NULL,NULL,NULL,'000'),(13,NULL,NULL,NULL,'1004'),(14,NULL,NULL,NULL,'002'),(15,NULL,NULL,NULL,''),(16,NULL,NULL,NULL,'테스트'),(17,NULL,NULL,NULL,'test'),(18,NULL,NULL,NULL,'123123'),(19,NULL,NULL,NULL,'123123'),(20,NULL,NULL,NULL,'123'),(24,'12px','java','twilight','2012'),(25,NULL,NULL,NULL,'0000'),(26,NULL,NULL,NULL,'lsj'),(27,NULL,NULL,NULL,'yss'),(28,NULL,NULL,NULL,'neh'),(29,NULL,'java',NULL,'201232036');
/*!40000 ALTER TABLE `user_setting` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-10 12:12:21

-- MySQL dump 10.13  Distrib 5.7.12, for osx10.9 (x86_64)
--
-- Host: allan.kr    Database: drcode
-- ------------------------------------------------------
-- Server version	5.6.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject_code` varchar(255) DEFAULT NULL,
  `subject_name` varchar(255) DEFAULT NULL,
  `professor_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_rxwvu8d59o18fjt8h17xfx0pa` (`professor_id`),
  CONSTRAINT `FK_rxwvu8d59o18fjt8h17xfx0pa` FOREIGN KEY (`professor_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (91,'01','고급웹프로그래밍1',27),(92,'02','소프트웨어프로젝트',27),(93,'001','자료구조',29),(94,'002','알고리즘 수정',29),(95,'00001','C프로그래밍',28),(97,'03','고급 자바프로그래밍',27);
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-10 12:12:22


-- MySQL dump 10.13  Distrib 5.7.12, for osx10.9 (x86_64)
--
-- Host: allan.kr    Database: drcode
-- ------------------------------------------------------
-- Server version	5.6.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `department_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'소프트웨어공학과'),(5,'정보통신공학과'),(6,'컴퓨터공학과');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-10 12:12:20


-- MySQL dump 10.13  Distrib 5.7.12, for osx10.9 (x86_64)
--
-- Host: allan.kr    Database: drcode
-- ------------------------------------------------------
-- Server version	5.6.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `assignment`
--

DROP TABLE IF EXISTS `assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assignment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `answer` varchar(255) DEFAULT NULL,
  `assignment_name` varchar(255) DEFAULT NULL,
  `content` text,
  `created_at` datetime DEFAULT NULL,
  `limit_time` datetime DEFAULT NULL,
  `extension_time` datetime DEFAULT NULL,
  `subject_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5u4sux7c3hs5riuq47tlcty68` (`subject_id`),
  CONSTRAINT `FK_5u4sux7c3hs5riuq47tlcty68` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignment`
--

LOCK TABLES `assignment` WRITE;
/*!40000 ALTER TABLE `assignment` DISABLE KEYS */;
INSERT INTO `assignment` VALUES (57,'','작업 코드 제출 #1','<b><span style=\"font-size: 12pt;\">작업일지를 <span style=\"font-size: 18pt;\">한글파일</span>로 작성 후 제출하세요.</span></b>','2016-06-23 14:29:00','2016-06-23 14:28:00','2016-06-24 14:29:00',92),(58,'','작업 코드 제출 #2','<p><span style=\"font-size: 14pt;\">작업 일지를 작성하고 제출하세요.</span></p>','2016-06-23 14:34:00','2016-06-29 14:33:00','2016-06-30 14:34:00',92),(59,'','병합 정렬 과제','<p><span style=\"font-size: 18pt;\">병합 정렬을 구현하여 <span style=\"color: rgb(255, 0, 0);\">소스코드</span>를 제출하세요.</span></p>','2016-06-23 14:36:00','2016-06-24 14:36:00','2016-06-27 14:37:00',95),(60,'','해시테이블','<p><span style=\"font-size: 18pt;\">해시테이블 구현하여 <span style=\"color: rgb(255, 108, 0);\">소스코드</span>를 제출하세요.</span></p>','2016-06-23 14:40:00','2016-06-24 14:40:00','2016-06-26 14:41:00',93),(62,'','이진검색트리 삭제','<p style=\"font-family: &quot;맑은 고딕&quot;, Dotum; font-size: 13.3333px; line-height: normal; background-color: rgb(255, 255, 255);\">hw5_1의 이진검색트리에 삭제 기능을 추가한 프로그램을 작성하시오.</p><p style=\"font-family: &quot;맑은 고딕&quot;, Dotum; font-size: 13.3333px; line-height: normal; background-color: rgb(255, 255, 255);\">- main에서는 다음을 수행한다.</p><p style=\"font-family: &quot;맑은 고딕&quot;, Dotum; font-size: 13.3333px; line-height: normal; background-color: rgb(255, 255, 255);\">(1) 비어있는 이진검색트리를 생성<br>(2) 다음과 같은 메뉴를 반복하여 제공</p><p style=\"font-family: &quot;맑은 고딕&quot;, Dotum; font-size: 13.3333px; line-height: normal; background-color: rgb(255, 255, 255);\">&nbsp;&nbsp;&nbsp; 1:회원가입 2:포인트조회 3:포인트적립 4:전체조회 5:회원수조회 6:회원탈퇴 7:종료</p><p style=\"font-family: &quot;맑은 고딕&quot;, Dotum; font-size: 13.3333px; line-height: normal; background-color: rgb(255, 255, 255);\">각 메뉴 선택시 할 일은 다음과 같다.<br>1:회원가입 - 사용자로부터 아이디를 입력받아 포인트가 0인 새로운 회원을 트리에 삽입<br>2:포인트조회 - 사용자로부터 아이디를 입력받아 존재하면 포인트를 조회하여 출력, 존재하지 않으면 실패를 알림<br>3:포인트적립 - 사용자로부터 아이디와 포인트적립액 입력받아 존재하면 포인트를 적립액만큼 올려줌, 존재하지 않으면 실패를 알림<br>4:전체조회 - 트리에 저장된 모든 회원 정보를 출력<br>5:회원수조회 - 트리에 저장된 회원 수를 출력<br>6:회원탈퇴 - 사용자로부터 아이디를 입력받아 존재하면 삭제, 존재하지 않으면 실패를 알림 ***** hw5_2에서 추가하는 부분<br>7:종료 - 반복을 마침</p>','2016-06-23 14:44:00','2016-06-24 14:44:00','2016-06-29 14:45:00',94),(63,'','소스코드 제출 과제입니다.','<p>소스코드를 제출하세요.</p>','2016-06-28 13:51:00','2016-06-28 13:50:00','2016-06-30 13:51:00',92);
/*!40000 ALTER TABLE `assignment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-10 12:12:21


-- MySQL dump 10.13  Distrib 5.7.12, for osx10.9 (x86_64)
--
-- Host: allan.kr    Database: drcode
-- ------------------------------------------------------
-- Server version	5.6.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `assignment_user`
--

DROP TABLE IF EXISTS `assignment_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assignment_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `score` double DEFAULT NULL,
  `assignment_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `code` text NOT NULL,
  `submit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_gde6t5n6go763b80nb0vx0sa6` (`assignment_id`),
  KEY `FK_73gucd2d8977alk8go94sgnse` (`user_id`),
  CONSTRAINT `FK_73gucd2d8977alk8go94sgnse` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_gde6t5n6go763b80nb0vx0sa6` FOREIGN KEY (`assignment_id`) REFERENCES `assignment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignment_user`
--

LOCK TABLES `assignment_user` WRITE;
/*!40000 ALTER TABLE `assignment_user` DISABLE KEYS */;
INSERT INTO `assignment_user` VALUES (30,NULL,58,2,'public class Main {\n\n	public static void main(String args[]) {\n\n\n\n\n\n        System.out.println(\"hello\");\n\n\n\n\n	 }\n}','2016-06-23 06:51:06'),(31,NULL,58,3,'import java.util.*;\r\n\r\npublic class Main {\r\n\r\n	public static void main(String args[]) {\r\n	    \r\n	System.out.println(\"입력 받은 값의 제곱값을 출력하는 예제입니다.\");\r\n\r\n    Scanner input = new Scanner(System.in);\r\n    \r\n    int num=\"dkssud하세요\";\r\n    \r\n    for(int i=0; i<3; i++) {\r\n        System.out.println(\"숫자를 입력하세요.\");\r\n        num = input.nextInt();\r\n        System.out.println(num + \"의 제곱은? = \" + num*num);\r\n    }\r\n    \r\n    System.out.println(\"프로그램 종료\");\r\n\r\n\r\n	 }\r\n}','2016-06-23 09:28:55'),(32,NULL,60,3,'public class Main {\n\n	public static void main(String args[]) {\n\n        System.out.println(\"테스트\");\n\n\n\n\n\n\n\n\n	 }\n}','2016-06-24 07:37:45');
/*!40000 ALTER TABLE `assignment_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-10 12:12:21
