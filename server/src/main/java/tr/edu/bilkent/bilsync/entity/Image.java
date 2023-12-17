package tr.edu.bilkent.bilsync.entity;

import jakarta.persistence.*;

import java.io.File;
import java.nio.file.Paths;

/**
 * The Image class represents an image entity, including its unique identifier and file path.
 * This class is mapped to a database table for persistent storage using JPA annotations.
 */
@Entity
@Table(name = "image")
public class Image {

    private static final String IMAGE_BASE_PATH = "." + File.separator + "chatImages";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Default constructor for the Image class.
     */
    public Image() {
    }

    /**
     * Parameterized constructor for creating an Image instance with a specified identifier.
     *
     * @param id The unique identifier for the image.
     */
    public Image(Long id){
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getPath() {
        return Paths.get(IMAGE_BASE_PATH, "image_" + id + ".dat").toString();
    }
}
