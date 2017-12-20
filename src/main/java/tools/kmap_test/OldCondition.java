package tools.kmap_test;

import java.util.AbstractMap.SimpleEntry;

public class OldCondition implements ICondition {

	public SimpleEntry<SimpleEntry<Boolean, String>, boolean[]> evaluate(boolean... conditions) {

		if (conditions[0] || conditions[1]) {
			if (conditions[0]) { // A
				if (conditions[2]) { // C
					if (conditions[3]) { // D
						return new SimpleEntry<SimpleEntry<Boolean, String>, boolean[]>(
								new SimpleEntry<Boolean, String>(true, "error-3"), conditions);
					} else {
						// Bad PID check
						if (conditions[5]) {
							return new SimpleEntry<SimpleEntry<Boolean, String>, boolean[]>(
									new SimpleEntry<Boolean, String>(true, "error-1"),
									conditions);
						} else {
							// Start the processing point again
							if (conditions[2]) {
								return new SimpleEntry<SimpleEntry<Boolean, String>, boolean[]>(
										new SimpleEntry<Boolean, String>(true,
												"error-3"),
										conditions);
							}
						}
					}
				}
			}

			if (conditions[1]) { // B
				if (conditions[3]) { // D
					if (conditions[4]) { // E
						// continue
					} else {
						return new SimpleEntry<SimpleEntry<Boolean, String>, boolean[]>(
								new SimpleEntry<Boolean, String>(true, "error-2"), conditions);
					}
				} else {
					if (conditions[5]) { // F
						if (conditions[0]) { //  A
							if (conditions[2]) { //  C
								return new SimpleEntry<SimpleEntry<Boolean, String>, boolean[]>(
										new SimpleEntry<Boolean, String>(true, "error-3"), conditions);
							} else {
								return new SimpleEntry<SimpleEntry<Boolean, String>, boolean[]>(
										new SimpleEntry<Boolean, String>(true,
												"error-1"),
										conditions);
							}
						} else {
							return new SimpleEntry<SimpleEntry<Boolean, String>, boolean[]>(
									new SimpleEntry<Boolean, String>(true, "error-1"),
									conditions);
						}
					}
				}
			}
			return new SimpleEntry<SimpleEntry<Boolean, String>, boolean[]>(
					new SimpleEntry<Boolean, String>(false, "SUCCESS"), conditions);
		} else {
			return new SimpleEntry<SimpleEntry<Boolean, String>, boolean[]>(
					new SimpleEntry<Boolean, String>(true, "INVAL"), conditions);
		}

	}

	public String getName() {
		// TODO Auto-generated method stub
		return "Old Condition";
	}

}
