package com.backend.repository;

import com.backend.entity.calendar.Calendar;
import com.backend.entity.calendar.CalendarContent;
import com.backend.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CalendarContentRepository extends JpaRepository<CalendarContent, Long> {
    List<CalendarContent> findAllByCalendar_UserAndCalendarStartDateEqualsAndCalendar_StatusIsNot(User user, LocalDate now, int i);

    Optional<CalendarContent> findByCalendarContentId(Long contentId);
}
