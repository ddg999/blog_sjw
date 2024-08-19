package com.tenco.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tenco.blog.model.Board;
import com.tenco.blog.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	BoardService boardService;

	@GetMapping("/")
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

	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}

	@GetMapping("/board/{id}/updateForm")
	public String updateForm(@PathVariable(name = "id") Integer id, Model model) {
		Board board = boardService.readBoardById(id);
		model.addAttribute("board", board);
		return "board/updateForm";
	}

	@PostMapping("/board/save")
	public String save(Board board, Model model) {
		if (board.getTitle() == null || board.getTitle().length() > 20 || board.getContent() == null || board.getContent().length() > 20) {
			return "errorPage";
		}
		boardService.createBoard(board);
		return "redirect:/";
	}

	@PostMapping("/board/{id}/update")
	public String update(Board board, @PathVariable(name = "id") Integer id) {
		if (board.getTitle() == null || board.getTitle().length() > 20 || board.getContent() == null || board.getContent().length() > 20) {
			return "errorPage";
		}
		boardService.updateBoard(board);
		return "redirect:/";
	}

	@PostMapping("/board/{id}/delete")
	public String delete(@PathVariable(name = "id") Integer id) {
		boardService.updatePostNumber(id);
		boardService.deleteBoard(id);
		return "redirect:/";
	}
}
