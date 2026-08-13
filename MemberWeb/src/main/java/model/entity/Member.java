package model.entity;

import java.util.Date;

import lombok.Data;

/**
   -- 建立資料庫 --
	create database if not exists shopping
	default character set utf8mb4
	collate utf8mb4_unicode_ci;
	
   -- 建立資料表 --	
	create table if not exists member (
		id int primary key auto_increment,
    	username varchar(50) unique not null,
    	password varchar(500) not null,
    	email varchar(100) not null,
    	role varchar(50) not null,
    	create_time datetime default current_timestamp
	);
	
 * */

@Data
public class Member {
	
	private Integer id;
	private String username;
	private String hash;
	private String salt;
	private String email;
	private String role; // 角色: ADMIN/USER
	private Date createTime;
	
}
