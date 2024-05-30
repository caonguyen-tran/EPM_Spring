-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: epmdb
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `start_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `end_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `active` bit(1) DEFAULT b'1',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `slots` int NOT NULL,
  `close` bit(1) DEFAULT b'0',
  `faculty_id` int NOT NULL,
  `semester_id` int NOT NULL,
  `term_id` int NOT NULL,
  `created_user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `end_date_UNIQUE` (`end_date`),
  KEY `fk_activity_term_idx` (`term_id`),
  KEY `fk_activity_semester_idx` (`semester_id`),
  KEY `fk_activity_faculty_idx` (`faculty_id`),
  KEY `fk_created_user_idx` (`created_user_id`),
  CONSTRAINT `fk_activity_faculty` FOREIGN KEY (`faculty_id`) REFERENCES `faculty` (`id`),
  CONSTRAINT `fk_activity_semester` FOREIGN KEY (`semester_id`) REFERENCES `semester` (`id`),
  CONSTRAINT `fk_activity_term` FOREIGN KEY (`term_id`) REFERENCES `term` (`id`),
  CONSTRAINT `fk_assistant` FOREIGN KEY (`created_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (4,'TẬP HUẤN NCKH CHỦ ĐỀ \"PHƯƠNG PHÁP NGHIÊN CỨU KHOA HỌC\"','2024-05-16 02:00:00','2024-05-17 02:00:00','Hoạt động được tổ chức cho tất cả sinh viên trường Đại Học Mở thành phố Hồ Chí Minh',NULL,'https://res.cloudinary.com/dndakokcz/image/upload/v1716640766/lflqzauyyavx8jqoenwl.jpg',500,NULL,2,5,3,1),(5,'Hội thảo định hướng nghiên cứu khoa học sinh viên khoa KTKT','2024-05-01 01:30:00','2024-05-02 02:00:00','Hoạt động được khoa Kinh tế tổ chức',NULL,'https://res.cloudinary.com/dndakokcz/image/upload/v1716640861/misps2f1nubwpx7wmwnk.jpg',100,NULL,6,5,3,1),(6,'Cuộc thi Giảng Đường Pháp Luật','2024-05-30 02:00:00','2024-05-31 02:00:00','Hoạt động được tổ chức cho tất cả sinh viên trường Đại học Mở thành phố Hồ Chí Minh có am hiểu về luật',NULL,'https://res.cloudinary.com/dndakokcz/image/upload/v1716640972/otssir8faxwboqrufwft.jpg',150,NULL,3,5,3,1),(7,'Chuyên đề DevOps và MLOps','2024-06-06 04:00:00','2024-06-07 04:00:00','Chuyên đề DevOps và MLOps dành cho sinh viên Khoa Công Nghệ Thông Tin trường Đại học Mở thành phố Hồ Chí Minh',NULL,'https://res.cloudinary.com/dndakokcz/image/upload/v1716648743/nrk46d6vwzsflvtd0iih.jpg',60,NULL,1,6,3,1),(8,'Chuyên đề Tiny Machine Learning','2024-06-10 03:30:00','2024-06-11 03:30:00','Tiny Machine Learning được tổ chức bởi khoa Công Nghệ Thông Tin',NULL,'https://res.cloudinary.com/dndakokcz/image/upload/v1716648812/yhxzwbkqbhilw8uylbov.jpg',60,NULL,1,6,3,1),(9,'Buổi Internship Orientation dành cho sinh viên năm 3, năm 4 chuyên ngành Tiếng Anh Thương Mại chuẩn bị đi thực tập','2024-06-12 03:30:00','2024-06-13 03:30:00','Hoạt động tổ chức cho sinh viên chuẩn bị thực tập',_binary '','https://res.cloudinary.com/dndakokcz/image/upload/v1716648812/yhxzwbkqbhilw8uylbov.jpg',60,_binary '\0',1,6,3,1);
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assistant`
--

DROP TABLE IF EXISTS `assistant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assistant` (
  `id` int NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  KEY `fk_user_assistant_idx` (`user_id`),
  CONSTRAINT `fk_user_assistant` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assistant`
--

LOCK TABLES `assistant` WRITE;
/*!40000 ALTER TABLE `assistant` DISABLE KEYS */;
/*!40000 ALTER TABLE `assistant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classes`
--

DROP TABLE IF EXISTS `classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `faculty_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_faculty_classes_idx` (`faculty_id`),
  CONSTRAINT `fk_faculty_classes` FOREIGN KEY (`faculty_id`) REFERENCES `faculty` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classes`
--

LOCK TABLES `classes` WRITE;
/*!40000 ALTER TABLE `classes` DISABLE KEYS */;
INSERT INTO `classes` VALUES (1,'DH20CS01',1),(2,'DH20CS02',1),(3,'DH21CS01',1),(4,'DH21CS02',1),(5,'DH21IT01',1),(6,'DH21IT02',1),(7,'DH22CS01',1),(8,'DH22CS02',1),(9,'DH22IT01',1),(10,'DH22IT02',1);
/*!40000 ALTER TABLE `classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `comment_parent` int DEFAULT NULL,
  `activity_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_comment_activity_idx` (`activity_id`),
  KEY `fk_comment_parent_idx` (`comment_parent`),
  KEY `fk_user_comment_idx` (`user_id`),
  CONSTRAINT `fk_comment_activity` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`),
  CONSTRAINT `fk_comment_parent` FOREIGN KEY (`comment_parent`) REFERENCES `comment` (`id`),
  CONSTRAINT `fk_user_comment` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty`
--

DROP TABLE IF EXISTS `faculty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faculty` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty`
--

LOCK TABLES `faculty` WRITE;
/*!40000 ALTER TABLE `faculty` DISABLE KEYS */;
INSERT INTO `faculty` VALUES (1,'Công nghệ thông tin','1996-10-02'),(2,'Công nghệ sinh học','1996-11-11'),(3,'Đông Nam Á học','1990-12-01'),(4,'Logistic - Quản lý chuỗi cung ứng','1990-09-09'),(5,'Marketing','1989-06-25'),(6,'Kinh tế học','1987-09-24');
/*!40000 ALTER TABLE `faculty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `join_activity`
--

DROP TABLE IF EXISTS `join_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `join_activity` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date_register` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `rollup` bit(1) DEFAULT b'0',
  `proof_joining` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `note` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `accept` bit(1) DEFAULT b'0',
  `activity_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_join_activity_idx` (`activity_id`),
  KEY `fk_join_activity_user_idx` (`user_id`),
  CONSTRAINT `fk_join_activity` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`),
  CONSTRAINT `fk_join_activity_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `join_activity`
--

LOCK TABLES `join_activity` WRITE;
/*!40000 ALTER TABLE `join_activity` DISABLE KEYS */;
INSERT INTO `join_activity` VALUES (2,'2024-05-25 14:48:18',_binary '\0',NULL,NULL,_binary '\0',4,4),(3,'2024-05-25 14:48:18',_binary '\0',NULL,NULL,_binary '\0',4,5),(4,'2024-05-25 14:48:49',_binary '\0',NULL,NULL,_binary '\0',5,3),(5,'2024-05-25 14:48:49',_binary '\0',NULL,NULL,_binary '\0',5,4),(6,'2024-05-25 14:49:12',_binary '\0',NULL,NULL,_binary '\0',6,3),(7,'2024-05-25 14:49:12',_binary '\0',NULL,NULL,_binary '\0',6,5),(8,'2024-05-25 14:49:50',_binary '','https://res.cloudinary.com/dndakokcz/image/upload/v1716640766/lflqzauyyavx8jqoenwl.jpg',NULL,_binary '\0',6,4),(9,'2024-05-25 14:54:55',_binary '','https://res.cloudinary.com/dndakokcz/image/upload/v1716640766/lflqzauyyavx8jqoenwl.jpg',NULL,_binary '\0',8,3),(10,'2024-05-25 14:54:55',_binary '','https://res.cloudinary.com/dndakokcz/image/upload/v1716640766/lflqzauyyavx8jqoenwl.jpg','tet tet tet etetet',_binary '\0',8,6),(12,'2024-05-25 14:55:01',_binary '','https://res.cloudinary.com/dndakokcz/image/upload/v1716640766/lflqzauyyavx8jqoenwl.jpg',NULL,_binary '\0',7,4),(13,'2024-05-25 14:55:22',_binary '','https://res.cloudinary.com/dndakokcz/image/upload/v1716640766/lflqzauyyavx8jqoenwl.jpg',NULL,_binary '\0',8,5);
/*!40000 ALTER TABLE `join_activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `liked`
--

DROP TABLE IF EXISTS `liked`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `liked` (
  `id` int NOT NULL AUTO_INCREMENT,
  `active` bit(1) DEFAULT b'0',
  `activity_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_liked_activity_idx` (`activity_id`),
  KEY `fk_user_idd_idx` (`user_id`),
  CONSTRAINT `fk_liked_activity` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`),
  CONSTRAINT `fk_user_idd` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `liked`
--

LOCK TABLES `liked` WRITE;
/*!40000 ALTER TABLE `liked` DISABLE KEYS */;
/*!40000 ALTER TABLE `liked` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `missing_report`
--

DROP TABLE IF EXISTS `missing_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `missing_report` (
  `id` int NOT NULL AUTO_INCREMENT,
  `proof_joining` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `note` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_id` int NOT NULL,
  `activity_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_report_activity_idx` (`activity_id`),
  KEY `fk_user_id_mr_idx` (`user_id`),
  CONSTRAINT `fk_missing_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_report_activity` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `missing_report`
--

LOCK TABLES `missing_report` WRITE;
/*!40000 ALTER TABLE `missing_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `missing_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score`
--

DROP TABLE IF EXISTS `score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `score` (
  `id` int NOT NULL AUTO_INCREMENT,
  `score_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `score_value` int NOT NULL,
  `number_of_score` int NOT NULL,
  `activity_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_score_activity_idx` (`activity_id`),
  CONSTRAINT `fk_score_activity` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score`
--

LOCK TABLES `score` WRITE;
/*!40000 ALTER TABLE `score` DISABLE KEYS */;
INSERT INTO `score` VALUES (5,'Tham gia','Điểm tham gia',5,500,4),(6,'Tham gia','Điểm tham gia hoạt động',5,100,5),(7,'Tham gia','Điểm tham gia',5,150,6),(8,'Tham gia','Điểm tham gia',5,60,7);
/*!40000 ALTER TABLE `score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score_student`
--

DROP TABLE IF EXISTS `score_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `score_student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `score_id` int NOT NULL,
  `join_activity_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_score_student_idx` (`score_id`),
  KEY `activity_join_fk_idx` (`join_activity_id`),
  CONSTRAINT `activity_join_fk` FOREIGN KEY (`join_activity_id`) REFERENCES `join_activity` (`id`),
  CONSTRAINT `fk_score_student` FOREIGN KEY (`score_id`) REFERENCES `score` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score_student`
--

LOCK TABLES `score_student` WRITE;
/*!40000 ALTER TABLE `score_student` DISABLE KEYS */;
/*!40000 ALTER TABLE `score_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semester`
--

DROP TABLE IF EXISTS `semester`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `semester` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `year_study` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semester`
--

LOCK TABLES `semester` WRITE;
/*!40000 ALTER TABLE `semester` DISABLE KEYS */;
INSERT INTO `semester` VALUES (1,'Kì 1','Kì 1 năm 2023','2023'),(2,'Kì 2','Kì 2 năm 2023','2023'),(3,'Kì 3','Kì 3 năm 2023','2023'),(4,'Kì 1 ','Kì 1 năm 2024','2024'),(5,'Kì 2','Kì 2 năm 2024','2024'),(6,'Kì 3','Kì 3 năm 2024','2024'),(7,'Kì 1','Kì 1 năm 2025','2025'),(8,'Kì 2','Kì 2 năm 2025','2025'),(9,'Kì 3','Kì 3 năm 2025','2025');
/*!40000 ALTER TABLE `semester` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `lastname` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `gender` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `day_of_birth` date NOT NULL,
  `phone_number` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `class_id` int NOT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  KEY `fk_student_class_idx` (`class_id`),
  KEY `fk_user_id_idx` (`user_id`),
  CONSTRAINT `fk_student_class` FOREIGN KEY (`class_id`) REFERENCES `classes` (`id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'Nhân','Tô Trọng','Nam','2003-09-17','0378461282','992 Phạm Văn Đồng, phường 8, quận Gò Vấp','',3,3),(2,'Nguyên','Trần Cao','Nam','2003-11-10','0374812888','153/35 Lê Văn Thọ, phường 8, quận Gò Vấp','',4,4),(3,'Linh','Phạm Thanh','Nữ','2002-03-12','0379847287','89/2 Hoàng Minh Giám, phường 6, quận Bình Thạnh','',1,5),(4,'Lâm','Hoàng Anh','Nam','2002-05-05','0374827238','324 Lê Đức Thọ, phường 11, quận Gò Vấp','',2,6),(5,'Đức','Trần Anh','Nam','2002-03-03','0893729993','84/2 Phan Văn Trị, phường 8, quận Gò Vấp','',2,NULL),(6,'Nguyên','Nguyễn Cao','Nam','2003-11-09','0836824662','21 Phan Văn Trị, phường 1, quận Bình Thạnh','',4,NULL),(7,'Tiến','Trần Nam','Nam','2004-12-01','0374827723','675 Phạm Văn Đồng, phường 9, quận Gò Vấp','',9,NULL);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `term`
--

DROP TABLE IF EXISTS `term`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `term` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(160) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `term`
--

LOCK TABLES `term` WRITE;
/*!40000 ALTER TABLE `term` DISABLE KEYS */;
INSERT INTO `term` VALUES (1,'Điều 1','Đánh giá về ý thức học tập'),(2,'Điều 2','Đánh giá về ý thức, kết quả chấp hành nội quy, quy định của nhà trường'),(3,'Điều 3','Đánh giá về ý thức và kết quả tham gia các hoạt động chính trị - xã hội, văn hóa, văn nghệ, thể thao, phòng chống các tệ nạn xã hội.'),(4,'Điều 4','Đánh giá về phẩm chất công dân và quan hệ với cộng đồng'),(5,'Điều 5','Đánh giá về ý thức và kết quả tham gia phụ trách lớp học, các đoàn thể, tổ chức khác trong nhà trường'),(6,'Điều 6','Các trường hợp đặc biệt');
/*!40000 ALTER TABLE `term` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(80) NOT NULL,
  `avatar` varchar(255) NOT NULL,
  `active` bit(1) NOT NULL DEFAULT b'1',
  `user_role_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_role_id_idx` (`user_role_id`),
  CONSTRAINT `fk_user_role_id` FOREIGN KEY (`user_role_id`) REFERENCES `user_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin1','$2a$10$yHS.syZ06jQf1fVwZ1ycTu5haEyPm4Lr3LFqXEpzGuwkQVyHi1cg2','https://res.cloudinary.com/dndakokcz/image/upload/v1716640220/pdigqnwgkka5tawqblh1.jpg',_binary '',1),(2,'admin2','$2a$10$FzM11Tqcd2YcgHcAQAfO8O.tezviKq1WRrGYZImPblEExvRRr8i/S','https://res.cloudinary.com/dndakokcz/image/upload/v1716640239/lw88mz32gvhnxqujk7oi.jpg',_binary '',1),(3,'totrongnhan2003','$2a$10$H90rzJposZji3HH9vVvh/OrhF/2hC0ElvwZV9UpPVvtuqWkLs8aLi','https://res.cloudinary.com/dndakokcz/image/upload/v1716644647/zcicm5gbqbyt6bkts7nv.jpg',_binary '',3),(4,'trancaonguyen2003','$2a$10$p/CfFk/rm4axTNBIHYd46uASfSLQRIx3MNksykylkn/A9CbT1fyHu','https://res.cloudinary.com/dndakokcz/image/upload/v1716644676/r3b2esheoharqvvealp0.jpg',_binary '',3),(5,'phamthanhlinh2002','$2a$10$WEmdGfmlKLYveM8vkMstfuzFLshs4XiLhTMUY.bL3FPz9fb20tlJ6','https://res.cloudinary.com/dndakokcz/image/upload/v1716644708/gbclemlszczj65tibom2.jpg',_binary '',3),(6,'lamhoang','$2a$10$d5UujX7kqtwAIMdARu0iEe2lwLoMNZFMsbMjHe8pCT5BOYEJgCHLC','https://res.cloudinary.com/dndakokcz/image/upload/v1716644736/wjhe4frrbgrqzref8b1v.jpg',_binary '',3);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_role` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_ASSISTANT'),(3,'ROLE_STUDENT');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-30 23:07:58
