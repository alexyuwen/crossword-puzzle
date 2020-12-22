
import java.util.Scanner;
import java.util.Arrays;

public class Game {
	
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
		for (int i = 0; i < lettersOfWord.length; i++) {
			square[row - 1][placement - 1 + i] = lettersOfWord[i];
		}
		
		return square;
	}
	
	// goes through all possible ways of adding a word horizontally — if impossible, then it returns the original square
	public static char[][] tryHorizontal(int dimension, char[][] square, char[] lettersOfWord) {
		
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
		
		outerLoop:
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
		for (int i = 0; i < lettersOfWord.length; i++) {
			square[placement - 1 + i][column - 1] = lettersOfWord[i];
		}
		
		return square;
	}
	
	// goes through all possible ways of adding a word vertically — if impossible, then it returns the original square
	public static char[][] tryVertical(int dimension, char[][] square, char[] lettersOfWord) {
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
		
		outerLoop:
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
		for (int i = 0; i < lettersOfWord.length; i++) {
			square[row - 1 + i][placement - 1 + i] = lettersOfWord[i];
		}
		
		return square;
	}
	
	public static char[][] tryDownwardDiagonal(int dimension, char[][] square, char[] lettersOfWord) {
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
		
		outerLoop:
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
			for (int i = 0; i < lettersOfWord.length; i++) {
				square[row - 1 - i][placement - 1 + i] = lettersOfWord[i];
			}
			
			return square;
		}
		
		public static char[][] tryUpwardDiagonal(int dimension, char[][] square, char[] lettersOfWord) {
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
			
			outerLoop:
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
		
	// adds all words to the square — if it is unable to, it returns the original square with only stars
	public static char[][] addAllWords(String[] words, char[][] square) {
		
		char[][] updatedSquare = createSquare(square.length);
		
		String[][] wordsFwdAndBkwd = new String[2][words.length];
		wordsFwdAndBkwd[0] = words;
		wordsFwdAndBkwd[1] = reverseElements(words);
		
		for (int j = 0; j < words.length; j++) {
			int randomBinary;
			randomBinary = (int) (2 * Math.random());
			
			String word = wordsFwdAndBkwd[randomBinary][j];

		// Randomize word direction and placement
			char[] lettersOfWord = new char[word.length()];
			lettersOfWord = word.toCharArray();
			int[] possibleDirections = {1, 2, 3, 4};
			possibleDirections = randomShuffle(possibleDirections);
			
			for (int k = 0; k < possibleDirections.length; k++) { // try a different case if the first one(s) doesn't work

				switch (possibleDirections[k]) {
				
				// HORIZONTAL
				case 1:
					tryHorizontal(square.length, updatedSquare, lettersOfWord);
					if (Arrays.deepEquals(updatedSquare, square)) {
						char[] lettersOfWordBackwards = new char[word.length()];
						lettersOfWordBackwards = reverseSort(lettersOfWord);
						tryHorizontal(square.length, updatedSquare, lettersOfWordBackwards);
					}
					
					break;
					
				// VERTICAL
				case 2:
					tryVertical(square.length, updatedSquare, lettersOfWord);
					if (Arrays.deepEquals(updatedSquare, square)) {
						char[] lettersOfWordBackwards = new char[word.length()];
						lettersOfWordBackwards = reverseSort(lettersOfWord);
						tryVertical(square.length, updatedSquare, lettersOfWordBackwards);
					}
					
					break;
					
				// DOWNWARD DIAGONAL
				case 3:
					tryDownwardDiagonal(square.length, updatedSquare, lettersOfWord);
					if (Arrays.deepEquals(updatedSquare, square)) {
						char[] lettersOfWordBackwards = new char[word.length()];
						lettersOfWordBackwards = reverseSort(lettersOfWord);
						tryDownwardDiagonal(square.length, updatedSquare, lettersOfWordBackwards);
					}
					
					break;
					
				// UPWARD DIAGONAL
				case 4:
					tryUpwardDiagonal(square.length, updatedSquare, lettersOfWord);
					if (Arrays.deepEquals(updatedSquare, square)) {
						char[] lettersOfWordBackwards = new char[word.length()];
						lettersOfWordBackwards = reverseSort(lettersOfWord);
						tryUpwardDiagonal(square.length, updatedSquare, lettersOfWordBackwards);
					}
					
					break;
				
				} // end of switch block
				
				if (Arrays.deepEquals(updatedSquare, square) && k == 3) { // word cannot be added in any direction or placement
					return createSquare(square.length);
				} else if (! Arrays.deepEquals(updatedSquare, square)) {
					// set 'square' array equal to 'updatedSquare' array
					for (int i = 0; i < updatedSquare.length; i++) {
						square[i] = Arrays.copyOf(updatedSquare[i], square.length);
					}
					break; // out of inner for loop
				}
				
			}
		}
		return square;
	}
		
	public static String[] reverseElements(String[] words) { // keyword argument for addAllWords ?! // duplicate of char[] reverseSort, how to consolidate???
		String[] wordsBackwards = new String[words.length];
		wordsBackwards = Arrays.copyOf(words, words.length);
		for (int i = 0; i < words.length; i++) {
			String reversed = ""; 
			for (int j = 0; j < words[i].length(); j++) {
				reversed = reversed + words[i].charAt(words[i].length() - 1 - j);
			}
			
			wordsBackwards[i] = reversed;
		}
		
		return wordsBackwards;
		
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
	
	
	/*
	 *  MAIN METHOD
	 */
	public static void main(String[] args) {
		
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
		
		// copies and truncates the 'words' array, leaving off the final element 'q'
		words = Arrays.copyOf(words, numWords);
		// use at end of program to display words to user in the order that they inputted them
		String[] wordsToDisplay = Arrays.copyOf(words, numWords);
		
		// sort Array from longest to shortest words using bubble sort
		String[] wordsCopy;
		do {
			wordsCopy = Arrays.copyOf(words, numWords);
			for (int j = 0; j < numWords - 1; j++) {
				if (words[j].length() < words[j+1].length()) {
					String temp = words[j];
					words[j] = words[j+1];
					words[j+1] = temp;
				}
			}
		}
		while (! Arrays.equals(words, wordsCopy));
		
		// capitalizes all words
		for (int i = 0; i < words.length; i++) {
			words[i] = words[i].toUpperCase();
		}
		
		int dimension = 0;
		
/*		// Prompt user for dimensions, handle exceptions
		System.out.print("How many characters should be in each line? ");
		
		do {
		
			try {
				dimension = scanner.nextInt();
				if (dimension < Math.min(10, lengthOfLongest) || dimension > 25)
					throw new Exception("Out of range.");
				break; // exit while loop if no exception is thrown
			}
			catch (Exception excpt) {
				System.out.printf("Please enter a number between %d and 25: ", Math.min(10, lengthOfLongest));
				scanner.nextLine();
			}
//			catch (Throwable thrw) { // useful as last catch block as it catches all errors and exceptions
//				System.out.println("HI!");
//			}
			
		}
		while (true);
*/
		dimension = lengthOfLongest;
		
		char[][] square;
		char[][] updatedSquare;
		
		do {
			// create and populate 'square' and 'updatedSquare' 2D arrays with stars
			square = createSquare(dimension);
			updatedSquare = createSquare(dimension);
			updatedSquare = addAllWords(words, updatedSquare);
			
			if (!Arrays.deepEquals(updatedSquare, square)) {
			}
			
			// if dimension is too small to successfully add all words, increment dimension and try again
			if (Arrays.deepEquals(updatedSquare, square)) {
				dimension = dimension + 1;
			} else {
				square = updatedSquare;
				break; // out of do–while loop
			}
		}
		while (true);
		

		
		System.out.println("\n");
		// displaySquare(square);
		displaySquare(fillIn(square));
		System.out.println("\nWords: \n");
		System.out.println(Arrays.toString(wordsToDisplay));
		
	}

}
