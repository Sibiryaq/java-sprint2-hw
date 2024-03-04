import Month;

import java.util.LinkedHashMap;
import java.util.List;

public class MonthStorage {
    public LinkedHashMap<Integer, List<Month>> monthReports = new LinkedHashMap<>();

    public void saveMonthReport(int month, List<Month> months) {
        monthReports.put(month, months);
    }

    public Month getMaxEarning(int month) {
        List<Month> items = monthReports.get(month);
        Month max = null;
        long total = 0;

        for (Month item : items) {
            if (item.isExpense) {
                continue;
            }
            if (item.getTotal() > total) {
                total = item.getTotal();
                max = item;
            }
        }
        return max;

    }

    public Month getMaxExpense(int month) {
        List<Month> items = monthReports.get(month);
        Month max = null;
        long total = 0;

        for (Month item : items) {
            if (!item.isExpense) {
                continue;
             }
            if (item.getTotal() > total) {
                total = item.getTotal();
                max = item;
            }
        }
        return max;
    }
}
