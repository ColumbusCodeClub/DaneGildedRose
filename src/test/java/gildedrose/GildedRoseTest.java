package gildedrose;
import static org.junit.Assert.*;

import org.junit.Test;


public class GildedRoseTest {

	@Test
	public void agedBrieIncreaseInQualityByOnePerDay() {
		Item brie = new Item("Aged Brie", 5, 5);
		
		GildedRose.addItem(brie);
		GildedRose.updateQuality();
		
		assertEquals(6, brie.getQuality());
	}
}
