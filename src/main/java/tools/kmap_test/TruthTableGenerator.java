package tools.kmap_test;



import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;

public class TruthTableGenerator {
	
	int curr = 0;
	int kmap_variable = 0;

	public static void main (String [] args)
	{
	
		TruthTableGenerator t = new TruthTableGenerator(6);
		
		// A condition object containing code block that is refactored using kmaps.
		ICondition newCondition = new Condition();
		t.printTruthTable(newCondition);
		
		// A condition object containing code block that needs to be refactored.
		ICondition oldCondition = new OldCondition();
		t.printTruthTable(oldCondition);
		
		SimpleEntry<Boolean, List<SimpleEntry<Integer, boolean[]>>> comparisionResult = t.compare(newCondition, oldCondition);
		
		if(comparisionResult.getKey()) {
			System.out.println("Both conditions are same");
		} else {
			System.out.println("Both conditions are not same");
			System.out.println(t.getDiffEntries(comparisionResult.getValue()));
			
		}
		
		
	}
	
	public TruthTableGenerator(int variable) {
		kmap_variable = variable;
	}

	public boolean[][] generateTTInput(int variableCount) {
		
		boolean conditionsList[][] = new boolean[1<<kmap_variable][kmap_variable];
		for (int i = 0 ; i != (1<<kmap_variable) ; i++) {
		    String s = Integer.toBinaryString(i);

		    while (s.length() != kmap_variable) {
		        s = '0'+s;
		    }
		    
		    for (int j = 0; j < kmap_variable; ++ j) {
		    	conditionsList[i][j] = (s.charAt(j) == '1');
		    }
		}
		return conditionsList;
	}
	
	/**
	 * Evaluates conditions and performs comparison. If they are not equal, it returns list of input combinations that are different. 
	 * @param newCondition
	 * @param oldCondition
	 * @return
	 */
	public SimpleEntry<Boolean, List<SimpleEntry<Integer, boolean[]>>> compare(ICondition newCondition, ICondition oldCondition) {
		
		List<SimpleEntry<Integer, boolean[]>> dissimilarCondtions = new ArrayList<SimpleEntry<Integer, boolean[]>>();
		boolean isSame = true;
		boolean[][] conditionsList= generateTTInput(6);
		for (int i = 0; i < conditionsList.length; ++i) {
			if (newCondition.evaluate(conditionsList[i]).getKey().getKey() != oldCondition.evaluate(conditionsList[i]).getKey().getKey())
			{
				isSame = false;
				dissimilarCondtions.add(new SimpleEntry<Integer, boolean[]>(i, conditionsList[i]));
			}
			
		}

		return new SimpleEntry<Boolean,List<SimpleEntry<Integer, boolean[]>>>(isSame, dissimilarCondtions);
	}

	/**
	 *	Prints truth table evaluating all conditions with all possible 2^kmap_variable combination.
	 * 	@param ICondition
	 */
	public void printTruthTable(ICondition c) {
		
		
		System.out.println("--------------- Truth table for " + c.getName() + "---------------");
		boolean[][] conditionsList= generateTTInput(6);
		curr = 0;
		for (int i = 0; i < conditionsList.length; ++i) {
			print(c.evaluate(conditionsList[i]));
		}
		System.out.println("--------------- ---------------  --------------- ---------------");
	}


	private void print(SimpleEntry<SimpleEntry<Boolean, String>, boolean[]> result) {
		int zero = 0;
		
		SimpleEntry<Boolean, String> res = result.getKey();
		boolean[] conditions = result.getValue();
		String input = "";
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < conditions.length; ++i) {
			
			if (conditions[i]) {
				buff.append("1").append(" ");
			} else {
				buff.append("0").append(" ");
			}
			
		}
		input = buff.toString();
		
		if (res.getKey()) zero = 1;
		System.out.println("("+curr+") "+input + " | " + zero + " (" +res.getValue()+")");
		curr++;
	}
	
	private String getDiffEntries(List<SimpleEntry<Integer, boolean[]>> result) {
		
		StringBuffer buff = new StringBuffer();
		for (SimpleEntry<Integer, boolean[]> entry : result)
		{
			buff.append("(").append(entry.getKey()).append(") ");
			for (int j = 0; j < entry.getValue().length; ++j) {
				
				
				
				if (entry.getValue()[j]) {
					buff.append("1").append(" ");
				} else {
					buff.append("0").append(" ");
				}
				
			}
			buff.append("\n");
			
		}
		
		return buff.toString();
	}
	

	
}
