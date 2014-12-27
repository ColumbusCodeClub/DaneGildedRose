import java.util.ArrayList;
import java.util.List;

public class GildedRose {

	private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
	private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
	private static final String AGED_BRIE = "Aged Brie";
	private static List<UsefulItem> items = null;

	public GildedRose() {
		items = new ArrayList<UsefulItem>();
		initItems();
	}

	public void addItem(UsefulItem item) {
		items.add(item);
	}

	public void updateQuality() {
		for (UsefulItem item : items) {
			item.adjustQualityForItem();
		}
	}

	public void adjustQualityForItem(UsefulItem item) {
		item.adjustQualityForItem();
	}

	private void initItems() {
		items.add(new UsefulItem(new Item("+5 Dexterity Vest", 10, 20)));
		items.add(new UsefulItem(new Item(AGED_BRIE, 2, 0)));
		items.add(new UsefulItem(new Item("Elixir of the Mongoose", 5, 7)));
		items.add(new UsefulItem(new Item(SULFURAS, 0, 80)));
		items.add(new UsefulItem(new Item(BACKSTAGE_PASSES, 15, 20)));
		items.add(new UsefulItem(new Item("Conjured Mana Cake", 3, 6)));
	}

}