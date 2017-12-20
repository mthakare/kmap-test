package tools.kmap_test;

import java.util.AbstractMap.SimpleEntry;

public class Condition implements ICondition {

	public SimpleEntry<SimpleEntry<Boolean, String>, boolean[]> evaluate(boolean... conditions) {

		if (conditions[0] || conditions[1]) {
			// ##### WRONG A'D + B'C + B'DF + BD'F + ACD' + BC'DE' ####//

			// y = [A'B' + ] AC + BD'F + BDE'
			if ((conditions[0] && conditions[2]) || (conditions[1] && !conditions[3] && conditions[5])
					|| (conditions[1] && conditions[3] && !conditions[4])) {

				// Failure Conditions

				// Case 1 : Application terminated unexpectedly -> BD'F + ACD'F / with dont
				// cares y = B'C + BD'F
				if ((conditions[1] && !conditions[3] && conditions[5])
						|| (conditions[0] && conditions[2] && !conditions[3] && conditions[5])) {

					return new SimpleEntry<SimpleEntry<Boolean, String>, boolean[]>(
							new SimpleEntry<Boolean, String>(true, "FAILURE_APP_TERMINATED_UNEXPECTEDLY"), conditions);
				}

				// Case 2 PID not set -> y = A'BDE' + BC'DE' / wiht dont cares y = BDE'
				if ((!conditions[0] && conditions[1] && conditions[3] && !conditions[4])
						|| (conditions[1] && !conditions[2] && conditions[3] && !conditions[4])) {

					return new SimpleEntry<SimpleEntry<Boolean, String>, boolean[]>(
							new SimpleEntry<Boolean, String>(true, "FAILURE_PID"), conditions);
				}

				// Case 3 Event Service Read Failure -> y = ACF' + ACD / with dont cares : y =
				// AC
				if ((conditions[0] && conditions[2] && !conditions[5])
						|| (conditions[0] && conditions[2] && conditions[3])) {

					return new SimpleEntry<SimpleEntry<Boolean, String>, boolean[]>(
							new SimpleEntry<Boolean, String>(true, "FAILURE_EVENTSERVICE"), conditions);
				}
			} else {
				return new SimpleEntry<SimpleEntry<Boolean, String>, boolean[]>(
						new SimpleEntry<Boolean, String>(false, "SUCCESS"), conditions);
			}
		} else {

			return new SimpleEntry<SimpleEntry<Boolean, String>, boolean[]>(
					new SimpleEntry<Boolean, String>(true, "INVAL"), conditions);
		}

		return new SimpleEntry<SimpleEntry<Boolean, String>, boolean[]>(
				new SimpleEntry<Boolean, String>(true, "INVAL**"), conditions);

	}

	public String getName() {
		// TODO Auto-generated method stub
		return "New Refactored Condition";
	}

}
