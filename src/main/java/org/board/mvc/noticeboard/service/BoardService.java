package org.board.mvc.noticeboard.service;

import org.board.mvc.noticeboard.dto.ListBoardDTO;
import org.board.mvc.noticeboard.dto.ModifyBoardDTO;
import org.board.mvc.noticeboard.dto.ReadBoardDTO;
import org.board.mvc.noticeboard.dto.RegistBoardDTO;
import org.board.mvc.util.page.PageRequestDTO;
import org.board.mvc.util.page.PageResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BoardService {

    // 게시판 등록
    Long registBoard(RegistBoardDTO registBoardDTO);

    // 게시판 삭제
    Long removeBoard(Long bno);

    // 게시판 상세 정보 조회
    ReadBoardDTO readOneBoard(Long bno);

    // 게시판 리스트
    PageResponseDTO<ListBoardDTO> getBoardList(PageRequestDTO pageRequestDTO);

    // 게시판 수정
    Long modifyBoard(ModifyBoardDTO modifyBoardDTO);
    
}
