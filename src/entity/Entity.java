// MlgEpicCar
// Alejandro the Greatest

// Entity.java
// 2023/11/30

package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {

    public int worldX, worldY;
    public int speed;

    public BufferedImage up0, up1, up2, down0, down1, down2, left0, left1, left2, left3, left4, right0, right1, right2, right3, right4; // If there are new images, add them here
    public String direction;
    // public String isStopped;
    
    public int spriteCounter = 0;
    public int spriteNum = 1;

    //Hitbox code fr
    public Rectangle solidArea; 
    public boolean collissionOn = false;
}
