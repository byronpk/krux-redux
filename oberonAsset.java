import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class oberonAsset {
// Constants
   public static final byte NO_ACTION = 0x00;
   public static final byte GRANT_HP = 0x01;
   public static final byte GRANT_ARMOR = 0x02;
   public static final byte GRANT_EXPERIENCE = 0x03;
   public static final byte TRIGGER_WARP = 0x04;
   public static final byte DAMAGE_HP = 0x05;
   public static final byte DAMAGE_ARMOR = 0x06;
   public static final byte DAMAGE_ALL = 0x07;
   public static final byte DAMAGE_EXP = 0x08;
   public static final byte GRANT_HOT = 0x09;
   public static final byte GRANT_EOT = 0x0A;
   public static final byte SET_FLAG_POISON = 0x0B;
   public static final byte SET_FLAG_STUN = 0x0C;
   public static final byte SET_FLAG_BURN = 0x0D;
   public static final byte SET_FLAG_INVISIBLITY = 0x0E;
   public static final byte SET_FLAG_NO_CLIP = 0x0F;
   public static final byte GRANT_LIFE = 0x10;
   public static final byte INSTANT_KILL = 0x11;
   public static final byte SPAWN_OBJECT = 0x12;
   public static final byte SET_OBJECT_FLAG = 0x13;
   public static final byte USE_OBJECT_HANDLER = 0x14;
   
   public static final byte DO_NOTHING = 0x00;
   public static final byte COOLDOWN_AFTER_COLLIDE = 0x01;
   public static final byte DESTROY_AFTER_COLLIDE = 0x02;
   public static final byte TELEPORT_AFTER_COLLIDE = 0x03;
   public static final byte USE_COLLISION_HANDLER = 0x04;

// Collision Control parameters
// Example usage of parameters
/*
   09 32 3C 00 02 00 = Grants Health over Time, maximum of 50 HP over 60 seconds and destroy after pickup
   0B 05 00 00 01 3C = Sets the POISON flag on the victim doing 5 damage per second, item has a 60 second cooldown
   14 00 00 00 00 04 = Used for sprites, as their operations are handled internally
*/
   public byte onCollideFlag           = 0x00;
   public byte onCollideParameter1     = 0x00;
   public byte onCollideParameter2     = 0x00;
   public byte onCollideParameter3     = 0x00;
   public byte afterCollideFlag        = 0x00;
   public byte afterCollideParameter1  = 0x00;

// Assets
   protected ImageIcon assetIconFrames[] = null;
   protected boolean animatedAsset = false;
   protected int displayedFrame = 0;
   private boolean isSprite = false;

// Constructor
   public oberonAsset(ImageIcon asset) {
   // Initialize the animation frames
      assetIconFrames = new ImageIcon[16];
      assetIconFrames[0] = asset;
   }
   
// Collision
   public boolean handleCollision() {
      return true;
   }
   
// Recolour an image
   public void recolorImage(int source, int target) {
      if(!animatedAsset) {
         this.assetIconFrames[0] = this.recolorImageWorker(source, target, this.assetIconFrames[0]);
      } else {
         for(int i = 0; i < assetIconFrames.length; i++) {
            if(assetIconFrames[i] != null) {
               this.assetIconFrames[i] = this.recolorImageWorker(source, target, this.assetIconFrames[i]);
            }
         }
      }
   }
   
// Recolour an image
   protected ImageIcon recolorImageWorker(int source, int target, ImageIcon sourceImg) {
   // Variables
      int width = sourceImg.getIconWidth();
      int height = sourceImg.getIconHeight();
          
   // First convert ImageIcon to BufferedImage
      BufferedImage imageSource = new BufferedImage(
                                       width,
                                       height,
                                       BufferedImage.TYPE_INT_ARGB
                                    );
      Graphics2D g = (Graphics2D) imageSource.createGraphics();
      sourceImg.paintIcon(null, g, 0, 0);
      g.dispose();
      
   // Now recolour the image
      BufferedImage tempImage = new BufferedImage(
                                       width,
                                       height,
                                       imageSource.getType()
                                    );
      int color;
      
      for (int i = 0; i < width; i++) {
         for (int j = 0; j < width; j++) {
            color = imageSource.getRGB(i, j);
         // System.out.println(String.format("0x%08x", color));
            if (color == source) {
               tempImage.setRGB(i, j, target);
            }
            else {
               tempImage.setRGB(i, j, color);
            }
         }
      }
      
   // Store the recoloured image back to the ImageIcon
      return new ImageIcon(tempImage);
   }
   
   public void setAssetImage(int index, ImageIcon asset) {
      this.assetIconFrames[index] = asset;
   }
   
   public ImageIcon getAssetImage(int index) {
      return this.assetIconFrames[index];
   }
   
   public void setCurrentFrame(int frame) {
      if(displayedFrame > 0 && displayedFrame < 16) {
         displayedFrame = frame;
      } else {
         displayedFrame = 0;
      }
   }
   
   public int getCurrentFrame() {
      return displayedFrame;
   }
   
// Set the image transparency colour
   public void setTransparentColour(int source) {
   // Transparency colour HEX 0x00000000 i.e RGBA(0,0,0,0)
      this.recolorImage(source, 0x00000000);
   }
   
// Called by the layer to draw the asset
   public void paintAsset(Graphics2D g, int x, int y) {
      this.assetIconFrames[displayedFrame].paintIcon(null, g, x, y);
   }
}