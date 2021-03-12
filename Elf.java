package Piace;

import java.awt.*;

public class Elf extends Piece {

    public Elf(int row, int col, Color color,int team) {

        super(team);

        this.row    = row;
        this.col    = col;
        this.color  = color;
        this.id     = "E";
    }

    public Elf(int team){
        super(team);
        attack = 5;
        armor = 1;
        health = 10;
        attack_square = 3;
        speed = 3;

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
