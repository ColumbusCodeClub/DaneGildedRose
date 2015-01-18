package gildedrose;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class GildedRoseTest {

	private GildedRose rose;

	@Before
	public void setup() {
		rose = new GildedRose();
	}
	
	private void timePasses(Item item) {
		rose.addItem(item);
		rose.updateQuality();
	}

	@Test
	public void agedBrieIncreaseInQualityByOnePerDay() {
		Item item = new Item("Aged Brie", 5, 5);
		
		timePasses(item);
		
		assertEquals(6, item.getQuality());
	}
	
	@Test
	public void agedBrieSellInDateDecreasesEachDay() {
		Item item = new Item("Aged Brie", 5, 5);
		
		timePasses(item);
		
		assertEquals(4, item.getSellIn());
	}
	
	@Test
	public void cakeDecreasesInQualityEachDay() {
		Item item = new Item("Conjured Mana Cake", 5, 5);
		
		timePasses(item);
		
		assertEquals(4, item.getQuality());
	}
	
	@Test
	public void cakeDecreasesInQualityByTwoWhenPastSellInDate() {
		Item item = new Item("Conjured Mana Cake", 0, 5);
		
		timePasses(item);
		
		assertEquals(3, item.getQuality());
	}
	
	@Test
	public void BackStagePassDecreasesInQualityToZeroWhenPastSellInDate() {
		Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 5);
		
		timePasses(item);
		
		assertEquals(0, item.getQuality());
	}

}
