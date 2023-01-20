public class MonthsConverting {
    static int countMonth = 3;
    public String getMonth(int monthNumber){
        switch (monthNumber) {
            case(1): return "Январь";
            case(2): return "Февраль";
            case(3): return "Март";
            default:
                return "Такого месяца не было в ТЗ";
        }
    }
} /* не стоит думать что я сильно шарю(switch/case/default не проходили),
     мне просто подсказали возможность реализации,
     я понял логику,
     и решил вставить это сюда, т.к. это выглядит красиво и пользоваться этим удобно */
