package com.tenco.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tenco.blog.handler.exception.DataDeliveryException;
import com.tenco.blog.model.Board;
import com.tenco.blog.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	// 글 목록 페이지
	@GetMapping({ "/", "/list" })
	public String index(@RequestParam(name = "size", defaultValue = "5") int size, @RequestParam(name = "page", defaultValue = "1") int page, Model model) {

		int totalRecords = boardService.countAllBoard();
		int totalPages = (int) Math.ceil((double) totalRecords / size);

		List<Board> boardList = boardService.readAllBoard(page, size);
		if (boardList.isEmpty()) {
			model.addAttribute("boardList", null);
		} else {
			model.addAttribute("boardList", boardList);
		}

		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("size", size);
		return "index";
	}

	// 글 작성 페이지
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}

	// 글 수정 페이지
	@GetMapping("/board/{id}/updateForm")
	public String updateForm(@PathVariable(name = "id") Integer id, Model model) {
		Board board = boardService.readBoardById(id);
		model.addAttribute("board", board);
		return "board/updateForm";
	}

	// 글 작성
	@PostMapping("/board/save")
	public String save(Board board, Model model) {
		if (board.getTitle() == null || board.getTitle().length() > 20 || board.getContent() == null || board.getContent().length() > 20) {
			throw new DataDeliveryException("제목 또는 내용은 20자 이내로 작성해야합니다.", HttpStatus.LENGTH_REQUIRED);
		}
		boardService.createBoard(board);
		return "redirect:/";
	}

	// 글 수정
	@PostMapping("/board/{id}/update")
	public String update(Board board, @PathVariable(name = "id") Integer id) {
		if (board.getTitle() == null || board.getTitle().length() > 20 || board.getContent() == null || board.getContent().length() > 20) {
			throw new DataDeliveryException("제목 또는 내용은 20자 이내로 작성해야합니다.", HttpStatus.LENGTH_REQUIRED);
		}
		boardService.updateBoard(board);
		return "redirect:/";
	}

	// 글 삭제
	@PostMapping("/board/{id}/delete")
	public String delete(@PathVariable(name = "id") Integer id) {
		boardService.deleteBoard(id);
		return "redirect:/";
	}
}
