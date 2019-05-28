package puzzle.substrnorep;

public class Main {

    public static void main(String[] args) {

        assert SubstringNoRep.lengthOfLongestSubstring("abcabcbb") == 3;
        assert SubstringNoRep.lengthOfLongestSubstring("cdefhgijkl") == 10;
        assert SubstringNoRep.lengthOfLongestSubstring("aabb") == 2;
        assert SubstringNoRep.lengthOfLongestSubstring("aabbccddeeff") == 2;
        assert SubstringNoRep.lengthOfLongestSubstring("abba") == 2;
    }
}
