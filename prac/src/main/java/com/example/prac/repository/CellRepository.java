package com.example.prac.repository;
import com.example.prac.models.Cell;
import com.example.prac.models.Spreadsheet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CellRepository extends JpaRepository<Cell, String> {
    Optional<Cell> findBySpreadsheetAndId(Spreadsheet spreadsheet, String cellId);
}