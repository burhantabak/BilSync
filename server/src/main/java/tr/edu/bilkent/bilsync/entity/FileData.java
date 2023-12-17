package tr.edu.bilkent.bilsync.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * The FileData class represents information about a file, including its name, type, and file path.
 * This class is mapped to a database table for persistent storage using JPA annotations.
 */
@Entity
@Table
public class FileData {

    @Id
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String filePath;

    /**
     * Default constructor for the FileData class.
     */
    public FileData() {}

    /**
     * Parameterized constructor for creating a FileData instance with specified name, type, and file path.
     *
     * @param name     The name of the file.
     * @param type     The type or format of the file.
     * @param filePath The file path indicating the location of the file.
     */
    public FileData(String name, String type, String filePath) {
        this.name = name;
        this.type = type;
        this.filePath = filePath;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getFilePath() { return filePath; }

    public void setFilePath(String filePath) { this.filePath = filePath; }
}
