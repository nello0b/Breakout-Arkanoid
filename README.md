# Breakout Arkanoid

A Java implementation of a classic Breakout/Arkanoid-style arcade game. The project uses the biuoop framework to render the game window and handle input while providing multiple handcrafted levels, scoring, and pause/game-over screens.

<img width="797" height="627" alt="image" src="https://github.com/user-attachments/assets/9e74cccd-8a1a-4712-a2a0-8c343aa8446f" />

<img width="797" height="627" alt="image" src="https://github.com/user-attachments/assets/e7ad8e3d-1633-40fb-ba49-70ac0dc4e26e" />

## Features
- Four built-in levels with unique block layouts and backgrounds.
- Paddle movement controlled by the keyboard with configurable speed and width.
- Multiple balls, collision detection, scoring, and life tracking.
- Pause screen, countdown animations, and game-over handling.

## Controls
- **Left / Right arrows**: Move the paddle horizontally.
- **P**: Pause the game.

## Building and Running
Requirements:
- Java (tested with Java 8+)
- [Apache Ant](https://ant.apache.org/)
- `biuoop-1.4.jar` available in the project root (already included in this repository)

Commands (run from the project root):

```bash
ant compile   # Compiles sources into the bin/ directory
ant run       # Compiles (if needed) and launches the game
```

### Choosing Levels
By default, all four levels run in order. To play specific levels or change the order, pass level numbers (1-4) as command-line arguments after compiling:

```bash
ant compile
java -cp "bin:biuoop-1.4.jar" Ass6Game 2 4 1
```

If no valid level numbers are provided, the default order is used.

## Project Structure
- `src/` – Game source code (animations, sprites, levels, and utilities)
- `build.xml` – Ant build script with targets for compiling and running the game
- `biuoop-1.4.jar` – Rendering and input dependency packaged with the project
