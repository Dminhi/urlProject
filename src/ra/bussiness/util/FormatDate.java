package ra.bussiness.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormatDate {
    public static String formattedDate(){
        // Lấy ngày tháng hiện tại
        LocalDate localDate = LocalDate.now();

        // Định dạng theo dd/MM/yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = localDate.format(formatter);
        return formattedDate;
    }
}
