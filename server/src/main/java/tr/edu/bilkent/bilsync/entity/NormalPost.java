package tr.edu.bilkent.bilsync.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "normal_post")
public class NormalPost extends ForumPost {
}
