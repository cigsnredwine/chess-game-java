package piece;

import main.GamePanel;
import main.Type;

public class Queen extends Piece {
    public Queen(int color, int col, int row) {
        super(color, col, row);
        type = Type.QUEEN;
        
        if(color == GamePanel.WHITE) {
            image = getImage("/piece/queen.png");
        } else {
            image = getImage("/piece/queen1.png");
        }
    }

    public boolean canMove(int targetCol, int targetRow) {

        if (isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
            //Queen can move any number of squares along a rank, file, or diagonal
            if (targetCol == preCol || targetRow == preRow) {
                if(isValidSquare(targetCol, targetRow) && pieceIsOnStraightLine(targetCol, targetRow) == false) {
                    return true;
                }
            } else if (Math.abs(targetCol - preCol) == Math.abs(targetRow - preRow)) {
                if(isValidSquare(targetCol, targetRow) && !pieceIsOnDiagonalLine(targetCol, targetRow)) {
                    return true;
                }
            }
        }
        return false;
    }
}
