package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static LocalDateTime stringParaData(String dataString) {
        try {
            return LocalDateTime.parse(dataString, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido. Use dd/MM/yyyy HH:mm");
            return null;
        }
    }

    public static String dataParaString(LocalDateTime data) {
        return data.format(formatter);
    }
}