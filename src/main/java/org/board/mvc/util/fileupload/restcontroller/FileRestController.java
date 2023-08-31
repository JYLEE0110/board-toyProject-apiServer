package org.board.mvc.util.fileupload.restcontroller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.board.mvc.util.fileupload.dto.FileUploadDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

@RestController
@Log4j2
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/files/")
public class FileRestController {

    // properties에 설정한 nginx 경론
    @Value("${org.board.upload.path}")
    private String uploadPath;

    @PostMapping("/upload")
    public List<FileUploadDTO> upload(MultipartFile[] files) {

        // 파일이 null이거나 배열의 길이가 0 일때 null을 반환
        if (files == null || files.length == 0) {

            return null;

        }

        List<FileUploadDTO> fileList = new ArrayList<>();

        // 파일 여러개를 등록 할 수 있으니 for each를 사용
        for (MultipartFile file : files) {

            // 초가값 null
            FileUploadDTO result = null;

            // 실제 파일명
            String fileName = file.getOriginalFilename();

            // 파일 크기
            long size = file.getSize();

            // uuid 생성
            String uuidStr = UUID.randomUUID().toString();

            // uuid + filename 파일 명 저장
            String saveFileName = uuidStr + "_" + fileName;

            // 파일 정보 객체 생성(저장 될 경로 + 파일 명)
            File saveFile = new File(uploadPath, saveFileName);

            try {

                // FileCopyUtils를 사용하여 InputStream으로 받고 outPutStream으로 내보낸다.
                // saveFile을 byte단위로 복사
                FileCopyUtils.copy(file.getBytes(), saveFile);

                // uuid와 fileName을 DTO에 저장
                result = FileUploadDTO.builder()
                        .uuid(uuidStr)
                        .fileName(fileName)
                        .build();

                // 파일 확장자가 이미지인지 mimeType으로 체크
                String mimeType = Files.probeContentType(saveFile.toPath());
                log.info("========== mime type==========");
                log.info(mimeType);

                // mimeType이 이미지인지 확인 후 썸네일 생성
                if (mimeType.startsWith("image")) {

                    File thumbFile = new File(uploadPath, "s_" + saveFileName);
                    Thumbnailator.createThumbnail(saveFile, thumbFile, 80, 90);

                    // DTO의 img를 true로 반환 => getLink를 사용
                    result.setImg(true);

                }

                fileList.add(result);

            } catch (IOException e) {
                log.error("Error uploading file : " + e.getMessage(), e);
                // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 업로드 중
                // 오류가 발생하였습니다.");
            }

        }

        return fileList;

    }

    // Nginx에 저장된 사진 삭제
    @DeleteMapping("remove/{fileName}")
    public Map<String, String> removeFile(
            @PathVariable("fileName") String fileName) {

        // PathVariable로 받은 fileName으로 해당 파일의 정보를 객체로 받음
        File originFile = new File(uploadPath, fileName);

        try {

            String mimeType = Files.probeContentType(originFile.toPath());

            // 파일의 mimeType을 확인하여 이미지 이면 썸네일 까지 삭제
            if (mimeType.startsWith("image")) {
                File thumbFile = new File(uploadPath, "s_" + fileName);
                thumbFile.delete();
            }

            originFile.delete();

        } catch (IOException e) {
            log.error("Error uploading file : " + e.getMessage(), e);
            // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 삭제 중
            // 오류가 발생하였습니다.");
        }

        return Map.of("result", "success");

    }

}
