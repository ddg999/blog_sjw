package com.tenco.blog.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tenco.blog.model.Board;

@Mapper
public interface BoardRepository {

	// 글 작성
	public int insert(Board board);

	// 글 수정
	public int updateById(Board board);

	// 글 삭제
	public int deleteById(Integer id);

	// 글 목록
	public List<Board> readAllBoard(@Param("limit") int limit, @Param("offset") int offset);

	// 글 찾기
	public Board readBoardById(Integer id);

	// 글 갯수
	public int countAllBoard();
}
