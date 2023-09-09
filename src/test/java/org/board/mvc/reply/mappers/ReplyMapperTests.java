package org.board.mvc.reply.mappers;

import org.board.mvc.noticeboard.dto.RegistBoardDTO;
import org.board.mvc.reply.dto.RegistReplyDTO;
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
public class ReplyMapperTests {

    // 의존성 주입
    @Autowired(required = false)
    private ReplyMapper replyMapper;

    // JUNIT 테스트에 쓰일 상수들 정의
    private static final String JUNIT_TEST_WRITER = "TEST_WRITER";
    private static final String JUNIT_TEST_REPLY = "TEST_REPLY";

    // DTO 정의
    private RegistReplyDTO registReplyDTO;

    // 테스트 코드가 돌아가기 전 수행
    @BeforeEach
    public void setUp() {
        registReplyDTO = RegistReplyDTO.builder()
                .writer(JUNIT_TEST_WRITER)
                .reply(JUNIT_TEST_REPLY)
                .build();

    }

    @Test
    // @Transactional
    @DisplayName("댓글 매퍼 테스트")
    public void testReplyMapper() {

        // GIVEN
        log.info("=== Start Regist Reply Mapper Test ===");

        // WHEN
        int result = replyMapper.registReply(20L, registReplyDTO);

        // THEN
        Assertions.assertEquals(1, result);
        log.info("==== End Regist Reply Mapper Test ===");
    }

    @Test
    // @Transactional
    @DisplayName("댓글 gno 업데이트 테스트")
    public void testUpdateGno() {

        // GIVEN
        log.info("=== Start Update Gno Mapper Test");

        // WHEN
        int result = replyMapper.updateGno(1L);

        // THEN
        Assertions.assertEquals(1, result);
        log.info("==== End Regist Reply Mapper Test ===");
    }
}
