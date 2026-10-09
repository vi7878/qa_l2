package org.example;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class MatrixTest {
    private Matrix matrixA;
    @BeforeEach
    public void setUp() {
        matrixA = new Matrix(new int[][]{
                {1, 2},
                {3, 4}
        });
    }
    @Test
    @DisplayName("Успішне додавання двох матриць однакового розміру")
    public void testSuccessfulMatrixAddition() {
        Matrix matrixB = new Matrix(new int[][]{
                {5, 6},
                {7, 8}
        });
        Matrix result = matrixA.add(matrixB);

        assertArrayEquals(new int[]{6, 8}, result.getData()[0], "Перший рядок результуючої матриці неправильний");
        assertArrayEquals(new int[]{10, 12}, result.getData()[1], "Другий рядок результуючої матриці неправильний");
    }
    @Test
    @DisplayName("Додавання матриці з від'ємними значеннями")
    public void testMatrixAdditionWithNegativeNumbers() {
        Matrix matrixB = new Matrix(new int[][]{
                {-1, -2},
                {-3, -4}
        });
        Matrix result = matrixA.add(matrixB);
        assertArrayEquals(new int[]{0, 0}, result.getData()[0]);
        assertArrayEquals(new int[]{0, 0}, result.getData()[1]);
    }
    @Test
    @DisplayName("Виняток при створенні порожньої матриці (межовий випадок)")
    public void testEmptyMatrixCreationThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Matrix(new int[][]{}),
                "Очікується виняток при спробі створити порожню матрицю");
    }
    @Test
    public void testAdditionWithZeroMatrix() {
        Matrix zeroMatrix = new Matrix(new int[][]{
                {0, 0},
                {0, 0}
        });
        Matrix result = matrixA.add(zeroMatrix);
        assertArrayEquals(matrixA.getData()[0], result.getData()[0], "Сума з нульовою матрицею не повинна змінювати значення");
        assertArrayEquals(matrixA.getData()[1], result.getData()[1], "Сума з нульовою матрицею не повинна змінювати значення");
    }
    @Test
    public void testAdditionWithDifferentDimensionsThrowsException() {
        Matrix matrixC = new Matrix(new int[][]{
                {1, 2, 3},
                {4, 5, 6}
        });
        assertThrows(IllegalArgumentException.class, () -> matrixA.add(matrixC),
                "Очікується виняток при додаванні матриць різних розмірностей");
    }
    @Test
    public void testMatrixPropertiesWithAssertAll() {
        Matrix matrixB = new Matrix(new int[][]{
                {10, 20},
                {30, 40}
        });
        Matrix result = matrixA.add(matrixB);
        assertAll("Комплексна перевірка властивостей результуючої матриці",
                () -> assertNotNull(result, "Результуюча матриця не повинна бути null"),
                () -> assertEquals(2, result.getRows(), "Кількість рядків має бути 2"),
                () -> assertEquals(2, result.getCols(), "Кількість стовпців має бути 2"),
                () -> assertEquals(11, result.getData()[0][0], "Елемент [0][0] має дорівнювати 11"),
                () -> assertEquals(44, result.getData()[1][1], "Елемент [1][1] має дорівнювати 44")
        );
    }
}