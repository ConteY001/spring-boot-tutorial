package de.yaxc.springboottutorial.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tbl_department")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {
    @Id
    @SequenceGenerator(
            name = "springboot_tutorial_qa",
            sequenceName = "springboot_tutorial_qa",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "springboot_tutorial_qa",
            strategy = GenerationType.SEQUENCE
    )
    private Long departmentId;

    @NotBlank(message = "Please Add Department Name")
    private String departmentName;
    private String departmentAddress;
    private String departmentCode;
}
