package tr.edu.bilkent.bilsync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tr.edu.bilkent.bilsync.entity.FileData;
import tr.edu.bilkent.bilsync.service.FileService;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {
    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) { this.fileService = fileService; }


    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadFileAsImage(@RequestParam("image") MultipartFile file) {
        if(file == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FILE_IS_EMPTY");
        String fileName = fileService.uploadFile(file);
        if(fileName != null) {
            FileData fileData = fileService.findByName(fileName);
            return ResponseEntity.ok().body(fileData.getFilePath());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FILE_COULD_NOT_BE_SAVED");
    }

    @GetMapping("/fileSystem/{fileName}")
    public ResponseEntity<?> downloadFileAsImage(@PathVariable String fileName) throws IOException {
        byte[] fileData = fileService.downloadFile(fileName);
        if(fileData == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FILE_DATA_IS_NULL");
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(fileData);
    }
}
