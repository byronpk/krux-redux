import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class oberonKeyboardController implements KeyListener {
   protected oberonLayer spriteLayer = null;
   protected oberonAsset playerSprite = null;

   public oberonKeyboardController(oberonLayer sprites) {
      super();
      
      spriteLayer = sprites;
      System.out.println(spriteLayer.hasPlayerSprite());
      if(spriteLayer.hasPlayerSprite()) {
         playerSprite = spriteLayer.getPlayerSprite();
         System.out.println(playerSprite.toString());
      }
   }

   public void keyTyped(KeyEvent e) {
      System.out.println(e.toString());
   }

   public void keyPressed(KeyEvent e) {
      int keyCode = e.getKeyCode();
      if(keyCode == KeyEvent.VK_UP) {
         spriteLayer.relocateAsset(playerSprite, 3, 1);
      }
      if(keyCode == KeyEvent.VK_DOWN) {
         spriteLayer.relocateAsset(playerSprite, 3, 3);
      }
      if(keyCode == KeyEvent.VK_LEFT) {
         spriteLayer.relocateAsset(playerSprite, 1, 2);
      }
      if(keyCode == KeyEvent.VK_RIGHT) {
         spriteLayer.relocateAsset(playerSprite, 4, 2);
      }
   }

   public void keyReleased(KeyEvent e) {
      System.out.println(e.toString());
   }
}
