
/** This class models a Delek in the game. A Delek has
 *  a position and can advance towards the Doctor.
 */
public class Dalek {

    private int row, col;
    private boolean hasCrashed;

    /**
     * Initializes the variables for a Dalek.
     *
     * @param theRow The row this Dalek starts at.
     * @param theCol The column this Dalek starts at.
     */
    public Dalek(int theRow, int theCol) {
    this.col = theCol;
    this.row = theRow;
    }
    /**
     * @param doc The Doctor to move towards.
     */
      public void advanceTowards(Doctor doc) {
        if (doc.getCol() == this.col && doc.getRow() < this.row) {
            this.row = this.row - 1;         
        }else if (doc.getCol() == this.col && doc.getRow() > this.row) {
            this.row = this.row + 1;
        }else if (doc.getRow() == this.row && doc.getCol() > this.col) {
            this.col = this.col + 1;
        }
        else if (doc.getRow() == this.row && doc.getCol() < this.col) {
            this.col = this.col - 1;
        }
        else if (doc.getRow() < this.row && doc.getCol() > this.col) {
            this.col = this.col + 1;
            this.row = this.row - 1;
        }
        else if (doc.getRow() < this.row && doc.getCol() < this.col) {
            this.col = this.col - 1;
            this.row = this.row - 1;
        }
        else if (doc.getRow() > this.row && doc.getCol() > this.col) {
            this.col = this.col + 1;
            this.row = this.row + 1;
        }
        else if (doc.getRow() > this.row && doc.getCol() < this.col) {
            this.col = this.col - 1;
            this.row = this.row + 1;
        }
    }
    /**
     * Returns the row of this Dalek.
     *
     * @return This Dalek's row.
     */
    public int getRow() {
        // return the row of the dalek
        return this.row;
    }
    /**
     * Returns the column of this Dalek.
     *
     * @return This Dalek's column.
     */
    public int getCol() {
        // return the column of the dalek
        return this.col;

    }
    /**
     * Sets the Dalek to be in a crashed state.
     */
    public void crash() {
        this.hasCrashed = true;
    }
    /**
     * Returns whether or not this Dalek has crashed.
     *
     * @return true if this Dalek has crashed, false otherwise
     */
    public boolean hasCrashed() {
        if (this.hasCrashed) {
            return true;
        } else {
            return false;
        }

    }
}