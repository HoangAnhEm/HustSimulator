package tile.tileStadium;

import main.GamePanel;
import tile.Tile;
import tile.TileManager;

import java.awt.*;

public class TileBall extends Tile {
    public int x,y;
    GamePanel gamePanel;
    TileManager tileManager;

    public TileBall(GamePanel gamePanel, int x, int y) {
        this.x = x;
        this.y = y;

        this.gamePanel=gamePanel;
        tileManager = new TileManager(gamePanel);
        this.BoundingBox();
    }

    public void BoundingBox() {
        setLeftX(x);
        setTopY(y);
        setRightX(x+14);
        setBottomY(y+14);
    }

    public void draw(Graphics2D g2) {
        tileManager.draw(g2, tileManager.tile[12].image,  getLeftX() * gamePanel.scale,  getTopY() * gamePanel.scale, 14 * gamePanel.scale, 14 * gamePanel.scale);
    }
}
