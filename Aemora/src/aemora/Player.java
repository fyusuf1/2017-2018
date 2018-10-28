package aemora;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class Player {

	private Animation Player, movementUp, movementDown, movementLeft, movementRight, stillUp, stillDown, stillLeft,
			stillRight;
	private char lastDirection;
	private Vector2f pos;
	private Rectangle rectangle;
	private static final int ANIMATIONSPEED = 400;
	private static final float SPEED = 0.2f;
	private int w, h;
	private boolean can_move; //can the player move right now?  --used so that direction keys
							 //aren't controlling the player's movement when they should be controlling other things
							 //like menu selection
	private int[] stats = new int[6]; //hp, atk, def, cash, lvl, exp
	
	public Player(float x, float y) throws SlickException {
		/**
		 * Set the Image arrays needed for the animations.
		 */
		Image[] animationUp = { new Image("images/hero20.png"), new Image("images/hero22.png") };
		Image[] animationDown = { new Image("images/hero00.png"), new Image("images/hero02.png") };
		Image[] animationLeft = { new Image("images/hero30.png"), new Image("images/hero32.png") };
		Image[] animationRight = { new Image("images/hero10.png"), new Image("images/hero12.png") };
		Image[] up = { new Image("images/hero21.png") };
		Image[] down = { new Image("images/hero01.png") };
		Image[] left = { new Image("images/hero31.png") };
		Image[] right = { new Image("images/hero11.png") };

		/**
		 * Set the width and the height of Player's image
		 */
		w = down[0].getWidth();
		h = down[0].getHeight();

		pos = new Vector2f(x, y);
		rectangle = new Rectangle(x, y, w, h);
		/**
		 * Set the Animations needed.
		 */
		stillUp = new Animation(up, ANIMATIONSPEED);
		stillDown = new Animation(down, ANIMATIONSPEED);
		stillLeft = new Animation(left, ANIMATIONSPEED);
		stillRight = new Animation(right, ANIMATIONSPEED);
		movementUp = new Animation(animationUp, ANIMATIONSPEED);
		movementDown = new Animation(animationDown, ANIMATIONSPEED);
		movementLeft = new Animation(animationLeft, ANIMATIONSPEED);
		movementRight = new Animation(animationRight, ANIMATIONSPEED);

		Player = stillDown;

		can_move = true;
		
		stats[0] = 10;
		stats[1] = stats[2] = 2;
		stats[3] = 0;
		stats[4] = 1;
		stats[5] = 0; //WILL CHANGE THESE LATER TO READ FROM SAVE
		
		
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta, GamePlayState gps) {
		Input input = gc.getInput();

		/**
		 * If the Player is moving we have to deal with changing Player's position and
		 * his movement animations.
		 */
		/*if (input.isKeyDown(Input.KEY_I)) {
			if (can_move) {
				can_move = false;
			} else {
				can_move = true;
			}
		}*/
		if (can_move) {
			if (input.isKeyDown(Input.KEY_UP)) {
				if (!gps.isBlocked(pos.x + w - 4, pos.y - delta * SPEED)
						&& !gps.isBlocked(pos.x + 4, pos.y - delta * SPEED)) {
					pos.y -= delta * SPEED;
				}
				Player = movementUp;
				Player.update(delta);
				lastDirection = 'u';
			} else if (input.isKeyDown(Input.KEY_DOWN)) {
				if (!gps.isBlocked(pos.x + w - 4, pos.y + h + delta * SPEED)
						&& !gps.isBlocked(pos.x + 4, pos.y + h + delta * SPEED)) {
					pos.y += delta * SPEED;
				}
				Player = movementDown;
				Player.update(delta);
				lastDirection = 'd';
			} else if (input.isKeyDown(Input.KEY_LEFT)) {
				if (!gps.isBlocked(pos.x - delta * SPEED, pos.y + 4)
						&& !gps.isBlocked(pos.x - delta * SPEED, pos.y + h - 4)) {
					pos.x -= delta * SPEED;
				}
				Player = movementLeft;
				Player.update(delta);
				lastDirection = 'l';

			} else if (input.isKeyDown(Input.KEY_RIGHT)) {
				if (!gps.isBlocked(pos.x + w + delta * SPEED, pos.y + h - 4)
						&& !gps.isBlocked(pos.x + w + delta * SPEED, pos.y + 4)) {
					pos.x += delta * SPEED;
				}
				Player = movementRight;
				Player.update(delta);
				lastDirection = 'r';
			}
		}
		/**
		 * If Player isn't moving he must be still so we have to change the animations.
		 */
		else {
			switch (lastDirection) {
			case 'd':
				Player = stillDown;
				break;
			case 'u':
				Player = stillUp;
				break;
			case 'l':
				Player = stillLeft;
				break;
			case 'r':
				Player = stillRight;
				break;
			}
		}
	}
	
	public int[] getStats() {
		return stats;
	}
	/*public int getHP() {
		return stats[0];
	}
	public void changeHP(int hp_b) {
		stats[0] += hp_b;
	}
	public int getATK() {
		return stats[1];
	}
	public void changeATK(int atk_b) {
		stats[1] += atk_b;
	}
	public int getDEF() {
		return stats[2];
	}
	public void changeDEF(int def_b) {
		stats[2] += def_b;
	}
	public int getCash() {
		return stats[3];
	}
	public void changeCash(int cash_b) {
		stats[3] += cash_b;
	}
	public int getLVL() {
		return stats[4];
	}
	public void changeLVL(int x) {  //NOPE
		stats[4] += x;
	}
	public int getEXP() {
		return stats[5];
	}
	public void changeEXP(int exp_b) {
		stats[5] += exp_b;
	}*/
	public void setCanMove(boolean c_m) {
		can_move = c_m;
	}
	public void render() {
		Player.draw(pos.x, pos.y);
	}

	public float getX() {
		return pos.x;
	}

	public float getY() {
		return pos.y;
	}

	public Vector2f getpos() {
		return pos;
	}

	public void setpos(Vector2f pos) {
		this.pos = pos;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}
}