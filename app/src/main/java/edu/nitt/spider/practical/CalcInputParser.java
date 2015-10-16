package edu.nitt.spider.practical;

/**
 * Created by SCHOOL on 16-10-2015.
 */
public class CalcInputParser {

    // Constants

    private enum State {
        ENTERING_NO_1,
        ENTERED_OPERATOR,
        ENTERING_NO_2
    };

    private static final char[] ALLOWED_DIGITS =
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    private static final char[] ALLOWED_OPS =
            {'+', '-', '*', '/'};

    // Variables

    private int firstOperand;
    private int secondOperand;
    private char latestOperator;
    private State currentState;

    //-------------------------

    // Constructor

    public CalcInputParser() {
        firstOperand = 0;
        secondOperand = 0;
        currentState = State.ENTERING_NO_1;
    }

    //-------------------------

    // Major functions

    public String parse(final String inputString) {
        // parses input string, returns output or error

        char currentChar;
        for (int i = 0; i < inputString.length(); i++) {
            currentChar = inputString.charAt(i);
            try {
                updateState(currentChar);
            } catch (Exception e) {
                return e.getMessage();
            }
        }

        // reached last character
        if (currentState == State.ENTERED_OPERATOR) {
            return "Malformed Input: Last character is operator";
        }
        else if (currentState == State.ENTERING_NO_2) {
            try {
                firstOperand = calculateValue(firstOperand, latestOperator, secondOperand);
            } catch (Exception e) {
                return e.getMessage();
            }
        }

        Integer result = new Integer(firstOperand);
        reset();
        return result.toString();
    }

    private void updateState(char currentChar) throws Exception {
        // takes current character, and updates state of parser

        if(contains(currentChar, ALLOWED_DIGITS)) {
            // currentChar is a digit
            int currentDigit = getDigit(currentChar);

            if (currentState == State.ENTERING_NO_1) {              //only for 1st operand
                firstOperand = firstOperand*10 + currentDigit;
            }
            else if (currentState == State.ENTERING_NO_2) {         //
                secondOperand = secondOperand*10 + currentDigit;
            }
            else if (currentState == State.ENTERED_OPERATOR) {      //after operator state, change state
                currentState = State.ENTERING_NO_2;
                secondOperand = currentDigit;
            }
        }

        else if(contains(currentChar, ALLOWED_OPS)) {
            // currentChar is an operator
            if (currentState == State.ENTERING_NO_1) {              //state 1 to state operator with saving operator
                currentState = State.ENTERED_OPERATOR;
                latestOperator = currentChar;
            }
            else if (currentState == State.ENTERED_OPERATOR) {
                throw new Exception("Malformed Input: Two consecutive operators");
            }
            else if (currentState == State.ENTERING_NO_2) {
                currentState = State.ENTERED_OPERATOR;
                firstOperand = calculateValue(firstOperand, latestOperator, secondOperand);
                latestOperator = currentChar;
            }
        }
    }


    private int calculateValue (int firstNo, char op, int secondNo) throws Exception {
        // Calculates firstNo (op) secondNo
        try {
            if (op == '+') return firstNo + secondNo;
            else if (op == '-') return firstNo - secondNo;
            else if (op == '*') return firstNo * secondNo;
            else if (op == '/') return firstNo / secondNo;
            else return 0;
        } catch (Exception e) {
            throw new Exception("Malformed Input: Divide-by-zero error");
        }
    }

    //-------------------------

    // Utility functions

    private boolean contains(char checkChar, final char[] container) {
        // checks if character is in the container

        boolean returnVal = false;
        for (int i = 0; i < container.length; i++) {
            if (container[i] == checkChar) {
                returnVal = true;
                break;
            }
        }

        return returnVal;
    }

    private int getDigit(char c) {
        // gets digit form from a single character
        return Integer.parseInt(Character.toString(c));
    }


    private void reset() {
        // resets variables and state
        firstOperand = 0;
        secondOperand = 0;
        currentState = State.ENTERING_NO_1;
    }
}
