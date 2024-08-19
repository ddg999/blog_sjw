package com.tenco.blog.model;

import java.security.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Board {
	private Integer id;
	private String author;
	private String title;
	private String content;
	private Timestamp createdAt;

	private Integer postNumber;
}
