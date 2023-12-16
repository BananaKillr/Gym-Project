import javax.swing.*;

public class AdminLogin {
    private JTextField textField2;
    private JButton loginButton;
    private JButton backButton;
    private JPasswordField passwordField1;
    private JPanel mainPanel;

    public void showForm(){
        JFrame frame = new JFrame("Coach Login Page");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
