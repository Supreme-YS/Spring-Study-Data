package com.supreme.board.domain.service;

import com.supreme.board.domain.dto.BoardDto;
import com.supreme.board.domain.repository.BoardRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BoardService {

    private BoardRepository boardRepository;

    // boardRepository를 BoardService에 주입한다. 생성자 주입
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional // Dto를 통해 넘겨받은 데이터를 기반으로 레파지토리에 저장한다.
    public Long savePost(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();
    }
}