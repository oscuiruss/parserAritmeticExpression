package expression.generic;

import expression.exceptions.ParseException;
import expression.operations.*;
import expression.types.NumType;

import java.util.Map;

public class ExpressionParser<T extends Number> implements Parser<T> {
    private String expression;
    private int pos = 0;
    private final Map<String, Integer> levels = Map.of(
            "max", 1,
            "min", 1,
            "-", 2,
            "+", 2,
            "*", 3,
            "/", 3
    );
    protected NumType<T> typeParser;

    public ExpressionParser(NumType<T> typeParser) {
        this.typeParser = typeParser;
    }

    private boolean eos() {
        return pos >= expression.length();
    }

    private void deleteWhiteSpace() {
        while (!eos() && Character.isWhitespace(expression.charAt(pos))) {
            pos++;
        }
    }

    public TripleExpression<T> createOperation(TripleExpression<T> first, TripleExpression<T> second, String sign) {
        if (sign.equals("+")) {
            return new Add<>(first, second, typeParser);
        } else if (sign.equals("-")) {
            return new Subtract<>(first, second, typeParser);
        } else if (sign.equals("*")) {
            return new Multiply<>(first, second, typeParser);
        } else if (sign.equals("/")) {
            return new Divide<>(first, second, typeParser);
        } else {
            return null;
        }
    }

    @Override
    public TripleExpression<T> parse(String expression) {
        this.expression = expression;
        pos = 0;
        TripleExpression<T> p = parseParts(1);
        if (pos < expression.length()) {
            throw new ParseException("Incorrect expression");
        }
        return p;
    }

    public TripleExpression<T> parseParts(int priority) {
        TripleExpression<T> result = priority < 3 ? parseParts(priority + 1) : parseUnaryOperation();
        deleteWhiteSpace();
        while (!eos() && levels.getOrDefault(checkSign(), -1) == priority) {
            String sign = checkSign();
            pos += sign.length();
            result = createOperation(result,
                    priority < 3 ? parseParts(priority + 1) : parseUnaryOperation(), sign);
            deleteWhiteSpace();
        }
        return result;
    }

    private String checkSign() {
        String sign = "";
        if (!eos()) {
            if (expression.startsWith("max", pos)) {
                sign = "max";
            } else if (expression.startsWith("min", pos)) {
                sign = "min";
            } else {
                sign = expression.charAt(pos) + "";
            }
        }
        return sign;
    }

    public TripleExpression<T> parseUnaryOperation() {
        deleteWhiteSpace();

        if (eos()) {
            throw new ParseException("Unexpected end of the expression");
        }

        if (expression.charAt(pos) == '-') {
            pos++;
//            if (expression.length() - 9 > pos && expression.startsWith("2147483648", pos)) {
//                pos += 10;
//                return new Const<T>(Integer.MIN_VALUE);
//            }
            return new UnaryMinus<T>(parseUnaryOperation(), typeParser);
        }


        if (expression.charAt(pos) == '(') {
            pos++;
            TripleExpression<T> current = parseParts(1);
            deleteWhiteSpace();
            if (eos() || expression.charAt(pos) != ')') {
                throw new ParseException("Incorrect expression");
            }
            pos++;
            return current;
        }

        if (expression.charAt(pos) == 'x' ||
                expression.charAt(pos) == 'y' ||
                expression.charAt(pos) == 'z') {
            char c = expression.charAt(pos);
            pos++;
            return new Variable<T>(String.valueOf(c));
        }

        if (Character.isDigit(expression.charAt(pos))) {
            int startPos = pos;
            while (!eos() && Character.isDigit(expression.charAt(pos))) {
                pos++;
            }
            try {
                return new Const<T>(typeParser.parse(expression.substring(startPos, pos)));
            } catch (NumberFormatException e) {
                throw new ParseException("The number is bigger than MAX.VALUE");
            }
        }

        throw new ParseException("Incorrect expression");
    }

}/*if (expression.startsWith("max")) {
                    sign = "max";
                    pos += 3;
                } else if (expression.startsWith("min")) {
                    sign = "min";
                    pos += 3;
                } else {
                    sign = expression.charAt(pos) + "";
                    pos++;
                }*/