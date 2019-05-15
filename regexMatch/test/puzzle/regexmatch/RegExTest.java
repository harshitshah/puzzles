package puzzle.regexmatch;
import static puzzle.regexmatch.RegEx.isMatch;

public class RegExTest
{

	public static void main(String args[])
	{
		assert isMatch("mississippi", "mis*is*p*.") == false;
		assert isMatch("mississi", "mis*is*p*.") == true;
		assert isMatch("mississpi", "mis*is*p*.") == true;
		assert isMatch("aab", "c*a*b") == true;

	}
}
