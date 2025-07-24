package com.example.prac.dtos;

public class CellDto {
    private String cellId;
    private String value;
    private String formulaString;

    // Constructors, getters and setters
    public CellDto() {
    }

    public CellDto(String cellId, String value, String formulaString) {
        this.cellId = cellId;
        this.value = value;
        this.formulaString = formulaString;
    }

    public String getCellId() {
        return cellId;
    }

    public void setCellId(String cellId) {
        this.cellId = cellId;
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
}