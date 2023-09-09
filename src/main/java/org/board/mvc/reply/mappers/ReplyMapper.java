package org.board.mvc.reply.mappers;

import org.apache.ibatis.annotations.Param;
import org.board.mvc.reply.dto.RegistReplyDTO;

public interface ReplyMapper {
    
    // 댓글 등록
    int registReply(@Param("bno") Long bno, @Param("rrd")RegistReplyDTO registReplyDTO);
    // gno 업데이트
    int updateGno(Long rno);

    // 대 댓글 등록
    int registChildReply(@Param("bno")Long bno, @Param("rrd")RegistReplyDTO registReplyDTO);

}
