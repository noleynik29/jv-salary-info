package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        int [] salaries = new int [names.length];

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);

        for (String record : data) {
            String[] parts = record.split(" ");
            LocalDate workDate = LocalDate.parse(parts[0], formatter);
            String name = parts[1];
            int hours = Integer.parseInt(parts[2]);
            int rate = Integer.parseInt(parts[3]);

            if (!workDate.isBefore(from) && !workDate.isAfter(to)) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(name)) {
                        salaries[i] += hours * rate;
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < names.length; i++) {
            result.append(System.lineSeparator())
                    .append(names[i]).append(" - ").append(salaries[i]);
        }

        return result.toString();
    }
}
