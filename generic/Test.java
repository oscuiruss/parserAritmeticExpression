package expression.generic;

import expression.exceptions.DividedByZero;
import expression.exceptions.VarOverflow;

public class Test {
    public static void main(String[] args) throws Exception {
        Tabulator tabulator = new GenericTabulator();
        if (args != null && args[0] != null && args[1] != null ) {
            String mode = args[0];
            String expression = args[1];
            Object[][][] table = tabulator.tabulate(mode, expression, -2, 2, -2, 2, -2, 2);
            for (int i = -2; i <= 2; i++) {
                for (int j = -2; j <= 2; j++) {
                    for (int k = -2; k <= 2; k++) {
                        System.out.println("x = " + i +
                                " y = " + j +
                                " z = " + k +
                                "  answer = " + table[i + 2][j + 2][k + 2]);

                    }
                }
            }
        } else {
            System.out.println("Incorrect input");
        }
    }
}
