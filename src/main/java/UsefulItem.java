
public class UsefulItem {

	private int quality = 0;
	private int sellIn = 0;

	public UsefulItem(Item item) {
		this.quality = item.getQuality();
		this.sellIn  = item.getSellIn();
	}

	public int quality() {
		return quality;
	}

	public int sellIn() {
		return sellIn;
	}

}
