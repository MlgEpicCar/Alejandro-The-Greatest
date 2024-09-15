// MlgEpicCar
// Alejandro the Greatest

// Player.java
// 2023/11/30

package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(8, 16, 32, 32);

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {
        
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
        // isStopped = "yeah";
    }
    public void getPlayerImage() {

        try {

            down0 = ImageIO.read(getClass().getResourceAsStream("/player/Alejandro-1.png.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/Alejandro-2.png.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/Alejandro-3.png.png"));
            up0 = ImageIO.read(getClass().getResourceAsStream("/player/Alejandro-4.png.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/Alejandro-5.png.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/Alejandro-6.png.png"));
            right0 = ImageIO.read(getClass().getResourceAsStream("/player/Alejandro-9.png.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/Alejandro-7.png.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/Alejandro-8.png.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/player/Alejandro-10.png.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/player/Alejandro-11.png.png"));
            left0 = ImageIO.read(getClass().getResourceAsStream("/player/Alejandro-14.png.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/Alejandro-12.png.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/Alejandro-13.png.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/player/Alejandro-15.png.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/player/Alejandro-16.png.png"));

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
            // isStopped = "nah";
            if(keyH.upPressed == true) {
                direction = "up";
            }
            else if(keyH.downPressed == true) {
                direction = "down";
            }
            else if(keyH.leftPressed == true) {
                direction = "left";
            }
            else if(keyH.rightPressed == true) {
                direction = "right";
            }

            // CHECK TILE COLLISION
            collissionOn = false;
            gp.cChecker.checkTile(this);

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if(collissionOn == false) {

                switch(direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
                }
            }

            spriteCounter++;
            if(spriteCounter > 12) {
                if(spriteNum == 0) {
                    spriteNum = 1;
                }
                else if(spriteNum == 1) {
                    spriteNum = 2;
                }
                else if(spriteNum == 2) {
                    spriteNum = 3;
                }
                else if(spriteNum == 3) {
                    spriteNum = 4;
                }
                else if(spriteNum == 4) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        // if(keyH.upPressed != true || keyH.downPressed != true || keyH.leftPressed != true || keyH.rightPressed != true) {
        //     isStopped = "yeah";
        // }

    }
    public void draw(Graphics2D g2) {
        
        BufferedImage image = null;
        
        // ANIMATIONS
        switch(direction) {
        case "up":
            if(spriteNum == 0) {
                image = up0;
            }
            if(spriteNum == 1) {
                image = up1;
            }
            if(spriteNum == 2) {
                image = up2;
            }
            if(spriteNum == 3) {
                image = up1;
            }
            if(spriteNum == 4) {
                image = up2;
            }
            break;
        case "down":
            if(spriteNum == 0) {
                image = down0;
            }
            if(spriteNum == 1) {
                image = down1;
            }
            if(spriteNum == 2) {
                image = down2;
            }
            if(spriteNum == 3) {
                image = down1;
            }
            if(spriteNum == 4) {
                image = down2;
            }
            break;
        case "left":
            if(spriteNum == 0) {
                image = left0;
            }
            if(spriteNum == 1) {
                image = left1;
            }
            if(spriteNum == 2) {
                image = left2;
            }
            if(spriteNum == 3) {
                image = left3;
            }
            if(spriteNum == 4) {
                image = left4;
            }
            break;
        case "right":
            if(spriteNum == 0) {
                image = right0;
            }
            if(spriteNum == 1) {
                image = right1;
            }
            if(spriteNum == 2) {
                image = right2;
            }
            if(spriteNum == 3) {
                image = right3;
            }
            if(spriteNum == 4) {
                image = right4;
            }
            break;
        }

        // IDLE POSES
        // switch(isStopped) {
        // case "yeah":
        //     if(direction == "up"){
        //         image = up0;
        //     }
        //     else if(direction == "down"){
        //         image = down0;
        //     }
        //     else if(direction == "left"){
        //         image = left0;
        //     }
        //     else if(direction == "right"){
        //         image = right0;
        //     }
        // case "nah":
        //     break;
        // }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
