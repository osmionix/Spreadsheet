package com.example.prac.service;

import com.example.prac.models.Cell;
import com.example.prac.models.Spreadsheet;
import com.example.prac.dtos.CellDto;
import com.example.prac.dtos.CellFormulaUpdate;
import com.example.prac.dtos.CellValueUpdate;
import com.example.prac.repository.CellRepository;
import com.example.prac.repository.SpreadsheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CellService {

    @Autowired
    private CellRepository cellRepository;

    @Autowired
    private SpreadsheetRepository spreadsheetRepository;

    @Autowired
    private DependencyService dependencyService;

    @Transactional
    public CellDto setCellValue(String spreadsheetId, String cellId, CellValueUpdate update) {
        Spreadsheet spreadsheet = spreadsheetRepository.findById(spreadsheetId)
                .orElseGet(() -> {
                    Spreadsheet newSpreadsheet = new Spreadsheet();
                    newSpreadsheet.setId(spreadsheetId);
                    return spreadsheetRepository.save(newSpreadsheet);
                });

        Cell cell = cellRepository.findBySpreadsheetAndId(spreadsheet, cellId)
                .orElseGet(() -> {
                    Cell newCell = new Cell();
                    newCell.setId(cellId);
                    newCell.setSpreadsheet(spreadsheet);
                    return newCell;
                });

        // Clear formula if setting direct value
        if (cell.getFormulaString() != null) {
            dependencyService.clearDependencies(cell);
            cell.setFormulaString(null);
        }

        cell.setValue(update.getValue());
        cellRepository.save(cell);

        return new CellDto(cellId, cell.getValue(), cell.getFormulaString());
    }

    @Transactional
    public CellDto setCellFormula(String spreadsheetId, String cellId, CellFormulaUpdate update) {
        Spreadsheet spreadsheet = spreadsheetRepository.findById(spreadsheetId)
                .orElseGet(() -> {
                    Spreadsheet newSpreadsheet = new Spreadsheet();
                    newSpreadsheet.setId(spreadsheetId);
                    return spreadsheetRepository.save(newSpreadsheet);
                });

        Cell cell = cellRepository.findBySpreadsheetAndId(spreadsheet, cellId)
                .orElseGet(() -> {
                    Cell newCell = new Cell();
                    newCell.setId(cellId);
                    newCell.setSpreadsheet(spreadsheet);
                    return newCell;
                });

        // Clear existing dependencies
        dependencyService.clearDependencies(cell);

        // Set new formula
        cell.setFormulaString(update.getFormulaString());
        cell.setValue(null); // Clear value as it needs to be recalculated

        cellRepository.save(cell);

        // Parse formula and set new dependencies
        List<String> precedentCellIds = parseFormula(update.getFormulaString());
        dependencyService.setDependencies(cell, precedentCellIds, spreadsheet);

        return new CellDto(cellId, cell.getValue(), cell.getFormulaString());
    }

    public CellDto getCell(String spreadsheetId, String cellId) {
        Spreadsheet spreadsheet = spreadsheetRepository.findById(spreadsheetId)
                .orElseThrow(() -> new RuntimeException("Spreadsheet not found"));

        Cell cell = cellRepository.findBySpreadsheetAndId(spreadsheet, cellId)
                .orElseThrow(() -> new RuntimeException("Cell not found"));

        return new CellDto(cellId, cell.getValue(), cell.getFormulaString());
    }

    private List<String> parseFormula(String formulaString) {
        // Implement formula parsing to extract cell references
        // This is a simplified version - in a real app you'd need a proper parser
        List<String> cellIds = new ArrayList<>();
        Pattern pattern = Pattern.compile("[A-Z]+[0-9]+");
        Matcher matcher = pattern.matcher(formulaString);
        while (matcher.find()) {
            cellIds.add(matcher.group());
        }
        return cellIds;
    }
}