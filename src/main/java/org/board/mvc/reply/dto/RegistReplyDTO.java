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
    
    private Long bno;       // 댓글 번호
    private String writer;  // 작성자
    private String reply;   // 댓글

}