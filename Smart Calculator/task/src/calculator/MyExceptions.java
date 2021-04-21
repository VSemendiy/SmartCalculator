package calculator;

class AssigmentException extends RuntimeException {
    AssigmentException() {
        super("Invalid assigment");
    }
}

class InvalidIdentifierException extends RuntimeException {
    InvalidIdentifierException() {
        super("Invalid identifier");
    }
}

class UnknownVariableException extends RuntimeException {
    UnknownVariableException() {
        super("Unknown variable");
    }
}