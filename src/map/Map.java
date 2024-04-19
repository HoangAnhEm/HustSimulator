package map;

import main.GamePanel;
import tile.Tile;

public class Map {
    GamePanel gamePanel;
    public int mapIndex;
    public int maxMapCol, maxMapRow;
    public int numTileContainer;
    public int playerX, playerY;
    public Tile [] tileContainer;
    public void addTile(Tile tile){
        tileContainer[numTileContainer++] = tile;
    }
    public void deleteTile(int index){
        --numTileContainer;
        for (int i = index; i < numTileContainer; ++i)
            tileContainer[i] = tileContainer[i + 1];
    }
    public void loadMap(GamePanel gamePanel){
        gamePanel.currentMap = this;
        gamePanel.mapWidth = maxMapCol * 16 * gamePanel.scale;
        gamePanel.mapHeight = maxMapRow * 16 * gamePanel.scale;
        gamePanel.player.setMapX(playerX);
        gamePanel.player.setMapY(playerY);
    }
}
