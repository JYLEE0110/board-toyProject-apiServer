package org.board.mvc.noticeboard.mappers;

import org.board.mvc.noticeboard.dto.RegistBoardDTO;

public interface BoardMapper {
    
    // 게시판 등록
    int registBoard(RegistBoardDTO registBoardDTO);

    // 게시판 삭제
    int removeBoard(Long bno);

}
