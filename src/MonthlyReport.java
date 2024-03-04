import java.util.ArrayList;
import java.util.List;

public class MonthlyReport {
    Reader reader = new Reader();
    MonthStorage storage = new MonthStorage();

    public void loadMonthReports() {
        for (int i = 1; i < 4; i++) {
            String path = "./resources/m.20210" + i + ".csv";
            List<Month> months = loadMonthReport(path);
            storage.saveMonthReport(i, months);

        }
    }

    public List<Month> loadMonthReport(String path) {
        List<String> lines = reader.readFile(path);
        List<Month> months = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) { //пропускаем 1ую строчку отчеты, т.к. там название столбцов
            String line = lines.get(i);
            String[] rows = line.split(",");

            String itemName = rows[0];
            boolean isExpense = Boolean.parseBoolean(rows[1]);
            int quantity = Integer.parseInt(rows[2]);
            long price = Long.parseLong(rows[3]);

            Month month = new Month(itemName, isExpense, quantity, price);
            months.add(month);
        }
        return months;
    }

    public void printMonthReport() {
        for (int month = 1; month < 4; month++) {
            System.out.println("Месяц: " + getMonthName(month));
            Month maxEarning = storage.getMaxEarning(month);
            System.out.println("Самый прибыльный товар: " + maxEarning.itemName + ". Сумма: " + maxEarning.getTotal());
            Month maxExpense = storage.getMaxExpense(month);
            System.out.println("Самая большая трата: " + maxExpense.itemName + ". Сумма: " + maxExpense.getTotal());
        }
    }

    private String getMonthName(int month) {
        switch (month) {
            case (1):
                return "Январь";
            case (2):
                return "Февраль";
            case (3):
                return "Март";
            default:
                return "Такого месяца не было в ТЗ";
        }
    }
}
