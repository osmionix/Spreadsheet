package com.example.prac.models;
import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Spreadsheet {
    @Id
    private String id;

    @OneToMany(mappedBy = "spreadsheet", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Cell> cells;

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<Cell> getCells() {
        return cells;
    }

    public void setCells(Set<Cell> cells) {
        this.cells = cells;
    }
}