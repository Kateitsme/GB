package hw_4;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Triangle {
    public static double triangle(int a, int b, int c) {
        double p = (double) (a + b + c) / 2;
        try {
            if (!(a + b > c && a + c > b && b + c > a))
                throw new Exception("Введен не тругольник");
        } catch (Exception e) {
            e.printStackTrace();
            return 0d;
        }
        double value = Math.sqrt(p * (p - (double) a) * (p - (double) b) * (p - (double) c));
        return round(value,2);
    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
