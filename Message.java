import greenfoot.*;  // imports Actor, World, Greenfoot, GreenfootImage

public class Message extends Actor {
    public Message(String text) {
        setImage(new GreenfootImage(text, 24, Color.WHITE, new Color(0, 0, 0, 128)));
    }
    
    // Optionally, remove the message after a few seconds
    public void act() {
        GreenfootImage img = getImage();
        img.setTransparency(img.getTransparency() - 1); // Fade out effect
        if (img.getTransparency() <= 0) {
            getWorld().removeObject(this);
        }
    }
}
