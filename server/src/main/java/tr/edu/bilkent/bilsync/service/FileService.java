package tr.edu.bilkent.bilsync.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tr.edu.bilkent.bilsync.entity.FileData;
import tr.edu.bilkent.bilsync.repository.FileRepository;

import java.io.IOException;
import java.nio.file.Files;

/**
 * Service class for managing file-related operations.
 */
@Service
public class FileService {
    private final FileRepository fileRepository;

    /**
     * Constructor for FileService, injecting the required repository.
     *
     * @param fileRepository The repository for handling file data.
     */
    public FileService(FileRepository fileRepository) { this.fileRepository = fileRepository; }

    /**
     * Handles the upload of a file.
     *
     * @param file The MultipartFile representing the uploaded file.
     * @return The name of the uploaded file if successful, null otherwise.
     */
    public String uploadFile(MultipartFile file) {
        try {
            int counter = 1;
            String fileName = file.getOriginalFilename();
            String originalFileName = fileName;

            // Ensure unique file name
            while (fileRepository.findByNameIgnoreCase(fileName) != null) {
                String baseName = originalFileName.substring(0, originalFileName.lastIndexOf('.'));
                String extension = originalFileName.substring(originalFileName.lastIndexOf('.'));
                fileName = baseName + "(" + counter + ")" + extension;
                counter++;
            }
            // Save file data in the repository
            String abs = new java.io.File(".").getAbsolutePath();
            String filePath = abs.substring(0,abs.length()-1) + fileName;
            FileData fileData = new FileData(fileName, file.getContentType(), filePath);
            fileRepository.save(fileData);

            // Transfer the file to the server
            file.transferTo(new java.io.File(filePath));
            return fileName;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Handles the download of a file.
     *
     * @param fileName The name of the file to be downloaded.
     * @return The byte array representing the file data.
     * @throws IOException If an I/O exception occurs during file download.
     */
    public byte[] downloadFile(String fileName) throws IOException {
        FileData fileData = fileRepository.findByNameIgnoreCase(fileName);
        String filePath= fileData.getFilePath();
        byte[] image = Files.readAllBytes(new java.io.File(filePath).toPath());
        return image;
    }

    /**
     * Finds file data by name.
     *
     * @param name The name of the file.
     * @return The FileData object if found, null otherwise.
     */
    public FileData findByName(String name) {
        return fileRepository.findByNameIgnoreCase(name);
    }
}
