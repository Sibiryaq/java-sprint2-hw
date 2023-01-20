import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Checker {

    public List<Integer> verificationIncome(MonthlyReport monthlyReport,YearlyReport yearlyReport) {
        Map<Integer,Integer> monthlyIncome = (HashMap<Integer, Integer>) monthlyReport.monthlyIncome();
        HashMap<Integer,Integer> yearlyIncome = yearlyReport.yearlyIncome();
        List<Integer> diffMonthlyIncome = new ArrayList<>();
        for(int i = 1; i<= MonthsConverting.countMonth; i++) {
            if (!monthlyIncome.getOrDefault(i, 0).equals(yearlyIncome.getOrDefault(i, 0))) {
                diffMonthlyIncome.add(i);
            }
        }
        return diffMonthlyIncome;
    }
    public List<Integer> verificationExpense(MonthlyReport monthlyReport,YearlyReport yearlyReport) {
        Map<Integer,Integer> monthlyExpense = (HashMap<Integer, Integer>) monthlyReport.monthlyExpense();
        HashMap<Integer,Integer> yearlyExpense = yearlyReport.yearlyExpense();
        List<Integer> diffMonthlyExpense = new ArrayList<>();

        for(int i = 1; i <= MonthsConverting.countMonth; i++) {
            if (!monthlyExpense.getOrDefault(i, 0).equals(yearlyExpense.getOrDefault(i, 0))) {
                diffMonthlyExpense.add(i);
            }
        }
        return diffMonthlyExpense;
    }

}