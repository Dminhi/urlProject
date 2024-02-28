package ra.bussiness.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashCode {

    // Phương thức để tạo một salt ngẫu nhiên
    public static byte[] generateSalt() {
        byte[] salt = {1,2,3,4,5};
        return salt;
    }

    // Phương thức để mã hóa mật khẩu
    public static String hashPassword(String password) {
        try {
            // Kết hợp mật khẩu với salt
            byte[] combined = combinePasswordAndSalt(password.getBytes(), generateSalt());

            // Sử dụng SHA-256 để băm mật khẩu
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(combined);

            // Chuyển đổi dữ liệu đã băm thành dạng hex
            StringBuilder hexStringBuilder = new StringBuilder();
            for (byte b : hashedBytes) {
                hexStringBuilder.append(String.format("%02x", b));
            }
            return hexStringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Phương thức để kết hợp mật khẩu và salt
    private static byte[] combinePasswordAndSalt(byte[] password, byte[] salt) {
        byte[] combined = new byte[password.length + salt.length];
        System.arraycopy(password, 0, combined, 0, password.length);
        System.arraycopy(salt, 0, combined, password.length, salt.length);
        return combined;
    }
}
