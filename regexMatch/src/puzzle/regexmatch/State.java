package puzzle.regexmatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class State
{
		private int name;
		private Map<Character, State> transitions;
		private boolean isFinal;

		State(int name)
		{
			this.name = name;
			this.isFinal = false;
			transitions = new HashMap<>();
		}

		void setFinalStatus(boolean isFinal)
		{
			this.isFinal = isFinal;
		}

		List<State> traverse(Character c)
		{
			List<State> states = null;
			if (transitions.containsKey(c))
			{
				states = new ArrayList<>(1);
				states.add(transitions.get(c));
			}
			if (transitions.containsKey('.'))
			{
				if (states == null)
				{
					states = new ArrayList<>(1);
				}
				states.add(transitions.get('.'));
			}
			return states;
		}

		void addEdge(Character c, State s)
		{
			transitions.put(c, s);
		}

		boolean isFinal()
		{
			return isFinal;
		}
}
