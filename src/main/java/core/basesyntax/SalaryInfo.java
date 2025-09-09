package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private static final int TOKEN_DATE = 0;
    private static final int TOKEN_NAME = 1;
    private static final int TOKEN_HOURS = 2;
    private static final int TOKEN_RATE = 3;
    private static final int EXPECTED_TOKENS = 4;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);

        for (String name : names) {
            int salary = 0;

            for (String record : data) {
                String[] parts = record.split(" ");
                if (parts.length != EXPECTED_TOKENS) {
                    continue;
                }

                LocalDate workDate = LocalDate.parse(parts[TOKEN_DATE], FORMATTER);
                String employee = parts[TOKEN_NAME];
                int hours;
                int rate;
                try {
                    hours = Integer.parseInt(parts[TOKEN_HOURS]);
                    rate = Integer.parseInt(parts[TOKEN_RATE]);
                } catch (NumberFormatException e) {
                    continue;
                }

                if (employee.equals(name)
                        && !workDate.isBefore(from)
                        && !workDate.isAfter(to)) {
                    salary += hours * rate;
                }
            }

            report.append(System.lineSeparator())
                    .append(name).append(" - ").append(salary);
        }

        return report.toString();
    }
}
