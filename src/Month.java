public class Month {
    public String itemName;
    public boolean isExpense;
    public int quantity;
    public long price;

    public Month(String itemName, boolean isExpense, int quantity, long sumOfOne) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.price = sumOfOne;
    }

    public long getTotal() {
        return quantity * price;
    }

    @Override
    public String toString() {
        return "Month[" +
                "itemName='" + itemName + '\'' +
                ", isExpense=" + isExpense +
                ", quantity=" + quantity +
                ", sumOfOne=" + price +
                ']';
    }
}
