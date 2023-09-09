package org.board.mvc.reply.service;

import org.board.mvc.reply.dto.RegistReplyDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ReplyService {
    Long registReply(Long bno, RegistReplyDTO registReplyDTO);
}
