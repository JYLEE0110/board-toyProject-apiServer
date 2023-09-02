package org.board.mvc.noticeboard.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ModifyBoardDTO {

    private Long bno;               // 게시판 번호
    private String writer;          // 작성자
    private String boardTitle;      // 게시판 제목
    private String boardContent;    // 게시판 내용
    private List<String> fileNames; // 게시판 파일
    
}
