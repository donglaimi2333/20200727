package com.example.demo.controller.viewobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FavoritesVO {

	/***/
	private Integer id;
	/***/
	private Integer user;
	/***/
	private Integer document;
}
