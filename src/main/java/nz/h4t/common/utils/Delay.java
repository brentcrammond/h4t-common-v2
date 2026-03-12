package nz.h4t.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
public class Delay {
    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            log.error("Sleep interupted", e);
        }
    }

    public static void sleep(Duration dur) {
        try {
            var ms = dur.toMillis();
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            log.error("Sleep interupted", e);
        }
    }
}
