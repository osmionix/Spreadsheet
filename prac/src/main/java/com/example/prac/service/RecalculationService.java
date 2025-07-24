package com.example.prac.service;

import com.example.prac.dtos.RecalculationOrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecalculationService {

    @Autowired
    private DependencyService dependencyService;

    public RecalculationOrderResponse getRecalculationOrder(String spreadsheetId, String changedCellId) {
        Set<String> visited = new HashSet<>();
        List<String> order = new ArrayList<>();
        Set<String> recursionStack = new HashSet<>();
        List<String> cycleCells = new ArrayList<>();

        if (hasCycle(spreadsheetId, changedCellId, visited, recursionStack, cycleCells)) {
            return new RecalculationOrderResponse("cycle_detected_involving_cells", cycleCells);
        }

        visited.clear();
        topologicalSort(spreadsheetId, changedCellId, visited, order);

        return new RecalculationOrderResponse(order);
    }

    private boolean hasCycle(String spreadsheetId, String cellId, Set<String> visited,
                             Set<String> recursionStack, List<String> cycleCells) {
        if (recursionStack.contains(cellId)) {
            cycleCells.add(cellId);
            return true;
        }

        if (visited.contains(cellId)) {
            return false;
        }

        visited.add(cellId);
        recursionStack.add(cellId);

        for (String dependent : dependencyService.getDependents(spreadsheetId, cellId)) {
            if (hasCycle(spreadsheetId, dependent, visited, recursionStack, cycleCells)) {
                if (!cycleCells.contains(cellId)) {
                    cycleCells.add(cellId);
                }
                return true;
            }
        }

        recursionStack.remove(cellId);
        return false;
    }

    private void topologicalSort(String spreadsheetId, String cellId, Set<String> visited, List<String> order) {
        if (visited.contains(cellId)) {
            return;
        }

        visited.add(cellId);

        for (String dependent : dependencyService.getDependents(spreadsheetId, cellId)) {
            topologicalSort(spreadsheetId, dependent, visited, order);
        }

        order.add(cellId);
    }
}