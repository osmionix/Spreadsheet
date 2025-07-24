package com.example.prac.dtos;
import java.util.List;

public class RecalculationOrderResponse {
    private List<String> order;
    private String error;
    private List<String> cells;

    // Constructors, getters and setters
    public RecalculationOrderResponse(List<String> order) {
        this.order = order;
    }

    public RecalculationOrderResponse(String error, List<String> cells) {
        this.error = error;
        this.cells = cells;
    }

    public List<String> getOrder() {
        return order;
    }

    public void setOrder(List<String> order) {
        this.order = order;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<String> getCells() {
        return cells;
    }

    public void setCells(List<String> cells) {
        this.cells = cells;
    }
}