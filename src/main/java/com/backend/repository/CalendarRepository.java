package com.backend.repository;

import com.backend.entity.calendar.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    List<Calendar> findAllByUserId(Long id);

    Optional<Calendar> findByCalendarIdAndStatus(long l, int i);

    Optional<Calendar> findByCalendarIdAndStatusIsNot(Long calendarId, int i);
}
