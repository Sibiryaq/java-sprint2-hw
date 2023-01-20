import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class YearlyReport {
    MonthsConverting monthsConverting = new MonthsConverting();

    public List<Yearly> YearlyRecords;

    public void constructYearlyReader(String fileName, YearlyReport yearlyReport) {

        String contentOfFile = readFileContentsOrNull(fileName);
        String[] lines = contentOfFile.split("\r?\n");

        for (int i = 1; i < lines.length; i++) {
            String[] content = lines[i].split(",");

            int monthNumber = Integer.parseInt(content[0]);
            int amount = Integer.parseInt(content[1]);
            boolean isExpense = Boolean.parseBoolean(content[2]);

            Yearly ourRecord = new Yearly(monthNumber, amount, isExpense);
            yearlyReport.addRecord(ourRecord);

        }
    }
    public static String readFileContentsOrNull(String path) //передали на вход путь к файлу, а он нам даст его содержимое
    {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }

    YearlyReport() {
        this.YearlyRecords = new ArrayList<>();
    }

    public void addRecord(Yearly record) {
        this.YearlyRecords.add(record);
    }

    public HashMap yearlyIncome(){
        HashMap <Integer, Integer> yearlyIncome = new HashMap<>();
        for(Yearly yearly: YearlyRecords) {
            if(yearly.isExpense == false) {
                yearlyIncome.put(yearly.monthNumber,yearly.amount);
            }
        }
        return yearlyIncome;
    }

    public HashMap yearlyExpense(){
        HashMap <Integer, Integer> yearlyExpense = new HashMap();
        for(Yearly yearly: YearlyRecords) {
            if(yearly.isExpense == true) {
                yearlyExpense.put(yearly.monthNumber,yearly.amount);
            }
        }
        return yearlyExpense;
    }
    /* Информация о годовом отчёте
     При вызове этой функции программа должна выводить следующие данные:
     Рассматриваемый год;
     Прибыль по каждому месяцу. Прибыль — это разность доходов и расходов;
     Средний расход за все месяцы в году;
     Средний доход за все месяцы в году.
 */
    //прибыль по каждому месяцу
    public ArrayList<ArrayList<String>> monthlyProfit() {
        ArrayList<String> consistMonthlyProfit= new ArrayList<>();
        ArrayList<ArrayList<String>> consistMonthlyProfits = new ArrayList<>();
        for(int i=1; i<=monthsConverting.countMonth; i++) {
            int monthlyIncome = 0;
            int monthlyExpense = 0;
            for(Yearly yearly: YearlyRecords) {
                if(yearly.monthNumber == i && yearly.isExpense == false) {
                    monthlyIncome += yearly.amount;
                } else if(yearly.monthNumber == i && yearly.isExpense == true) {
                    monthlyExpense += yearly.amount;
                }
            }
            consistMonthlyProfit.add("Месяц: ");
            consistMonthlyProfit.add(monthsConverting.getMonth(i));
            consistMonthlyProfit.add(Integer.toString((monthlyIncome - monthlyExpense)));
        }
        consistMonthlyProfits.add(consistMonthlyProfit);
        return consistMonthlyProfits;
    }
    public int getYearlyIncomeAVG(){
        int sumYearlyIncome = 0;
        int avgYearlyIncome = 0;
        int k = 0;
        for(Yearly yearly: YearlyRecords) {
            if(yearly.isExpense == false) {
                sumYearlyIncome += yearly.amount;
                k+=1;
            }
        }
        avgYearlyIncome = sumYearlyIncome/k;
        return avgYearlyIncome;
    }
    public int getYearlyExpenseAVG(){
        int sumYearlyExpense = 0;
        int avgYearlyExpense = 0;
        int k = 0;
        for(Yearly yearly: YearlyRecords) {
            if(yearly.isExpense == true) {
                sumYearlyExpense += yearly.amount;
                k+=1;
            }
        }
        avgYearlyExpense = sumYearlyExpense/k;
        return avgYearlyExpense;
    }

}