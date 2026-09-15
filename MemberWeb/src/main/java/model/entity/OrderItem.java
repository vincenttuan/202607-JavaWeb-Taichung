package model.entity;

/**
 * 訂單明細
 * 
 use shopping;
 
 create table if not exists order_item (
 	id int primary key auto_increment,
 	order_id int not null,
 	product_id int not null,
 	product_name varchar() not null,
 	unit_price int not null,
 	quantity int not null check (quantity > 0),
 	subtotal int not null,
 	create_at timestamp default current_timestamp,
 	update_at timestamp default current_timestamp on update current_timestamp,
 	
 	constraint fk_order_item_member_order foreign key (order_id) references member_order(id),
 	constraint fk_order_item_product foreign key (product_id) references product(id) 
 );
  
  
 * */

public class OrderItem {

}
