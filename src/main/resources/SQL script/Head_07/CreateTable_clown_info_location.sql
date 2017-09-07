CREATE TABLE `clown_info_location` (
  `id` int(11) NOT NULL,
  `location_id` int(11) NOT NULL,
  `whens` varchar(50) NOT NULL,
  KEY `id` (`id`),
  KEY `location_id` (`location_id`),
  CONSTRAINT `clown_info_location_ibfk_1` FOREIGN KEY (`id`) REFERENCES `clown_clown_info` (`id`),
  CONSTRAINT `clown_info_location_ibfk_2` FOREIGN KEY (`location_id`) REFERENCES `clown_location` (`location_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8