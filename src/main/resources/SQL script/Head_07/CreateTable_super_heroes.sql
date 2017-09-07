CREATE TABLE `super_heroes` (
  `name` varchar(20) NOT NULL,
  `power` varchar(50) NOT NULL default '',
  `weakness` varchar(20) NOT NULL default '',
  `city` varchar(20) NOT NULL,
  `country` varchar(20) NOT NULL,
  `arch_enemy` varchar(50) NOT NULL,
  `initials` varchar(2) NOT NULL
)