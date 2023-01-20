import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class MonthlyReport {
    MonthlyReport() {
        this.monthlyRecords = new ArrayList<>();
    }
    MonthsConverting monthsConverting = new MonthsConverting();
    public List<Monthly> monthlyRecords;
    public void addRecord(Monthly record) {
        this.monthlyRecords.add(record);
    }

    public void constructMonthlyReader(int monthNumber, MonthlyReport monthlyReport) {

        String fileName = "resources/m.20210" + monthNumber + ".csv";
        String contentOfFile = readFileContentsOrNull(fileName);
        String[] lines = contentOfFile.split("\r?\n");

        for (int i = 1; i < lines.length; i++) {
            String[] content = lines[i].split(",");

            String itemName = content[0];
            boolean isExpense = Boolean.parseBoolean(content[1]);
            int quantity = Integer.parseInt(content[2]);
            int sumOfOne = Integer.parseInt(content[3]);

            Monthly ourRecord = new Monthly(monthNumber, itemName, isExpense, quantity, sumOfOne);
            monthlyReport.addRecord(ourRecord);

        }
    }

    public String readFileContentsOrNull(String path) { //передали на вход путь к файлу, а он нам даст его содержимое
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return null;
        }
    }

    public HashMap monthlyIncome(){  //список общих доходов по каждому из месяцев
        HashMap<Integer, Integer> monthlyIncome = new HashMap<>();
        for(int i=1;i<=monthsConverting.countMonth;i++) {
            int sumMonthlyIncome = 0;
            for(Monthly monthly: monthlyRecords) {
                if (!monthly.isExpense && monthly.monthNumber == i) {
                    sumMonthlyIncome += monthly.quantity * monthly.sumOfOne;
                }
            }
            monthlyIncome.put(i,sumMonthlyIncome);
        }
        return monthlyIncome;
    }

    public HashMap monthlyExpense(){ //список общих расходов по каждому из месяцев
        HashMap <Integer, Integer> monthlyExpense = new HashMap<>();
        for(int i=1;i<=monthsConverting.countMonth;i++) {
            int sumMonthlyExpense = 0;
            for(Monthly monthly: monthlyRecords) {
                if (monthly.isExpense && monthly.monthNumber == i) {
                    sumMonthlyExpense += monthly.quantity * monthly.sumOfOne;
                }
            }
            monthlyExpense.put(i,sumMonthlyExpense);
        }
        return monthlyExpense;
    }

    /*  Информация о всех месячных отчётах
      Название месяца;
      Самый прибыльный товар, то есть товар для которого is_expense == false, а произведение количества
              (quantity) на сумму (sum_of_one) максимально. Вывести название товара и сумму;
      Самую большую трату. Вывести название товара и сумму.
      Эта информация должна выводиться по каждому из месяцев. */
    public ArrayList<ArrayList<String>> getMonthlyReports() {
        ArrayList<String> consistMonthlyReport = new ArrayList<>();
        ArrayList<ArrayList<String>> consistMonthlyReports = new ArrayList<>();
        for(int i=1;i<=monthsConverting.countMonth;i++) {
            int maxMonthlyIncome = 0;
            int monthlyIncome = 0;
            String maxItemNameIncome = "";
            int maxMonthlyExpense = 0;
            int monthlyExpense = 0;
            String maxItemNameExpense = "";
            for(Monthly monthly: monthlyRecords) { //месячные доходы
                if (!monthly.isExpense && monthly.monthNumber == i) {
                    monthlyIncome = monthly.quantity * monthly.sumOfOne;
                    if(monthlyIncome>maxMonthlyIncome) {
                        maxMonthlyIncome = monthlyIncome;
                        maxItemNameIncome = monthly.itemName;
                    }
                }
            }
            for(Monthly monthly: monthlyRecords) { //месячные траты
                if (monthly.isExpense && monthly.monthNumber == i) {
                    monthlyExpense = monthly.quantity * monthly.sumOfOne;
                    if(monthlyExpense>maxMonthlyExpense) {
                        maxMonthlyExpense = monthlyExpense;
                        maxItemNameExpense = monthly.itemName;
                    }
                }
            }
            consistMonthlyReport.add("Месяц: ");
            consistMonthlyReport.add(monthsConverting.getMonth(i));
            consistMonthlyReport.add("Самый прибыльный товар: ");
            consistMonthlyReport.add(maxItemNameIncome);
            consistMonthlyReport.add("Сумма: ");
            consistMonthlyReport.add(Integer.toString(maxMonthlyIncome));
            consistMonthlyReport.add("Самая большая трата: ");
            consistMonthlyReport.add(maxItemNameExpense);
            consistMonthlyReport.add("Сумма: ");
            consistMonthlyReport.add(Integer.toString(maxMonthlyExpense));
        }
        consistMonthlyReports.add(consistMonthlyReport);
        return consistMonthlyReports;
    }


}