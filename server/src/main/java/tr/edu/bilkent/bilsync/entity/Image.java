package tr.edu.bilkent.bilsync.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "image")
public class Image {

    private static final String IMAGE_BASE_PATH = "./chatImages";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getPath(){
        return IMAGE_BASE_PATH + "image_" + id + ".dat";
    }
}
