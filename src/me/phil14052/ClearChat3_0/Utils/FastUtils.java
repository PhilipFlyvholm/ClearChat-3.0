package me.phil14052.ClearChat3_0.Utils;

import org.bukkit.plugin.Plugin;

public class FastUtils {

	
	public static boolean isLessThan(int n1, int n2){
		if(n1 < n2){
			return true;
		}else{
			return false;
		}
	}
	public static boolean isGreaterThan(int n1, int n2){
		if(n1 > n2){
			return true;
		}else{
			return false;
		}
	}
	
	public static int calcGUIRowSize(int amount, int minusamount, boolean withCancel, boolean withSpaceBeforeCancel){
		int size = amount-minusamount;
		if(withCancel && withSpaceBeforeCancel) size += 2;
		else if(withCancel) size += 1;
		if(FastUtils.isLessThan(size, 10)){
			return 1;
		}
		else if(FastUtils.isLessThan(size, 19)){
			return 2;
		}
		else if(FastUtils.isLessThan(size, 28)){
			return 3;
		}
		else if(FastUtils.isLessThan(size, 37)){
			return 4;
		}
		else if(FastUtils.isLessThan(size, 46)){
			return 5;
		}
		else if(FastUtils.isLessThan(size, 55)){
			return 6;
		}else{
			int pages = 0;
			while(FastUtils.isGreaterThan(size/6, 9)){
				pages++;
				if(FastUtils.isLessThan(size-54, 55)){
					size = 0;
					break;
				}else{
					size-=54;
				}
			}
			return pages*amount;
		}
		
	}
	
	public static void debug(String message, Plugin plugin){
		plugin.getLogger().info(message);
	}
}
