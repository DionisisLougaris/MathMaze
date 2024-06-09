import greenfoot.*;  

public class LessonScreen extends World {
    public LessonScreen(int x, int y, String lessonText) {
        super(x, y, 1); 
        showLessonText(lessonText);
    }

    private void showLessonText(String lessonText) {
        
        GreenfootImage bgImage = new GreenfootImage("Lesson.png");
        bgImage.scale(getWidth(), getHeight());
        setBackground(bgImage);

        
        int fontSize = 14; 
        GreenfootImage label = new GreenfootImage(lessonText, fontSize, Color.WHITE, new Color(0, 0, 0, 160));
        
        
        getBackground().drawImage(label, 20, 20);
    }
}
