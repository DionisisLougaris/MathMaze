import greenfoot.*;  // imports Actor, World, Greenfoot, GreenfootImage

public class Wall extends Actor {
    public Wall() {
        GreenfootImage image = new GreenfootImage("Wall.png"); // Load the image
        image.scale(50, 50); // Resize the image to 50x50 pixels
        setImage(image); // Set the modified image as the actor's image
    }
}
