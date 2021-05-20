package generate;

import org.apache.commons.lang3.StringUtils;

import java.util.Random;

public class ChineseMobileNumberGenerator extends GenericGenerator {
    private static final int[] MOBILE_PREFIX = new int[] { 133, 153, 177, 180,
        181, 189, 134, 135, 136, 137, 138, 139, 150, 151, 152, 157, 158, 159,
        178, 182, 183, 184, 187, 188, 130, 131, 132, 155, 156, 176, 185, 186,
        145, 147, 170 };
    private static ChineseMobileNumberGenerator instance = new ChineseMobileNumberGenerator();

    private ChineseMobileNumberGenerator() {
    }

    public static ChineseMobileNumberGenerator getInstance() {
        return instance;
    }

    private static String genMobilePre() {
        return "" + MOBILE_PREFIX[new Random().nextInt(MOBILE_PREFIX.length)];
    }

    @Override
    public String generate() {
        return genMobilePre() + StringUtils
            .leftPad("" + new Random().nextInt(99999999 + 1), 8, "0");
    }

    /**
     * 生成假的手机号，以19开头
     */
    public String generateFake() {
        return "19" + StringUtils
            .leftPad("" + new Random(999999999 + 1), 9, "0");
    }
}
