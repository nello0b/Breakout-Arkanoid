# Breakout Arkanoid

A Java implementation of a classic Breakout/Arkanoid-style arcade game.

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
- **Q**: Spawn a spread of extra balls (cheat code) to clear blocks quickly.

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

## Architecture overview
- **Animation loop**: `AnimationRunner` advances the game at a fixed frames-per-second rate by repeatedly calling
  the active animation's `doOneFrame` until `shouldStop` returns true.
- **Sprites and collidables**: Moving objects implement `Sprite`, while solid objects implement `Collidable`. The
  `SpriteCollection` and `GameEnvironment` track these objects and handle drawing and collision detection.
- **Levels**: Each level implements `LevelInformation` to describe its blocks, backgrounds, balls, and paddle
  settings. `GameFlow` iterates through the configured levels, while `GameLevel` manages the objects and logic for a
  single stage.
- **Input and game states**: Keyboard input is supplied by `KeyboardSensor` from the biuoop library. Convenience
  animations (pause screen, countdown, and end screens) wrap the core game loop to provide smooth transitions.
