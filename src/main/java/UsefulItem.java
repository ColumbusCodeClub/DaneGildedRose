
public class UsefulItem {

	private int quality = 0;
	private int sellIn = 0;
	private String name = "";

	public UsefulItem(Item item) {
		this.quality = item.getQuality();
		this.sellIn  = item.getSellIn();
		this.name   = item.getName();
	}

	public int quality() {
		return quality;
	}

	public int sellIn() {
		return sellIn;
	}

	public String name() {
		return name;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public void setSellIn(int sellIn) {
		this.sellIn = sellIn;
	}

}
