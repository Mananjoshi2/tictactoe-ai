# tictactoe-ai

A configurable Tic-Tac-Toe game in Java with a computer opponent. Board size, win length, and AI search depth are all adjustable — so it plays anything from a classic 3×3 to a 10×10 board where you need 6 in a row.

The AI uses a game-tree search and caches evaluated board positions in a hash table to avoid re-evaluating the same configuration twice.

## Run

```bash
javac *.java

# java Play <board_size> <win_length> <search_depth>
java Play 3 3 5    # classic 3×3, win with 3, search 5 levels deep
java Play 8 5 4    # 8×8 board, win with 5 in a row
```

A Swing window opens. Click a square to place your move (X). The computer (O) responds immediately.

## How the AI works

Each board state is serialized to a string and stored in a hash table (`HashDictionary`) with its evaluation score. Before expanding a node in the game tree, the AI checks the table — if the position has been seen before, it returns the cached score instead of re-evaluating. This is essentially memoization over the game tree.

Board evaluation:
- `3` — computer wins
- `2` — draw  
- `1` — game still in progress
- `0` — human wins

## Implementation

| File | Role |
|---|---|
| `Play.java` | Swing UI, game loop |
| `Configurations.java` | Board state, win detection, evaluation |
| `HashDictionary.java` | Hash table with separate chaining |
| `PosPlay.java` | AI move selection |
| `Data.java` | Key-value record stored in the hash table |

## Tech

Java · Swing · hash table (separate chaining) · game tree search
