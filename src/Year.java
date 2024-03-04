public class Year {
    public int monthNumber;
    public long amount;
    public boolean isExpense;

    public Year(int monthNumber, long amount, boolean isExpense) {
        this.monthNumber = monthNumber;
        this.amount = amount;
        this.isExpense = isExpense;
    }

    @Override
    public String toString() {
        return "Year{" +
                "monthNumber=" + monthNumber +
                ", amount=" + amount +
                ", isExpense=" + isExpense +
                '}';
    }
}
