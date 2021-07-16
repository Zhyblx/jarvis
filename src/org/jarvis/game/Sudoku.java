package org.jarvis.game;

import java.util.*;

/**
 * Sudoku 数独
 *
 * @author zhangyibin
 * <p>
 * {0, 0, 0, 0, 0, 0, 0, 0, 0},
 * {0, 0, 0, 0, 0, 0, 0, 0, 0},
 * {0, 0, 0, 0, 0, 0, 0, 0, 0},
 */

public class Sudoku {

    private static final int SIZE = 9;
    private static int[][] matrix = {
            {0, 0, 0, 2, 0, 7, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 4, 0, 0},
            {0, 2, 0, 0, 5, 0, 8, 0, 6},

            {7, 1, 0, 0, 9, 0, 0, 0, 0},
            {8, 0, 0, 0, 0, 0, 5, 0, 0},
            {6, 0, 0, 3, 0, 0, 0, 2, 1},

            {9, 6, 0, 0, 4, 0, 0, 3, 8},
            {0, 0, 7, 8, 0, 0, 9, 0, 5},
            {0, 0, 8, 9, 6, 0, 0, 0, 0}
    };

    private static void printSudoku() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println("");
        }
    }

    //该方法用于检查是否还有未赋值的单元格
    //如果有未赋值的单元格
    //那么这个方法将相应地修改行和列的值
    private static int[] numberUnassigned(int row, int col) {
        int numunassign = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                //单元格未被赋值
                if (matrix[i][j] == 0) {
                    //修改行和列的值
                    row = i;
                    col = j;
                    //有一个或多个未赋值的单元格
                    numunassign = 1;
                    int[] a = {numunassign, row, col};
                    return a;
                }
            }
        }
        int[] a = {numunassign, -1, -1};
        return a;
    }

    //该方法用于检查是否可以填写一个
    //值到对应的单元格中
    private static boolean isSafe(int n, int r, int c) {
        //检查行
        for (int i = 0; i < SIZE; i++) {
            //某个单元格具有相同值
            if (matrix[r][i] == n)
                return false;
        }
        //检查列
        for (int i = 0; i < SIZE; i++) {
            //某个单元格的值等于i
            if (matrix[i][c] == n)
                return false;
        }
        //检查子矩阵
        int row_start = (r / 3) * 3;
        int col_start = (c / 3) * 3;
        for (int i = row_start; i < row_start + 3; i++) {
            for (int j = col_start; j < col_start + 3; j++) {
                if (matrix[i][j] == n)
                    return false;
            }
        }
        return true;
    }

    //该方法用回溯来求解数独问题
    private static boolean solveSudoku() {
        int row = 0;
        int col = 0;
        int[] a = numberUnassigned(row, col);
        //如果所有单元格都被赋值了的，那么数独问题已经通过引用被求解了；
        //因为number_unassigned将修改行和列的值
        if (a[0] == 0)
            return true;
        //用1到9之间的数字
        row = a[1];
        col = a[2];
        for (int i = 1; i <= SIZE; i++) {
            //是否可以将i赋值给单元格
            //单元格是matrix[row][col]
            if (isSafe(i, row, col)) {
                matrix[row][col] = i;
                //回溯
                if (solveSudoku())
                    return true;
                //如果我们不能继续求解这个问题
                //重新赋值单元
                matrix[row][col] = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        if (solveSudoku())
            printSudoku();
        else
            System.out.println("No solution");
    }
}


