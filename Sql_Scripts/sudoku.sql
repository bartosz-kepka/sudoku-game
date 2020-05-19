CREATE DATABASE Sudoku;

USE Sudoku;

CREATE table SudokuBoards(
    savename varchar(30) NOT NULL,
    board varbinary(4096),
    PRIMARY KEY (savename)
);

INSERT INTO SudokuBoards VALUES ('Tester Baz Trzeci Testowal Ma', 7312);

SELECT * FROM SudokuBoards;

DELETE FROM SudokuBoards WHERE savename = 'Tester Baz Trzeci Testowal Ma';

SELECT * FROM SudokuBoards;

DROP TABLE SudokuBoards;

