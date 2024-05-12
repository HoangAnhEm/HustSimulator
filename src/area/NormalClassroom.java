package area;

import main.GamePanel;
import map.Map;
import tile.Tile;
import tile.TileSection;
import java.awt.*;

public class NormalClassroom extends Map {
    GamePanel gamePanel;
    Tile background;
    public Tile tileDoor01,tileDoor02,tileTableTeacher,tileWallNormalClassroom;
    public Tile [] tileTable;

    public NormalClassroom(GamePanel gamePanel) {
        super();
        this.gamePanel = gamePanel;

        width = (int)(21 * 16 * gamePanel.scale);
        height = (int)(16 * 16 * gamePanel.scale);
        tileContainer = new Tile[50];
        background = new Tile();
        background.image = gamePanel.tileManager.tile[17].image;
        setDefaultValues();
    }

    public void setDefaultValues() {
        playerX = (int) (35 * GamePanel.scale);
        playerY = (int) (64 * GamePanel.scale);
        background.setWidth((int) (320 * GamePanel.scale));
        background.setHeight((int) (240 * GamePanel.scale));
        tileTable = new Tile[10];
        tileDoor01 = new Tile(gamePanel,30,28,41,51,"Door Classroom","Teleport","","res/tile/cua_ra_vao.png",1);
        tileDoor02 = new Tile(gamePanel,261,28,41,51,"Door Classroom","Teleport","","res/tile/cua_ra_vao.png",1);
        tileTableTeacher = new Tile(gamePanel,273,177,30,62,"Table Teacher","Obstacle","","res/tile/ban_gv.png",1);
        tileWallNormalClassroom = new Tile(gamePanel,0,0,320,79,"","Obstacle","","res/tile/no_thing.png",1);
        setUpTable();
        setUpTileNormalClassroom();
    }
    public void setUpTileNormalClassroom() {
        numTileContainer = 0;
        mapIndex = 1;
        addTile(tileDoor01);
        addTile(tileDoor02);
        addTile(tileWallNormalClassroom);
        addTile(tileTableTeacher);
        for (int i = 0; i < 10; i++)
            addTile(tileTable[i]);
    }

    private void setUpTable() {
        int x1 = 33;
        int y1 = 103;
        int count = 0;
        for (int i = 0; i < 10; i++) {
            count++;
            if (count <= 5) {
                tileTable[i] = new Tile(gamePanel,x1,y1,20,46,"Table Student","Obstacle","","res/tile/ban_hs.png",1);
                x1 += 42;
            }
            if (count == 5) {
                y1 = 178;
                x1 = 33;
            }
            if (count > 5) {
                tileTable[i] = new Tile(gamePanel,x1,y1,20,46,"Table Student","Obstacle","","res/tile/ban_hs.png",1);
                x1 += 42;
            }
        }
    }

    public void resetTile(){
        width = (int) (21 * 16 * GamePanel.scale);
        height = (int) (16 * 16 * GamePanel.scale);
        playerX = (int) (35 * GamePanel.scale);
        playerY = (int) (64 * GamePanel.scale);
        background.setWidth((int) (320 * GamePanel.scale));
        background.setHeight((int) (240 * GamePanel.scale));
        for (int i = 0; i < numTileContainer; ++i) {
            tileContainer[i].reSizeTile();
        }
    }

    public void draw(Graphics2D g2) {

        gamePanel.tileManager.draw(g2, background);
        for (int i = 0; i < numTileContainer; ++i)
            gamePanel.tileManager.draw(g2, tileContainer[i]);
    }
}