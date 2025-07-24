package com.example.prac.repository;

import com.example.prac.models.Spreadsheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpreadsheetRepository extends JpaRepository<Spreadsheet, String> {
}