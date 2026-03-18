package piece;

import main.GamePanel;
import main.Type;

public class Rook extends Piece {
    public Rook(int color, int col, int row) {
        super(color, col, row);
        type = Type.ROOK;
        
        if(color == GamePanel.WHITE) {
            image = getImage("/piece/rook.png");
        } else {
            image = getImage("/piece/rook1.png");
        }
    }


    public boolean canMove(int targetCol, int targetRow) {

        if (isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
            //Rook can move any number of squares along a rank or file
            if (targetCol == preCol || targetRow == preRow) {
                if(isValidSquare(targetCol, targetRow) && pieceIsOnStraightLine(targetCol, targetRow) == false) {
                    return true;
                }
            }
        }
        return false;
    }
}