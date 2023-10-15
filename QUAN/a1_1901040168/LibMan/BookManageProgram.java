package LibMan;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookManageProgram {
    public static void main(String[] args) {
        LocalDate d1 = LocalDate.parse("2018-05-26", DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate d2 = LocalDate.parse("2018-05-28", DateTimeFormatter.ISO_LOCAL_DATE);
        Duration diff = Duration.between(d1.atStartOfDay(), d2.atStartOfDay());
        long diffDays = diff.toDays();
        System.out.println(diffDays);
    }
}
