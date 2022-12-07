import java.io.File;
import java.io.IOException;
import java.util.Scanner;
/** Melvin Berkoh
 * Professor Bamford
 * 10 May 2022
 *Data Structures 
 */

public class Main
{
    private static BinarySearchTree<Character> tree = new BinarySearchTree<>();
    
    public static void main(String[] args) throws IOException 
    {  
    	// Add the root to start the tree (‘*’)
        Scanner morseSetUpSc = new Scanner(new File("MORSECODE"));
        tree.add('*');
        //  While loop that reads until end of file has been reached 
       //  (use hasNext method of Scanner class)
  
        while (morseSetUpSc.hasNext())
        {
        // Use next method of Scanner to read 2 individual Strings
        //  Store the character in a Character object by getting charAt(0)
            Character letter = morseSetUpSc.next().charAt(0);
            String morseLetter = morseSetUpSc.next();
        //  Add to morsecode tree using overloaded add method was added(pass 0 to first)
            tree.add(letter, 0, morseLetter);
        }
        morseSetUpSc.close();
        Scanner reader = new Scanner(new File("TESTMORSECODE"));
        while (reader.hasNext()) 
        {
            String line = reader.nextLine();
            System.out.println("Code to Decode: " + line);
            System.out.println("Decoded Sentence: " + decodeSentence(line));
        }
        reader.close();
    }
    public static String decodeSentence(String sentence) 
    {
        String decodedSentence = "";
        int numWords = countWords(sentence);
        String[] words = new String[numWords];
        separateWords(words, sentence);
        for (int i = 0; i < words.length; i++) 
        {
            String word = words[i];
            int numLetters = countSpaces(word);
            String[] codes = new String[numLetters];
            separateLetters(codes, word);
            decodedSentence += decodeWord(codes) + " ";
        }
        return decodedSentence.substring(0, decodedSentence.length() -1);
    }


    public static Character decodeLetter(String code)
    {
        Character letter;
        /*
        get the root of morsecode tree and store in a BinarySearchTreeNode object
        loop for the length of the code
        if the charAt current spot of code is a ‘.’ Then go left (getLeft()) store in
        root */
        BinarySearchTreeNode<Character> node = tree.getRoot();
        for (int i = 0; i < code.length(); i++)
        {
       //if the charAt current spot of code is a ‘-‘ Then go right(getRight()) store in root
            if (code.charAt(i) == '.') 
            {
                node = node.getLeft();
            } else 
            {  
                node = node.getRight();
            }
        }
       // store the letter of the code (getInfo())
        letter = node.getInfo();
        return letter;
    }

    public static String decodeWord(String[] codes) 
    {
    	// word stores each decoded letter (from morsecode to english). 
    	//it's empty because nothing has been decoded yet.
        String word = "";
        // for each morsecode-letter, decode and add it to `word`
        for (int i = 0; i < codes.length; i++) 
        {
            word += decodeLetter(codes[i]);
        }
        // return decoded word
        return word;
    }
    public static void separateLetters(String[] letters, String word) 
    {
    	// where to store the current morsecode-letter.
        String code = "";
        int count = 0;
        // iterating over the word
        for (int i = 0; i < word.length(); i++)
        {
            char c = word.charAt(i);
            // if the current character is a space, the current morsecode-letter ended.
            // the settings for the next morsecode-letter by 1: assign `code` 
            //to `letters[count]` 2: resetting `code` 3: updating the index of `letters` at which the index.
            if (c == ' ') 
            {
                letters[count] = code;
                code = "";
                count++;
            } // continue adding each morsecode-character to the morsecode-letter
            else 
            {
                code += c;
            }
        }
        letters[count] = code;
    }
    public static void separateWords(String[] words, String sentence)
    {
            String word = "";
            int count = 0;
            for (int i = 0; i < sentence.length(); i++) 
        {
            char c = sentence.charAt(i);
            if (c == '/') 
            {
                words[count] = word;
                word = "";
                count++;
            } else {
                word += c;
            }
        }
        words[count] = word;
    }

    public static int countSpaces(String phrase)
    {
        int count = 0;
        for(int i = 0; i < phrase.length(); i++)
        {
            if(phrase.charAt(i) == ' ')
                count++;
        }
        return count+1; //add one more to count the last letter
    }
    public static int countWords(String phrase) 
    {
        int count = 0;
        for(int i = 0; i < phrase.length(); i++)
        {
            if(phrase.charAt(i) == '/')
                count++;
        }
        return count+1; //add one more for the last word
    }
}

/**
 Your message to us
 Code to Decode: .... . .-.. .-.. ---/-.-. .-.. .- ... ...
 
Decoded Sentence: HELLO CLASS
Code to Decode: -.-. --- -. --. .-. .- - ..- .-.. .- - .. --- -. ...
Decoded Sentence: CONGRATULATIONS
Code to Decode: -.-- --- ..-/.... .- ...- ./... ..- -.-. -.-. . ... ... ..-. ..- .-.. .-.. -.--/-.. . -.-. --- -.. . -../-- --- .-. ... ./-.-. --- -.. .
Decoded Sentence: YOU HAVE SUCCESSFULLY DECODED MORSE CODE

 My message to you
  - .... .- -. -.-/-.-- --- ..-/..-. --- .-./.- -./.- -- .- --.. .. -. --./-.-- . .- .-./.--. .-. --- ..-. . ... ... --- .-./-... .- -- ..-. --- .-. -..
 
-.-- --- ..-/.... .- ...- ./-... . . -./... ..- -.-. ..../--. --- --- -../.... . .-.. .--.
output
Code to Decode: - .... .- -. -.-/-.-- --- ..-/..-. --- .-./.- -./.- -- .- --.. .. -. --./-.-- . .- .-./.--. .-. --- ..-. . ... ... --- .-./-... .- -- ..-. --- .-. -..
Decoded Sentence: THANK YOU FOR AN AMAZING YEAR PROFESSOR BAMFORD
Code to Decode: -.-- --- ..-/.... .- ...- ./-... . . -./... ..- -.-. ..../--. --- --- -../.... . .-.. .--.
Decoded Sentence: YOU HAVE BEEN SUCH GOOD HELP*/