package tr.edu.bilkent.bilsync.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.edu.bilkent.bilsync.entity.FileData;

@Repository
public interface FileRepository extends CrudRepository<FileData, Integer> {
    FileData findByName(String fileName);
}