package org.board.mvc.noticeboard.service;

import org.board.mvc.noticeboard.dto.RegistBoardDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BoardService {

    // 게시판 등록
    Long registBoard(RegistBoardDTO registBoardDTO);

    // 게시판 삭제
    Long removeBoard(Long bno);
    
}
