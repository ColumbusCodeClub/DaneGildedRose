public class UsefulItem {

	private int quality = 0;
	private int sellIn = 0;
	private String name = "name";

	public UsefulItem(Item item) {
		this.quality = item.getQuality();
		this.sellIn = item.getSellIn();
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

	public void decrementQuality() {
		this.quality -= 1;
	}

	public void incrementQuality() {
		if (quality < 50) {
			this.quality += 1;
		}
	}

}
