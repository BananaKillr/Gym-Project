import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage {
    private JButton customerLoginButton;
    private JButton customerRegistrationButton;
    private JButton AdminLoginButton;
    private JButton CoachLoginButton;
    private JButton CoachRegistrationButton;
    private JPanel mainPanel;

    public LoginPage() {
        customerLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame mainFrame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
                mainFrame.setVisible(false);

                CustomerLoginPage customerLoginPage = new CustomerLoginPage();
//                customerLoginPage.setVisible(true);
//                customerLoginPage.pack();
//                customerLoginPage.setLocationRelativeTo(null);
//                customerLoginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                customerLoginPage.showForm();
            }
        });
        CoachLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame mainFrame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
                mainFrame.setVisible(false);

                CoachLogin coachLogin = new CoachLogin();
                coachLogin.showForm();
            }
        });
        AdminLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame mainFrame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
                mainFrame.setVisible(false);

                AdminLogin adminLogin = new AdminLogin();
                adminLogin.showForm();
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("LoginPage");
        frame.setContentPane(new LoginPage().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}