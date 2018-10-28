package aemora;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class GamePlayState extends BasicGameState {

	static boolean fullscreen = false;
	static boolean showFPS = true;
	private boolean[][] blocked;
	static String title = "Aemora";
	static int fpslimit = 60;
	TiledMap map;
	Player player;
	Camera camera;
	int mapHeight, mapWidth;
	int tileHeight, tileWidth;
	int stateid;
	Image inv_menu;
	int mode; // what are you currently controlling? menu? player movement??
	int inv_item_choice, inv_action_choice; // index of which inventory item is currently selected & what action is
											// currently selected
	final int INV_MAX = 10; // max size of inventory arraylist, so max size of inventory
	ArrayList<Item> inventory; // string for now, changed to Item type when Item
								// class is finally created
	final int INV_CHOICES = 3;

	public GamePlayState(int id) {
		stateid = id;
	}

	@Override
	public int getID() {
		return stateid;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		map = new TiledMap("tiledmaps/test3.tmx");
		mapWidth = map.getWidth() * map.getTileWidth();
		mapHeight = map.getHeight() * map.getTileHeight();
		tileHeight = map.getTileHeight();
		tileWidth = map.getTileWidth();
		player = new Player(30, 30);
		camera = new Camera(map, mapWidth, mapHeight);
		blocked = new boolean[map.getWidth()][map.getHeight()];
		inv_menu = new Image("images/gui/inv.png");
		inv_action_choice = 0;
		mode = 0; // zero means it can move
		inventory = new ArrayList<Item>(
				(Arrays.asList(new Consumable(0), new Consumable(1), new Consumable(4), new Consumable(3))));
		inv_item_choice = 0;
		initializeBlocked();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		player.update(gc, sbg, delta, this);
		if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			sbg.enterState(Aemora.MENUSTATE);
		}
		// OPENING INVENTORY
		if (gc.getInput().isKeyPressed(Input.KEY_I)) {
			if (mode == 1) { // already in inventory
				mode = 0; // now switch to moving
				player.setCanMove(true);
			} else { // already in moving mode
				mode = 1; // switch to inventory
				player.setCanMove(false);
				// put the stuff here...
			}
		}
		// FLIPPING THROUGH INVENTORY
		if (gc.getInput().isKeyPressed(Input.KEY_DOWN)) {
			if (mode == 1) {
				if (inv_item_choice == inventory.size() - 1) {
					inv_item_choice = 0;
				} else {
					inv_item_choice++;
				}
			}
		}
		// FLIPPING THROUGH INVENTORY
		if (gc.getInput().isKeyPressed(Input.KEY_UP)) {
			if (mode == 1) {
				if (inv_item_choice == 0) {
					inv_item_choice = inventory.size() - 1;
				} else {
					inv_item_choice--;
				}
			}
		}
		// FLIPPING THROUGH INVENTORY ACTIONS
		if (gc.getInput().isKeyPressed(Input.KEY_LEFT)) {
			if (mode == 1) {
				if (inv_action_choice == 0) {
					inv_action_choice = INV_CHOICES - 1;
				} else {
					inv_action_choice--;
				}
			}
		}
		// FLIPPING THROUGH INVENTORY ACTIONS
		if (gc.getInput().isKeyPressed(Input.KEY_RIGHT)) {
			if (mode == 1) {
				if (inv_action_choice == INV_CHOICES - 1) {
					inv_action_choice = 0;
				} else {
					inv_action_choice++;
				}
			}
		}
		// INVENTORY ACTIONS
		if (gc.getInput().isKeyPressed(Input.KEY_ENTER)) {
			if (mode == 1) {
				switch (inv_action_choice) {
				case 0:// USE
						// update stats/equip, remove from inventory
					break;
				case 1:// INFO
						// draw info box for that item, new mode??
					break;
				case 2:// DROP
					if (inventory.size() == 0) {
						break;
					}
					inventory.remove(inv_item_choice);
					inv_item_choice = 0;
					break;
				}
			}
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		camera.translate(g, player);
		map.render(0, 0);
		player.render();
		if (mode == 1) {
			renderInventory();
		}
	}

	private void renderInventory() {
		inv_menu.draw(180 - camera.getX(), 60 - camera.getY());
		// highlight selection & write items to screen

		Color notChosen = new Color(153, 204, 255);
		Font menuFont = new Font("Verdana", Font.BOLD, 10);
		TrueTypeFont playersOptionsTTF = new TrueTypeFont(menuFont, true);

		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i).getType() == 0)
				if (inv_item_choice == i) {
					playersOptionsTTF.drawString(220 - camera.getX(), i * 50 + (80 - camera.getY()),
							inventory.get(i).getName());
				} else {
					playersOptionsTTF.drawString(220 - camera.getX(), i * 50 + (80 - camera.getY()),
							inventory.get(i).getName(), notChosen);
				}
		}
	}

	public boolean isBlocked(float x, float y) {
		int xBlock = (int) x / map.getTileWidth();
		int yBlock = (int) y / map.getTileHeight();
		return blocked[xBlock][yBlock];
	}

	public Vector2f getPlayerPosition() {
		return player.getpos();
	}

	public void setPlayerPosition(Vector2f pos) {
		player.setpos(pos);
	}

	private void initializeBlocked() {
		for (int l = 0; l < map.getLayerCount(); l++) {
			String layerValue = map.getLayerProperty(l, "blocked", "false");
			if (layerValue.equals("true")) {
				for (int c = 0; c < map.getWidth(); c++) {
					for (int r = 0; r < map.getHeight(); r++) {
						if (map.getTileId(c, r, l) != 0) {
							blocked[c][r] = true;
						}
					}
				}
			}
		}
	}
}