package org.board.mvc.board.service;

import java.util.List;
import java.util.UUID;

import org.board.mvc.noticeboard.dto.ReadBoardDTO;
import org.board.mvc.noticeboard.dto.RegistBoardDTO;
import org.board.mvc.noticeboard.mappers.BoardMapper;
import org.board.mvc.noticeboard.service.BoardService;
import org.board.mvc.util.fileupload.mappers.FileMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BoardServiceTests {

    @Autowired(required = false)
    private BoardService boardService;

    // 테스트 코드가 돌아가기 전 메모리상에 static 상수로 먼저 올려 놓는다.
    private static final String JUNIT_TEST_WRITER = "Junit_Test_Writer";
    private static final String JUNIT_TEST_TITLE = "Junit_Test_Title";
    private static final String JUNIT_TEST_CONTENT = "Junit_Test_Content";
    // private static final String JUNIT_TEST_UUID = UUID.randomUUID().toString();
    private static final String JUNIT_TEST_FILENAME1 = "test1.jpg";
    private static final String JUNIT_TEST_FILENAME2 = "test2.jpg";

    // BeforeEach를 사용 하기 위한 DTO들 정의
    private RegistBoardDTO registBoardDTO;

    // 테스트 코드가 돌아가기 전 수행
    @BeforeEach
    public void setUp() {

        registBoardDTO = RegistBoardDTO.builder()
                .writer(JUNIT_TEST_WRITER)
                .boardTitle(JUNIT_TEST_TITLE)
                .boardContent(JUNIT_TEST_CONTENT)
                .fileNames(List.of(UUID.randomUUID() + "_" + JUNIT_TEST_FILENAME1,
                        UUID.randomUUID() + "_" + JUNIT_TEST_FILENAME2))
                .build();
    }

    @Test
    @Transactional
    @DisplayName("게시판 등록 서비스 테스트")
    public void registBoard() {

        // GIVEN
        log.info("=========Start Regist Board Service Test==========");

        // WHEN
        Long bno = boardService.registBoard(registBoardDTO);

        // THEN
        Assertions.assertEquals(bno, registBoardDTO.getBno());
        log.info("==========End Regist Board Service Test ========== ");

    }

    @Test
    @Transactional
    @DisplayName("게시판 삭제 서비스 테스트")
    public void removeBoard(){

        // GIVEN
        log.info("==========Start Remove Board Service Test==========");

        // WHEN
        boardService.removeBoard(11L);

        // THEN
        log.info("===========End Remove Board Service Test===========");

    }

    @Test
    @Transactional
    @DisplayName("게시판 상세 조회 서비스 테스트")
    public void readOneBoard(){

        // GIVEN
        log.info("=========Start ReadOneBoard Service Test===========");

        // WHEN
        ReadBoardDTO result = boardService.readOneBoard(15L);
        log.info(result);

        // THEN
        Assertions.assertNotNull(result);
        log.info("=========End ReadOneBoard Service Test===========");

    }

}
