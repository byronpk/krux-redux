import java.util.ArrayList;
import java.awt.Graphics2D;

public class oberonLayer {
   protected ArrayList<oberonAsset> assetCache;
   protected int layerMatrix[][];
   protected int width;
   protected int height;
   protected int gridSize = 8;
   protected boolean hasPlayerSprite = false;
   protected int playerSpriteID = -1;
   
   public oberonLayer(int w, int h) {
      width = w;
      height = h;
      layerMatrix = new int[w][h];
      
   // Initialize the layerMatrix
      for(int x = 0; x < this.width; x++) {
         for(int y = 0; y < this.height; y++) {
            layerMatrix[x][y] = -1;
         }
      }
      
      assetCache = new ArrayList<oberonAsset>(256);
   }
   
   public void setGridSize(int size) throws GridSizeViolation {
      if(size % 8 != 0) {
         throw new GridSizeViolation();
      } else {
         this.gridSize = size;
      }
   }
   
   public int getGridSize() {
      return gridSize;
   }
   
   public void addAssetAt(int x, int y, oberonAsset oa) {
      this.assetCache.add(oa);
      this.layerMatrix[x][y] = this.assetCache.indexOf(oa);
   }
   
   public void addAssetAt(int x, int y, oberonAsset oa, boolean isPlayer) {
      this.addAssetAt(x, y, oa);
      this.hasPlayerSprite = true;
      this.playerSpriteID = this.assetCache.indexOf(oa);
   }
   
   public void relocateAssetAt(int x, int y, int toX, int toY) {
      if(this.layerMatrix[x][y] != -1) {
         oberonAsset temp = this.assetCache.get(this.layerMatrix[x][y]);
         this.layerMatrix[toX][toY] = this.layerMatrix[x][y];
         this.layerMatrix[x][y] = -1;
      } else {
         // DO NOTHING FOR NOW
      }
   }
   
   public void relocateAsset(oberonAsset oa, int toX, int toY) {
      int assetID = this.assetCache.indexOf(oa);
      
      if(assetID != -1) {         
      // Locate the asset in the matrix
         for(int b = 0; b < width; b++) {
            for(int a = 0; a < height; a++) {
               if(this.layerMatrix[a][b] == assetID) {
                  this.layerMatrix[a][b] = -1;
               }
            }
         }
         this.layerMatrix[toX][toY] = assetID;
      } else {
      // DO NOTHING FOR NOW
      }
   }
   
   public void setHasPlayerSprite(boolean val) {
      this.hasPlayerSprite = val;
   }
   
   public void setPlayerSprite(oberonAsset ast) {
      this.playerSpriteID = assetCache.indexOf(ast);
   }
   
   public boolean hasPlayerSprite() {
      return this.hasPlayerSprite;
   }
   
   public oberonAsset getPlayerSprite() {
      return this.assetCache.get(this.playerSpriteID);
   }
   
// Called by the DisplayController to draw this layer.
   public void paintLayer(Graphics2D g) {
      for(int x = 0; x < this.width; x++) {
         for(int y = 0; y < this.height; y++) {
            if (layerMatrix[x][y] != -1) {
               oberonAsset temp = assetCache.get(layerMatrix[x][y]);
               temp.paintAsset(g, x * gridSize, y * gridSize);
            }
         }
      }
   }
}

class GridSizeViolation extends Exception {
}