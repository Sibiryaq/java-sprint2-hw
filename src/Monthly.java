public class Monthly {
    int monthNumber;
    String itemName;
    boolean isExpense;
    int quantity;
    int sumOfOne;

    Monthly(int monthNumber, String itemName, boolean isExpense,  int quantity , int sumOfOne){
        this.monthNumber = monthNumber;
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }
}
