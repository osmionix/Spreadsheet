package com.example.prac.controller;

import com.example.prac.dtos.CellDto;
import com.example.prac.dtos.CellFormulaUpdate;
import com.example.prac.dtos.CellValueUpdate;
import com.example.prac.service.CellService;
import com.example.prac.service.DependencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/spreadsheets/{spreadsheet_id}/cells")
public class CellController {

    @Autowired
    private CellService cellService;

    @Autowired
    private DependencyService dependencyService;

    @PostMapping("/{cell_id}/value")
    public ResponseEntity<CellDto> setCellValue(
            @PathVariable("spreadsheet_id") String spreadsheetId,
            @PathVariable("cell_id") String cellId,
            @RequestBody CellValueUpdate update) {
        CellDto result = cellService.setCellValue(spreadsheetId, cellId, update);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{cell_id}/formula")
    public ResponseEntity<CellDto> setCellFormula(
            @PathVariable("spreadsheet_id") String spreadsheetId,
            @PathVariable("cell_id") String cellId,
            @RequestBody CellFormulaUpdate update) {
        CellDto result = cellService.setCellFormula(spreadsheetId, cellId, update);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{cell_id}")
    public ResponseEntity<CellDto> getCell(
            @PathVariable("spreadsheet_id") String spreadsheetId,
            @PathVariable("cell_id") String cellId) {
        CellDto result = cellService.getCell(spreadsheetId, cellId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{cell_id}/dependents")
    public ResponseEntity<List<String>> getCellDependents(
            @PathVariable("spreadsheet_id") String spreadsheetId,
            @PathVariable("cell_id") String cellId) {
        List<String> dependents = dependencyService.getDependents(spreadsheetId, cellId);
        return ResponseEntity.ok(dependents);
    }

    @GetMapping("/{cell_id}/precedents")
    public ResponseEntity<List<String>> getCellPrecedents(
            @PathVariable("spreadsheet_id") String spreadsheetId,
            @PathVariable("cell_id") String cellId) {
        List<String> precedents = dependencyService.getPrecedents(spreadsheetId, cellId);
        return ResponseEntity.ok(precedents);
    }
}