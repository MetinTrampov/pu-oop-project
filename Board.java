package game;

import Piace.Dwarf;
import Piace.Elf;
import Piace.Piece;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Board  extends JFrame implements MouseListener {

    public static  final int TILE_ROW_SIZE = 7;
    public static  final int  TILE_COL_SIZE = 9;


    private Piece[][] pieceCollection;
    private Piece selectedPiece;
    private int team ;

    public Board (){

        this.pieceCollection = new Piece[TILE_ROW_SIZE][TILE_COL_SIZE];


       // this.pieceCollection[0][0]= (new Dwarf(0,0,Color.red));
       // this.pieceCollection[0][1] = (new Elf(0, 1, Color.YELLOW));

        this.pieceCollection[0][0] = (new Dwarf(0,0,Color.CYAN, team));
        this.pieceCollection[1][4] = (new Dwarf(1,4,Color.CYAN,team));
        this.pieceCollection[6][0] = (new Dwarf(6,0,Color.CYAN,team));
        this.pieceCollection[6][5] = (new Dwarf(6,5,Color.CYAN,team));

        this.pieceCollection[0][5] = (new Elf(0,5,Color.RED,team));
        this.pieceCollection[1][3] = (new Elf(1,3,Color.RED,team));
        this.pieceCollection[6][4] = (new Elf(6,4,Color.RED,team));
        this.pieceCollection[5][2] = (new Elf(5,2,Color.RED,team));










        this.setSize(1200,700);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       // this.setLocationRelativeTo(null);
        this.setBackground(Color.black);
        this.addMouseListener(this);
        this.setVisible(true);














    }

    @Override
    public void mouseClicked(MouseEvent e) {

        int row = this.getBoardDimentionBasedOnCoordinates(e.getY());
        int col = this.getBoardDimentionBasedOnCoordinates(e.getX());

        // check if piece is already selected
        if(this.selectedPiece != null) {

            // TODO: Update pieceCollection array in order to match the new coordinates
            Piece p = this.selectedPiece;
            p.move(row,col);
            this.selectedPiece = null;

            this.repaint();
            /*if(p.isMoveValid(row, col)) {

              //  movePiece(row, col, p);
                this.repaint();
                return;
            }*/
        }
      /*  else {
            // new game.Modal(this, "Внимание", "Невалиден ход, по дъската");

           //this.setLocationRelativeTo(null); Modal.render(this, "Внимание", "Невалиден ход, по дъската");
            return;
        }*/
        // * move

        // check if piece is available
        if(this.hasBoardPiece(row, col)) {
            this.selectedPiece = this.getBoardPiece(row, col);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void paint(Graphics g) {

        for(int row = 0; row < 7; row++) {
            for(int col = 0; col < 9; col++) {

                this.renderGameTile(g, row, col);
              this.renderGamePiece(g, row, col);
            }
        }
    }

    private void movePiece(int row, int col, Piece p) {
        // 1. Get the original coordinates of the selected piece
        int initialRow = p.getRow();
        int initialCol = p.getCol();

        // 2. Move the piece to trhe new coordinates
        p.move(row, col);

        // 3. Swap the reference to the selected piece from the original coordinates
        // TODO: Abstraction of piece collection
        this.pieceCollection[p.getRow()][p.getCol()] = this.selectedPiece;
        this.pieceCollection[initialRow][initialCol] = null;

        // 4. Remove reference to selected piece
        // TODO: Abstraction of selected piece access
        this.selectedPiece = null;
    }

    private Color getTileColor(int row, int col) {
        boolean isBattleRow = false ;
        if (row == 2){
            isBattleRow=true;
        }else if (row==3){
            isBattleRow=true;
        }else if(row == 4){
            isBattleRow=true;
        }
        if(isBattleRow)return Color.DARK_GRAY ;


        boolean isRowEven  = (row % 2 == 0);
        boolean isRowOdd   = !isRowEven;
        boolean isColEven  = (col % 2 == 0);
        boolean isColOdd   = !isColEven;

        if(isRowEven && isColEven   ) return Color.BLACK;
        if(isRowEven && isColOdd    ) return Color.gray;
        if(isRowOdd  && isColEven   ) return Color.gray;

        return Color.BLACK;
    }


    private void renderGameTile(Graphics g, int row, int col) {

        Color tileColor = this.getTileColor(row, col);
        GameTile tile = new GameTile(row, col, tileColor);
        tile.render(g);
    }

    private Piece getBoardPiece(int row, int col) {
        return this.pieceCollection[row][col];
    }

    private boolean hasBoardPiece(int row, int col) {
        return this.getBoardPiece(row, col) != null;
    }

    private void renderGamePiece(Graphics g, int row, int col) {

        if(this.hasBoardPiece(row, col)) {

            Piece p = this.getBoardPiece(row, col);
            p.render(g);
        }
    }

    private int getBoardDimentionBasedOnCoordinates(int coordinates) {
        return coordinates / GameTile.TILE_SIZE;
    }
  /*  public void paint (Graphics g){

        super.paint(g);

        for (int row = 0; row < 9; row++) {

            for (int col = 0; col < 7 ; col++) {

                game.GameTile tile = new game.GameTile(row,col);
                tile.render(g);
            }

        }

    }*/




}
