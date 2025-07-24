package com.example.prac.controller;


import com.example.prac.dtos.RecalculationOrderResponse;
import com.example.prac.service.RecalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spreadsheets/{spreadsheet_id}")
public class RecalculationController {

    @Autowired
    private RecalculationService recalculationService;

    @GetMapping("/recalculate-order")
    public ResponseEntity<RecalculationOrderResponse> getRecalculationOrder(
            @PathVariable("spreadsheet_id") String spreadsheetId,
            @RequestParam("changed_cell_id") String changedCellId) {
        RecalculationOrderResponse response = recalculationService.getRecalculationOrder(spreadsheetId, changedCellId);
        return ResponseEntity.ok(response);
    }
}