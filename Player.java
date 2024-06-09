import greenfoot.*;  // imports Actor, World, Greenfoot, GreenfootImage

public class Player extends Actor {
     public Player() {
        GreenfootImage image = new GreenfootImage("Player.png"); 
        image.scale(50, 50); 
        setImage(image); 
    }
     public void act() {
        handleMovement();
        checkForMathQuestion();
    }

    private void handleMovement() {
        if (Greenfoot.isKeyDown("up") && canMove(0, -2)) {
            setLocation(getX(), getY() - 2);
        }
        if (Greenfoot.isKeyDown("down") && canMove(0, 2)) {
            setLocation(getX(), getY() + 2);
        }
        if (Greenfoot.isKeyDown("left") && canMove(-2, 0)) {
            setLocation(getX() - 2, getY());
        }
        if (Greenfoot.isKeyDown("right") && canMove(2, 0)) {
            setLocation(getX() + 2, getY());
        }
    }

    private boolean canMove(int dx, int dy) {
        return getOneObjectAtOffset(dx, dy, Wall.class) == null;
    }

    private void checkForMathQuestion() {
        MathQuestion question = (MathQuestion) getOneIntersectingObject(MathQuestion.class);
        if (question != null) {
            question.askQuestion();
        }
    }
}

