package org.board.mvc.noticeboard.restcontroller;

import org.board.mvc.noticeboard.dto.ReadBoardDTO;
import org.board.mvc.noticeboard.dto.RegistBoardDTO;
import org.board.mvc.noticeboard.service.BoardService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/board")
public class BoardRestController {

    // 의존성 주입
    private final BoardService boardService;

    // 게시판 등록
    @PostMapping("")
    public Long registBoard(@RequestBody RegistBoardDTO registBoardDTO) {

        log.info("=======================");
        log.info("POST | /api/board");
        log.info(registBoardDTO);
        log.info("=======================");

        Long bno = boardService.registBoard(registBoardDTO);

        return bno;

    }

    // 게시판 삭제
    @PutMapping("/{bno}")
    public Long removeBoard(
            @PathVariable("bno") Long bno) {

        log.info("=======================");
        log.info("POST | /api/board/", bno);
        log.info("=======================");

        boardService.removeBoard(bno);

        return bno;
    }

    // 게시판 상세 조회
    @GetMapping("/{bno}")
    public ReadBoardDTO readOneBoard(
        @PathVariable("bno") Long bno
    ){

        log.info("====================");
        log.info("Get | /api/board/",bno);
        log.info("====================");

        return boardService.readOneBoard(bno);

    }

}
