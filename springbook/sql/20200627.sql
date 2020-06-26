CREATE TABLE `user` (
  `username` varchar(255) NOT NULL,
  `nick_name` varchar(255) DEFAULT NULL COMMENT '昵称',
  `password` varchar(255) NOT NULL,
  `role_id` int(2) NOT NULL,
  `last_password_change` bigint(13) NOT NULL,
  `enable` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) NOT NULL,
  `book_name` varchar(255) NOT NULL,
  `book_price` varchar(255) NOT NULL,
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `book_id` (`book_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;


