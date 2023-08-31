package org.board.mvc.noticeboard.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReadBoardDTO {
    
    private Long bno;                   // 게시판 번호
    private String writer;              // 작성자
    private String boardTitle;          // 게시판 제목
    private String boardContent;        // 게시판 내용
    private LocalDateTime regDate;      // 게시판 작성일
    private LocalDateTime modDate;      // 게시판 수정일
    private List<String> fileNames;     // 게시판 파일이름
}
