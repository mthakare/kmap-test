package tools.kmap_test;

import java.util.AbstractMap.SimpleEntry;

public class OldCondition implements ICondition {

	// condition[0] - > settings.monitorEvents() A
	// condition[1] - > settings.monitorPID() B
	// condition[2] - > eventReadFailed C
	// condition[3] - > pid == 0 D
	// condition[4] - > settings.getPIDFromEvent() E
	// condition[5] - > isInValidPid F


	public SimpleEntry<SimpleEntry<Boolean, String>, boolean[]> evaluate(boolean... conditions) {

		if (conditions[0] || conditions[1]) {
			if (conditions[0]) { // settings.monitorEvents() A
				if (conditions[2]) { // eventReadFailed C
					if (conditions[3]) { // pid == 0
						return new SimpleEntry<SimpleEntry<Boolean, String>, boolean[]>(
								new SimpleEntry<Boolean, String>(true, "FAILURE_EVENTSERVICE"), conditions);
					} else {
						// Bad PID check
						if (conditions[5]) {
							return new SimpleEntry<SimpleEntry<Boolean, String>, boolean[]>(
									new SimpleEntry<Boolean, String>(true, "FAILURE_APP_TERMINATED_UNEXPECTEDLY"),
									conditions);
						} else {
							// Start the processing point again
							if (conditions[2]) {
								return new SimpleEntry<SimpleEntry<Boolean, String>, boolean[]>(
										new SimpleEntry<Boolean, String>(true,
												"FAILURE_EVENTSERVICE *** if recreation of event file fails"),
										conditions);
							}
						}
					}
				}
			}

			if (conditions[1]) { // settings.monitorPID() B
				if (conditions[3]) { // pid == 0 D
					if (conditions[4]) { // settings.getPIDFromEvent() E
						// return new SimpleEntry(new SimpleEntry<Boolean, String>(false, "SUCCESS"),
						// conditions);
					} else {
						return new SimpleEntry<SimpleEntry<Boolean, String>, boolean[]>(
								new SimpleEntry<Boolean, String>(true, "FAILURE_PID"), conditions);
					}
				} else {
					if (conditions[5]) { // isInValidPid F
						if (conditions[0]) { // settings.monitorEvents() A
							if (conditions[2]) { // eventReadFailed C
								return new SimpleEntry<SimpleEntry<Boolean, String>, boolean[]>(
										new SimpleEntry<Boolean, String>(true, "FAILURE_EVENTSERVICE"), conditions);
							} else {
								return new SimpleEntry<SimpleEntry<Boolean, String>, boolean[]>(
										new SimpleEntry<Boolean, String>(true,
												"FAILURE_APP_TERMINATED_UNEXPECTEDLY **after 10 minutes if reboot does not happen"),
										conditions);
							}
						} else {
							return new SimpleEntry<SimpleEntry<Boolean, String>, boolean[]>(
									new SimpleEntry<Boolean, String>(true, "FAILURE_APP_TERMINATED_UNEXPECTEDLY"),
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
