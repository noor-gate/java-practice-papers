package concurrency;

import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class Store {

	private SortedMap<String, Integer> variables;

	public Store(Set<String> variableNames) {

		variables = new TreeMap<>();
		for (String var : variableNames) {
			variables.put(var, 0);
		}

	}

	/**
	 * Looks up the value of a variable in the store
	 *
	 * @param name
	 * 			the variable to be queried
	 * @return the value associated with this variable
	 */
	public int lookupVariable(String name) {
		checkVariableExists(name);
		return variables.get(name);
	}

	/**
	 * Updates the value of a variable in the store
	 *
	 * @param name
	 * 			the variable to be updated
	 * @param value
	 * 			the new value for this variable
	 */
	public void updateVariable(String name, int value) {
		checkVariableExists(name);
		variables.put(name, value);
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("[");
		boolean first = true;
		for (String variable : variables.keySet()) {
			if (first) {
				first = false;
			} else {
				result.append(", ");
			}
			result.append(variable + " -> " + variables.get(variable));
		}
		result.append("]");
		return result.toString();
	}

	private void checkVariableExists(String name) {
		if (!variables.containsKey(name)) {
			throw new IllegalArgumentException(
					"Attempted lookup of unknown variable \"" + name + "\"");
		}
	}

}
