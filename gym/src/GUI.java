import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private JPanel ParentPanel;
    private JPanel MainPage;
    private JButton coachLoginButton;
    private JButton coachRegistrationButton; //TODO Add registration for coach & customer
    private JButton adminLoginButton;
    private JButton customerRegistrationButton;
    private JButton customerLoginButton;
    private JPanel CustomerLoginPage;
    private JButton returnButton;
    private JButton loginButtonCustomer;
    private JTextField customerUsernameField;
    private JPasswordField customerPasswordField;
    private JPanel AdminLoginPage;
    private JButton loginButtonAdmin;
    private JTextField adminUsernameField;
    private JPasswordField adminPasswordField;
    private JButton returnButton1;
    private JPanel CoachLoginPage;
    private JButton returnButton2;
    private JTextField coachLoginField;
    private JPasswordField coachLoginPassword;
    private JButton loginButtonCoach;

    public GUI() {
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception a){
            System.out.println("Look And Feel Error");
        }
        customerLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ParentPanel.removeAll();
                ParentPanel.add(CustomerLoginPage);
                ParentPanel.revalidate();
                ParentPanel.repaint();
            }
        });
        adminLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ParentPanel.removeAll();
                ParentPanel.add(AdminLoginPage);
                ParentPanel.revalidate();
                ParentPanel.repaint();
            }
        });
        coachLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ParentPanel.removeAll();
                ParentPanel.add(CoachLoginPage);
                ParentPanel.revalidate();
                ParentPanel.repaint();
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ParentPanel.removeAll();
                ParentPanel.add(MainPage);
                ParentPanel.revalidate();
                ParentPanel.repaint();
            }
        });
        returnButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ParentPanel.removeAll();
                ParentPanel.add(MainPage);
                ParentPanel.revalidate();
                ParentPanel.repaint();
            }
        });
        returnButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ParentPanel.removeAll();
                ParentPanel.add(MainPage);
                ParentPanel.revalidate();
                ParentPanel.repaint();
            }
        });

        loginButtonAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = adminUsernameField.getText();
                char[] password = adminPasswordField.getPassword();
                if (Gym.gym.AdminLogin(username, password)) {
                    JFrame mainFrame = (JFrame) SwingUtilities.getWindowAncestor(ParentPanel);
                    mainFrame.setVisible(false);
                    GUIPage gui = new GUIPage();
                    gui.AdminPage();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Login", "", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        loginButtonCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = customerUsernameField.getText();
                char[] password = customerPasswordField.getPassword();
                if (Gym.gym.CustomerLogin(email, password)) {
                    JFrame mainFrame = (JFrame) SwingUtilities.getWindowAncestor(ParentPanel);
                    mainFrame.setVisible(false);
                    GUIPage gui = new GUIPage();
                    gui.CustomerPage();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Login", "", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        loginButtonCoach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = coachLoginField.getText();
                char[] password = coachLoginPassword.getPassword();
                if (Gym.gym.CoachLogin(email, password)) {
                    JFrame mainFrame = (JFrame) SwingUtilities.getWindowAncestor(ParentPanel);
                    mainFrame.dispose();
                    GUIPage gui = new GUIPage();
                    gui.CoachPage();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Login", "", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        //Loading GUI
//        JFrame frame = new JFrame("X Gym");
//        frame.setContentPane(new GUI().ParentPanel);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);

        //Reading gym data from file
        Gym.setGym(Gym.getDataFromFile());
        Gym gym = Gym.gym;

        //To create a custom gym use constructor
        //To make program use that gym object, use Gym.setGym
        //To save the gym currently in use run Gym.saveData
        //By default data is read from to written to "data.ser"

        GUIPage gui = new GUIPage();
        gui.AdminPage();
    }
}
