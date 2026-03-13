package nz.h4t.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class Delay {
    private static final Logger log = LoggerFactory.getLogger(Delay.class);

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
