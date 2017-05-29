import java.awt.*;
import javax.swing.*;
import java.io.*;

public class kruxRebornEntryPoint implements Runnable {
     public void run() {
     
     }
     
     public static void main(String args[]) {
     
     }
}

class oberonKeyboardController {

}

class oberonDisplayController extends JPanel {
// You can use this constructor to provide the object layers when creating the MainWindow
   public oberonDisplayController(oberonLayer rbo[]) {
      
   }

   public void paintComponent(Graphics g) {
   // Draw layers from BG#2 to OVERLAY, in order, to overlap correctly   
   // BG#2
   
   // BG#1
   
   // SPRITE
   
   // FG#1
   
   // OVERLAY
   }
}

class oberonAIController {

}

class oberonAsset {
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
   protected ImageIcon assetIcon = null;
   private boolean isSprite = false;

   public oberonAsset(ImageIcon asset) {
   
   }
}

class oberonSprite extends oberonAsset {
   public oberonSprite(ImageIcon asset) {
      super(asset);
   }
}

class oberonLayer {
   
}

class oberonOverlay extends oberonLayer {

}

class oberonPlayfield {

}