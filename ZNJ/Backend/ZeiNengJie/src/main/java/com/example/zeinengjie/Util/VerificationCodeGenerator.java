package com.example.zeinengjie.Util;

import java.security.SecureRandom;
public class VerificationCodeGenerator {
    public static String generateSixDigitCode() {
        SecureRandom secureRandom = new SecureRandom();
        int code = secureRandom.nextInt(1_000_000); // 生成0到999,999之间的随机数
        return String.format("%06d", code); // 格式化为6位字符串，前面补零
    }

}
