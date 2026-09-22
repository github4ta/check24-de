package anna.novysh.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

public enum Rule {
    TIMESTAMP {
        @Override
        public String generate() {
            return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        }
    },
    RANDOM_NUMBER {
        @Override
        public String generate() {
            int randomNum = ThreadLocalRandom.current().nextInt(100_000, 1_000_000);
            return String.valueOf(randomNum);
        }
    };

    public abstract String generate();
}
