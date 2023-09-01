package org.board.mvc.util.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageRequestDTO {

    @Builder.Default
    private int page = 1;       // page 번호
    @Builder.Default
    private int size = 10;      // 한 page당 보여 줄 사이즈
    private String type;        // 검색 type
    private String keyword;     // 검색어
    private boolean replyLast;  // 댓글 페이징 마지막 페이지 유무

    // 쿼리 스트링으로 page에 음수 값을 넣었을 시 처리
    public void setPage(int page){

        this.page = (page < 0) ? 1 : page;

    }

    // 쿼리 스트링으로 size에 음수 값, 과도한 값을 넣었을 시 처리
    public void setSize(int size){

        this.size = (size < 0 || size > 100) ? 1 : size;

    }

    // limit ? ? 에 들어갈 첫번째 인자 값 계산
    public int getSkip(){

        // page = 1  => (limit 0, 10) ==> 1 ~ 10
        // page = 2  => (limit 10, 10) ==> 11 ~ 20
        // page = 3  => (limit 20, 10) ==> 21 ~ 30
        // 각 페이지 당 size에 맞게 데이터를 불러온다.
        return (this.page - 1)*this.size;

    }

    // 이전 / 다음 페이지를 위한 count
    public int getCountEnd(){
        // 101 이면 page 11
        // 201 이면 page 21 
        return ((int) Math.ceil(this.page / 10.0) * (10 * this.size)) + 1;
    }

    // 검색 조건과 통합검색을 위한 처리
    public String [] getTypes(){

        return (this.type == null || this.type.isEmpty()) ? null : this.type.split("");

    }

    
}
