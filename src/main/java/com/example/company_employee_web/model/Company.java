package com.example.company_employee_web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Company {

    private int id;
    private String name;
    private String country;
}
