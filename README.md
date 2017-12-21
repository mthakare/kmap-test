Requirements

	jre 1.4.0.

	maven 3.3

Steps to execute
	
	mvn package
	
	java -jar target\kmap-test-0.0.1-SNAPSHOT.jar
	
Description
	
	Refer https://mayurthakare.wordpress.com/2017/12/21/refactoring-code-using-k-maps/ to get background and purpose of this tool.
	
	This tool does following.
	
	1. Generates input for n-variable truth table.
	2. Evaluates ICondition and generates truth table.
	3. Compares two ICondition objects.
	
	

	ICondition.
		Any class implementing ICondition implements evaluate method which takes boolean array and returns result object (SimpleEntry<SimpleEntry<Boolean, String>, boolean[]>).
	
	Code implements two sample condition classes that implements ICondition. Condition and OldCondition.
	
	Refer tools.kmap_test.TruthTableGenerator.main(String[]) for sample use case.
	
	


