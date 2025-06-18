package com.rungroup.web.repository;

import com.rungroup.web.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepositoy extends JpaRepository<Event, Long> {



}
