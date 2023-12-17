import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

public class GUIPage extends JFrame implements ActionListener {

    Gym gym;
    Border blackLine = BorderFactory.createLineBorder(Color.black);
    String userType;
    JPanel topPanel = new JPanel();
    JPanel sidebar = new JPanel();
    JPanel mainArea = new JPanel();
    JTextArea mainTextArea = new JTextArea("");
    String textAreaOutput;
    Font font = new Font(mainTextArea.getFont().getName(), mainTextArea.getFont().getStyle(), 20);
    JScrollPane scrollableArea;
    ArrayList<Customer> listOfCustomers;
    List<Coach> listOfCoaches;

    public GUIPage(){
        this.gym = Gym.gym;
        this.listOfCoaches = gym.coaches;

        //Settings for Frame
        try{
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception a){
            System.out.println("Look And Feel Error");
        };

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() { //To run save function when closing
            @Override
            public void windowClosing(WindowEvent e) {
                CloseOperation();
            }
        });
        this.setLayout(new BorderLayout());
        this.setSize(1000,1000);
        this.setVisible(true);
        this.setTitle(gym.getName());

        //Settings for panels and layout

        topPanel.setPreferredSize(new Dimension(1000,100));
        topPanel.setLayout(new GridLayout(3,2));
        topPanel.setBorder(blackLine);
        this.add(topPanel, BorderLayout.NORTH);

        sidebar.setPreferredSize(new Dimension(200,900));
        sidebar.setBorder(blackLine);
        sidebar.setLayout(new GridLayout(14,1));
        this.add(sidebar, BorderLayout.WEST);

        mainArea.setBorder(blackLine);
        mainArea.setLayout(new GridLayout(12,4));
        this.add(mainArea);

        mainTextArea.setFont(font);
        mainTextArea.setEditable(false);
        scrollableArea = new JScrollPane(mainTextArea);
        scrollableArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollableArea.setPreferredSize(new Dimension(1000, 200));
        this.getContentPane().add(scrollableArea, BorderLayout.SOUTH);

    }// Constructor with GUI Settings & Layout

    public void actionPerformed(ActionEvent e){

    }// Everything breaks when I don't have this

    public void UpdateLists(){
        listOfCustomers = new ArrayList<Customer>();
        listOfCoaches = gym.getCoaches();
        for (Coach x : listOfCoaches) {
            if (x.getCustomers() == null) continue;
            listOfCustomers.addAll(x.getCustomers());
        }
    }
    public void ResetPanels(){
        //Resetting All Panels
        topPanel.removeAll();
        topPanel.revalidate();
        topPanel.repaint();

        mainArea.removeAll();
        mainArea.revalidate();
        mainArea.repaint();

        mainTextArea.setText("");
        textAreaOutput = "";
        scrollableArea.revalidate();
        scrollableArea.repaint();

        sidebar.removeAll();
        sidebar.revalidate();
        sidebar.repaint();
    }// Clear all panels

    public void ResetMainArea(){
        mainArea.removeAll();
        mainArea.revalidate();
        mainArea.repaint();
    }// Clear only main area

    public void AddPanelsToMain(int panels){
        for (int i = 0; i < panels; i++) mainArea.add(new JPanel());
    }// Adds n panels to mainArea for spacing

    public void ResetTextArea(){
        mainTextArea.setText("");
        textAreaOutput = "";
        scrollableArea.revalidate();
        scrollableArea.repaint();
    }// Resets mainTextArea

    public void CloseOperation(){
        System.out.println("Data Saved");
        this.dispose();
        System.exit(0);
        Gym.saveData();
    }

    public void LoginPage(){
        JTextField UserName = new JTextField(20);
        JButton loginButton = new JButton("Continue");
        String[] selection = {"Employee", "Customer"};
        JComboBox Jselection = new JComboBox(selection);

        ResetPanels();

        //Creating Login Panel
        topPanel.add(new JLabel("Enter ID:", SwingConstants.RIGHT));
        topPanel.add(UserName);
        topPanel.add(new JLabel());
        topPanel.add(Jselection);
        topPanel.add(new JLabel());
        topPanel.add(loginButton);

        userType = "Employee";
        Jselection.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                userType = Jselection.getSelectedItem().toString();
            }
        });

        loginButton.addActionListener(new ActionListener() { //Button passes text from field to LoginFunction
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = UserName.getText();
                LoginFunction(username, userType);
            }
        });
    }// Default page

    public void LoggedInPage(){
        ResetPanels();
        //String userName = currentUser.getName();
        JButton logoutButton = new JButton("Logout");

        topPanel.add(new JLabel("Welcome", SwingConstants.RIGHT));
        //topPanel.add(new JLabel(userName));
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());
        topPanel.add(logoutButton);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI.main(new String[]{});
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(sidebar);
                frame.dispose();
            }
        });
    }// User's name + logout button

    public void LoginFunction(String username, String userType) {
        boolean loginSuccess = false;
        // Get list of customers and coaches in gym

        if (!loginSuccess) JOptionPane.showMessageDialog(null, "Invalid Login", "", JOptionPane.WARNING_MESSAGE);
    }// Checks if user exists then calls appropriate page

    public void AdminPage() {//TODO FINISH ADMIN
            ResetPanels();
            LoggedInPage();

        JButton addCoach = new JButton("Add Coach");
        JButton deleteCoach = new JButton("Delete Coaches");
        JButton editCoaches = new JButton("Edit Coaches");

        JButton addEquipment = new JButton("Add Equipment");
        JButton deleteEquipment = new JButton("Delete Equipment");
        JButton editEquipment = new JButton("Edit Equipment");

        JButton addCustomer = new JButton("Add Customer");
        JButton deleteCustomer = new JButton("Delete Customer");
        JButton editCustomer = new JButton("Edit Customer");

        JButton customerHistory = new JButton("Customer History");
        JButton subscriptions = new JButton("Subscriptions");
        JButton monthlyIncome = new JButton("Monthly Income");
        JButton coachCustomers = new JButton("Customers by Coach");
        JButton displayCoaches = new JButton("Coaches by No. of Customers");

        sidebar.add(addCoach);
        sidebar.add(deleteCoach);
        sidebar.add(editCoaches);

        sidebar.add(addEquipment);
        sidebar.add(deleteEquipment);
        sidebar.add(editEquipment);

        sidebar.add(addCustomer);
        sidebar.add(deleteCustomer);
        sidebar.add(editCustomer);

        sidebar.add(customerHistory);
        sidebar.add(subscriptions);
        sidebar.add(monthlyIncome);
        sidebar.add(coachCustomers);
        sidebar.add(displayCoaches);

        addCoach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField coachID = new JTextField();
                JTextField coachName = new JTextField();
                String[] selection = {"M", "F"};
                JComboBox coachGender = new JComboBox(selection);
                JTextField coachAddress = new JTextField();
                JTextField coachPhoneNum = new JTextField();
                JTextField coachEmail = new JTextField();
                JTextField coachMaxHours = new JTextField();
                JButton getInput = new JButton("Add Coach");

                ResetMainArea();
                ResetTextArea();

                AddPanelsToMain(1);
                mainArea.add(new JLabel("Enter ID:"));
                mainArea.add(coachID);
                AddPanelsToMain(2);
                mainArea.add(new JLabel("Address:"));
                mainArea.add(coachAddress);
                AddPanelsToMain(2);
                mainArea.add(new JLabel("Enter Name:"));
                mainArea.add(coachName);
                AddPanelsToMain(2);
                mainArea.add(new JLabel("Select Gender:"));
                mainArea.add(coachGender);
                AddPanelsToMain(2);
                mainArea.add(new JLabel("Enter Phone Number:"));
                mainArea.add(coachPhoneNum);
                AddPanelsToMain(2);
                mainArea.add(new JLabel("Enter Email:"));
                mainArea.add(coachEmail);
                AddPanelsToMain(2);
                mainArea.add(new JLabel("Enter Max Working Hours:"));
                mainArea.add(coachMaxHours);
                AddPanelsToMain(2);
                mainArea.add(getInput);
                AddPanelsToMain(2+4*4);

                mainArea.revalidate();
                mainArea.repaint();
            }
        });

        deleteCoach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField coachID = new JTextField();
                JButton getInput = new JButton("Delete Coach");

                ResetMainArea();
                ResetTextArea();

                AddPanelsToMain(1);
                mainArea.add(new JLabel("Enter ID: "));
                mainArea.add(coachID);
                AddPanelsToMain(2);
                mainArea.add(getInput);
                AddPanelsToMain(1+10*4);

                mainArea.revalidate();
                mainArea.repaint();


            }
        });

        editCoaches.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField coachID = new JTextField();
                JTextField coachName = new JTextField();
                String[] selection = {"M", "F"};
                JComboBox coachGender = new JComboBox(selection);
                JTextField coachAddress = new JTextField();
                JTextField coachPhoneNum = new JTextField();
                JTextField coachEmail = new JTextField();
                JTextField coachMaxHours = new JTextField();
                JButton getInput = new JButton("Edit Coach");
                JButton getCoachID = new JButton("Enter Coach Info");

                ResetMainArea();
                ResetTextArea();

                AddPanelsToMain(1);
                mainArea.add(new JLabel("Enter ID:"));
                mainArea.add(coachID);
                mainArea.add(getCoachID);
                AddPanelsToMain(1+4*11);
                mainArea.revalidate();
                mainArea.repaint();

                getCoachID.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        boolean foundCoach = false;
                        for (Coach coach : listOfCoaches) {
                            if (coach.getId() == Integer.parseInt(coachID.getText())){
                            ResetMainArea();
                            AddPanelsToMain(5);
                            mainArea.add(new JLabel("Enter Address"));
                            mainArea.add(coachAddress);
                            AddPanelsToMain(2);
                            mainArea.add(new JLabel("Enter Name:"));
                            mainArea.add(coachName);
                            AddPanelsToMain(2);
                            mainArea.add(new JLabel("Select Gender:"));
                            mainArea.add(coachGender);
                            AddPanelsToMain(2);
                            mainArea.add(new JLabel("Enter Phone Number:"));
                            mainArea.add(coachPhoneNum);
                            AddPanelsToMain(2);
                            mainArea.add(new JLabel("Enter Email:"));
                            mainArea.add(coachEmail);
                            AddPanelsToMain(2);
                            mainArea.add(new JLabel("Enter Max Working Hours:"));
                            mainArea.add(coachMaxHours);
                            AddPanelsToMain(2);
                            mainArea.add(getInput);
                            AddPanelsToMain(2 + 4 * 4);
                            mainArea.revalidate();
                            mainArea.repaint();

                            coachAddress.setText(coach.getAddress());
                            coachName.setText(coach.getName());
                            if (coach.getGender() == 'F') coachGender.setSelectedIndex(1);
                            coachPhoneNum.setText(coach.getPhoneNumber());
                            coachEmail.setText(coach.getEmail());
                            coachMaxHours.setText(Integer.toString(coach.getMaxWorkingHoursPerDay()));
                            foundCoach = true;
                            break;
                            }}
                            if (!foundCoach)JOptionPane.showMessageDialog(null, "Invalid ID", "", JOptionPane.WARNING_MESSAGE);
                    }
                });
            }
        });

        addEquipment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField equipmentName = new JTextField();
                JTextField equipmentID = new JTextField();
                JTextField equipmentQuantity = new JTextField();
                JTextField equipmentPhoto = new JTextField();
                JButton getInput = new JButton("Add Equipment");

                ResetMainArea();
                ResetTextArea();

                AddPanelsToMain(1);
                mainArea.add(new JLabel("Equipment ID:"));
                mainArea.add(equipmentID);
                AddPanelsToMain(2);
                mainArea.add(new JLabel("Equipment Name:"));
                mainArea.add(equipmentName);
                AddPanelsToMain(2);
                mainArea.add(new JLabel("Equipment Quantity:"));
                mainArea.add(equipmentQuantity);
                AddPanelsToMain(2);
                mainArea.add(new JLabel("Photo Path:"));
                mainArea.add(equipmentPhoto);
                AddPanelsToMain(2);
                mainArea.add(getInput);
                AddPanelsToMain(2+7*4);

                mainArea.revalidate();
                mainArea.repaint();
            }
        });

        deleteEquipment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField equipmentID = new JTextField();
                JButton getInput = new JButton("Delete Equipment");

                ResetMainArea();
                ResetTextArea();

                AddPanelsToMain(1);
                mainArea.add(new JLabel("Enter ID:"));
                mainArea.add(equipmentID);
                AddPanelsToMain(2);
                mainArea.add(getInput);
                AddPanelsToMain(2+10*4);
                mainArea.revalidate();
                mainArea.repaint();
            }
        });

        editEquipment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField equipmentName = new JTextField();
                JTextField equipmentID = new JTextField();
                JTextField equipmentQuantity = new JTextField();
                JTextField equipmentPhoto = new JTextField();
                JButton getInput = new JButton("Add Equipment");
                JButton getEquipmentID = new JButton("Get ID");

                ResetMainArea();
                ResetTextArea();

                AddPanelsToMain(1);
                mainArea.add(new JLabel("Equipment ID:"));
                mainArea.add(equipmentID);
                mainArea.add(getEquipmentID);
                AddPanelsToMain(4*11);
                mainArea.revalidate();
                mainArea.repaint();

                getEquipmentID.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ResetMainArea();
                        AddPanelsToMain(5);
                        mainArea.add(new JLabel("Equipment Name:"));
                        mainArea.add(equipmentName);
                        AddPanelsToMain(2);
                        mainArea.add(new JLabel("Equipment Quantity:"));
                        mainArea.add(equipmentQuantity);
                        AddPanelsToMain(2);
                        mainArea.add(new JLabel("Photo Path:"));
                        mainArea.add(equipmentPhoto);
                        AddPanelsToMain(2);
                        mainArea.add(getInput);
                        AddPanelsToMain(2+7*4);
                        mainArea.revalidate();
                        mainArea.repaint();
                    }
                });
            }
        });

        addCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResetMainArea();
                ResetTextArea();

                JButton getInput = new JButton("Add Customer");
                // Plan and Subscription
                JTextField coachID = new JTextField();
                JTextField numberOfMonths = new JTextField();
                String[] planSelection = {"3 Days per Week", "6 Days per Week"};
                JComboBox membershipPlan = new JComboBox<>(planSelection);

                //Customer Details
                JTextField customerID = new JTextField();
                JTextField customerName = new JTextField();
                String[] genderSelection = {"M", "F"};
                JComboBox customerGender = new JComboBox(genderSelection);
                JTextField customerAddress = new JTextField();
                JTextField customerEmail = new JTextField();
                JTextField customerPhoneNum = new JTextField();

                AddPanelsToMain(1);
                mainArea.add(new JLabel("Customer ID:"));
                mainArea.add(customerID);
                AddPanelsToMain(2);
                mainArea.add(new JLabel("Customer Name: "));
                mainArea.add(customerName);
                AddPanelsToMain(2);
                mainArea.add(new JLabel("Gender:"));
                mainArea.add(customerGender);
                AddPanelsToMain(2);
                mainArea.add(new JLabel("Address: "));
                mainArea.add(customerAddress);
                AddPanelsToMain(2);
                mainArea.add(new JLabel("Email"));
                mainArea.add(customerEmail);
                AddPanelsToMain(2);
                mainArea.add(new JLabel("Phone Number: "));
                mainArea.add(customerPhoneNum);
                AddPanelsToMain(2);
                mainArea.add(new JLabel("Coach ID:"));
                mainArea.add(coachID);
                AddPanelsToMain(2);
                mainArea.add(new JLabel("Plan Type: "));
                mainArea.add(membershipPlan);
                AddPanelsToMain(2);
                mainArea.add(new JLabel("Number of Months: "));
                mainArea.add(numberOfMonths);
                AddPanelsToMain(2);
                mainArea.add(getInput);
                AddPanelsToMain(2+8);

//                getInput.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        PlanType planType;
//                        MembershipPlan membership;
//                        Subscription subscription;
//                        Customer customer;
//                        if (membershipPlan.getSelectedIndex() == 0)  planType = PlanType.THREE_DAYS_PER_WEEK;
//                        else planType = PlanType.SIX_DAYS_PER_WEEK;
//
//                        //Creating Membership
//                        try {
//                            membership = new MembershipPlan(LocalDate.now(), planType, Integer.parseInt(numberOfMonths.getText()));
//                        }catch (Exception exception){
//                            JOptionPane.showMessageDialog(null, "Invalid Membership Plan", "", JOptionPane.WARNING_MESSAGE);
//                            return;
//                        }
//
//                        //Creating Subscription
//                        try {
//                            subscription = new Subscription(Integer.parseInt(customerID.getText()), Integer.parseInt(coachID.getText()), membership);
//                        } catch (Exception exception){
//                            JOptionPane.showMessageDialog(null, "Invalid Subscription", "", JOptionPane.WARNING_MESSAGE);
//                            return;
//                        }
//
//                        //Creating Customer
//                        try {
//                            char gender = 'M';
//                            if (customerGender.getSelectedIndex() == 1) gender = 'F';
//                            customer = new Customer(Integer.parseInt(customerID.getText()), customerName.getText(),
//                                    gender, customerAddress.getText(), customerPhoneNum.getText(), customerEmail.getText(), subscription, null);
//                        } catch (Exception exception){
//                            JOptionPane.showMessageDialog(null, "Invalid Customer Details", "", JOptionPane.WARNING_MESSAGE);
//                            return;
//                        }
//                        gym.addCustomer(customer);
//                        for (Coach coach : gym.coaches){
//                            if (Integer.parseInt(coachID.getText()) == coach.getId()){
//                                List<Customer> tempCustomers = coach.getCustomers();
//                                tempCustomers.add(customer);
//                                coach.setCustomers(tempCustomers);
//                            }
//                        }
//                        UpdateLists();
//                    }
//                });

                mainArea.revalidate();
                mainArea.repaint();
            }
        });

        deleteCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResetMainArea();
                JButton getInput = new JButton("Delete Customer");
                JTextField customerID = new JTextField();

                AddPanelsToMain(1);
                mainArea.add(new JLabel("Customer ID"));
                mainArea.add(customerID);
                AddPanelsToMain(2);
                mainArea.add(getInput);
                AddPanelsToMain(2+10*4);

                mainArea.revalidate();
                mainArea.repaint();
            }
        });

        editCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResetMainArea();
                ResetTextArea();
                mainArea.revalidate();
                mainArea.repaint();
            }
        });

        customerHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResetMainArea();
                ResetTextArea();
                JTextField customerID = new JTextField();
                JButton getInput = new JButton("Get Customer History");

                AddPanelsToMain(1);
                mainArea.add(new JLabel("Customer ID:"));
                mainArea.add(customerID);
                mainArea.add(getInput);
                AddPanelsToMain(11*4);

                getInput.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String customerIDString = customerID.getText();
                        //mainTextArea.setText(new Admin().returnSubscriptionHistory(customerIDString));
                        if (mainTextArea.getText() == "") mainTextArea.setText("Customer Not Found or No Customer History");
                    }
                });
                mainArea.revalidate();
                mainArea.repaint();
            }
        });

        subscriptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField day = new JTextField();
                JTextField month = new JTextField();
                JTextField year = new JTextField();
                JButton getMonthly = new JButton("Month's Customers");
                JButton getDaily = new JButton("Day's Customers");

                ResetMainArea();
                ResetTextArea();
                AddPanelsToMain(1);
                mainArea.add(new JLabel("Day:"));
                mainArea.add(day);
                AddPanelsToMain(2);
                mainArea.add(new JLabel("Month:"));
                mainArea.add(month);
                AddPanelsToMain(2);
                mainArea.add(new JLabel("Year:"));
                mainArea.add(year);
                AddPanelsToMain(2);
                mainArea.add(getDaily);
                mainArea.add(getMonthly);
                AddPanelsToMain(1+8*4);
                mainArea.revalidate();
                mainArea.repaint();
            }
        });

        monthlyIncome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField month = new JTextField();
                JTextField year = new JTextField();
                JButton getMonthly = new JButton("Month's Income");

                ResetMainArea();
                ResetTextArea();
                AddPanelsToMain(1);
                mainArea.add(new JLabel("Month:"));
                mainArea.add(month);
                AddPanelsToMain(2);
                mainArea.add(new JLabel("Year:"));
                mainArea.add(year);
                AddPanelsToMain(2);
                mainArea.add(getMonthly);
                AddPanelsToMain(2+9*4);
                mainArea.revalidate();
                mainArea.repaint();

                getMonthly.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String dateString = "01/" + month.getText() + "/" + year.getText();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate date = LocalDate.parse(dateString, formatter);
                        double income = gym.displayIncomeInMonth(Integer.parseInt(month.getText()), Integer.parseInt(year.getText()));
                        textAreaOutput = Double.toString(income) + " per month";
                        mainTextArea.setText(textAreaOutput);
                    }
                });
            }
        });

        coachCustomers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField coachID = new JTextField();
                JButton getInput = new JButton("Get Customers");

                ResetMainArea();
                ResetTextArea();
                AddPanelsToMain(1);
                mainArea.add(new JLabel("Coach ID:"));
                mainArea.add(coachID);
                mainArea.add(getInput);
                AddPanelsToMain(11*4);
                mainArea.revalidate();
                mainArea.repaint();

                getInput.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int ID = Integer.parseInt(coachID.getText());
                        ResetTextArea();
                        for (Customer customer : listOfCustomers){
                            if (customer.getCoachID() == ID){
                                textAreaOutput += "Customer ID: " + customer.getId() + ", Name: " + customer.getName() + "\n";
                            }
                        }
                        if (textAreaOutput.equals("")) textAreaOutput = "No customers";
                        mainTextArea.setText(textAreaOutput);
                    }
                });
            }
        });

        displayCoaches.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResetTextArea();
                ResetMainArea();
                mainArea.revalidate();
                mainArea.repaint();
                textAreaOutput = gym.SortCoachesByCustomers(listOfCoaches);
                mainTextArea.setText(textAreaOutput);
            }
        });
    }

    public void CustomerPage(){
        ResetPanels();
        LoggedInPage();
        JButton coachInfo = new JButton("My Coach");
        JButton gymEquipment = new JButton("Gym Equipment");
        JButton membershipPlan = new JButton("Membership");
        JButton inBody = new JButton("In-Bodies");
        JButton calculator = new JButton("Calculator");
        JButton addInBody = new JButton("Add In Body");

        sidebar.add(coachInfo);
        sidebar.add(gymEquipment);
        sidebar.add(membershipPlan);
        sidebar.add(inBody);
        sidebar.add(calculator);
        sidebar.add(addInBody);

        coachInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResetTextArea();
                ResetMainArea();
                mainArea.revalidate();
                mainArea.repaint();

                textAreaOutput = ((Customer)gym.currentPerson).getCoachInfo();
                mainTextArea.setText(textAreaOutput);
            }
        });

        gymEquipment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResetTextArea();
                ResetMainArea();
                mainArea.revalidate();
                mainArea.repaint();

                textAreaOutput = Gym.gym.getEquipmentList();
                mainTextArea.setText(textAreaOutput);
            }
        });

        membershipPlan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResetTextArea();
                ResetMainArea();
                mainArea.revalidate();
                mainArea.repaint();

                textAreaOutput = ((Customer)gym.currentPerson).displayMembershipDetails();
                mainTextArea.setText(textAreaOutput);
            }
        });

        inBody.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResetTextArea();
                ResetMainArea();

                JTextField yearInput = new JTextField();
                JTextField monthInput = new JTextField();
                JTextField dayInput = new JTextField();
                JButton calculate = new JButton("Get In-Body");


                AddPanelsToMain(1);
                mainArea.add(new JLabel("Year: "));
                mainArea.add(yearInput);
                AddPanelsToMain(1);

                AddPanelsToMain(1);
                mainArea.add(new JLabel("Month: "));
                mainArea.add(monthInput);
                AddPanelsToMain(1);

                AddPanelsToMain(1);
                mainArea.add(new JLabel("Day: "));
                mainArea.add(dayInput);
                AddPanelsToMain(1);

                mainArea.add(calculate);
                AddPanelsToMain(4*9-1);

                mainArea.revalidate();
                mainArea.repaint();

                calculate.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String year = yearInput.getText();
                        String month = monthInput.getText();
                        String day = dayInput.getText();
                        String dateString = day + "/" + month + "/" + year;
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate date = LocalDate.parse(dateString, formatter);

                        textAreaOutput = ((Customer)gym.currentPerson).displayInBodyInfoAtDate(LocalDate.now());
                        mainTextArea.setText(textAreaOutput);
                    }
                });

                //List<InBody> tempInBody = ((Customer)gym.currentPerson).getInBodyInfo();
                mainTextArea.setText(textAreaOutput);
            }
        });

        calculator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResetTextArea();
                ResetMainArea();
                mainArea.revalidate();
                mainArea.repaint();

                textAreaOutput = ((Customer)gym.currentPerson).displayWeightLossGoal();
                mainTextArea.setText(textAreaOutput);
            }
        });

        addInBody.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton getInput = new JButton("Add In Body");
                JTextField height = new JTextField();
                JTextField weight = new JTextField();
                JTextField bodyFat = new JTextField();
                JTextField minerals = new JTextField();
                JTextField water = new JTextField();
                JTextField protein = new JTextField();

                ResetTextArea();
                ResetMainArea();

                AddPanelsToMain(1);
                mainArea.add(new JLabel("Height (cm):"));
                mainArea.add(height);
                AddPanelsToMain(2);
                mainArea.add(new JLabel("Weight (KG): "));
                mainArea.add(weight);
                AddPanelsToMain(2);
                mainArea.add(new JLabel("Body Fat Mass (KG)"));
                mainArea.add(bodyFat);
                AddPanelsToMain(2);
                mainArea.add(new JLabel("Minerals (KG)"));
                mainArea.add(minerals);
                AddPanelsToMain(2);
                mainArea.add(new JLabel("Total Body Water (KG)"));
                mainArea.add(water);
                AddPanelsToMain(2);
                mainArea.add(new JLabel("Protein (KG)"));
                mainArea.add(protein);
                AddPanelsToMain(2);
                mainArea.add(getInput);
                AddPanelsToMain(2+5*4);

                mainArea.repaint();
                mainArea.revalidate();

                getInput.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        LocalDate today = LocalDate.now();
                        try{
                            InBody inBody = new InBody(today,
                                    Double.parseDouble(height.getText()), Double.parseDouble(weight.getText()),
                                    Double.parseDouble(bodyFat.getText()), Double.parseDouble(minerals.getText()),
                                    Double.parseDouble(water.getText()), Double.parseDouble(protein.getText()));
                            ((Customer)Gym.gym.currentPerson).addInBody(inBody);
                            textAreaOutput = "InBody added successfully";
                        }
                        catch (Exception a){
                            textAreaOutput = "Invalid InBody";
                        }
                        mainTextArea.setText(textAreaOutput);
                    }
                });
            }
        });

    }

    public void CoachPage(){
        ResetPanels();
        LoggedInPage();

        JButton myCustomers = new JButton("My Customers");
        JButton myMaleCustomers = new JButton("My Male Customers");
        JButton myFemaleCustomers = new JButton("My Female Customers");
        JButton myCustomersInBody = new JButton("Customer In-Bodies");
        JButton allCustomers = new JButton("Customer Info By Name");

        sidebar.add(myCustomers);
        sidebar.add(myMaleCustomers);
        sidebar.add(myFemaleCustomers);
        sidebar.add(allCustomers);
        sidebar.add(myCustomersInBody);
        sidebar.add(new JLabel());
        sidebar.add(new JLabel());
        myCustomers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResetTextArea();
                ResetTextArea();
                mainArea.repaint();
                mainArea.revalidate();

                textAreaOutput = ((Coach)Gym.gym.currentPerson).showCustomerList();
                mainTextArea.setText(textAreaOutput);
            }
        });

        myMaleCustomers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResetTextArea();
                ResetTextArea();
                mainArea.repaint();
                mainArea.revalidate();
                textAreaOutput = ((Coach)Gym.gym.currentPerson).showMaleCustomers();
                mainTextArea.setText(textAreaOutput);
            }
        });

        myFemaleCustomers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResetTextArea();
                ResetTextArea();
                mainArea.repaint();
                mainArea.revalidate();

                textAreaOutput = ((Coach)Gym.gym.currentPerson).showFemaleCustomers();
                mainTextArea.setText(textAreaOutput);
            }
        });

        allCustomers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResetMainArea();
                mainArea.repaint();
                mainArea.revalidate();

                JButton getInput = new JButton("Get Customer Details");
                JTextField customerName = new JTextField();

                AddPanelsToMain(1);
                mainArea.add(getInput);
                mainArea.add(customerName);
                AddPanelsToMain(1+11*4);

                getInput.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ResetTextArea();

                        String name = customerName.getText();
                        textAreaOutput = ((Coach)Gym.gym.currentPerson).getCustomerDetailsByName(name);
                        mainTextArea.setText(textAreaOutput);
                    }
                });
            }
        });

        myCustomersInBody.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResetMainArea();
                mainArea.repaint();
                mainArea.revalidate();

                JButton getInput = new JButton("Get InBody");
                JTextField customerID = new JTextField();

                AddPanelsToMain(1);
                mainArea.add(getInput);
                mainArea.add(customerID);
                AddPanelsToMain(1+11*4);

                getInput.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ResetTextArea();

                        int ID = Integer.parseInt(customerID.getText());
                        textAreaOutput = ((Coach)Gym.gym.currentPerson).getInBodyHistory(ID);
                        mainTextArea.setText(textAreaOutput);
                    }
                });
            }
        });
    }
}