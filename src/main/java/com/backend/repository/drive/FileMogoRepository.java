package com.backend.repository.drive;


import com.backend.entity.folder.FileMogo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileMogoRepository extends MongoRepository<FileMogo, String> {


}
