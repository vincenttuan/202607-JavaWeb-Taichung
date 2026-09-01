package model.entity;

/**
 use shopping;
  
 -- 建立商品資料表 --
 create table if not exists product (
 	id int primary key auto_increment,
 	name varchar(50) not null,
 	category enum('BURGER', 'SNACK', 'DRINK') not null,
 	price int not null,
 	stock int not null default 0,
 	image_base64 longtext,
 	image_type varchar(50),
 	create_at timestamp default current_timestamp,
 	update_at timestamp default current_timestamp on update current_timestamp
 );
 */

public class Product {

}
