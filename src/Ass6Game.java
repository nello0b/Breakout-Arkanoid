// 318844677 Netanel Berkovits

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Name: Ass3Game Class
 * Description: The main, run the game.
 */
public class Ass6Game {

    /**
     * Name: main
     * Description: Run the game.
     *
     * @param args the order of levels we want to play.
     */
    public static void main(String[] args) {
        LevelInformation[] defaultLevelOrder = {new LevelOne(), new LevelTwo(), new LevelThree(), new LevelFour()};
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        if (args.length == 0) {
            Collections.addAll(levels, defaultLevelOrder);
        } else {
            for (String arg : args) {
                int levelIndex = -1;
                try {
                    levelIndex = Integer.parseInt(arg);
                } catch (NumberFormatException ignored) {
                }
                if (1 <= levelIndex && levelIndex <= defaultLevelOrder.length) {
                    levels.add(defaultLevelOrder[levelIndex - 1]);
                }
            }
        }
        GameFlow game = new GameFlow(Configuration.GAME_NAME, Configuration.SCREEN_WIDTH, Configuration.SCREEN_HIGHT,
                Configuration.FRAMES_PER_SECOND);
        game.runLevels(levels);
    }
}
