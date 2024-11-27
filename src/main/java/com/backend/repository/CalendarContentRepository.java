package com.backend.repository;

import com.backend.entity.calendar.CalendarContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarContentRepository extends JpaRepository<CalendarContent, Long> {
}
