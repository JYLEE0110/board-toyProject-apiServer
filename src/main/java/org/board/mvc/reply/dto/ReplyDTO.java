package org.board.mvc.reply.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class ReplyDTO {

    private Long rno;               // 댓글 번호
    private Long bno;               // 해당 게시물 번호
    private Long gno;               // 댓글 대댓글 구분 그룹번호
    private String wirter;          // 작성자
    private String reply;           // 댓글
    private Boolean isDeleted;      // 삭제 여부
    private LocalDateTime regDate;  // 작성일
    private LocalDateTime modDate;  // 수정일
    
}
