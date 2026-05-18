package com.example.neurofitbot.message;

import com.example.neurofitbot.common.MessageCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PreparedMessageRepository extends JpaRepository<PreparedMessage, Long> {

    Optional<PreparedMessage> findByCode(MessageCode code);
}
