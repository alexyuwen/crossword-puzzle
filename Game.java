package crossword;

import java.util.Scanner;
import java.util.Arrays;


public class Game {
	
	interface Direction {
		char[][] test(char[] letters);
	}
	
	static class Horizontal {
		
		// goes through all possible ways of adding a word horizontally — if impossible, then it returns the original square
		public char[][] tryHorizontal(int dimension, char[][] square, char[] lettersOfWord) {
			
			int[] possibleRows = new int[dimension];
			int[] possiblePlacements = new int[dimension - lettersOfWord.length + 1]; // columns that word can start in
			
			for (int l = 1; l <= possibleRows.length; l++) {
				possibleRows[l - 1] = l;
			}
			
			for (int m = 1; m <= possiblePlacements.length; m++) {
				possiblePlacements[m - 1] = m;
			}
			
			possibleRows = randomShuffle(possibleRows);
			possiblePlacements = randomShuffle(possiblePlacements);
			
			for (int a : possibleRows) {
				for (int b : possiblePlacements) {
					if (isHorizontalPossible(lettersOfWord, square, a, b)) {
						square = addHorizontal(lettersOfWord, square, a, b);
						return square;
					}
				}
			}
			return square;
		}
		
	}
	
	static class Vertical {
		
		// goes through all possible ways of adding a word vertically — if impossible, then it returns the original square
		public char[][] tryVertical(int dimension, char[][] square, char[] lettersOfWord) {
			int[] possibleColumns = new int[dimension];
			int[] possiblePlacements = new int[dimension - lettersOfWord.length]; // row that word can start in
			
			for (int l = 1; l <= possibleColumns.length; l++) {
				possibleColumns[l - 1] = l;
			}
			
			for (int m = 1; m <= possiblePlacements.length; m++) {
				possiblePlacements[m - 1] = m;
			}
			
			possibleColumns = randomShuffle(possibleColumns);
			possiblePlacements = randomShuffle(possiblePlacements);
			
			for (int a : possibleColumns) {
				for (int b : possiblePlacements) {
					if (isVerticalPossible(lettersOfWord, square, a, b)) {
						square = addVertical(lettersOfWord, square, a, b);
						return square;
					}
				}
			}
			return square;
		}
		
	}
	
	static class DownwardDiagonal {
		
		public char[][] tryDownwardDiagonal(int dimension, char[][] square, char[] lettersOfWord) {
			int[] possibleRows = new int[dimension - lettersOfWord.length + 1]; // row that word can start in
			int[] possiblePlacements = new int[dimension - lettersOfWord.length + 1]; // column that word can start in
			
			for (int l = 1; l <= possibleRows.length; l++) {
				possibleRows[l - 1] = l;
			}
			
			for (int m = 1; m <= possiblePlacements.length; m++) {
				possiblePlacements[m - 1] = m;
			}
			
			possibleRows = randomShuffle(possibleRows);
			possiblePlacements = randomShuffle(possiblePlacements);
			
			for (int a : possibleRows) {
				for (int b : possiblePlacements) {
					if (isDownwardDiagonalPossible(lettersOfWord, square, a, b)) {
						square = addDownwardDiagonal(lettersOfWord, square, a, b);
						return square;
					}
				}
			}
			return square;
		}
		
	}
	
	static class UpwardDiagonal {
		
		public char[][] tryUpwardDiagonal(int dimension, char[][] square, char[] lettersOfWord) {
			int[] possibleRows = new int[dimension - lettersOfWord.length + 1]; // row that word can start in
			int[] possiblePlacements = new int[dimension - lettersOfWord.length + 1]; // column that word can start in
			
			for (int l = dimension; l >= lettersOfWord.length; l--) {
				possibleRows[dimension - l] = l;
			}
			
			for (int m = 1; m <= possiblePlacements.length; m++) {
				possiblePlacements[m - 1] = m;
			}
			
			possibleRows = randomShuffle(possibleRows);
			possiblePlacements = randomShuffle(possiblePlacements);
			
			for (int a : possibleRows) {
				for (int b : possiblePlacements) {
					if (isUpwardDiagonalPossible(lettersOfWord, square, a, b)) {
						square = addUpwardDiagonal(lettersOfWord, square, a, b);
						return square;
					}
				}
			}
			return square;
		}
		
	}
	
	static Horizontal HORIZONTAL = new Horizontal();
	static Vertical VERTICAL = new Vertical();
	static DownwardDiagonal DOWNWARDDIAGONAL = new DownwardDiagonal();
	static UpwardDiagonal UPWARDDIAGONAL = new UpwardDiagonal();
	
	
	
	/**
	 * Methods of the 'Game' class
	 */
	
	
	// randomly shuffles an array
	public static int[] randomShuffle(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int randomIndex = (int) (arr.length * Math.random());
			int temp = arr[i];
			arr[i] = arr[randomIndex];
			arr[randomIndex] = temp;
		}
		
		return arr;
	}
	
	// reverse the order of an array
	public static char[] reverseSort(char[] arr) {
		char[] result = new char[arr.length];
		for (char i = 0; i < arr.length; i++) {
			result[i] = arr[arr.length - 1 - i];
		}
		return result;
	}
	
	// initialize each element with '*'
	public static char[][] createSquare(int dimension) {
		char[][] square = new char[dimension][dimension];
		for (int x = 0; x < dimension; x++) {
			for (int y = 0; y < dimension; y++) {
				square[x][y] = '*';
			}
		}
		return square;
	}
	
	// determines if it is possible to add a word horizontally at a specific row and column placement
	public static boolean isHorizontalPossible(char[] lettersOfWord, char[][] square, int row, int placement) {
		for (int i = 0; i < lettersOfWord.length; i++) {
			char ch = square[row - 1][placement - 1 + i];
			if (ch != '*' && ch != lettersOfWord[i]) {
				return false;
			}
		}
		return true;
	}
	
	// add a word horizontally
	public static char[][] addHorizontal(char[] lettersOfWord, char[][] square, int row, int placement) {
		for (int i = 0; i < lettersOfWord.length; i++)
			square[row - 1][placement - 1 + i] = lettersOfWord[i];
		return square;
	}
	
	// determines if it is possible to add a word vertically at a specific row and column placement
	public static boolean isVerticalPossible(char[] lettersOfWord, char[][] square, int column, int placement) {
		for (int i = 0; i < lettersOfWord.length; i++) {
			char ch = square[placement - 1 + i][column - 1];
			if (ch != '*' && ch != lettersOfWord[i]) {
				return false;
			}
		}
		return true;
	}
	
	// add a word vertically
	public static char[][] addVertical(char[] lettersOfWord, char[][] square, int column, int placement) {
		for (int i = 0; i < lettersOfWord.length; i++)
			square[placement - 1 + i][column - 1] = lettersOfWord[i];
		return square;
	}
	
	// determines if it is possible to add a word downward diagonally (top left to bottom right) at a specific row and column placement
	public static boolean isDownwardDiagonalPossible(char[] lettersOfWord, char[][] square, int row, int placement) {
		for (int i = 0; i < lettersOfWord.length; i++) {
			char ch = square[row - 1 + i][placement - 1 + i];
			if (ch != '*' && ch != lettersOfWord[i]) {
				return false;
			}
		}
		return true;
	}
		
	// add a word downward diagonally
	public static char[][] addDownwardDiagonal(char[] lettersOfWord, char[][] square, int row, int placement) {
		for (int i = 0; i < lettersOfWord.length; i++)
			square[row - 1 + i][placement - 1 + i] = lettersOfWord[i];
		return square;
	}
	
	
	// determines if it is possible to add a word upward diagonally (bottom left to top right) at a specific row and column placement
		public static boolean isUpwardDiagonalPossible(char[] lettersOfWord, char[][] square, int row, int placement) {
			for (int i = 0; i < lettersOfWord.length; i++) {
				char ch = square[row - 1 - i][placement - 1 + i];
				if (ch != '*' && ch != lettersOfWord[i]) {
					return false;
				}
			}
			return true;
		}
			
		// add a word upward diagonally
		public static char[][] addUpwardDiagonal(char[] lettersOfWord, char[][] square, int row, int placement) {
			for (int i = 0; i < lettersOfWord.length; i++)
				square[row - 1 - i][placement - 1 + i] = lettersOfWord[i];
			return square;
		}
		
	// adds all words to the square — if it is unable to, it returns the original square with only stars
	public static char[][] addAllWords(String[] words, char[][] square, int dimension) {
		
		char[][] updatedSquare = createSquare(square.length);
		
		for (int j = 0; j < words.length; j++) { // for each word in list
			
			String word = words[j];

			// array of anonymous inner classes
			Direction[] tryDirections = {
					new Direction() { public char[][] test(char[] letters) { 
						return HORIZONTAL.tryHorizontal(dimension, updatedSquare, letters); } },
					new Direction() { public char[][] test(char[] letters) { 
						return VERTICAL.tryVertical(dimension, updatedSquare, letters); } },
					new Direction() { public char[][] test(char[] letters) { 
						return DOWNWARDDIAGONAL.tryDownwardDiagonal(dimension, updatedSquare, letters); } },
					new Direction() { public char[][] test(char[] letters) { 
						return UPWARDDIAGONAL.tryUpwardDiagonal(dimension, updatedSquare, letters); } }
				};
			
			// Randomize word direction
			int[] possibleDirections = {0, 1, 2, 3};
			possibleDirections = randomShuffle(possibleDirections);
			
			// Randomize forward/backward
			char[][] letters = new char[2][word.length()];
			letters[0] = word.toCharArray();
			letters[1] = reverseSort(letters[0]);
			int randomBinary;
			
			for (int k = 0; k < possibleDirections.length; k++) { // try a different case if the first one(s) doesn't work
				
				randomBinary = (int) (2 * Math.random());
				tryDirections[possibleDirections[k]].test(letters[randomBinary]);
				
				if (Arrays.deepEquals(updatedSquare, square)) // try adding the word reversed if it didn't fit
					tryDirections[possibleDirections[k]].test(letters[randomBinary ^= 1]); // uses XOR operator to flip the boolean value
				
				if (Arrays.deepEquals(updatedSquare, square) && k == 3) { // All directions and placements have been exhausted, and the word couldn't be added
					return createSquare(square.length);
				} else if (! Arrays.deepEquals(updatedSquare, square)) {
					// set 'square' array equal to 'updatedSquare' array
					for (int i = 0; i < updatedSquare.length; i++) {
						square[i] = Arrays.copyOf(updatedSquare[i], square.length);
					}
					break; // out of inner for-loop
				}
				
			} // end inner for-loop
			
		} // end outer for-loop
		
		return square;
	}
	
	// fills in rest of square with random letters
	public static char[][] fillIn(char[][] square) {
		char randomUnicode;
		for (int i = 0; i < square.length; i++) {
			for (int j = 0; j < square.length; j++) {
				if (square[i][j] == '*') {
					randomUnicode = (char) ((int) (26 * Math.random()) + 65);
					square[i][j] = randomUnicode;
				}
			}
		}
		return square;
	}
	
	// prints square
	public static void displaySquare(char[][] square) {
		for (char[] row : square) {
			for (char ch : row) {
				System.out.print(ch + "  ");
			}
			System.out.println("");
		}
	}
	
	// sorts an array of words in order of decreasing word length
	private static void mergeSort(String[] words) {
		String[] temp = new String[words.length];
		mergeSort(words, temp, 0, words.length - 1);	
	}
	
	// overloaded method
	private static void mergeSort(String[] words, String[] temp, int leftStart, int rightEnd) {
		if (leftStart >= rightEnd) // base case
			return;
		int middle = leftStart + (rightEnd - leftStart) / 2;
		mergeSort(words, temp, leftStart, middle);
		mergeSort(words, temp, middle + 1, rightEnd);
		mergeHalves(words, temp, leftStart, rightEnd);
	}
	
	private static void mergeHalves(String[] words, String[] temp, int leftStart, int rightEnd) {
		int leftEnd = leftStart + (rightEnd - leftStart) / 2;
		int left = leftStart;
		int right = leftEnd + 1;
		int index = leftStart;
		
		while (left <= leftEnd && right <= rightEnd) {
			if (words[left].length() > words[right].length()) {
				temp[index] = words[left];
				left++;
			}
			else {
				temp[index] = words[right];
				right++;
			}
			index++;
		}
		
		System.arraycopy(words, left, temp, index, leftEnd - left + 1);
		System.arraycopy(words, right, temp, index, rightEnd - right + 1);
		System.arraycopy(temp, leftStart, words, leftStart, rightEnd - leftStart + 1);
	}
	
	public static void main(String[] args) {
		String[] arr = {"cat", "hi", "balloon", "tiger", "p", "mmmmmmmmmm"};
		mergeSort(arr);
		System.out.println(Arrays.toString(arr));
		
		System.out.println("* * * CROSSWORD PUZZLE * * * \n");
		
		Scanner scanner = new Scanner(System.in);
		
		// USE FOR DEBUGGING String[] words = {"aaaaaa", "bbbbbb", "cccccc", "dddddd", "eeeeeee", "ffffff", "gggggg", "hhhhhh", "iiiiiiii", "jjjjjjjj", "kkkkkkk"};
 		String[] words = new String[100];
		int numWords = 0;
		int lengthOfLongest = 0;
		
		// Prompts user to input words
		System.out.println("Enter all the words you want in your puzzle. When you are done, enter the letter 'q.'");
		
		for (int i = 0; i < words.length; i++) {
			words[i] = scanner.next();
			if (words[i].equals("q")) {
				numWords = i;
				break;
			} else if (words[i].length() > lengthOfLongest) {
				lengthOfLongest = words[i].length();
			}
		}
		scanner.close();
		
		// copies and truncates the 'words' array, leaving off the final element 'q'
		words = Arrays.copyOf(words, numWords);
		// use at end of program to display words to user in the order that they were inputed
		String[] wordsToDisplay = Arrays.copyOf(words, numWords);
		
		// sort 'words' array from longest to shortest words using mergesort
		mergeSort(words);
		
		// capitalizes all words
		for (int i = 0; i < words.length; i++) {
			words[i] = words[i].toUpperCase();
		}
		
		int dimension = lengthOfLongest;
		
		char[][] square;
		char[][] updatedSquare;
		
		do {
			// create and populate 'square' and 'updatedSquare' 2D arrays with stars
			square = createSquare(dimension);
			updatedSquare = createSquare(dimension);
			updatedSquare = addAllWords(words, updatedSquare, dimension);
			
			if (!Arrays.deepEquals(updatedSquare, square)) {
			}
			
			// if dimension is too small to fit all words, increment dimension and try again
			if (Arrays.deepEquals(updatedSquare, square)) {
				dimension++;
			} else {
				square = updatedSquare;
				break; // out of do–while loop
			}
		}
		while (true);
		

		// Print crossword puzzle along with list of words
		System.out.println("\n");
		displaySquare(fillIn(square));
		System.out.println("\nWords: \n");
		System.out.println(Arrays.toString(wordsToDisplay));
		
	}

}
