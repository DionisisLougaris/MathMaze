import greenfoot.*;  // imports Actor, World, Greenfoot, GreenfootImage
import java.util.Random;  // To generate positions for walls and math questions

public class MathMaze extends World {
    private static final int CELL_SIZE = 50;
    private static final int PLAYER_START_X = 100;
    private static final int PLAYER_START_Y = 100;
    private static final int QUESTION_SAFE_ZONE = 25; // smaller safe zone for question

    private int questionX, questionY; // Coordinates for the math question
    private int correctAnswers = 0;
    private int level =1;
    private int lives;

    public MathMaze() {
        super(600, 400, 1);
        setLevelBackground();
        setPaintOrder(Player.class, Wall.class, MathQuestion.class);
        lives = 3; // Start with 3 lives
        prepare();
        showLevel(); // Initial display of level
        showLives();
        Greenfoot.playSound("welcome.mp3");
    }

    private void setLevelBackground() {
        String backgroundFile = level == 1 ? "River.png" : level == 2 ? "Forest.png" : "Mountain.png";
        GreenfootImage bg = new GreenfootImage(backgroundFile);
        bg.scale(getWidth(), getHeight());
        setBackground(bg);
    }

    private void prepare() {
        Player player = new Player();
        addObject(player, PLAYER_START_X, PLAYER_START_Y);

        spawnNewQuestion();
        createStructuredWalls();
        removeWallsNearImportantObjects();
    }

    private void spawnNewQuestion() {
        Random rand = new Random();
        do {
            questionX = rand.nextInt(getWidth() - CELL_SIZE) + CELL_SIZE;
            questionY = rand.nextInt(getHeight() - CELL_SIZE) + CELL_SIZE;
        } while (Math.abs(questionX - PLAYER_START_X) <= CELL_SIZE && Math.abs(questionY - PLAYER_START_Y) <= CELL_SIZE); // Ensure the question is not too close to the player

        addObject(new MathQuestion(), questionX, questionY);
    }

    public void handleCorrectAnswer() {
    correctAnswers++;
    if (correctAnswers >= 3) {
        correctAnswers = 0;
        level++;
        updateLives(1); // Gain 1 life when moving up a level, capped at 3
        if (level > 3) {
            showWinMessage();
            Greenfoot.stop();
        } else {
            setLevelBackground();
            showLevel(); // Update the level display after level change
        }
    }
    spawnNewQuestion();
}

public void handleWrongAnswer() {
    lives--;
    showLives();  // Update the lives display
    if (lives <= 0) {
        showGameOver();  // Show the game over screen and stop the game
    } else {
        spawnNewQuestion();  // Spawn a new question if there are still lives left
    }
}



    private void createStructuredWalls() {
        int numStructures = 18; // Number of structures to create
        Random rand = new Random();

        for (int n = 0; n < numStructures; n++) {
            int x = rand.nextInt((getWidth() - CELL_SIZE) / CELL_SIZE) * CELL_SIZE;
            int y = rand.nextInt((getHeight() - CELL_SIZE) / CELL_SIZE) * CELL_SIZE;

            boolean horizontal = rand.nextBoolean();
            int length = rand.nextInt(3) + 2;

            for (int i = 0; i < length; i++) {
                int dx = horizontal ? CELL_SIZE / 2 * i : 0;
                int dy = horizontal ? 0 : CELL_SIZE / 2 * i;

                Wall wall = new Wall();
                addObject(wall, x + dx, y + dy);
            }
        }
    }

    private void removeWallsNearImportantObjects() {
        removeWallsNear(PLAYER_START_X, PLAYER_START_Y, CELL_SIZE);
        removeWallsNear(questionX, questionY, QUESTION_SAFE_ZONE);
    }

    private void removeWallsNear(int x, int y, int safeZone) {
        int safeZoneStartX = x - safeZone;
        int safeZoneEndX = x + safeZone;
        int safeZoneStartY = y - safeZone;
        int safeZoneEndY = y + safeZone;

        for (Object obj : getObjects(Wall.class)) {
            Wall wall = (Wall) obj;
            if (wall.getX() >= safeZoneStartX && wall.getX() <= safeZoneEndX &&
                wall.getY() >= safeZoneStartY && wall.getY() <= safeZoneEndY) {
                removeObject(wall);
            }
        }
    }

    private void showWinMessage() {
        removeObjects(getObjects(Wall.class));
        removeObjects(getObjects(MathQuestion.class));

        GreenfootImage winImage = new GreenfootImage("GameWon.png");
        winImage.scale(getWidth(), getHeight());
        setBackground(winImage);
        Greenfoot.playSound("victory.mp3");

        Greenfoot.stop();
    }
    private void showLevel() {
    String levelText = "Level " + level; // Create a string that says "Level X" where X is the current level
    showText(levelText, getWidth() / 2, 20); // Display this text at the middle top of the screen
    }
    private void updateLives(int change) {
    lives += change;
    if (lives > 3) {
        lives = 3; // Ensure lives do not exceed 3
    } else if (lives < 0) {
        lives = 0; // Ensure lives do not go below 0
    }
    showLives();
    if (lives == 0) {
        showGameOver(); // End game if no lives left
    }
    }

    private void showLives() {
    String livesText = "Lives " + lives; 
    showText(livesText, 50, 20);
    }
   private void showGameOver() {
    removeObjects(getObjects(Wall.class));
    removeObjects(getObjects(MathQuestion.class));
    GreenfootImage gameOverImage = new GreenfootImage("GameOver.png");
    gameOverImage.scale(getWidth(), getHeight());
    setBackground(gameOverImage);
    Greenfoot.playSound("defeat.mp3");
    Greenfoot.stop();
}



}
