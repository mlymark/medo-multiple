DROP TABLE IF EXISTS `user_role`; 
DROP TABLE IF EXISTS `resc_role`;  
DROP TABLE IF EXISTS `user`;  
DROP TABLE IF EXISTS `roles`; 
DROP TABLE IF EXISTS `resc`; 
DROP TABLE IF EXISTS `persistent_logins`;  


-- ----------------------------  
-- Table structure for roles  
-- ----------------------------  
 
CREATE TABLE `roles` (  
  `id` bigint(20) NOT NULL AUTO_INCREMENT,  
  `name` varchar(50) NOT NULL,  
  `descn` varchar(200) DEFAULT NULL,  
  PRIMARY KEY (`id`)  
)  ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
  
-- ----------------------------  
-- Records of roles  
-- ----------------------------  
INSERT INTO `roles`(`id`,`name`,`descn`) VALUES ('1', 'ROLE_USER', '商家用户');  
INSERT INTO `roles`(`id`,`name`,`descn`) VALUES ('2', 'ROLE_DRIVER', '送货员');  
INSERT INTO `roles`(`id`,`name`,`descn`) VALUES ('3', 'ROLE_INSTALLER', '安装员');
INSERT INTO `roles`(`id`,`name`,`descn`) VALUES ('4', 'ROLE_BUSNIESS', '商务人员');
INSERT INTO `roles`(`id`,`name`,`descn`) VALUES ('5', 'ROLE_PLATFORM_MANAGER', '平台管理');  


CREATE TABLE `resc` (  
  `id` bigint(20) NOT NULL AUTO_INCREMENT,  
  `name` varchar(50) NOT NULL,  
  `res_type` varchar(50) DEFAULT NULL,  
  `res_string` varchar(200) NOT NULL,  
  `descn` varchar(200) DEFAULT NULL,  
  PRIMARY KEY (`id`)  
)  ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
  
-- ----------------------------  
-- Records of resc  
-- ----------------------------  
INSERT INTO `resc`(`id`,`res_type`,`name`,`res_string`,`descn`) VALUES ('1', null, 'URL', '/views/content/**', '平台管理页面');  
INSERT INTO `resc`(`id`,`res_type`,`name`,`res_string`,`descn`) VALUES ('2', null, 'URL', '/views/index.xhtml', '测试页面'); 

  
-- ----------------------------  
-- Table structure for resc_role  
-- ----------------------------  
 
CREATE TABLE `resc_role` (  
  `resc_id` bigint(20) NOT NULL DEFAULT '0',  
  `role_id` bigint(20) NOT NULL DEFAULT '0',  
  PRIMARY KEY (`resc_id`,`role_id`),  
  KEY `fk_resc_role_roles` (`role_id`),  
  CONSTRAINT `fk_resc_role_roles` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),  
  CONSTRAINT `fk_resc_role_resc` FOREIGN KEY (`resc_id`) REFERENCES `resc` (`id`)  
) ENGINE=InnoDB DEFAULT CHARSET=utf8;  
  
-- ----------------------------  
-- Records of resc_role  
-- ----------------------------  
INSERT INTO `resc_role` VALUES (1, 1);  
INSERT INTO `resc_role` VALUES (1, 2);  
INSERT INTO `resc_role` VALUES (1, 3);
INSERT INTO `resc_role` VALUES (1, 4);
INSERT INTO `resc_role` VALUES (1, 5); 
INSERT INTO `resc_role` VALUES (2, 5);    


-- ----------------------------  
-- Table structure for user  
-- ----------------------------  
CREATE TABLE `user` (  
  `id` bigint(20) NOT NULL AUTO_INCREMENT,  
  `username` varchar(50) NOT NULL,  
  `type` int(11) DEFAULT 0,
  `password` varchar(50) NOT NULL,  
  `address` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `status` int(11) DEFAULT 0,  
  `descn` varchar(200) DEFAULT NULL,  
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`id`)  
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;  
  
  


-- ----------------------------  
-- Table structure for persistent_logins  
-- ----------------------------  

CREATE TABLE `persistent_logins` (
    `username` varchar(64) NOT NULL, 
    `series` varchar(64) PRIMARY KEY, 
    `token` varchar(64)NOT NULL, 
    `last_used` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8; 
