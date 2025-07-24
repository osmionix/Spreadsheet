package com.example.prac.models;

import jakarta.persistence.*;

@Entity
public class CellDependency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dependent_cell_id")
    private Cell dependentCell;

    @ManyToOne
    @JoinColumn(name = "precedent_cell_id")
    private Cell precedentCell;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cell getDependentCell() {
        return dependentCell;
    }

    public void setDependentCell(Cell dependentCell) {
        this.dependentCell = dependentCell;
    }

    public Cell getPrecedentCell() {
        return precedentCell;
    }

    public void setPrecedentCell(Cell precedentCell) {
        this.precedentCell = precedentCell;
    }
}