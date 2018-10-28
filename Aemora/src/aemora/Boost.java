package aemora;

public class Boost extends Item {

	private int atk_boost, def_boost;
	private int base_cost;
	
	public Boost(int id) {
		super(id);
		
		atk_boost = Integer.parseInt(super.getInfoArray()[5]);
		def_boost = Integer.parseInt(super.getInfoArray()[6]);
	}
	
	public int getATKBoost() {
		return atk_boost;
	}
	public int getDEFBoost() {
		return def_boost;
	}
	public int getBaseCost() {
		return base_cost;
	}
	
	
}
