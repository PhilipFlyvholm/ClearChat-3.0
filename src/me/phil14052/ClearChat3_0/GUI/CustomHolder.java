package me.phil14052.ClearChat3_0.GUI;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class CustomHolder implements InventoryHolder{

	private final Map<Integer, Icon> icons = new HashMap<>();
	
	private final int size;
	private final String title;
	private final String titleFallback; //Old versions only support 32 characters in the title
	
	public CustomHolder(int size, String title, String titleFallback){
		this.size = size;
		this.title = title;
		this.titleFallback = titleFallback;
	}
	
	public void setIcon(int position, Icon icon) {
        this.icons.put(position, icon);
    }
 
    public Icon getIcon(int position) {
        return this.icons.get(position);
    }
	
	@Override
	public Inventory getInventory() {
		Inventory inventory;
		try {
			inventory = Bukkit.createInventory(this, this.size, this.title);
		} catch (IllegalArgumentException e) {
			inventory = Bukkit.createInventory(this, this.size, this.titleFallback);
		}
        
        for (Entry<Integer, Icon> entry : this.icons.entrySet()) {
        	if(entry.getKey() > this.size) continue;
            inventory.setItem(entry.getKey(), entry.getValue().itemStack);
        }
   
        return inventory;
 
	}

}
