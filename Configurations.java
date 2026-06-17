public class Configurations {
    private char[][] board;
    private int boardSize;
    private int lengthToWin;
    private int maxLevels;

    // Constructor initializes the game board, winning condition and max depth of game tree
    public Configurations(int board_size, int lengthToWin, int max_levels) {
        this.boardSize = board_size;
        this.lengthToWin = lengthToWin;
        this.maxLevels = max_levels;
        this.board = new char[boardSize][boardSize];
        
        // Initialize board (with empty spaces)
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // Create and return an empty HashDictionary
    public HashDictionary createDictionary() {
        return new HashDictionary(9973); // 9973 is a large prime number
    }

    // Check if current board configuration is in hash table
    public int repeatedConfiguration(HashDictionary hashTable) {
        String boardString = boardToString();
        return hashTable.get(boardString);
    }

    // Adds current board configuration to hash table
    public void addConfiguration(HashDictionary hashDictionary, int score) {
        String boardString = boardToString();
        Data data = new Data(boardString, score);
        try {
            hashDictionary.put(data);
        } catch (DictionaryException e) {
            // Configuration already exists, no need to add
        }
    }

    // Saves a play on board
    public void savePlay(int row, int col, char symbol) {
        board[row][col] = symbol;
    }

    // Checks if square is empty
    public boolean squareIsEmpty(int row, int col) {
        return board[row][col] == ' ';
    }

    // Check if given symbol has won
    public boolean wins(char symbol) {
        // Check rows and columns
        for (int i = 0; i < boardSize; i++) {
            if (checkLine(0, i, 1, 0, symbol) || checkLine(i, 0, 0, 1, symbol)) {
                return true;
            }
        }
        
        // Check diagonals
        for (int i = 0; i <= boardSize - lengthToWin; i++) {
            for (int j = 0; j <= boardSize - lengthToWin; j++) {
                if (checkLine(i, j, 1, 1, symbol) || checkLine(i, j + lengthToWin - 1, 1, -1, symbol)) {
                    return true;
                }
            }
        }
        
        return false;
    }

    // Checks if game is a draw
    public boolean isDraw() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return !wins('X') && !wins('O');
    }

    // Evaluates current board state
    public int evalBoard() {
        if (wins('O')) return 3;
        if (wins('X')) return 0;
        if (isDraw()) return 2;
        return 1;
    }

    // Helper method to convert board to string
    private String boardToString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                sb.append(board[i][j]);
            }
        }
        return sb.toString();
    }

    // Helper method to check for winning line
    private boolean checkLine(int startRow, int startCol, int dRow, int dCol, char symbol) {
        int count = 0;
        for (int i = 0; i < lengthToWin; i++) {
            int row = startRow + i * dRow;
            int col = startCol + i * dCol;
            if (row < 0 || row >= boardSize || col < 0 || col >= boardSize) {
                return false;
            }
            if (board[row][col] == symbol) {
                count++;
            } else {
                count = 0;
            }
            if (count == lengthToWin) {
                return true;
            }
        }
        return false;
    }
}
