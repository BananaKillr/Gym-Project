import javax.swing.*;

public class CustomerLoginPage extends JFrame{
    private JPanel mainPanel;
    private JTextField textField2;
    private JButton loginButton;
    private JButton backButton;
    private JPasswordField passwordField1;

    public void showForm(){
        JFrame frame = new JFrame("Customer Login Page");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}