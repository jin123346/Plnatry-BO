package com.backend.repository.drive;


import com.backend.entity.folder.FileMogo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileMogoRepository extends MongoRepository<FileMogo, String> {

    List<FileMogo> findByFolderId(String folderId);


}
