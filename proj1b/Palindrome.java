public class Palindrome {

    /** Helper function that given a String paramater. Returns
     *  a Deque where the characters appear in the same order
     * as in the String.
     */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> newDeque = new LinkedListDeque<>();
        for (int j = 0; j < word.length(); j++) {
            newDeque.addLast(word.charAt(j));
        }
        return newDeque;
    }

    /** Returns boolean indicating if  given paramater word is palindrome.
     * Note: Any word of length 1 or 0 is a palindrome.
     * Note: If word paramater is null, returns false.
     */
    public boolean isPalindrome(String word) {
        if (word == null) {
            return false;
        }
        Deque<Character> tempDeque = wordToDeque(word);
        return isPalindromeHelper(tempDeque);
    }

    /** Helper method for isPalindrome() */
    private boolean isPalindromeHelper(Deque<Character> tempDeque) {
        if (tempDeque.isEmpty() || tempDeque.size() == 1) {
            return true;
        } else {
            Character firstChar = tempDeque.removeFirst();
            Character lastChar = tempDeque.removeLast();

            if (firstChar == lastChar) {
                return isPalindromeHelper(tempDeque);
            } else {
                return false;
            }
        }
    }

    /** Returns return true if the word is a palindrome
     * according to the character comparison test provided by
     * CharacterComparator object, the paramater cc.
     * Note: Any word of length 1 or 0 is a palindrome.
     * Note: If word paramater is null, returns false.
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null) {
            return false;
        }
        Deque<Character> tempDeque = wordToDeque(word);
        return isPalindromeHelper(tempDeque, cc);
    }

    /** Helper method for overloaded isPalindrome() with
     * character comparison test.
     */
    private boolean isPalindromeHelper(Deque<Character> tempDeque, CharacterComparator cc) {
        if (tempDeque.isEmpty() || tempDeque.size() == 1) {
            return true;
        } else {
            Character firstChar = tempDeque.removeFirst();
            Character lastChar = tempDeque.removeLast();

            if (cc.equalChars(firstChar, lastChar)) {
                return isPalindromeHelper(tempDeque, cc);
            } else {
                return false;
            }
        }
    }
}
