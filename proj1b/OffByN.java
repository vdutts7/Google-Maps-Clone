public class OffByN implements CharacterComparator {
    private int n;

    public OffByN(int N) {
        n = N;
    }

    /** Returns true given two characters (as parameters) if
     * the characters are off by N from each other.
     */
    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == n;
    }
}
