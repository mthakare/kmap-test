package tools.kmap_test;

import java.util.AbstractMap.SimpleEntry;

public class Condition implements ICondition {

	public SimpleEntry<SimpleEntry<Boolean, String>, boolean[]> evaluate(boolean... conditions) {

		if (conditions[0] || conditions[1]) {

			// y = [A'B' + ] AC + BD'F + BDE'
			if ((conditions[0] && conditions[2]) || (conditions[1] && !conditions[3] && conditions[5])
					|| (conditions[1] && conditions[3] && !conditions[4])) {

				// Failure Conditions

				// Case 1 : error-1 -> BD'F + ACD'F 
				if ((conditions[1] && !conditions[3] && conditions[5])
						|| (conditions[0] && conditions[2] && !conditions[3] && conditions[5])) {

					return new SimpleEntry<SimpleEntry<Boolean, String>, boolean[]>(
							new SimpleEntry<Boolean, String>(true, "error-1"), conditions);
				}

				// Case 2 : error-2 -> y = A'BDE' + BC'DE' 
				if ((!conditions[0] && conditions[1] && conditions[3] && !conditions[4])
						|| (conditions[1] && !conditions[2] && conditions[3] && !conditions[4])) {

					return new SimpleEntry<SimpleEntry<Boolean, String>, boolean[]>(
							new SimpleEntry<Boolean, String>(true, "error-2"), conditions);
				}

				// Case 3 : error-3 -> y = ACF' + ACD 
				if ((conditions[0] && conditions[2] && !conditions[5])
						|| (conditions[0] && conditions[2] && conditions[3])) {

					return new SimpleEntry<SimpleEntry<Boolean, String>, boolean[]>(
							new SimpleEntry<Boolean, String>(true, "error-3"), conditions);
				}
			} else {
				return new SimpleEntry<SimpleEntry<Boolean, String>, boolean[]>(
						new SimpleEntry<Boolean, String>(false, "SUCCESS"), conditions);
			}
		} else {

			return new SimpleEntry<SimpleEntry<Boolean, String>, boolean[]>(
					new SimpleEntry<Boolean, String>(true, "INVAL"), conditions);
		}

		// Should not be here
		return new SimpleEntry<SimpleEntry<Boolean, String>, boolean[]>(
				new SimpleEntry<Boolean, String>(true, "INVAL**"), conditions);

	}

	public String getName() {
		// TODO Auto-generated method stub
		return "New Refactored Condition";
	}

}
