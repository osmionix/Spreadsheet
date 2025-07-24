package com.example.prac.repository;

import com.example.prac.models.Cell;
import com.example.prac.models.CellDependency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CellDependencyRepository extends JpaRepository<CellDependency, Long> {
    List<CellDependency> findByDependentCell(Cell cell);
    List<CellDependency> findByPrecedentCell(Cell cell);
    void deleteByDependentCell(Cell cell);
}