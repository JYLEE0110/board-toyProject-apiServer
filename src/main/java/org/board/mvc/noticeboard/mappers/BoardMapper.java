package org.board.mvc.noticeboard.mappers;

import java.util.List;

import org.board.mvc.noticeboard.dto.ListBoardDTO;
import org.board.mvc.noticeboard.dto.ReadBoardDTO;
import org.board.mvc.noticeboard.dto.RegistBoardDTO;
import org.board.mvc.util.page.PageRequestDTO;
import org.board.mvc.util.page.PageResponseDTO;

public interface BoardMapper {
    
    // 게시판 등록
    int registBoard(RegistBoardDTO registBoardDTO);

    // 게시판 삭제
    int removeBoard(Long bno);

    // 게시판 상세 정보 조회
    ReadBoardDTO readOneBoard(Long bno);

    // 게시판 리스트
    List<ListBoardDTO> getBoardList(PageRequestDTO pageRequestDTO);
    int boardCnt(PageRequestDTO pageRequestDTO);


}
