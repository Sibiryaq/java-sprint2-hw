import java.util.Scanner;

public class BookKeeperApplication {
    Scanner scanner;
    MonthlyReport monthlyReport;
    YearlyReport yearlyReport;

    public void run() {
        System.out.println("Введите команду:");
        scanner = new Scanner(System.in);
        monthlyReport = new MonthlyReport();
        yearlyReport = new YearlyReport();

        while (true) {
            printMenu();
            String line = scanner.nextLine();

            if (line.equals("1")) {
                monthlyReport.loadMonthReports();
            } else if (line.equals("2")) {
                yearlyReport.loadYearReport();
            } else if (line.equals("3")) {
                return;
            } else if (line.equals("4")) {
                monthlyReport.printMonthReport();
            } else if (line.equals("5")) {
                yearlyReport.printYearReport();
            } else if (line.equals("0")) {
                System.out.println("Выход!");
                break;
            } else {
                System.out.println("Такого пункта в меню нет");
            }
        }

    }

    public void printMenu() {
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию обо всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выход");
    }
}
