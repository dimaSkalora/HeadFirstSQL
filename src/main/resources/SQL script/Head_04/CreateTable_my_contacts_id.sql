CREATE TABLE `my_contacts` (
  `last_name` varchar(30) ,
  `first_name` varchar(20) ,
  `email` varchar(50) ,
  `gender` char(1),
  `birthday` date ,
  `profession` varchar(50),
  `location` varchar(50),
  `status` varchar(20),
  `interests` varchar(100),
  `seeking` varchar(100),
  #Здесь назначается первичный ключ таблицы. Синтаксис прост: за ключевыми словами
#PRIMARY KEY в круглых скобках указывается имя столбца, который будет первичным
#ключом — в нашем примере это новый столбец contact id.
  PRIMARY KEY (contact_id)
)