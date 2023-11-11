package tr.edu.bilkent.bilsync.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table
public class ForumPostEntity extends PostEntity{
}
