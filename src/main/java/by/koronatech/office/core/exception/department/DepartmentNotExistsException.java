package by.koronatech.office.core.exception.department;

public class DepartmentNotExistsException extends RuntimeException {

    public DepartmentNotExistsException(String name) {
        super("Department " + name + " not exists");
    }

    public DepartmentNotExistsException(String name, Throwable cause) {
        super("Department " + name + " not exists", cause);
    }

    public DepartmentNotExistsException(long id) {
        super("Department with id " + id + " not exists");
    }

    public DepartmentNotExistsException(long id, Throwable cause) {
        super("Department with id " + id + " not exists", cause);
    }
}
