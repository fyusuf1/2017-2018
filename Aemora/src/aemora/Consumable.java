package aemora;

public class Consumable extends Item{

	private int hp_boost;
	private int base_cost; //hmmm, should stores define that??
	
	public Consumable(int id) {
		super(id);
		//super.myType = 0;  nah, already in txt/xml
		hp_boost = Integer.parseInt(super.getInfoArray()[4]);
		base_cost = Integer.parseInt(super.getInfoArray()[7]);
	}

	public int getHPBoost() {
		return hp_boost;
	}
	
	public int getBaseCost() {
		return base_cost;
	}
}
