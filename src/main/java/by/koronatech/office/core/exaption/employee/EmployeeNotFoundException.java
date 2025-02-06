package by.koronatech.office.core.exaption.employee;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(long id) {
        super("Employee with id " + id + " not found");
    }

    public EmployeeNotFoundException(long id, Throwable cause) {
        super("Employee with id " + id + " not found", cause);
    }
}
