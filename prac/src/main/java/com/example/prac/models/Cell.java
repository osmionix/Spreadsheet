package com.example.prac.models;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Cell {
    @Id
    @Column(name = "cell_id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "spreadsheet_id")
    private Spreadsheet spreadsheet;

    private String value;
    private String formulaString;

    @OneToMany(mappedBy = "dependentCell", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CellDependency> dependencies;

    @OneToMany(mappedBy = "precedentCell", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CellDependency> dependents;

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Spreadsheet getSpreadsheet() {
        return spreadsheet;
    }

    public void setSpreadsheet(Spreadsheet spreadsheet) {
        this.spreadsheet = spreadsheet;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFormulaString() {
        return formulaString;
    }

    public void setFormulaString(String formulaString) {
        this.formulaString = formulaString;
    }

    public Set<CellDependency> getDependencies() {
        return dependencies;
    }

    public void setDependencies(Set<CellDependency> dependencies) {
        this.dependencies = dependencies;
    }

    public Set<CellDependency> getDependents() {
        return dependents;
    }

    public void setDependents(Set<CellDependency> dependents) {
        this.dependents = dependents;
    }
}