package org.board.mvc.noticeboard.service.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.board.mvc.noticeboard.dto.ListBoardDTO;
import org.board.mvc.noticeboard.dto.ModifyBoardDTO;
import org.board.mvc.noticeboard.dto.ReadBoardDTO;
import org.board.mvc.noticeboard.dto.RegistBoardDTO;
import org.board.mvc.noticeboard.mappers.BoardMapper;
import org.board.mvc.noticeboard.service.BoardService;
import org.board.mvc.util.fileupload.mappers.FileMapper;
import org.board.mvc.util.page.PageRequestDTO;
import org.board.mvc.util.page.PageResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

    // Mapper 의존성 주입
    private final BoardMapper boardMapper;
    private final FileMapper fileMapper;

    // 게시판 등록
    @Override
    public Long registBoard(RegistBoardDTO registBoardDTO) {

        int resultRegistBoard = boardMapper.registBoard(registBoardDTO);

        Long bno = registBoardDTO.getBno();
        List<String> fileNames = registBoardDTO.getFileNames();

        // tbl_board에 관한 Mapper 쿼리가 정상 실행 되고 registDTO 객체가 존재하고 fileNames가 비어있지 않을 시
        // 파일이 존재 할 시
        if (resultRegistBoard > 0 && registBoardDTO != null && !fileNames.isEmpty()) {

            AtomicInteger index = new AtomicInteger();

            List<Map<String, String>> list = fileNames.stream().map(str -> {
                String uuid = str.substring(0, 36);
                String fileName = str.substring(37);

                return Map.of("uuid", uuid, "fileName", fileName, "bno", "" + bno, "ord", "" + index.getAndIncrement());

            }).collect(Collectors.toList());

            fileMapper.registFile(list);

        }
        return bno;
    }

    // 게시판 삭제
    @Override
    public Long removeBoard(Long bno) {

        // 게시판 삭제
        boardMapper.removeBoard(bno);
        // 게시판에 포함된 파일 삭제
        fileMapper.removeFile(bno);

        return bno;

    }

    // 게시판 상세 조회
    @Override
    public ReadBoardDTO readOneBoard(Long bno) {

        return boardMapper.readOneBoard(bno);

    }

    @Override
    public PageResponseDTO<ListBoardDTO> getBoardList(PageRequestDTO pageRequestDTO) {

        // mapper에서 추출한 list 
        List<ListBoardDTO> list = boardMapper.getBoardList(pageRequestDTO);
        // mapper에서 추출한 total 값
        int total = boardMapper.boardCnt(pageRequestDTO);

        return PageResponseDTO.<ListBoardDTO>withAll()
                .list(list)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();

    }

    @Override
    public Long modifyBoard(ModifyBoardDTO modifyBoardDTO) {

        // 이미 read에서 bno를 가지고 있기 때문에 수정은 bno를 따로 받을 필요가 없다.
        int resultModifyBoard = boardMapper.modifyBoard(modifyBoardDTO);

        Long bno = modifyBoardDTO.getBno();

        // 해당 bno의 파일을 모두 삭제한다. => ord 값이 달라질 수 도 있기 때문에
        fileMapper.removeFile(bno);

        List<String> fileNames = modifyBoardDTO.getFileNames();
        // tbl_board에 관한 Mapper 쿼리가 정상 실행 되고 registDTO 객체가 존재하고 fileNames가 비어있지 않을 시
        // 파일이 존재 할 시
        if (resultModifyBoard > 0 && modifyBoardDTO != null && !fileNames.isEmpty()) {

            AtomicInteger index = new AtomicInteger();

            List<Map<String, String>> list = fileNames.stream().map(str -> {
                String uuid = str.substring(0, 36);
                String fileName = str.substring(37);

                return Map.of("uuid", uuid, "fileName", fileName, "bno", "" + bno, "ord", "" + index.getAndIncrement());

            }).collect(Collectors.toList());

            fileMapper.registFile(list);

        }
        return bno;

    }

}
