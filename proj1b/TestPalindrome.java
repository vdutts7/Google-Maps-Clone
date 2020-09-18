import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static OffByOne offByOne = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        String palindromeWord = "noon";
        String nonPalindromeWord = "pizza";
        String cornerCase1 = "f";
        String cornerCase2 = "";

        assertTrue(palindrome.isPalindrome(palindromeWord));
        assertFalse(palindrome.isPalindrome(nonPalindromeWord));
        assertTrue(palindrome.isPalindrome(cornerCase1));
        assertTrue(palindrome.isPalindrome(cornerCase2));
    }

    @Test
    public void testIsPalindromeCC() {
        String palindromeWordCC = "mopn";
        String nonPalindromeWordCC = "noon";
        String cornerCase1 = "f";
        String cornerCase2 = "";

        assertTrue(palindrome.isPalindrome(palindromeWordCC, offByOne));
        assertFalse(palindrome.isPalindrome(nonPalindromeWordCC, offByOne));
        assertTrue(palindrome.isPalindrome(cornerCase1, offByOne));
        assertTrue(palindrome.isPalindrome(cornerCase1, offByOne));
    }
}
