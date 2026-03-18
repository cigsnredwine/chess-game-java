package piece;

import main.GamePanel;
import main.Type;

public class King extends Piece {
    public King(int color, int col, int row) {
        super(color, col, row);
        type = Type.KING;
        
        if(color == GamePanel.WHITE) {
            image = getImage("/piece/king.png");
        } else {
            image = getImage("/piece/king1.png");
        }
    }

    public boolean canMove(int targetCol, int targetRow) {

        if (isWithinBoard(targetCol, targetRow)) {
            if (Math.abs(targetCol - preCol) + Math.abs(targetRow - preRow) == 1 || 
            Math.abs(targetCol - preCol) * Math.abs(targetRow - preRow) == 1) {
                
                if(isValidSquare(targetCol, targetRow)) {
                    return true;
                }
            }

            // Right Castling
            if (!moved && targetCol == preCol + 2 && targetRow == preRow && !pieceIsOnStraightLine(targetCol, targetRow)) {
                for(Piece piece: GamePanel.simPieces) {
                    if (piece instanceof Rook && piece.color == color && piece.col == preCol + 3 && piece.row == preRow && !piece.moved) {
                        // target square must be empty for castling
                        if (getHittingP(targetCol, targetRow) == null) {
                            GamePanel.castlingP = piece;
                            return true;
                        }
                    }
                }
            }
            // Left Castling
            if (!moved && targetCol == preCol - 2 && targetRow == preRow && !pieceIsOnStraightLine(targetCol, targetRow)) {
                for (Piece piece: GamePanel.simPieces) {
                    if (piece instanceof Rook && piece.color == color && piece.col == preCol - 4 && piece.row == preRow && !piece.moved) {
                        // target square must be empty for castling
                        if (getHittingP(targetCol, targetRow) == null) {
                            GamePanel.castlingP = piece;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}