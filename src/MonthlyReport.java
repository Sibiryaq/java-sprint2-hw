import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class MonthlyReport {
    MonthsConverting monthsConverting = new MonthsConverting();
    public List<Monthly> monthlyRecords;

    MonthlyReport() {
        this.monthlyRecords = new ArrayList<>();
    }

    public void addRecord(Monthly record) {
        this.monthlyRecords.add(record);
    }

    public void constructMonthlyReader(int monthNumber, MonthlyReport monthlyReport) {

        String fileName = "resources/m.20210" + monthNumber + ".csv";
        String contentOfFile = Reader.readFileContentsOrNull(fileName);
        if (contentOfFile == null) { //вы рекомендовали -  if(contentOfFile!=null), но по логике должно быть как у меня т.к. в вашем случае я получаю значения и сразу завершаю метод
            System.out.println("Файл по такому пути не найден");
            return;
        } else {
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
    }

    public Map<Integer, Integer> monthlyIncome() {  //(возвращать интерфейс с дженериком Map<Integer, Integer>)список общих доходов по каждому из месяцев
        Map<Integer, Integer> monthlyIncome = new HashMap<>();
        for (int i = 1; i <= MonthsConverting.countMonth; i++) {
            int sumMonthlyIncome = 0;
            for (Monthly monthly : monthlyRecords) {
                if (!monthly.isExpense && monthly.monthNumber == i) {
                    sumMonthlyIncome += monthly.quantity * monthly.sumOfOne;
                }
            }
            monthlyIncome.put(i, sumMonthlyIncome);
        }
        return monthlyIncome;
    }

    public Map<Integer, Integer> monthlyExpense() { //(Аналогично в monthlyExpense)список общих расходов по каждому из месяцев
        Map<Integer, Integer> monthlyExpense = new HashMap<>();
        for (int i = 1; i <= MonthsConverting.countMonth; i++) {
            int sumMonthlyExpense = 0;
            for (Monthly monthly : monthlyRecords) {
                if (monthly.isExpense && monthly.monthNumber == i) {
                    sumMonthlyExpense += monthly.quantity * monthly.sumOfOne;
                }
            }
            monthlyExpense.put(i, sumMonthlyExpense);
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
        for (int i = 1; i <= MonthsConverting.countMonth; i++) {
            int maxMonthlyIncome = 0;
            int monthlyIncome = 0;
            String maxItemNameIncome = "";
            int maxMonthlyExpense = 0;
            int monthlyExpense = 0;
            String maxItemNameExpense = "";
            for (Monthly monthly : monthlyRecords) { //месячные доходы
                if (!monthly.isExpense && monthly.monthNumber == i) {
                    monthlyIncome = monthly.quantity * monthly.sumOfOne;
                    if (monthlyIncome > maxMonthlyIncome) {
                        maxMonthlyIncome = monthlyIncome;
                        maxItemNameIncome = monthly.itemName;
                    }
                }
            }
            for (Monthly monthly : monthlyRecords) { //месячные траты
                if (monthly.isExpense && monthly.monthNumber == i) {
                    monthlyExpense = monthly.quantity * monthly.sumOfOne;
                    if (monthlyExpense > maxMonthlyExpense) {
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