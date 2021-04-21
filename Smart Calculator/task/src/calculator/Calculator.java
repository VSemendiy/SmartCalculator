package calculator;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Calculator {
    private static final Map<String, String> VARS = new HashMap<>();

    static String calculate(String expr) {
        BigInteger sum = BigInteger.ZERO;

        if (expr.matches("(.*[*/^+=-].*)|(\\s*[A-Za-z]*\\s*)|(\\s*\\d*\\s*)")) {
            expr = expr.replaceAll("(\\s+)", "");
        }

        try {

            if (expr.contains("=")) {
                assignment(expr);
                return null;
            }

            if (expr.matches(".*[A-Za-z].*")) expr = substitutionOfValues(expr);

            if (expr.matches(".*\\(.+\\).*")) expr = parentheses(expr);

            if (expr.matches(".*\\d+\\^[*/^+-]*\\d+.*")) expr = power(expr);

            if (expr.matches(".*\\d+\\*[*/^+-]*\\d+.*")) expr = multiplication(expr);

            if (expr.matches(".*\\d+/[*/^+-]*\\d+.*")) expr = division(expr);

            sum = sum.add(subAndAdd(expr));

        } catch (NumberFormatException e) {
            return "Invalid expression";
        } catch (Exception e) {
            return e.getMessage();
        }
        return (String.valueOf(sum));
    }

    private static BigInteger subAndAdd(String expr) throws NumberFormatException {
        while (expr.matches(".*[+-]{2,}.*")) {
            expr = expr.replaceAll("\\+-", "-");
            expr = expr.replaceAll("-\\+", "-");
            expr = expr.replaceAll("-{2}", "\\+");
            expr = expr.replaceAll("\\+{2,}", "+");
        }

        BigInteger sum = BigInteger.ZERO;
        int indexOfOperand = -1;
        do {
            indexOfOperand = Math.max(expr.lastIndexOf('+'), expr.lastIndexOf('-'));
            if (indexOfOperand > 0) {
                sum = sum.add(new BigInteger(expr.substring(indexOfOperand)));
                expr = expr.substring(0, indexOfOperand);
            } else {
                sum = sum.add(new BigInteger(expr));
                return sum;
            }
        } while (true);
    }

    private static void assignment(String expr) throws UnknownVariableException, InvalidIdentifierException, AssigmentException {

        String variable = expr.substring(0, expr.indexOf("="));
        if (isVarNameIncorrect(variable)) throw new InvalidIdentifierException();

        expr = expr.substring(expr.indexOf("=") + 1);
        if (expr.contains("=")) throw new AssigmentException();

        try {
            if (expr.matches(".*[A-Za-z].*")) expr = substitutionOfValues(expr);

            if (VARS.containsKey(variable)) VARS.replace(variable, calculate(expr));
            else VARS.put(variable, calculate(expr));

        } catch (NumberFormatException | InvalidIdentifierException e) {
            throw new AssigmentException();
        }
    }

    private static String substitutionOfValues(String expr) throws InvalidIdentifierException, UnknownVariableException {
        while (expr.matches("((.*\\d+.*[*/^+-]+)|[*/^+-]*)[A-Za-z]+[*/^+-]*.*")) {
            String key = expr.replaceFirst("[0-9()*/+=^-]*", "");
            key = key.replaceFirst("[()*/+=^-].*", "");

            if (isVarNameIncorrect(key)) throw new InvalidIdentifierException();

            if (VARS.containsKey(key)) expr = expr.replaceFirst(key, VARS.get(key));
            else throw new UnknownVariableException();
        }
        if (expr.matches(".*[A-Za-z].*")) throw new InvalidIdentifierException();
        return expr;
    }

    private static boolean isVarNameIncorrect(String key) {
        return !key.matches("[a-zA-Z]*");
    }

    private static String parentheses(String expr) {
        while (expr.matches(".*\\(.+\\).*")) {
            int openP = expr.lastIndexOf('(');
            int closeP = expr.substring(openP).indexOf(')') + openP;
            String temp = expr.substring(openP + 1, closeP);
            expr = expr.replace("(" + temp + ")", calculate(temp));
        }
        return expr;
    }

    private static String multiplication(String expr) {
        while (expr.matches(".*\\d+\\*[*/^+-]*\\d+.*")) {
            String left = expr.substring(0, expr.indexOf('*')).replaceAll(".*(?!(\\d*$))", "").replaceAll("\\*|\\\\", "");
            String right = expr.substring(expr.indexOf('*') + 1).replaceAll("(?!^)\\D.*$", "");
            String result = "+" + (new BigInteger(left)).multiply(new BigInteger(right));
            expr = expr.replace(String.format("%s*%s", left, right), result);
        }
        return expr;
    }

    private static String division(String expr) {
        while (expr.matches(".*\\d+/[*/^+-]*\\d+.*")) {
            String left = expr.substring(0, expr.indexOf('/')).replaceAll(".*(?!(\\d*$))", "").replaceAll("\\*|\\\\", "");
            String right = expr.substring(expr.indexOf('/') + 1).replaceAll("(?!^)\\D.*$", "");
            if (right.equals("0")) throw new NumberFormatException();
            String result = "+" + (new BigInteger(left)).divide(new BigInteger(right));
            expr = expr.replace(String.format("%s/%s", left, right), result);
        }
        return expr;
    }

    private static String power(String expr) {
        while (expr.matches(".*\\d+\\^[*/^+-]*\\d+.*")) {
            String left = expr.substring(0, expr.indexOf('^')).replaceAll(".*(?!(\\d*$))", "").replaceAll("\\*|\\\\", "");
            String right = expr.substring(expr.indexOf('^') + 1).replaceAll("(?!^)\\D.*$", "");
            String result = "+" + (int) Math.pow(Integer.valueOf(left), Integer.valueOf(right));
            expr = expr.replace(String.format("%s^%s", left, right), result);
        }
        return expr;
    }


}