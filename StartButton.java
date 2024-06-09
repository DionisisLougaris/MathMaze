import greenfoot.*;  

public class StartButton extends Actor {
    public StartButton() {
        GreenfootImage image = new GreenfootImage("Start Game", 24, Color.WHITE, new Color(0, 128, 0));
        GreenfootImage buttonBg = new GreenfootImage(image.getWidth() + 20, image.getHeight() + 10);
        buttonBg.setColor(new Color(0, 128, 0));
        buttonBg.fill();
        buttonBg.drawImage(image, 10, 5);
        setImage(buttonBg);
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(new MathMaze());
        }
    }
}
