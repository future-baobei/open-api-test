package generate;

import java.util.Date;
import java.util.Random;

public abstract class GenericGenerator {
    private static Random random = null;

    public abstract String generate();

    protected Random getRandomInstance() {
        if (random == null) {
            random = new Random(new Date().getTime());
        }

        return random;
    }
}
