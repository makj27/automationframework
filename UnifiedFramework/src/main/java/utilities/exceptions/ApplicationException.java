package utilities.exceptions;

import org.testng.annotations.Test;

public class ApplicationException extends Exception{


    private final FailureType failureType;
    private final TestStatus testStatus;

    public ApplicationException(FailureType failureType, TestStatus testStatus) {
        super();
        this.failureType = failureType;
        this.testStatus = testStatus;
    }

    public ApplicationException(String message, FailureType failureType, TestStatus testStatus){
        super(message);
        this.failureType = failureType;
        this.testStatus = testStatus;
    }

    public ApplicationException(String message, Throwable cause, FailureType failureType, TestStatus testStatus) {
        super(message, cause);
        this.failureType = failureType;
        this.testStatus = testStatus;
    }

    public ApplicationException(Throwable cause, FailureType failureType, TestStatus testStatus) {
        super(cause);
        this.failureType = failureType;
        this.testStatus = testStatus;
    }

    public FailureType getFailureType() {
        return this.failureType;
    }

    public TestStatus getTestStatus() {
        return this.testStatus;
    }

}
