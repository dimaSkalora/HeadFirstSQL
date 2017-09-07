CREATE TABLE `clown_info_activities` (
  `id` int(11) NOT NULL,
  `activity_id` int(11) NOT NULL,
  KEY `id` (`id`),
  KEY `activity_id` (`activity_id`),
  CONSTRAINT `clown_info_activities_ibfk_1` FOREIGN KEY (`id`) REFERENCES `clown_clown_info` (`id`),
  CONSTRAINT `clown_info_activities_ibfk_2` FOREIGN KEY (`activity_id`) REFERENCES `clown_activities` (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8