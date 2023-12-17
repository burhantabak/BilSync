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

/**
 * Controller class for handling file-related operations.
 */
@RestController
@RequestMapping("/file")
public class FileController {
    private final FileService fileService;

    /**
     * Constructor for FileController, injecting the required service.
     *
     * @param fileService The service for handling file operations.
     */
    @Autowired
    public FileController(FileService fileService) { this.fileService = fileService; }

    /**
     * Handles the upload of a file as an image.
     *
     * @param file The MultipartFile representing the uploaded file.
     * @return ResponseEntity with a status code and a message indicating the result of the upload.
     */
    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadFileAsImage(@RequestParam("image") MultipartFile file) {
        if(file == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FILE_IS_EMPTY");
        String fileName = fileService.uploadFile(file);
        if(fileName != null) {
            FileData fileData = fileService.findByName(fileName);
            return ResponseEntity.ok().body(fileData.getName());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FILE_COULD_NOT_BE_SAVED");
    }

    /**
     * Handles the download of a file as an image.
     *
     * @param fileName The name of the file to be downloaded.
     * @return ResponseEntity with the file data as bytes and appropriate content type.
     * @throws IOException If an I/O exception occurs during file download.
     */
    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadFileAsImage(@PathVariable String fileName) throws IOException {
        FileData fileData = fileService.findByName(fileName);
        byte[] file = fileService.downloadFile(fileName);
        if(fileData == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File could not be loaded");
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(fileData.getType()))
                .body(file);
    }
}
