import javax.swing.*;
import java.awt.*;
import java.io.File;

public class RecursiveListerFrame extends JFrame {
    private JTextArea fileDisplay;
    private JButton startButton;
    private JButton quitButton;

    public RecursiveListerFrame() {
        setTitle("Recursive File Lister");
        setLayout(new BorderLayout());
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fileDisplay = new JTextArea();
        fileDisplay.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(fileDisplay);

        startButton = new JButton("Start");
        quitButton = new JButton("Quit");

        startButton.addActionListener(e -> openDirectoryChooser());
        quitButton.addActionListener(e -> System.exit(0));

        JPanel panel = new JPanel();
        panel.add(startButton);
        panel.add(quitButton);

        add(new JLabel("Select Directory to List Files:"), BorderLayout.NORTH);
        add(panel, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void openDirectoryChooser() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int option = chooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = chooser.getSelectedFile();
            fileDisplay.setText("");  // Clear previous output
            listFilesRecursive(selectedDirectory);
        }
    }

    private void listFilesRecursive(File directory) {
        if (directory.isDirectory()) {
            for (File file : directory.listFiles()) {
                if (file.isDirectory()) {
                    fileDisplay.append("Directory: " + file.getAbsolutePath() + "\n");
                    listFilesRecursive(file);  // Recurse into sub-directory
                } else {
                    fileDisplay.append("File: " + file.getAbsolutePath() + "\n");
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RecursiveListerFrame().setVisible(true);
        });
    }
}
