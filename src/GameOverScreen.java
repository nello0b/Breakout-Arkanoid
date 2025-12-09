// 318844677 Netanel Berkovits


import biuoop.DrawSurface;

/**
 * Name: PauseScreen Class
 * Description: A PauseScreen object, this is the game it self!!!!.
 */
public class GameOverScreen implements Animation {
    private final boolean win;

    private final Counter score;

    /**
     * Name: GameOverScreen
     * Description: A constructor for GameOverScreen.
     *
     * @param win   did you won or not?
     * @param score the score of the game
     */
    public GameOverScreen(boolean win, Counter score) {
        this.score = score;
        this.win = win;
    }

    /**
     * Name: doOneFrame
     * Description: Do all the logic that need to happened in 1 frame.
     *
     * @param d the draw surface we want to draw on.
     */
    public void doOneFrame(DrawSurface d) {
        String str = "Game Over";
        if (win) {
            str = "You Won";
        }
        str = str + "! Your score is " + score.getValue();
        int fontSize = Configuration.BOARDER_SIZE * 2;
        d.drawText(Configuration.BOARDER_SIZE * 4, Configuration.SCREEN_HIGHT / 2, str, fontSize);
        str = "(press space to exit)";
        d.drawText(Configuration.BOARDER_SIZE * 4, Configuration.SCREEN_HIGHT / 2 + fontSize, str, fontSize / 2);
    }

    /**
     * Name: shouldStop
     * Description: check if the game should stop.
     *
     * @return if the game should stop.
     */
    public boolean shouldStop() {
        return false;
    }
}