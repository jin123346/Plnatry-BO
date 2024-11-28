package com.backend.repository;

import com.backend.entity.folder.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface FolderMogoRepository extends MongoRepository<Folder, Long> {

    List<Folder> findByOwnerIdAndAndParentId(String uid, String parentId);

    Folder findByName(String name);

}
