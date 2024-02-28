package ra.bussiness.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public static boolean validateUserName(String input) {
        String regex = "^[a-zA-Z0-9]{6,}$";

        // Tạo Pattern
        Pattern pattern = Pattern.compile(regex);

        // Tạo Matcher
        Matcher matcher = pattern.matcher(input);

        // Kiểm tra xem chuỗi có khớp với biểu thức chính quy hay không
        return matcher.matches();
    }

    public static boolean validateEmail(String input) {
        String regex = "^[a-zA-Z0-9_%+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}$";

        // Tạo Pattern
        Pattern pattern = Pattern.compile(regex);

        // Tạo Matcher
        Matcher matcher = pattern.matcher(input);

        // Kiểm tra xem chuỗi có khớp với biểu thức chính quy hay không
        return matcher.matches();
    }

    public static boolean validateEmpty(String input) {
        String regex = "^.+$";

        // Tạo Pattern
        Pattern pattern = Pattern.compile(regex);

        // Tạo Matcher
        Matcher matcher = pattern.matcher(input);

        // Kiểm tra xem chuỗi có khớp với biểu thức chính quy hay không
        return matcher.matches();
    }

    public static boolean validateVNPhoneNumber(String input) {
        // Mẫu biểu thức chính quy có ít nhất 6 ký tự và không chứ ký tự đặc biệt
        String regex = "^\\+84[0-9]{9}$";

        // Tạo Pattern
        Pattern pattern = Pattern.compile(regex);

        // Tạo Matcher
        Matcher matcher = pattern.matcher(input);

        // Kiểm tra xem chuỗi có khớp với biểu thức chính quy hay không
        return matcher.matches();
    }

    public static boolean validateEmptyNumber(double input) {
        String regex = "^\\d+(\\.\\d+)?$";

        // Tạo Pattern
        Pattern pattern = Pattern.compile(regex);

        // Tạo Matcher
        Matcher matcher = pattern.matcher((String.valueOf(input)));

        // Kiểm tra xem chuỗi có khớp với biểu thức chính quy hay không
        return matcher.matches();
    }
    public static boolean validateDate(String input) {
        String regex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$";

        // Tạo Pattern
        Pattern pattern = Pattern.compile(regex);

        // Tạo Matcher
        Matcher matcher = pattern.matcher((String.valueOf(input)));

        // Kiểm tra xem chuỗi có khớp với biểu thức chính quy hay không
        return matcher.matches();
    }
    public static boolean validateMonth(String input) {
        String regex = "^(0[1-9]|1[0-2])/(\\d{4})$";

        // Tạo Pattern
        Pattern pattern = Pattern.compile(regex);

        // Tạo Matcher
        Matcher matcher = pattern.matcher((String.valueOf(input)));

        // Kiểm tra xem chuỗi có khớp với biểu thức chính quy hay không
        return matcher.matches();
    }

    public static boolean validateYear(String input) {
        String regex = "^\\d{4}$";

        // Tạo Pattern
        Pattern pattern = Pattern.compile(regex);

        // Tạo Matcher
        Matcher matcher = pattern.matcher((String.valueOf(input)));

        // Kiểm tra xem chuỗi có khớp với biểu thức chính quy hay không
        return matcher.matches();
    }
}