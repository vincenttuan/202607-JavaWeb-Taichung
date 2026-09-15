package model.entity;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * 會員訂單資料 table 建立
  
 use shopping;
 
 create table if not exists member_order (
 	id int primary key auto_increment,
 	member_id int not null,
 	total_amount int not null,
 	status enum('PENDING', 'PAID', 'CANCEL') not null default 'PENDING',
 	create_at timestamp default current_timestamp,
 	update_at timestamp default current_timestamp on update current_timestamp,
 	
 	constraint fk_member_order_member foreign key (member_id) references member(id)
 	
 );
 
  
 * */
@Data
public class MemberOrder {
	private Integer id;
	private Integer memberId;
	private Integer totalAmount;
	private String status;
	private LocalDateTime createAt;
	private LocalDateTime updateAt;
}
