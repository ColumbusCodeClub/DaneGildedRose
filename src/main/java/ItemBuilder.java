public  class ItemBuilder {
	
	Item item;
	
	public ItemBuilder() {
		item = new Item();
	}
	public ItemBuilder setName(String name) {
		item.setName(name);
		return this;
	}
	
	public ItemBuilder setQuality(int quality) {
		item.setQuality(quality);
		return this;
	}
	
	public ItemBuilder setSellIn(int sellIn) {
		item.setSellIn(sellIn);
		return this;
	}
	
	Item build() {
		return item;
	}

	
}
