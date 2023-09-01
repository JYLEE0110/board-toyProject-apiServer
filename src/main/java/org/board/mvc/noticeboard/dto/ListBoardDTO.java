package org.board.mvc.noticeboard.dto;

import java.time.LocalDateTime;

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
public class ListBoardDTO {

    private Long bno;
    private String writer;
    private String boardTitle;
    private String boardContent;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private String fileName;
    
}
