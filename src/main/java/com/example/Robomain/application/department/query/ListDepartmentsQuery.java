package com.example.Robomain.application.department.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @AllArgsConstructor @NoArgsConstructor
public class ListDepartmentsQuery {
    private int page = 0;
    private int size = 10;
    private String search;
}
