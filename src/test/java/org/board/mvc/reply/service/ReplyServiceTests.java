package org.board.mvc.reply.service;

import org.board.mvc.reply.dto.RegistReplyDTO;
import org.board.mvc.reply.mappers.ReplyMapper;
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
public class ReplyServiceTests {
    
       // 의존성 주입
    @Autowired(required = false)
    private ReplyService replyService;

    // JUNIT 테스트에 쓰일 상수들 정의
    private static final Long JUNIT_TEST_RNO = 1L;
    private static final Long JUNIT_TEST_GNO = 1L;
    private static final Long JUNIT_TEST_BNO = 20L;
    private static final String JUNIT_TEST_WRITER = "TEST_WRITER";
    private static final String JUNIT_TEST_REPLY = "TEST_REPLY";

    // DTO 정의
    private RegistReplyDTO registReplyDTO;
    private RegistReplyDTO registChildReplyDTO;

    // 테스트 코드가 돌아가기 전 수행
    @BeforeEach
    public void setUp() {
        registReplyDTO = RegistReplyDTO.builder()
                .writer(JUNIT_TEST_WRITER)
                .reply(JUNIT_TEST_REPLY)
                .build();
        
        registChildReplyDTO = RegistReplyDTO.builder()
                .writer(JUNIT_TEST_WRITER)
                .reply(JUNIT_TEST_REPLY)
                .gno(JUNIT_TEST_GNO)
                .build();

    }

    @Test
    // @Transactional
    @DisplayName("댓글 대 댓글 서비스 테스트")
    public void testRegistReply(){


        // GIVEN
        log.info("=== Start Regist Reply Service Test ===");

        // WHEN
        // 댓글 등록
        Long resultReply = replyService.registReply(JUNIT_TEST_BNO, registReplyDTO);
        // 대 댓글 등록
        Long resultChildReply =  replyService.registReply(JUNIT_TEST_BNO, registChildReplyDTO);

        // THEN
        Assertions.assertEquals(resultReply, registReplyDTO.getRno());
        Assertions.assertEquals(resultChildReply, registChildReplyDTO.getRno());
        log.info("=== End Regist Reply Service Test ===");

    }


}
