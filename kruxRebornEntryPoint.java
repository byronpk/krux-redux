import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

public class kruxRebornEntryPoint implements Runnable {
// Main control method of the Thread
   public void run() {
      
   }
   
// TEST
   public void runBasicTest() {
   // TEST CODE
      JFrame f = new JFrame("HELLO WORLD!");
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      try {
         BufferedImage test = ImageIO.read(new File("test_asset.png"));
         oberonAsset testAsset = new oberonAsset(new ImageIcon(test));
         oberonLayer testLayer = new oberonLayer(128, 128);
         oberonAsset testAsset2 = new oberonAsset(new ImageIcon(test));
         
      // Add assets to the test layer
         testLayer.setGridSize(32);
         testLayer.addAssetAt(3, 2, testAsset, true);
         testLayer.addAssetAt(2, 2, testAsset2);
         
      // Add keyboard controls
         oberonKeyboardController kc = new oberonKeyboardController(testLayer);
         f.addKeyListener(kc);
         
      // Set the background to transparent
         testAsset.recolorImage(0xFF0000FF, 0x00000000);
         testAsset2.recolorImage(0xFF0000FF, 0x00000000);
         
      // Recolour the sprite green
         testAsset.recolorImage(0xFFFF00FF, 0xFF00FF00);
         testAsset.recolorImage(0xFF800080, 0xFF008000);
         
         oberonPlayfield mainPlayfield = new oberonPlayfield(32, 32);
         mainPlayfield.setSpriteLayer(testLayer);
         
         oberonDisplayController mainDisplay = new oberonDisplayController(mainPlayfield);
         
      
         f.setSize(640, 480);
         f.setContentPane(mainDisplay);
         f.show();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

// Main application entry point
   public static void main(String args[]) {
      kruxRebornEntryPoint krux = new kruxRebornEntryPoint();
      krux.runBasicTest();
   }
}

class oberonDisplayController extends JPanel {
// Playing field
   protected oberonPlayfield field = null;

// You can use this constructor to provide the object layers when creating the MainWindow
   public oberonDisplayController(oberonPlayfield pfl) {
      field = pfl;
   }

   public void paintComponent(Graphics gf) {
      Graphics2D g = (Graphics2D)gf;
   
   // Draw layers from BG#2 to OVERLAY, in order, to overlap correctly   
   // BG#2
      if(this.field.getBg2Layer() != null) {
         this.field.getBg2Layer().paintLayer(g);
      }
   
   // BG#1
      if(this.field.getBg1Layer() != null) {
         this.field.getBg1Layer().paintLayer(g);
      }
   
   // SPRITE
      if(this.field.getSpriteLayer() != null) {
         this.field.getSpriteLayer().paintLayer(g);
      }
   
   // FG#1
      if(this.field.getFgLayer() != null) {
         this.field.getFgLayer().paintLayer(g);
      }
   
   // OVERLAY
   }
}

class oberonAIController {

}

class oberonSprite extends oberonAsset {
   public oberonSprite(ImageIcon asset) {
      super(asset);
   }
}

class oberonOverlay extends oberonLayer {
   public oberonOverlay (int w, int h) {
      super(w, h);
   }
}

class oberonPlayfield {
   protected int width = 0;
   protected int height = 0;
   protected oberonLayer bg2;
   protected oberonLayer bg1;
   protected oberonLayer sprite;
   protected oberonLayer fg;
   
   public oberonPlayfield(int w, int h) {
      width = w;
      height = h;
   }
   
   public oberonPlayfield(oberonLayer obl[], int w, int h) {
      width = w;
      height = h;
   }
   
   public void setBg2Layer(oberonLayer o) {
      bg2 = o;
   }
   
   public void setBg1Layer(oberonLayer o) {
      bg1 = o;
   }
   
   public void setSpriteLayer(oberonLayer o) {
      sprite = o;
   }
   
   public void setFgLayer(oberonLayer o) {
      fg = o;
   }
   
   public oberonLayer getBg2Layer() {
      return bg2;
   }
   
   public oberonLayer getBg1Layer() {
      return bg1;
   }
   
   public oberonLayer getSpriteLayer() {
      return sprite;
   }
   
   public oberonLayer getFgLayer() {
      return fg;
   }
}