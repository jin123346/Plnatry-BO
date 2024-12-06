package com.backend.repository.page;

import com.backend.document.page.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepository extends MongoRepository<Page, String> {

}