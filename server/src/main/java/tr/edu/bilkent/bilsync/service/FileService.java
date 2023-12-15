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
    //TODO: Refine this :(

    public FileService(FileRepository fileRepository) { this.fileRepository = fileRepository; }

    public String uploadFile(MultipartFile file) throws IOException {
        try {
            int counter = 1;
            String fileName = file.getOriginalFilename();
            String originalFileName = fileName;
            while (fileRepository.findByNameIgnoreCase(fileName) != null) {
                String baseName = originalFileName.substring(0, originalFileName.lastIndexOf('.'));
                String extension = originalFileName.substring(originalFileName.lastIndexOf('.'));
                fileName = baseName + "(" + counter + ")" + extension;
                counter++;
            }
            String abs = new java.io.File(".").getAbsolutePath();
            String filePath = abs.substring(0,abs.length()-1) + fileName;
            FileData fileData = new FileData(fileName, file.getContentType(), filePath);
            fileRepository.save(fileData);
            file.transferTo(new java.io.File(filePath));
            return fileName;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public byte[] downloadFile(String fileName) throws IOException {
        FileData fileData = fileRepository.findByNameIgnoreCase(fileName);
        String filePath= fileData.getFilePath();
        byte[] image = Files.readAllBytes(new java.io.File(filePath).toPath());
        return image;
    }

    public FileData findByName(String name) {
        return fileRepository.findByNameIgnoreCase(name);
    }
}
