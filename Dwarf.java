package Piace;

import java.awt.*;

public class Dwarf extends Piace {

    public Dwarf(int row, int col, Color color) {

        this.row    = row;
        this.col    = col;
        this.color  = color;
        this.id     = "D";
    }

    public boolean isMoveValid(int moveRow, int moveCol) {

        int rowCoeficient = Math.abs(moveRow - this.row);
        int colCoeficient = moveCol - this.col;

        return  rowCoeficient == 1 &&
                colCoeficient == 0;
    }

    public boolean isAttackValid(int attackRow, int attackCol) {
        return false;
    }
}
