package tr.edu.bilkent.bilsync.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "app_normal_post")
public class NormalPostEntity extends ForumPostEntity{
}
