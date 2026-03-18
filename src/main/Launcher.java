package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URL;

public class Launcher {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Launcher::createAndShowGui);
    }

    private static void createAndShowGui() {
        JFrame launcherFrame = new JFrame("Chess Launcher");
        launcherFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        launcherFrame.setLayout(new BorderLayout(10, 10));
        launcherFrame.setResizable(false);

        ImageIcon kingIcon = loadKingIcon();
        JLabel iconLabel = new JLabel("", kingIcon, JLabel.CENTER);
        iconLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel text = new JLabel("Chess by Jayden😂", JLabel.CENTER);
        text.setFont(text.getFont().deriveFont(Font.BOLD, 18f));

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener((ActionEvent e) -> {
            launcherFrame.dispose();
            SwingUtilities.invokeLater(() -> {
                try {
                    openGame();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Failed to start game: " + ex, "Launch Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        });
        startButton.setPreferredSize(new Dimension(180, 40));

        JPanel centerPanel = new JPanel(new BorderLayout(5, 5));
        centerPanel.add(text, BorderLayout.NORTH);
        centerPanel.add(iconLabel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.add(startButton);

        launcherFrame.add(centerPanel, BorderLayout.CENTER);
        launcherFrame.add(southPanel, BorderLayout.SOUTH);

        launcherFrame.pack();
        launcherFrame.setLocationRelativeTo(null);
        launcherFrame.setVisible(true);
    }

    private static void openGame() {
        JFrame window = new JFrame("Chess");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        GamePanel gp = new GamePanel();
        window.add(gp);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gp.launchGame();
    }

    private static ImageIcon loadKingIcon() {
        String[] candidatePaths = {
            "/res/piece/king.png",
            "res/piece/king.png",
            "res/piece/king1.png",
            "piece/king.png"
        };

        for (String path : candidatePaths) {
            URL resource = Launcher.class.getResource(path);
            if (resource != null) {
                ImageIcon icon = new ImageIcon(resource);
                return scaleIcon(icon, 128, 128);
            }

            File f = new File(path);
            if (f.exists()) {
                ImageIcon icon = new ImageIcon(f.getAbsolutePath());
                return scaleIcon(icon, 128, 128);
            }
        }

        // Fallback: simple placeholder
        Icon placeholder = UIManager.getIcon("OptionPane.informationIcon");
        if (placeholder instanceof ImageIcon) {
            return scaleIcon((ImageIcon) placeholder, 128, 128);
        }

        return new ImageIcon();
    }

    private static ImageIcon scaleIcon(ImageIcon original, int width, int height) {
        if (original == null || original.getImage() == null) {
            return new ImageIcon();
        }
        Image scaled = original.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }
}
