package expression.generic;

import expression.exceptions.DividedByZero;
import expression.exceptions.VarOverflow;
import expression.types.*;

import java.util.Map;

public class GenericTabulator implements Tabulator {
    private final Map<String, NumType<?>> modes = Map.of(
            "i", new IntegerOkType(),
            "bi", new BigIntegerType(),
            "d", new DoubleType(),
            "u", new IntegerType(),
            "f", new FloatType(),
            "b", new ByteType()
    );

    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1,
                                 int y2, int z1, int z2) throws Exception {
        return createTable(modes.get(mode), expression, x1, x2, y1, y2, z1, z2);
    }


    private <T extends Number> Object[][][] createTable(NumType<T> myMode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) {
        ExpressionParser<T> parser = new ExpressionParser<>(myMode);
        int x = x2 - x1 + 1;
        int y = y2 - y1 + 1;
        int z = z2 - z1 + 1;
        Object[][][] table = new Object[x][y][z];
        TripleExpression<T> myExpression = parser.parse(expression);
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                for (int k = z1; k <= z2; k++) {
                    try {
                        table[i - x1][j - y1][k - z1] = myExpression.evaluate(
                                myMode.parse(Integer.toString(i)),
                                myMode.parse(Integer.toString(j)),
                                myMode.parse(Integer.toString(k))
                        );
                    } catch (DividedByZero | VarOverflow e ) {
                        table[i - x1][j - y1][k - z1] = null;
                    }
                }
            }
        }
        return table;
    }
}
