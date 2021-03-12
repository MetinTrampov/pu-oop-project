package game;

import Piace.Dwarf;
import Piace.Elf;
import Piace.Piece;
import Piace.Кnight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Board  extends JFrame implements MouseListener {

    public static  final int TILE_ROW_SIZE = 7;
    public static  final int  TILE_COL_SIZE = 9;



    public Piece[][] pieceCollection;
    private Piece selectedPiece;
    private int team ;
    public int pointPlayer1 = 0 ;
    public int pointPlayer2 = 0;

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

        this.pieceCollection[0][2] = (new Кnight(0,2,Color.GREEN,team));
        this.pieceCollection[1][6] = (new Кnight(1,6,Color.GREEN,team));
        this.pieceCollection[6][2] = (new Кnight(6,2,Color.GREEN,team));
        this.pieceCollection[5][5] = (new Кnight(5,5,Color.GREEN,team));










        this.setSize(1200,700);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       // this.setLocationRelativeTo(null);
        this.setBackground(Color.black);
        this.addMouseListener(this);
        this.setVisible(true);

    }

    /**
     * Mean method which catch click for mouse and move pieces
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        int row = this.getBoardDimentionBasedOnCoordinates(e.getY());
        int col = this.getBoardDimentionBasedOnCoordinates(e.getX());

        // check if piece is already selected
        if (this.selectedPiece != null) {

            // TODO: Update pieceCollection array in order to match the new coordinates
            Piece p = this.selectedPiece;
            p.move(row, col);
            this.selectedPiece = null;

            this.repaint();
            /*if(p.isMoveValid(row, col)) {

              //  movePiece(row, col, p);
                this.repaint();
                return;
            }*/

        if (!p.isMoveValid(row,col)) {
            // new game.Modal(this, "Внимание", "Невалиден ход, по дъската");

            //this.setLocationRelativeTo(null);
            Modal.render(this, "Внимание", "Невалиден ход, по дъската");
            return;
        }
    }
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

        int x =0;
        int y = 0;

        for(int row = 0; row < 7; row++) {
            for(int col = 0; col < 9; col++) {
                addPointsOfPlayer1(900,100,g);
                addPointsOfPlayer2(900,500,g);

                addKnight(920,200,g);
                addDwarf(1000,200 , g);
                addElf(1100,200,g);
                this.renderGameTile(g, row, col);
              this.renderGamePiece(g, row, col);
            }
        }
    }

    /**
     * Method which draw button  for elf
     * @param x first coordinate
     * @param y secoond coordinate
     * @param g draw method from graphics
     */

    private static void addElf(int x, int y, Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, 70, 70);
        g.setColor(Color.black);
        g.drawRect(x, y, 70, 70);
        g.setColor(Color.black);
        g.drawString("E", x + 30, y + 40);

    }

    /**
     * Method which draw button  for knight
     *@param x first coordinate
     * @param y secoond coordinate
     * @param g draw method from graphics
     */

    private static void addKnight(int x, int y, Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, 70, 70);
        g.setColor(Color.black);
        g.drawRect(x, y, 70, 70);
        g.setColor(Color.black);
        g.drawString("K", x + 30, y + 40);
    }
    /**
     * Method which draw button  for dwarf
     *@param x first coordinate
     * @param y secoond coordinate
     * @param g draw method from graphics
     */
    private static void addDwarf(int x, int y, Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(x, y, 70, 70);
        g.setColor(Color.black);
        g.drawRect(x, y, 70, 70);
        g.setColor(Color.black);
        g.drawString("D", x + 30, y + 40);

    }
    private static void addPointsOfPlayer1 (int x , int y , Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 70, 70);
        g.setColor(Color.black);
        g.drawRect(x, y, 70, 70);
        g.setColor(Color.black);

    }

    private static void addPointsOfPlayer2(int x , int y , Graphics g) {

        g.setColor(Color.green);
        g.fillRect(x, y, 70, 70);
        g.setColor(Color.black);
        g.drawRect(x, y, 70, 70);
        g.setColor(Color.black);
    }
    /**
     * method which moves pieces
     * @param row
     * @param col
     * @param p
     */
    private void movePiece(int row, int col, Piece p) {
        // 1. Get the original coordinates of the selected piece
        int initialRow = p.getRow();
        int initialCol = p.getCol();

        // 2. Move the piece to the new coordinates
        p.move(row, col);

        // 3. Swap the reference to the selected piece from the original coordinates
        // TODO: Abstraction of piece collection
        this.pieceCollection[p.getRow()][p.getCol()] = this.selectedPiece;
        this.pieceCollection[initialRow][initialCol] = null;

        // 4. Remove reference to selected piece
        // TODO: Abstraction of selected piece access
        this.selectedPiece = null;
    }

    /**
     * method whcih drawing game board
     * @param row
     * @param col
     * @return
     */
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

    /**
     * method which drawing game tile
     * @param g
     * @param row
     * @param col
     */
    private void renderGameTile(Graphics g, int row, int col) {

        Color tileColor = this.getTileColor(row, col);
        GameTile tile = new GameTile(row, col, tileColor);
        tile.render(g);
    }

    /**
     * method which check for pieces
     * @param row
     * @param col
     * @return
     */
    private Piece getBoardPiece(int row, int col) {
        return this.pieceCollection[row][col];
    }

    private boolean hasBoardPiece(int row, int col) {
        return this.getBoardPiece(row, col) != null;
    }

    /**
     * method which draw pieces
     * @param g
     * @param row
     * @param col
     */
    private void renderGamePiece(Graphics g, int row, int col) {

        if(this.hasBoardPiece(row, col)) {

            Piece p = this.getBoardPiece(row, col);
            p.render(g);
        }
    }

    private int getBoardDimentionBasedOnCoordinates(int coordinates) {
        return coordinates / GameTile.TILE_SIZE;
    }





}
