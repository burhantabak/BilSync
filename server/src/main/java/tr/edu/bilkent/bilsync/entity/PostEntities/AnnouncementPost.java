package tr.edu.bilkent.bilsync.entity.PostEntities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "announcement_post")
public class AnnouncementPost extends ForumPost {
    public AnnouncementPost() {}
}