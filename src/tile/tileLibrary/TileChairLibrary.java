package tile.tileLibrary;

import main.GamePanel;
import tile.Tile;
import tile.TileManager;

import java.awt.*;

public class TileChairLibrary extends Tile {
    public int x,y;
    GamePanel gamePanel;
    TileManager tileManager;

    public TileChairLibrary(GamePanel gamePanel, int x, int y) {
        this.x = x;
        this.y = y;
        this.gamePanel=gamePanel;
        Name = "Chair Library";
        tileManager = new TileManager(gamePanel);
        this.BoundingBox();
    }

    public void BoundingBox() {
        setLeftX(x * gamePanel.scale);
        setTopY(y * gamePanel.scale);
        setRightX((x+16) * gamePanel.scale);
        setBottomY((y+16) * gamePanel.scale);
    }

    public void draw(Graphics2D g2) {
        tileManager.draw(g2, tileManager.tile[6].image,  getLeftX(),  getTopY(), 16 * gamePanel.scale, 16 * gamePanel.scale);
    }
}
