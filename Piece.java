package Piace;

import  game.GameTile;
import java.awt.*;

public  abstract class Piece {

    protected int row;
    protected int col;
    protected Color color;
    protected String id;

    public int attack;
    public int armor;
    public int health;
    public int attack_square;
    public int speed;
    public int team;


    public Piece(int team){
        this.team=team;

    }
    public Piece(int attack, int armor, int health, int attack_square, int speed ){
        this.attack = attack;
        this.armor=armor;
        this.health = health;
        this.attack_square = attack_square;
        this.speed =speed;

    }
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void move(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void render(Graphics g) {

        int x = this.col * GameTile.TILE_SIZE;
        int y = this.row * GameTile.TILE_SIZE;

        g.setColor(this.color);
        g.fillRect(x, y, 50, 50);

        g.setColor(Color.BLACK);
        g.drawString(this.id, x + 25, y + 25);
    }


//    public boolean isMoveValid(int moveRow, int moveCol) {
//        return false;
//    }

    public abstract boolean isMoveValid(int moveRow, int moveCol);

//    public boolean isAttackValid(int attackRow, int attackCol) {
//        return false;
//    }

    public abstract boolean isAttackValid(int attackRow, int attackCol);
}
