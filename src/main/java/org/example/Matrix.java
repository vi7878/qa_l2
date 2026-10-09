package org.example;

public class Matrix {
    private final int[][] data;
    private final int rows;
    private final int cols;

    public Matrix(int[][] data) {
        if (data == null || data.length == 0 || data[0] == null || data[0].length == 0) {
            throw new IllegalArgumentException("Матриця не може бути порожньою або null");
        }
        this.rows = data.length;
        this.cols = data[0].length;
        this.data = new int[rows][cols];

        // Копіювання масиву для уникнення змін ззовні та перевірка форми матриці
        for (int i = 0; i < rows; i++) {
            if (data[i] == null || data[i].length != cols) {
                throw new IllegalArgumentException("Некоректна форма матриці (різна кількість стовпців у рядках)");
            }
            System.arraycopy(data[i], 0, this.data[i], 0, cols);
        }
    }

    public Matrix add(Matrix other) {
        if (other == null) {
            throw new IllegalArgumentException("Матриця для додавання не може бути null");
        }
        // Перевірка на збіг розмірностей
        if (this.rows != other.getRows() || this.cols != other.getCols()) {
            throw new IllegalArgumentException("Розмірності матриць не збігаються");
        }

        int[][] resultData = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                resultData[i][j] = this.data[i][j] + other.data[i][j];
            }
        }
        return new Matrix(resultData);
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int[][] getData() {
        return data;
    }
}
