package org.board.mvc.util.fileupload.dto;

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
public class FileUploadDTO {
    
    private String uuid;        // 파일의 고유 UUID
    private String fileName;    // 실제 파일 이름
    private boolean img;        // 파일의 존재 유무

    // 파일 경로 가져오기 => 프론트 쪽에서 확인
    public String getLink(){

        if(img){
            return "s_" + uuid + "_" + fileName;
        } else {
            return "noImage.png";
        }

    }


}
