package Piace;

import java.awt.*;

public class Кnight  extends Piece{

    public Кnight (int row, int col, Color color,int team) {

        super(team);

        this.row    = row;
        this.col    = col;
        this.color  = color;
        this.id     = "K";
    }
    public Кnight(int team)
    {
        super(team);
        attack = 8;
        armor = 3;
        health = 15;
        attack_square = 1;
        speed = 1;
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
