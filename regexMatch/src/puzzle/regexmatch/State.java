package puzzle.regexmatch;

import java.util.*;

public class State
{
		private int name;
		private Map<Character, Set<State>> transitions;
		private boolean isFinal;

		State(int name)
		{
			this.name = name;
			this.isFinal = false;
			transitions = new HashMap<>();
		}


		public boolean equals(State s)
		{
			if (s == null)
			{
				return false;
			}
			return s.getName() == name;
		}

		void setFinalStatus(boolean isFinal)
		{
			this.isFinal = isFinal;
		}

		Set<State> traverse(Character c)
		{
			Set<State> states = null;
			if (transitions.containsKey(c))
			{
				states = new HashSet<>(1);
				states.addAll(transitions.get(c));
			}
			if (transitions.containsKey('.'))
			{
				if (states == null)
				{
					states = new HashSet<>(1);
				}
				states.addAll(transitions.get('.'));
			}

			return doEpsilonTransitions(states);
		}

	public static Set<State> doEpsilonTransitions(Set<State> states)
	{
		if (states == null || states.size() == 0)
		{
			return null;
		}

		Set<State> st = new HashSet<>();
		for (State s: states)
		{
			doEpsilonTransitionsRecursive(s, st);
		}
		return st;
	}

	private static void doEpsilonTransitionsRecursive(State s, Set<State> st)
	{
		st.add(s);
		if (s.transitions.containsKey('_'))
		{
			for (State t: s.transitions.get('_'))
			{
				doEpsilonTransitionsRecursive(t, st);
			}
		}
	}

	void addEdge(Character c, State s)
		{

			if (transitions.containsKey(c))
			{
				transitions.get(c).add(s);
			}
			else
			{
				Set<State> st = new HashSet<>(1);
				st.add(s);
				transitions.put(c,st);
			}
		}

		boolean isFinal()
		{
			return isFinal;
		}

	public int getName()
	{
		return name;
	}
}
