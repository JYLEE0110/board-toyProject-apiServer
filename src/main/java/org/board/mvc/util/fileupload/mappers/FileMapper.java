package org.board.mvc.util.fileupload.mappers;

import java.util.List;
import java.util.Map;

import org.board.mvc.util.fileupload.dto.FileUploadDTO;

public interface FileMapper {
    
    int registFile(List<Map<String,String>> fileList);

    int removeFile(Long bno);

}
