package com.tenco.blog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenco.blog.model.Board;
import com.tenco.blog.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	@Transactional
	public void createBoard(Board board) {
		int result = 0;
		try {
			result = boardRepository.insert(board);
		} catch (Exception e) {
			System.out.println("알수 없는 오류");
		}
		if (result != 1) {
			System.out.println("계좌 생성 실패");
		}
	}

	@Transactional
	public void updateBoard(Board board) {
		boardRepository.updateById(board);
	}

	@Transactional
	public void deleteBoard(Integer id) {
		boardRepository.deleteById(id);
	}

	@Transactional
	public List<Board> readAllBoard(int page, int size) {
		List<Board> boardList = new ArrayList<>();
		int limit = size;
		int offset = (page - 1) * size;
		boardList = boardRepository.readAllBoard(limit, offset);
		return boardList;
	}

	@Transactional
	public int countAllBoard() {
		return boardRepository.countAllBoard();
	}

	@Transactional
	public Board readBoardById(Integer id) {
		return boardRepository.readBoardById(id);
	}

	@Transactional
	public void updatePostNumber(Integer deletePostNumber) {
		boardRepository.updatePostNumber(deletePostNumber);
	}
}
