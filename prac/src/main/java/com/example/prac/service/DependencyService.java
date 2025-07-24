package com.example.prac.service;

import com.example.prac.models.Cell;
import com.example.prac.models.CellDependency;
import com.example.prac.models.Spreadsheet;
import com.example.prac.repository.CellDependencyRepository;
import com.example.prac.repository.CellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DependencyService {

    @Autowired
    private CellDependencyRepository cellDependencyRepository;

    @Autowired
    private CellRepository cellRepository;

    @Transactional
    public void clearDependencies(Cell cell) {
        cellDependencyRepository.deleteByDependentCell(cell);
    }

    @Transactional
    public void setDependencies(Cell dependentCell, List<String> precedentCellIds, Spreadsheet spreadsheet) {
        for (String precedentCellId : precedentCellIds) {
            Cell precedentCell = cellRepository.findBySpreadsheetAndId(spreadsheet, precedentCellId)
                    .orElseGet(() -> {
                        Cell newCell = new Cell();
                        newCell.setId(precedentCellId);
                        newCell.setSpreadsheet(spreadsheet);
                        return cellRepository.save(newCell);
                    });

            CellDependency dependency = new CellDependency();
            dependency.setDependentCell(dependentCell);
            dependency.setPrecedentCell(precedentCell);
            cellDependencyRepository.save(dependency);
        }
    }

    public List<String> getDependents(String spreadsheetId, String cellId) {
        Spreadsheet spreadsheet = new Spreadsheet();
        spreadsheet.setId(spreadsheetId);

        Cell cell = new Cell();
        cell.setId(cellId);
        cell.setSpreadsheet(spreadsheet);

        List<CellDependency> dependencies = cellDependencyRepository.findByPrecedentCell(cell);
        return dependencies.stream()
                .map(d -> d.getDependentCell().getId())
                .collect(Collectors.toList());
    }

    public List<String> getPrecedents(String spreadsheetId, String cellId) {
        Spreadsheet spreadsheet = new Spreadsheet();
        spreadsheet.setId(spreadsheetId);

        Cell cell = new Cell();
        cell.setId(cellId);
        cell.setSpreadsheet(spreadsheet);

        List<CellDependency> dependencies = cellDependencyRepository.findByDependentCell(cell);
        return dependencies.stream()
                .map(d -> d.getPrecedentCell().getId())
                .collect(Collectors.toList());
    }
}