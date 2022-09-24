package me.gicheol.calendar.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {

    @Modifying
    @Query("UPDATE Calendar c SET c.isDel = true WHERE c.id = :id")
    int calendarDelete(Long id);

    @Override
    @Query("SELECT c FROM Calendar c WHERE c.isDel = false")
    List<Calendar> findAll();
}
