package com.example.demo.dataobject;

import lombok.*;

/**
 * classification实体类
 * 
 * @author 
 *
 */

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Classification {
	/***/
	private Integer id;
	/***/
	private Integer parentId;
	/***/
	private String title; 
	/***/
	private String department;
	/**标识文章状态 1：正常，0：隐藏*/
	private Integer state;
	/**表示分类是否可以被删除，隐藏等 1：不可隐藏，不可删除*/
	private Integer type;
	/**数据逻辑删除字段 1：数据正常，0：数据被删除*/
	private Integer status;
	/** 创建人*/
	private String creator;
	/***/
	private Long modifyTime;
	/***/
	private Long createTime;
}
