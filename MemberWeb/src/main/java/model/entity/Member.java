package model.entity;

import java.util.Date;

import lombok.Data;

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
