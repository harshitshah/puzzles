package puzzle.regexmatch;

import java.util.*;


public class RegEx
{
    static final Character ANYCHAR = '.';

    public static void main(String[] args)
    {

        String input;
        String pattern;

        try(Scanner sc = new Scanner(System.in))
        {
            input = sc.next();
            pattern = sc.next();
        }

        if (isMatch(input, pattern))
        {
            System.out.println("Accept");
        }
        else
        {
            System.out.println("Reject");
        }
    }

    public static boolean isMatch(String s, String p)
    {
        if (p.length() == 0 )
        {
            if (s.length() == 0)
            {
                return true;
            }
            return false;
        }
        char[] input = s.toCharArray();
        printInput(input);
        char[] pattern = p.toCharArray();
        printInput(pattern);

        //create automata
        State start = new State(0);
        int stateCount = 1;
        System.out.println("Created state 0");
        State curState = start;
        int curPos = 0;
        int patternLength = pattern.length;
        while (true)
        {
            if (curPos < patternLength-1 && pattern[curPos+1] == '*')
            {
                System.out.println("Adding edge: from " + (stateCount-1) + " to " + stateCount + " on " + "_");
                State newState = new State(stateCount++);
                curState.addEdge('_', newState);
                System.out.println("Adding edge: from " + (stateCount-1) + " to itself on " + pattern[curPos]);
                newState.addEdge(pattern[curPos], newState);
                curState = newState;
                curPos += 2;
                if (curPos == patternLength)
                {
                    break;
                }
                continue;
            }

            System.out.println("Adding edge: from " + (stateCount-1) + " to " + stateCount + " on " + pattern[curPos]);
            State newState = new State(stateCount++);
            curState.addEdge(pattern[curPos], newState);
            curState = newState;
            curPos++;
            if (curPos == patternLength)
            {
                break;
            }

        }
        System.out.println("Setting " + (stateCount-1) + " as final state");
        curState.setFinalStatus(true);
        System.out.println("Num states: " + stateCount);

        //match input
        Set<State> states = new HashSet<>(1);
        states.add(start);
        states = State.doEpsilonTransitions(states);
        curPos = 0;
        for (char c : input)
        {
            System.out.println("Matching character " + c + " at position " + curPos);
            Set<State> newStates = new HashSet<>();
            for (State st: states)
            {
                Set<State> n = st.traverse(c);
                if (n != null)
                {
                    for (State nst: n)
                    {
                        System.out.println("adding target state: " + nst.getName());
                        newStates.add(nst);
                    }
                }
            }
            if (newStates.size() == 0)
            {
                return false;
            }
            states.clear();
            states = newStates;
            curPos++;
        }

        for (State st: states)
        {
            if (st.isFinal())
            {
                return true;
            }
        }
        return false;
    }

    private static void printInput(char[] input)
    {
        System.out.println("input length: " + input.length);
        for (char c: input)
        {
            System.out.print(c + " ");
        }
        System.out.println();
    }
}
