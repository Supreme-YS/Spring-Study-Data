package com.supreme.board.controller;

import com.supreme.board.domain.dto.BoardDto;
import com.supreme.board.domain.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BoardController {

    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/board")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum) {
        // 페이징
        List<BoardDto> boardDtoList = boardService.getBoardList(pageNum);
        Integer[] pageList = boardService.getPageList(pageNum);
        model.addAttribute("boardList", boardDtoList);
        model.addAttribute("pageList", pageList);
        // 페이징 끝

        return "board/list";
    }

    @GetMapping("/post")
    public String write() {
        return "board/write";
    }

    @PostMapping("/post")
    public String write(BoardDto boardDto) {
        boardService.savePost(boardDto);
        return "redirect:/";
    }

    @GetMapping("/post/{no}")
    public String detail(@PathVariable("no") Long id, Model model) {
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("boardDto", boardDto);
        return "board/detail";
    }

    // 일단 데이터를 유지시킨 채로 페이지를 이동해서 그 쪽에서 처리할 예정인가봄
    @GetMapping("/post/edit/{no}")
    public String edit(@PathVariable("no") Long id, Model model) {
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("boardDto", boardDto);
        System.out.println("boardDto 첫 번 째= " + boardDto);
        return "board/update";
    }

    // 이게 왜 PUT 요청인지 모르겠다만..
    @PostMapping("/post/edit/{no}")
    public String update(BoardDto boardDto) {
        System.out.println("boardDto 두 번 째 = " + boardDto);
        boardService.savePost(boardDto);
        return "redirect:/";
    }

    @PostMapping("/post/{no}")
    public String delete(@PathVariable("no") Long id) {
        boardService.deletePost(id);
        return "redirect:/";
    }

    @GetMapping("/board/search")
    public String search(@RequestParam(value = "keyword") String keyword, Model model) {
        List<BoardDto> boardDtoList = boardService.searchPosts(keyword);
        model.addAttribute("boardList", boardDtoList);
        return "board/list";
    }
}

