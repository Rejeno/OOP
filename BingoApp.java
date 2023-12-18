import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class BingoApp extends JFrame {
    private static final String[] BINGO_LETTERS = { "B", "I", "N", "G", "O" };
    private List<JButton> bingoButtons;

    public BingoApp() {
        super("BINGO App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(430, 550);

        // Creating JPanel
        JPanel contentPane = new JPanel(new GridLayout(6, 5, 0, 0));
        contentPane.setBorder(new EmptyBorder(10, 20, 20, 20));
        contentPane.setBackground(new Color(225, 210, 120));

        setContentPane(contentPane);

        bingoButtons = new ArrayList<>();
        initializeBingoButtons();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeBingoButtons() {
        Map<String, List<Integer>> numberRanges = new HashMap<>();
        numberRanges.put("B", generateNumbersInRange(1, 15));
        numberRanges.put("I", generateNumbersInRange(16, 30));
        numberRanges.put("N", generateNumbersInRange(31, 45));
        numberRanges.put("G", generateNumbersInRange(46, 60));
        numberRanges.put("O", generateNumbersInRange(61, 75));

        for (int i = 0; i < 5; i++) {
            JLabel label = new JLabel(BINGO_LETTERS[i], SwingConstants.CENTER);
            label.setFont(new Font("Impact", Font.BOLD, 50));
            getContentPane().add(label);
        }

        for (int i = 0; i < 25; i++) {
            JButton button;
            if (i == 12) {
                button = new FreeButton("FREE");
                button.setBackground(new Color(225, 210, 10));
            } else {
                String letter = BINGO_LETTERS[i % 5];
                List<Integer> numbers = numberRanges.get(letter);
                button = new JButton(Integer.toString(numbers.get(i / 5)));
                button.addActionListener(new BingoButtonListener());
                button.setBackground(Color.WHITE);
                button.setFont(new Font("Times New Roman", Font.BOLD, 35));
            }
            button.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 2));

            bingoButtons.add(button);
            getContentPane().add(button);
        }
    }

    private List<Integer> generateNumbersInRange(int start, int end) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers;
    }

    private class BingoButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();
            Color originalColor = clickedButton.getBackground();

            if (originalColor.equals(Color.WHITE)) {
                clickedButton.setBackground(new Color(225, 210, 10));
            } else {
                clickedButton.setBackground(Color.WHITE);
            }
        }
    }

    private class FreeButton extends JButton {
        public FreeButton(String text) {
            super(text);
            setForeground(new Color(0, 102, 0));
            setFont(new Font("Times New Roman", Font.BOLD, 20));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BingoApp());
    }
}
