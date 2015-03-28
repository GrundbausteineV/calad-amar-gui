package util.codegeneration;

public class CodeGenerator {
	/** Minimum length for a decent password */
	public static final int MIN_LENGTH = 10;

	/** The random number generator. */
	protected static java.util.Random r = new java.util.Random();

	/*
	 * Set of characters that is valid. Must be printable, memorable, and "won't
	 * break HTML" (i.e., not ' <', '>', '&', '=', ...). or break shell commands
	 * (i.e., not ' <', '>', '$', '!', ...). I, L and O are good to leave out,
	 * as are numeric zero and one.
	 */
	protected static char[] largeChar = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'K',
		'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
		'2', '3', '4', '5', '6', '7', '8', '9', };
	protected static char[] smallChar = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'K',
		'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
		'2', '3', '4', '5', '6', '7', '8', '9', };
	protected static char[] mixedChar = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'K',
		'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z','a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'k',
		'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
		'2', '3', '4', '5', '6', '7', '8', '9', };

	/* Generate a Password object with a random password. */
	public static String getNext() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < MIN_LENGTH; i++) {
			sb.append(mixedChar[r.nextInt(mixedChar.length)]);
		}
		return sb.toString();
	}

	/* Generate a Password object with a random password. */
	public static String getNext(int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append(mixedChar[r.nextInt(mixedChar.length)]);
		}
		return sb.toString();
	}
}
