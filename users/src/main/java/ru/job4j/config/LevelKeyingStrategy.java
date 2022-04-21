package ru.job4j.config;

import ch.qos.logback.classic.spi.ILoggingEvent;
import com.github.danielwegener.logback.kafka.keying.KeyingStrategy;

public class LevelKeyingStrategy implements KeyingStrategy<ILoggingEvent> {
    @Override
    public byte[] createKey(ILoggingEvent e) {
        return e.getLevel().levelStr.getBytes();
    }
}