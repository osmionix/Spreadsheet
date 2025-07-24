# Spreadsheet Cell Management API

A Spring Boot application for managing spreadsheet cells, their values, formulas, and dependencies with a relational database backend.

## Features

- **Cell Value Management**: Set/update direct cell values (numbers or strings)
- **Formula Handling**: Store formulas and track cell dependencies
- **Dependency Tracking**: Identify cell precedents and dependents
- **Recalculation Engine**: Determine proper recalculation order with cycle detection
- **RESTful API**: Fully documented endpoints for all operations

## API Endpoints

### Base Functionality

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST   | `/spreadsheets/{spreadsheet_id}/cells/{cell_id}/value` | Set a cell's direct value |
| POST   | `/spreadsheets/{spreadsheet_id}/cells/{cell_id}/formula` | Set a cell's formula |
| GET    | `/spreadsheets/{spreadsheet_id}/cells/{cell_id}` | Get a cell's current state |

### Milestone Features

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET    | `/spreadsheets/{spreadsheet_id}/cells/{cell_id}/dependents` | Get cells that depend on this cell |
| GET    | `/spreadsheets/{spreadsheet_id}/cells/{cell_id}/precedents` | Get cells this cell depends on |
| GET    | `/spreadsheets/{spreadsheet_id}/recalculate-order?changed_cell_id={cell_id}` | Get recalculation order |

---

### Submitted By - AYUSH RAJ
