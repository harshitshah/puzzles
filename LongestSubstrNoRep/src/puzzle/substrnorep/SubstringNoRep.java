package puzzle.substrnorep;

public class SubstringNoRep
{
	public static int lengthOfLongestSubstring(String s)
	{
		if (s == null || s.length() == 0)
		{
			return 0;
		}

		int[] pos = new int[256];
		for (int i = 0; i < 256; i++)
		{
			pos[i] = -1;
		}
		char[] input = s.toCharArray();

		int maxLen = -1;
		int startOff = 0;
		for (int i = 0; i < s.length(); i++)
		{
			int p = pos[input[i]];
			if (p != -1)
			{
				if (maxLen < i - startOff)
				{
					maxLen = i - startOff;
				}
				for (int j = startOff; j < p; j++)
				{
					pos[input[j]] = -1;
				}
				startOff = p + 1;
			}
			pos[input[i]] = i;
		}

		if (maxLen < s.length() - startOff)
		{
			maxLen = s.length() - startOff;
		}
		return maxLen;
	}
}
