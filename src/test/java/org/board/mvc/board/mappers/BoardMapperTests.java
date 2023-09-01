package org.board.mvc.board.mappers;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.board.mvc.noticeboard.dto.ReadBoardDTO;
import org.board.mvc.noticeboard.dto.RegistBoardDTO;
import org.board.mvc.noticeboard.mappers.BoardMapper;
import org.board.mvc.util.fileupload.dto.FileUploadDTO;
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
public class BoardMapperTests {

    // 의존성 주입 DI
    @Autowired(required = false)
    private BoardMapper boardMapper;

    @Autowired(required = false)
    private FileMapper fileMapper;

    // 테스트 코드가 돌아가기 전 메모리상에 static 상수로 먼저 올려 놓는다.
    private static final String JUNIT_TEST_WRITER = "Junit_Test_Writer";
    private static final String JUNIT_TEST_TITLE = "Junit_Test_Title";
    private static final String JUNIT_TEST_CONTENT = "Junit_Test_Content";
    // private static final String JUNIT_TEST_UUID = UUID.randomUUID().toString();
    private static final String JUNIT_TEST_FILENAME1 = "test1.jpg";
    private static final String JUNIT_TEST_FILENAME2 = "test2.jpg";

    // BeforeEach를 사용 하기 위한 DTO들 정의
    private RegistBoardDTO registBoardDTO;
    private FileUploadDTO fileUploadDTO;

    // 테스트 코드가 돌아가기 전 수행
    @BeforeEach
    public void setUp() {

        registBoardDTO = RegistBoardDTO.builder()
                .writer(JUNIT_TEST_WRITER)
                .boardTitle(JUNIT_TEST_TITLE)
                .boardContent(JUNIT_TEST_CONTENT)
                .fileNames(List.of(UUID.randomUUID() + "_" + JUNIT_TEST_FILENAME1, UUID.randomUUID() + "_" +JUNIT_TEST_FILENAME2))
                .build();
    }

    @Test
    @Transactional
    @DisplayName("게시판 작성 테스트")
    public void registBoard() {

        // GIVEN
        log.info("=== Start Regist Board Mapper Test ===");

        log.info(registBoardDTO);

        // WHEN
        int resultBoard = boardMapper.registBoard(registBoardDTO);
        int resultFile = 0;

        long bno = registBoardDTO.getBno();
        // 파일 테이블에 삽입할 bno값 추출
        // log.info("==========bno============");
        // log.info(bno);

        // DTO에서 파일 이름을 추출
        List<String> fileNames = registBoardDTO.getFileNames();
        // log.info("==========fileNamse============");
        // log.info(fileNames);

        if (resultBoard > 0 && registBoardDTO != null && !registBoardDTO.getFileNames().isEmpty()) {
            AtomicInteger index = new AtomicInteger();

            List<Map<String, String>> list = fileNames.stream().map(str -> {
                String uuid = str.substring(0, 36);
                String fileName = str.substring(37);

                return Map.of("uuid", uuid, "fileName", fileName, "bno", "" + bno, "ord", "" + index.getAndIncrement());

            }).collect(Collectors.toList());

            log.info(list);

            resultFile = fileMapper.registFile(list);

        }
       
        // THEN
        Assertions.assertNotEquals(resultBoard, 0);
        Assertions.assertNotEquals(resultFile, 0);

        log.info("=== End Regist Board Mapper Test ===");
    }

    @Test
    @Transactional
    @DisplayName("게시판 삭제 테스트")
    public void removeBoardTest(){

        // GIVEN
        log.info("======== Start Remove Board Mapper Test==========");
        long bno = 10L;

        // WHEN
        int resultRemoveBoard = boardMapper.removeBoard(bno);
        int resultRemoveFile = fileMapper.removeFile(bno);

        // THEN
        Assertions.assertNotEquals(resultRemoveBoard, 0);
        Assertions.assertNotEquals(resultRemoveFile, 0);
        log.info("========== END Remove Board Mapper Test ============");

    }

    @Test
    @Transactional
    @DisplayName("게시판 상세 정보 테스트")
    public void readOneBoard(){

        // GIVEN
        log.info("========Start ReadOneBoard Mapper Test=========");

        // WHEN
        ReadBoardDTO result = boardMapper.readOneBoard(15L);
        log.info(result);

        // THEN
        Assertions.assertNotNull(result);
        log.info("==========END ReadOneBoard Mapper Test============");

    }

}
