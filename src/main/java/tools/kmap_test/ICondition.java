package tools.kmap_test;

import java.util.AbstractMap.SimpleEntry;

public interface ICondition {
	
	public String getName();
	
	public SimpleEntry<SimpleEntry<Boolean, String>, boolean[]> evaluate(boolean... conditions);
}
