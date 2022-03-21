import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public class Wordl {
    private WordHandler wh;
    private final String guessedWord;

    public Wordl() {
        wh = new WordHandler();
        guessedWord = wh.chooseRandomWord();
    }

    public boolean checkIfTestWordFits(String testWord){
        for (String word: wh.finalWords) {
            if(testWord.equals(word)){
                return true;
            }
        }
        return false;
    }

    public void displayWord(String testWord) {
        char[] testWordArray = convertStringToCharArray(testWord);
        char[] guessedWordArray = convertStringToCharArray(guessedWord);

        for (int i = 0; i < testWordArray.length; i++) {
            if (testWordArray[i] == guessedWordArray[i]) {
                //Färga grönt
                Character temp = testWordArray[i];
                int index = findNextAvailableELabel();
                GUI.labels[index].setText(temp.toString());
                GUI.labels[index].setBackground(Color.green);
                GUI.panel.revalidate();
                GUI.panel.repaint();
            } else {
                for (int j = 0; j < guessedWordArray.length; j++) {
                    if (testWordArray[i] == guessedWordArray[j]) {
                        //Färga gult
                        Character temp = testWordArray[i];
                        int index = findNextAvailableELabel();
                        GUI.labels[index].setText(temp.toString());
                        GUI.labels[index].setBackground(Color.yellow);
                        GUI.panel.revalidate();
                        GUI.panel.repaint();
                        break;
                    } else if(testWordArray[i] != guessedWordArray[j] && j == guessedWordArray.length - 1){
                        //Färga grått
                        Character temp = testWordArray[i];
                        int index = findNextAvailableELabel();
                        GUI.labels[index].setText(temp.toString());
                        GUI.labels[index].setBackground(Color.lightGray);
                        GUI.panel.revalidate();
                        GUI.panel.repaint();
                    }
                }

            }
        }
        GUI.wordGuessed = checkIfWon(testWord, guessedWord);
        if(GUI.wordGuessed){
            GUI.endGame = true;
            JOptionPane.showMessageDialog(null, "Du vann! :)");
        } else if(GUI.tryNumber == 5){
            JOptionPane.showMessageDialog(null, "Du förlorade! :(" +
                    "\nDet rätta ordet var " + guessedWord);
            GUI.endGame = true;
        }
        GUI.tryNumber = GUI.tryNumber + 1;
    }
    public char[] convertStringToCharArray(String string){
        char[] charArray = new char[string.length()];

        for (int i = 0; i < string.length(); i++) {
            charArray[i] = string.charAt(i);
        }

        return charArray;
    }

    public int findNextAvailableELabel(){
        int result = 0;
        for (int i = 0; i < GUI.labels.length; i++) {
            if(GUI.labels[i].getText().isEmpty()){
                result = i;
                break;
            }
        }
        return result;
    }

    public boolean checkIfWon(String testWord, String guessedWord){
        if(testWord.equalsIgnoreCase(guessedWord)){
            return true;
        }
        return false;
    }
}
