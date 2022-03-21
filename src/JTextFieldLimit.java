import javax.swing.*;
import javax.swing.text.PlainDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import java.awt.event.KeyEvent;

public class JTextFieldLimit extends PlainDocument {
    private int limit;
    JTextField[] array;
    int indexOfCurrentTextField;

    JTextFieldLimit(int limit, JTextField[] array, int indexOfCurrentTextField) {
        super();
        this.limit = limit;
        this.array = array;
        this.indexOfCurrentTextField = indexOfCurrentTextField;
    }

    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) return;

        if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);
        }
    }

    public static void focusOnNextDisableTheRest(JTextField[] array, int indexOfCurrentTextField) {
            if (indexOfCurrentTextField != array.length - 1) {
                for (int i = 0; i <= array.length - 1; i++) {
                if (i != indexOfCurrentTextField + 1) {
                    array[i].setEditable(false);
                } else {
                    array[i].requestFocus();
                    array[i].setEditable(true);
                }
            }
                GUI.currentTF = indexOfCurrentTextField + 1;
        } else {
                array[indexOfCurrentTextField].requestFocus();
                array[indexOfCurrentTextField].setEditable(true);
                GUI.currentTF = indexOfCurrentTextField;
            }

    }

    public static void hopBackOneStep(JTextField[] array, int indexOfCurrentTextField) {
        if(indexOfCurrentTextField != 0){
            for (int i = 0; i <= array.length - 1; i++) {
                if (i != indexOfCurrentTextField - 1) {
                    array[i].setEditable(false);
                } else {
                    array[i].requestFocus();
                    array[i].setEditable(true);
                }
            }
            GUI.currentTF = indexOfCurrentTextField - 1;
        }
    }

}