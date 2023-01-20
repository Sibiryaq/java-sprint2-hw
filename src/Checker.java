import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Checker {
    MonthsConverting monthsConverting = new MonthsConverting();

    public List<Integer> verificationIncome(MonthlyReport monthlyReport,YearlyReport yearlyReport) {
        HashMap<Integer,Integer> monthlyIncome = monthlyReport.monthlyIncome();
        HashMap<Integer,Integer> yearlyIncome = yearlyReport.yearlyIncome();
        List<Integer> diffMonthlyIncome = new ArrayList<>();
        for(int i=1; i<=monthsConverting.countMonth; i++) {
            if (!monthlyIncome.getOrDefault(i, 0).equals(yearlyIncome.getOrDefault(i, 0))) {
                diffMonthlyIncome.add(i);
            }
        }
        return diffMonthlyIncome;
    }
    public List<Integer> verificationExpense(MonthlyReport monthlyReport,YearlyReport yearlyReport) {
        HashMap<Integer,Integer> monthlyExpense = monthlyReport.monthlyExpense();
        HashMap<Integer,Integer> yearlyExpense = yearlyReport.yearlyExpense();
        List<Integer> diffMonthlyExpense = new ArrayList<>();

        for(int i = 1; i <= monthsConverting.countMonth; i++) {
            if (!monthlyExpense.getOrDefault(i, 0).equals(yearlyExpense.getOrDefault(i, 0))) {
                diffMonthlyExpense.add(i);
            }
        }
        return diffMonthlyExpense;
    }

}