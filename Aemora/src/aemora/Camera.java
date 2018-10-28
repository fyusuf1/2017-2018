package aemora;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;

public class Camera {

    private int x, y;
    private int mapWidth, mapHeight;
    private Rectangle viewPort;

    public Camera(TiledMap map, int mapWidth, int mapHeight) {
        x = 0;
        y = 0;
        viewPort = new Rectangle(0, 0, Aemora.WIDTH, Aemora.HEIGHT);
        //viewPort = new Rectangle(0, 0, mapWidth, mapWidth);
        this.mapWidth = mapWidth;
        this.mapHeight = mapWidth;
    }

    public void translate(Graphics g, Player hero) {

        if (hero.getX() - Aemora.WIDTH / 2 + 16 < 0) {
            x = 0;
        } else if (hero.getX() + Aemora.WIDTH / 2 + 16 > mapWidth) {
            x = -mapWidth + Aemora.WIDTH;
        } else {
            x = (int) -hero.getX() + Aemora.WIDTH / 2 - 16;
        }

        if (hero.getY() - Aemora.HEIGHT / 2 + 16 < 0) {
            y = 0;
        } else if (hero.getY() + Aemora.HEIGHT / 2 + 16 > mapHeight) {
            y = -mapHeight + Aemora.HEIGHT;
        } else {
            y = (int) -hero.getY() + Aemora.HEIGHT / 2 - 16;
        }
        g.translate(x, y);
        viewPort.setX(-x);
        viewPort.setY(-y);
    }
    
    public int getX() {
    		return x;
    }
    
    public int getY() {
		return y;
}
}