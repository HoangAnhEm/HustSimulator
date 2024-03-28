package tile;

import main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.PublicKey;
import java.util.Objects;
import java.io.*;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int[][] typeTile;

    public int[][] fixedTile, movableTile;
    public Tile[] numToTile;
    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[60];
        typeTile = new int[gp.maxMapCol][gp.maxMapRow];
        getTileImage();
    }

    public void loadMap(String filePath) {
        try {
            //Khai báo 1 biến để nhập file
            InputStream is = getClass().getResourceAsStream(filePath);
            //System.out.println("1");
            //Khai báo biến để đọc pfile vừa nhập
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            //Vòng lặp để đọc file và tách xâu thnahf các số để đưa vào mảng 2 chiều mapTileNum
            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();

                while (col < gp.maxScreenCol) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    typeTile[col][row] = num;
                    col++;
                }
                if (col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {

        }

    }
    public void getTileImage() {
        try {
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new FileInputStream("res/tile/ban_hs.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new FileInputStream("res/tile/ban_gv.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(new FileInputStream("res/tile/may_tinh_hs.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(new FileInputStream("res/tile/may_tinh_gv.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(new FileInputStream("res/tile/ban_thu_vien.png"));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(new FileInputStream("res/tile/ghe_thu_vien.png"));

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(new FileInputStream("res/tile/tu_sach01.png"));

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(new FileInputStream("res/tile/tu_sach02.png"));

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(new FileInputStream("res/tile/tu_sach03.png"));

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(new FileInputStream("res/tile/xe_hoi_trang.png"));

            tile[11] = new Tile();
            tile[11].image = ImageIO.read(new FileInputStream("res/tile/xe_tai_do.png"));

            tile[12] = new Tile();
            tile[12].image = ImageIO.read(new FileInputStream("res/tile/qua_bong.png"));

            tile[13] = new Tile();
            tile[13].image = ImageIO.read(new FileInputStream("res/tile/cua_thu_vien.png"));

            tile[14] = new Tile();
            tile[14].image = ImageIO.read(new FileInputStream("res/tile/cua_ra_vao.png"));

            tile[15] = new Tile();
            tile[15].image = ImageIO.read(new FileInputStream("res/tile/tuong_va_cua_svd01.png"));

            tile[16] = new Tile();
            tile[16].image = ImageIO.read(new FileInputStream("res/tile/tuong_va_cua_svd02.png"));

            tile[17] = new Tile();
            tile[17].image = ImageIO.read(new FileInputStream("res/tile/backgroud_classroom.png"));

            tile[18] = new Tile();
            tile[18].image = ImageIO.read(new FileInputStream("res/tile/backgroud_svd.png"));

            tile[19] = new Tile();
            tile[19].image = ImageIO.read(new FileInputStream("res/tile/background_library.png"));




        }catch (IOException e) {
            e.printStackTrace();
        }
    }
//    public void draw(Graphics2D g2) {
//
//        int mapCol = 0, mapRow = 0;
//
//        while (mapCol < gp.maxMapCol && mapRow < gp.maxMapRow) {
//
//            int mapX = mapCol * gp.tileSize;
//            int mapY = mapRow * gp.tileSize;
//
//            int screenX = mapX - gp.player.mapX + gp.player.screenX;
//            int screenY = mapY - gp.player.mapY + gp.player.screenY;
//            if     (mapX + gp.tileSize > gp.player.mapX - gp.player.screenX &&
//                    mapX - gp.tileSize < gp.player.mapX + gp.player.screenX &&
//                    mapY + gp.tileSize > gp.player.mapY - gp.player.screenY &&
//                    mapY - gp.tileSize < gp.player.mapY + gp.player.screenY) {
//                g2.drawImage(tile[typeTile[mapCol][mapRow]].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
//
//            }
//
//            ++mapCol;
//            if (mapCol == gp.maxMapCol) {
//                mapCol = 0;
//                ++mapRow;
//            }
//        }
//    }
}
