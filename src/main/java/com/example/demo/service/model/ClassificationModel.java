package com.example.demo.service.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ClassificationModel {

	/***/
	private Integer id;
	/**
	 * 上级分类id
	 */
	private Integer parentId;
	/***/
	private String title;

	private Integer type;

	private Long createTime;

	private Long modifyTime;

	private String creator;

	private List<ClassificationModel> nodeClassification;


}
