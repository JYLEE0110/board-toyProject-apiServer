package org.board.mvc.reply.service.impl;

import org.board.mvc.reply.dto.RegistReplyDTO;
import org.board.mvc.reply.mappers.ReplyMapper;
import org.board.mvc.reply.service.ReplyService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReplyServiceImpl implements ReplyService {

    private final ReplyMapper replyMapper;

    @Override
    public Long registReply(Long bno, RegistReplyDTO registReplyDTO) {

        Long gno = registReplyDTO.getGno();

        /* 댓글 먼저 insert 후 gno를 rno로 update */
        if (gno == 0) {
            replyMapper.registReply(bno, registReplyDTO);
            Long rno = registReplyDTO.getRno();
            replyMapper.updateGno(rno);
        } else {
            /* 대 댓글 => 댓글의 rno를 내려받아 insert 할때 gno도 같이 해줌 */
            replyMapper.registChildReply(bno, registReplyDTO);

        }

        return registReplyDTO.getRno();

    }

}
