import java.util.ArrayList;
import java.util.List;

public class YearlyReport {
    static int reportYear = 2021;
    Reader reader = new Reader();
    YearStorage storage = new YearStorage();

    List<Year> years = new ArrayList<>();

    public void loadYearReport() {
        List<String> lines = reader.readFile("./resources/y.2021.csv");
        for (int i = 1; i < lines.size(); i++) { //пропускаем 1ую строчку отчеты, т.к. там название столбцов
            String line = lines.get(i);
            String[] rows = line.split(",");

            int monthNumber = Integer.parseInt(rows[0]);
            long amount = Long.parseLong(rows[1]);
            boolean isExpense = Boolean.parseBoolean(rows[2]);

            Year year = new Year(monthNumber, amount, isExpense);
            years.add(year);
        }
        storage.saveYearReport(years);
    }

    public void printYearReport() {
        System.out.println(storage.yearReports);
        System.out.println("Рассматриваемый год: " + reportYear);

        Year earningMonths = storage.getMonthsEarning();
        System.out.println("Прибыль по каждому месяцу: " + earningMonths);

        Year avgExpenseInYear = storage.getAvgExpenseInYear();
        System.out.println("Средний расход за все имеющиеся операции в году;: " + avgExpenseInYear);

        Year avgEarningInYear = storage.getAvgEarningInYear();
        System.out.println("Средний доход за все имеющиеся операции в году." + avgEarningInYear);
    }

}

