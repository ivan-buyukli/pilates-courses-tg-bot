package com.example.neurofitbot.message;

import com.example.neurofitbot.common.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduledMessageRepository extends JpaRepository<ScheduledMessage, Long> {

    List<ScheduledMessage> findTop50ByStatusAndScheduledAtLessThanEqualOrderByScheduledAtAsc(
            MessageStatus status, LocalDateTime now);
}
