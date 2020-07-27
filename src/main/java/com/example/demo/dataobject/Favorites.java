package com.example.demo.dataobject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * favorites实体类
 * 
 * @author 
 *
 */
@Getter
@Setter
@ToString
public class Favorites {
	/***/
	private Integer id; 
	/***/
	private Long user;
	/***/
	private Integer document; 
	/**数据逻辑删除字段 1：数据正常，0：数据被删除*/
	private Integer status; 
	/***/
	private Integer modifyTime; 
	/***/
	private Integer createTime;
}
