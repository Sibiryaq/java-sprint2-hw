import java.util.ArrayList;
import java.util.List;

public class Manager {
    //хранение
    MonthlyReport monthlyReport = new MonthlyReport();
    YearlyReport yearlyReport = new YearlyReport();
    MonthsConverting monthsConverting = new MonthsConverting();
    Checker verificationReports = new Checker();

    public void getMonthlyReport() {  //загрузка
        String fileName;
        for (int monthNumber = 1; monthNumber <= monthsConverting.countMonth; monthNumber++) {
            monthlyReport.constructMonthlyReader(monthNumber, monthlyReport);
        }
    }

    //верификация
    public void getYearlyReport() {
        yearlyReport.constructYearlyReader("resources/y.2021.csv", yearlyReport);
    }

    public List<Integer> verificationMonthlyIncome() {
        return verificationReports.verificationIncome(monthlyReport, yearlyReport);
    }

    public List<Integer> verificationMonthlyExpense() {
        return verificationReports.verificationExpense(monthlyReport, yearlyReport);
    }

    public ArrayList<ArrayList<String>> getMonthlyReports() {  // месячная статистика
        return monthlyReport.getMonthlyReports();
    }

    //годовая статистика
    public ArrayList<ArrayList<String>> getMonthlyProfit() {
        return yearlyReport.monthlyProfit();
    }

    public int getYearlyIncomeAVG() {
        return yearlyReport.getYearlyIncomeAVG();
    }

    public int getYearlyExpenseAVG() {
        return yearlyReport.getYearlyExpenseAVG();
    }
}