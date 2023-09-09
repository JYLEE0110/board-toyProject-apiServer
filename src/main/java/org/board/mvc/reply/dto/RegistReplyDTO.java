package org.board.mvc.reply.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Data
public class RegistReplyDTO {
    
    private Long rno;       // 댓글 번호
    @Builder.Default
    private Long gno = 0L;  // 댓글 대댓글 구분 번호
    private String writer;  // 작성자
    private String reply;   // 댓글

}
