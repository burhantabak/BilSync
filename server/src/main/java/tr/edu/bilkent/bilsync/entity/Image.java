package tr.edu.bilkent.bilsync.entity;

import jakarta.persistence.*;

import java.io.File;
import java.nio.file.Paths;

@Entity
@Table(name = "image")
public class Image {

    private static final String IMAGE_BASE_PATH = "." + File.separator + "chatImages";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Image() {
    }

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
