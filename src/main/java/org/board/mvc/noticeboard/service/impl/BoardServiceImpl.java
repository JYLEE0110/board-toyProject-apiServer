package org.board.mvc.noticeboard.service.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.board.mvc.noticeboard.dto.RegistBoardDTO;
import org.board.mvc.noticeboard.mappers.BoardMapper;
import org.board.mvc.noticeboard.service.BoardService;
import org.board.mvc.util.fileupload.mappers.FileMapper;
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

    @Override
    public Long removeBoard(Long bno) {

        boardMapper.removeBoard(bno);
        fileMapper.removeFile(bno);

        return bno;

    }

}
