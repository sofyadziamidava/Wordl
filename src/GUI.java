import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUI extends JFrame implements KeyListener {
    public static JPanel panel = new JPanel();
    static JLabel[] labels = new JLabel[]{new JLabel("", SwingConstants.CENTER), new JLabel("", SwingConstants.CENTER), new JLabel("", SwingConstants.CENTER), new JLabel("", SwingConstants.CENTER), new JLabel("", SwingConstants.CENTER), new JLabel("", SwingConstants.CENTER),
            new JLabel("", SwingConstants.CENTER), new JLabel("", SwingConstants.CENTER), new JLabel("", SwingConstants.CENTER), new JLabel("", SwingConstants.CENTER), new JLabel("", SwingConstants.CENTER), new JLabel("", SwingConstants.CENTER), new JLabel("", SwingConstants.CENTER), new JLabel("", SwingConstants.CENTER), new JLabel("", SwingConstants.CENTER), new JLabel("", SwingConstants.CENTER), new JLabel("", SwingConstants.CENTER), new JLabel("", SwingConstants.CENTER),
            new JLabel("", SwingConstants.CENTER), new JLabel("", SwingConstants.CENTER), new JLabel("", SwingConstants.CENTER), new JLabel("", SwingConstants.CENTER), new JLabel("", SwingConstants.CENTER), new JLabel("", SwingConstants.CENTER),
            new JLabel("", SwingConstants.CENTER), new JLabel("", SwingConstants.CENTER), new JLabel("", SwingConstants.CENTER), new JLabel("", SwingConstants.CENTER), new JLabel("", SwingConstants.CENTER), new JLabel("", SwingConstants.CENTER)};
    private JTextField txtField1 = new JTextField("", SwingConstants.CENTER);
    private JTextField txtField2 = new JTextField("", SwingConstants.CENTER);
    private JTextField txtField3 = new JTextField("", SwingConstants.CENTER);
    private JTextField txtField4 = new JTextField("", SwingConstants.CENTER);
    private JTextField txtField5 = new JTextField("", SwingConstants.CENTER);
    private JTextField textFields[] = new JTextField[]{txtField1, txtField2, txtField3, txtField4, txtField5};
    private Dimension size;
    public static int currentTF = 0;
    public static int tryNumber = 0;
    private boolean tryCounts = false;
    public static boolean wordGuessed = false;
    public static boolean endGame = false;
    private Wordl wordl = new Wordl();
    private Font font;

    public GUI() {
        font = new Font("Serif", Font.ITALIC, 24);
        this.setSize(400, 500);
        this.setTitle("Wordl");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        createGUI();
        this.add(panel);
        this.setVisible(true);

    }

    public void createGUI() {
        panel.setLayout(new GridLayout(7, 5));
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        for (JLabel label : labels) {
            label.setOpaque(true);label.setBorder(border);
            label.setFont(font);
            panel.add(label);
            label.setSize(50, 50);
            size = label.getSize();
        }
        for (int i = 0; i < textFields.length; i++) {
           Border borderTF = BorderFactory.createLineBorder(Color.GRAY, 1);
            textFields[i].setSize(size);
            textFields[i].setBorder(borderTF);
            textFields[i].setDocument(new JTextFieldLimit(1, textFields, i));
            textFields[i].addKeyListener(this);
            textFields[i].setFont(font);
            panel.add(textFields[i]);
            if (i == 0) {
                textFields[i].requestFocus();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        Character c = e.getKeyChar();
        if (Character.isAlphabetic(c)) {
            textFields[currentTF].setText(c.toString());
            JTextFieldLimit.focusOnNextDisableTheRest(textFields, currentTF);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            String fromTextField = textFields[currentTF].getText();
            if (fromTextField.isEmpty() || fromTextField.isBlank()) {
                JTextFieldLimit.hopBackOneStep(textFields, currentTF);
            } else {
                textFields[currentTF].setText("");
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                String chars[] = new String[5];
                String testWord = "";
                for (int i = 0; i <= textFields.length - 1; i++) {
                    chars[i] = textFields[i].getText();
                }
                for (String temp : chars) {
                    if (!temp.isBlank() || !temp.isEmpty()) {
                        testWord = testWord + temp;
                    }
                }
                if (testWord.length() == 5) {
                    if (wordl.checkIfTestWordFits(testWord)) {
                        wordl.displayWord(testWord);
                        for (JTextField tf : textFields) {
                            tf.setText("");
                        }
                        JTextFieldLimit.focusOnNextDisableTheRest(textFields, -1);
                }
            }
                if(endGame){
                    for (JTextField tf: textFields) {
                        tf.setEditable(false);
                    }
                }
        }
    }
        @Override
        public void keyReleased (KeyEvent e){
        }

    }
