package tr.edu.bilkent.bilsync.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tr.edu.bilkent.bilsync.entity.FileData;
import tr.edu.bilkent.bilsync.repository.DonationPostRepository;
import tr.edu.bilkent.bilsync.repository.FileRepository;

import java.io.IOException;
import java.nio.file.Files;

@Service
public class FileService {
    private FileRepository fileRepository;
    //TODO: FolderPath should be placed in the application for now
    private final String FOLDER_PATH="C://Users//Lenovo//Desktop//Files/";

    public FileService(FileRepository fileRepository) { this.fileRepository = fileRepository; }

    public boolean uploadFile(MultipartFile file) throws IOException {
        try {
            int counter = 1;
            String fileName = file.getOriginalFilename();
            String originalFileName = fileName;
            while (fileRepository.findByName(fileName) != null) {
                String baseName = originalFileName.substring(0, originalFileName.lastIndexOf('.'));
                String extension = originalFileName.substring(originalFileName.lastIndexOf('.'));
                fileName = baseName + "(" + counter + ")" + extension;
                counter++;
            }
            String filePath = FOLDER_PATH + fileName;
            System.out.println(filePath);
            FileData fileData = new FileData(fileName, file.getContentType(), filePath);
            fileRepository.save(fileData);
            file.transferTo(new java.io.File(filePath));
            return true;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public byte[] downloadFile(String fileName) throws IOException {
        FileData fileData = fileRepository.findByName(fileName);
        String filePath= fileData.getFilePath();
        byte[] image = Files.readAllBytes(new java.io.File(filePath).toPath());
        return image;
    }
}