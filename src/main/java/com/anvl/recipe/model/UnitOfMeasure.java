package com.anvl.recipe.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class UnitOfMeasure {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String uom;
}
