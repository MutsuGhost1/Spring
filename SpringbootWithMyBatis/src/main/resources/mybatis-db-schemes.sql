CREATE TABLE `user` (
                        `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                        `username` varchar(64) NOT NULL DEFAULT '',
                        `mobile` varchar(16) NOT NULL DEFAULT '',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `order` (
                         `order_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
                         `user_id` int(10) unsigned NOT NULL DEFAULT '0',
                         `order_no` varchar(16) NOT NULL DEFAULT '',
                         `money` float(10,2) unsigned DEFAULT '0.00',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;