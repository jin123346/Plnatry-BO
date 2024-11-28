package com.backend.repository;

import com.backend.entity.calendar.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    List<Calendar> findAllByUserId(Long id);

    Optional<Calendar> findByCalendarIdAndStatusIsNot(Long calendarId, int i);

    Optional<Calendar> findByUserIdAndStatus(long l, int i);

    List<Calendar> findAllByUserIdAndStatusIsNotAndCalendarContents_CalendarStartDateEquals(Long userId, int i, LocalDate now);

    Optional<Calendar> findByCalendarId(Long calendarId);
}
