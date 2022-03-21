import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WordHandler {
    public List<String> allWords = new ArrayList<>();
    public List<String> fiveLetterWords = new ArrayList<>();
    public List<String> finalWords = new ArrayList<>();

    public WordHandler() {
        readInAllWords();
        only5LetterWords();
        onlyAlphabeticalWords();
    }

    public void readInAllWords(){
        try {
            File myObj = new File("src/allWords.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                allWords.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

}

public void only5LetterWords(){
    for (String s: allWords) {
        if(s.length() == 5){
            fiveLetterWords.add(s);
        }
    }
}

public void onlyAlphabeticalWords(){
        boolean sutbleWord = true;
    for (String s: fiveLetterWords) {
        char[] charArray = new char[s.length()];

        for (int i = 0; i < s.length(); i++) {
            charArray[i] = s.charAt(i);
        }
        for (int i = 0; i < charArray.length; i++) {
            if(!Character.isAlphabetic(charArray[i])){
                sutbleWord = false;
            }
        }

        if(sutbleWord){
            finalWords.add(s);
        }

        sutbleWord = true;
    }
}

    public String chooseRandomWord(){
        System.out.println("Inside chooseRandomWord method");
        Random r = new Random();
        int low = 0;
        int high = finalWords.size();
       // int result = r.nextInt(high-low) + low;
        int result = (int) (Math.random() * (high - low)) + low;
        return finalWords.get(result);
    }

}
