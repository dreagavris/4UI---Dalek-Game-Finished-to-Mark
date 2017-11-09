
import java.awt.Color;

/**
 * This class manages the interactions between the different pieces of the game:
 * the Board, the Daleks, and the Doctor. It determines when the game is over
 * and whether the Doctor won or lost.
 */
public class CatchGame {

    private Board board = new Board(12, 12);
    private Dalek dalekOne = new Dalek((int) (Math.random() * 12), (int) (Math.random() * 12));
    private Dalek dalekTwo = new Dalek((int) (Math.random() * 12), (int) (Math.random() * 12));
    private Dalek dalekThree = new Dalek((int) (Math.random() * 12), (int) (Math.random() * 12));
    private Doctor doc = new Doctor((int) (Math.random() * 12), (int) (Math.random() * 12));

    /**
     * The constructor for the game. Use it to initialize your game variables.
     * (create people, set positions, etc.)
     */
    public CatchGame() {
        board.putPeg(Color.green, doc.getRow(), doc.getCol());
        board.putPeg(Color.black, dalekOne.getRow(), dalekOne.getCol());
        board.putPeg(Color.black, dalekTwo.getRow(), dalekTwo.getCol());
        board.putPeg(Color.black, dalekThree.getRow(), dalekThree.getCol());
    }

    /**
     * The playGame method begins and controls a game: deals with when the user
     * selects a square, when the Daleks move, when the game is won/lost.
     */
    public void playGame() {
        boolean endGame = false;
        while ((dalekOne.getCol() == doc.getCol() && dalekOne.getRow() == doc.getRow()) || (dalekTwo.getCol() == doc.getCol() && dalekTwo.getRow() == doc.getRow()) || (dalekThree.getCol() == doc.getCol() && dalekThree.getRow() == doc.getRow())) {
            board.putPeg(Color.yellow, doc.getRow(), doc.getCol());
            endGame = true;
            break;
        }
        while ((dalekOne.getCol() == dalekTwo.getCol()) && dalekOne.getRow() == dalekTwo.getRow()) {
            dalekOne.crash();
            dalekTwo.crash();
        }
        while ((dalekOne.getRow() == dalekTwo.getRow() && dalekOne.getCol() == dalekTwo.getCol()) && (dalekTwo.getRow() == dalekThree.getRow() && dalekTwo.getCol() == dalekThree.getCol())) {
            dalekOne.crash();
            dalekTwo.crash();
            dalekThree.crash();
            board.removePeg(dalekOne.getCol(), dalekOne.getRow());
            board.removePeg(dalekTwo.getCol(), dalekTwo.getRow());
            board.putPeg(Color.red, dalekOne.getRow(), dalekOne.getCol());
            endGame = true;
            break;
        }
        while (((dalekOne.getCol() == doc.getCol() && dalekOne.getRow() == doc.getRow()) || (dalekTwo.getCol() == doc.getCol() && dalekTwo.getRow() == doc.getRow()) || (dalekThree.getCol() == doc.getCol() && dalekThree.getRow() == doc.getRow())) && ((dalekOne.getRow() == dalekTwo.getRow() && dalekOne.getCol() == dalekTwo.getCol()) && (dalekTwo.getRow() == dalekThree.getRow() && dalekTwo.getCol() == dalekThree.getCol()))) {
            dalekOne.crash();
            dalekTwo.crash();
            dalekThree.crash();
            board.putPeg(Color.yellow, doc.getRow(), doc.getCol());
            endGame = true;
            break;
        }

        while (true && !endGame) {
            Coordinate click = board.getClick();
            board.removePeg(doc.getRow(), doc.getCol());
            doc.move(click.getRow(), click.getCol());
            board.putPeg(Color.green, doc.getRow(), doc.getCol());
            if (!dalekOne.hasCrashed()) {
                board.removePeg(dalekOne.getRow(), dalekOne.getCol());
                dalekOne.advanceTowards(doc);
                board.putPeg(Color.black, dalekOne.getRow(), dalekOne.getCol());
            }
            if (!dalekTwo.hasCrashed()) {
                board.removePeg(dalekTwo.getRow(), dalekTwo.getCol());
                dalekTwo.advanceTowards(doc);
                board.putPeg(Color.black, dalekTwo.getRow(), dalekTwo.getCol());
            }
            if (!dalekThree.hasCrashed()) {
                board.removePeg(dalekThree.getRow(), dalekThree.getCol());
                dalekThree.advanceTowards(doc);
                board.putPeg(Color.black, dalekThree.getRow(), dalekThree.getCol());
            }
            if ((dalekOne.getCol() == dalekTwo.getCol()) && (dalekOne.getRow() == dalekTwo.getRow())) {
                dalekOne.crash();
                dalekTwo.crash();
                board.putPeg(Color.red, dalekOne.getRow(), dalekOne.getCol());
            }
            if ((dalekTwo.getRow() == dalekThree.getRow()) && (dalekTwo.getCol() == dalekThree.getCol())) {
                dalekTwo.crash();
                dalekThree.crash();
                board.putPeg(Color.red, dalekTwo.getRow(), dalekTwo.getCol());
            }
            if ((dalekOne.getCol() == doc.getCol() && dalekOne.getRow() == doc.getRow())
                    || (dalekTwo.getCol() == doc.getCol() && dalekTwo.getRow() == doc.getRow())
                    || (dalekThree.getCol() == doc.getCol() && dalekThree.getRow() == doc.getRow())) {
                board.putPeg(Color.yellow, doc.getRow(), doc.getCol());
                board.displayMessage("Doctor has lost try, try again");
                break;
            }
            if (dalekOne.hasCrashed() && dalekTwo.hasCrashed() && dalekThree.hasCrashed()) {
                board.displayMessage("Doctor has won!");
                break;
            }
        }
    }
}

