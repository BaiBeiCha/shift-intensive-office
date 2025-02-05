package by.koronatech.office.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeDTO {

    private String name;

    private double salary;

    private String department;

    private boolean manager;

}
