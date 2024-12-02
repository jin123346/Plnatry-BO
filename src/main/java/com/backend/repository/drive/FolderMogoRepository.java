package com.backend.repository.drive;

import com.backend.entity.folder.Folder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
public interface FolderMogoRepository extends MongoRepository<Folder, String>   {

    List<Folder> findByOwnerIdAndParentIdOrderByOrder(String uid, String parentId);
    Optional<Folder> findByParentId(String parentId);
    Folder findByName(String name);



}
