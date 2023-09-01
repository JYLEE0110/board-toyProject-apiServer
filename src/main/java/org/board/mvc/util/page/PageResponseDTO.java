package org.board.mvc.util.page;

import java.util.List;
import java.util.stream.IntStream;

import lombok.Builder;
import lombok.Data;

@Data
public class PageResponseDTO<E> {
    
    private List<E> list;   // 출력할 list를 제네릭 타입으로 받는다.
    private long total; 

    private int page;               // 페이지 번호
    private int size;               // 페이지 사이즈
    private int startNum;           // 시작 페이지 번호
    private int endNum;             // 끝 페이지 번호
    private boolean prevBtn;        // 이전 버튼 유무
    private boolean nextBtn;        // 다음 버튼 유무
    private boolean replyLast;      // 댓글 마지막 페이징 유무
    private List<Integer> pageNums; // 페이징 넘버

    @Builder (builderMethodName = "withAll")
    public PageResponseDTO(
        List<E> list, long total, PageRequestDTO pageRequestDTO    
    ){
        this.list = list;
        this.total = total;
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();
        this.replyLast = pageRequestDTO.isReplyLast();

        // 페이징 계산
        // 시작 페이지 번호 계산 (size = 10이면 1, 11, 21, 31 ....)
        this.startNum = ((int) (Math.ceil(this.page / 10.0) * 10) -9);

        // 끝 페이지 번호 계산 (size = 10이면 10, 20, 30 ...)
        this.endNum = this.startNum + 9;

        int last = (int)(Math.ceil((total/(double)size)));
        // endNum이 last보다 크면 last가 endNum
        this.endNum = endNum > last ? last : endNum;

        // 시작페이지가 1보다 클때만 이전 버튼
        this.prevBtn = this.startNum > 1;

        // total이 endNum과 size를 곱한 값 보다 크면 다음버튼
        this.nextBtn = total > this.endNum * this.size;

        this.pageNums = IntStream.rangeClosed(startNum, endNum).boxed().toList();


    }


}
