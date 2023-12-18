import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class GUIPage extends JFrame implements ActionListener {

//    Gym gym;
    Border blackLine = BorderFactory.createLineBorder(Color.black);
    JPanel topPanel = new JPanel();
    JPanel sidebar = new JPanel();
    JPanel mainArea = new JPanel();
    JTextArea mainTextArea = new JTextArea("");
    String textAreaOutput;
    Font font = new Font(mainTextArea.getFont().getName(), mainTextArea.getFont().getStyle(), 20);
    JScrollPane scrollableArea;
    public GUIPage(){
        //Settings for Frame
        try{
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception a){
            System.out.println("Look And Feel Error");
        }

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
        this.setTitle(Gym.gym.getName());

        //Settings for panels and layout

        topPanel.setPreferredSize(new Dimension(1000,100));
        topPanel.setLayout(new GridLayout(3,2));
        topPanel.setBorder(blackLine);
        this.add(topPanel, BorderLayout.NORTH);

        sidebar.setPreferredSize(new Dimension(200,900));
        sidebar.setBorder(blackLine);
        sidebar.setLayout(new GridLayout(12,1));
        this.add(sidebar, BorderLayout.WEST);

        mainArea.setBorder(blackLine);
        mainArea.setLayout(new GridLayout(12,4));
        this.add(mainArea);

        mainTextArea.setFont(font);
        mainTextArea.setEditable(false);
        scrollableArea = new JScrollPane(mainTextArea);
        scrollableArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollableArea.setPreferredSize(new Dimension(1000, 300));
        this.getContentPane().add(scrollableArea, BorderLayout.SOUTH);

    }// Constructor with GUI Settings & Layout

    public void actionPerformed(ActionEvent e){

    }// Everything breaks when I don't have this

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
        this.dispose();
        Gym.saveData();
        System.exit(0);
    }

    public void LoggedInPage(){
        ResetPanels();
        JButton logoutButton = new JButton("Logout");

        topPanel.add(new JLabel("Welcome", SwingConstants.RIGHT));
        topPanel.add(new JLabel());
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

    public void CustomerPage(){
        ResetPanels();
        LoggedInPage();
        JButton coachInfo = new JButton("My Coach");
        JButton gymEquipment = new JButton("Gym Equipment");
        JButton membershipPlan = new JButton("Membership");
        JButton inBody = new JButton("In Bodies");
        JButton calculator = new JButton("Calculator");
        JButton addInBody = new JButton("Add In Body");
        JButton deleteInBody = new JButton("Delete In Body");

        sidebar.add(coachInfo);
        sidebar.add(gymEquipment);
        sidebar.add(membershipPlan);
        sidebar.add(inBody);
        sidebar.add(calculator);
        sidebar.add(addInBody);
        sidebar.add(deleteInBody);

        coachInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResetTextArea();
                ResetMainArea();
                mainArea.revalidate();
                mainArea.repaint();

                textAreaOutput = ((Customer)Gym.gym.currentPerson).getCoachInfo();
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

                textAreaOutput = ((Customer)Gym.gym.currentPerson).displayMembershipDetails();
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
                JButton calculate = new JButton("Get In Body");


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
                        try {
                            String dateString = day + "/" + month + "/" + year;
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            LocalDate date = LocalDate.parse(dateString, formatter);
                            textAreaOutput = ((Customer)Gym.gym.currentPerson).displayInBodyInfoAtDate(date);
                        } catch (DateTimeParseException exception){
                            textAreaOutput = "Invalid Date";
                        }

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

                textAreaOutput = ((Customer)Gym.gym.currentPerson).displayWeightLossGoal();
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
                        if (((Customer)Gym.gym.currentPerson).isAllowedToPerformInBody()){
                            try{
                                InBody inBody = new InBody(today,
                                        Double.parseDouble(height.getText()), Double.parseDouble(weight.getText()),
                                        Double.parseDouble(bodyFat.getText()), Double.parseDouble(minerals.getText()),
                                        Double.parseDouble(water.getText()), Double.parseDouble(protein.getText()));
                                ((Customer)Gym.gym.currentPerson).addInBody(inBody);
                                textAreaOutput = "InBody added successfully";
                            }
                            catch (NumberFormatException a){
                                textAreaOutput = "Invalid InBody";
                            }
                        } else textAreaOutput = "Not Allowed to Do InBody";
                        mainTextArea.setText(textAreaOutput);
                    }
                });
            }
        });

        deleteInBody.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(GUIPage.this, "Are you sure you want to proceed?", "Delete", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) ((Customer)Gym.gym.currentPerson).deleteLastInBody();
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

    public void AdminPage() {
        ResetPanels();
        LoggedInPage();

        JButton addCoach = new JButton("Add Coach");
        JButton editCoaches = new JButton("Edit Coaches");

        JButton addEquipment = new JButton("Add Equipment");
        JButton editEquipment = new JButton("Edit Equipment");

        JButton addCustomer = new JButton("Add Customer");
        JButton editCustomer = new JButton("Edit Customer");

        JButton customerHistory = new JButton("Customer History");
        JButton subscriptions = new JButton("Subscriptions");
        JButton monthlyIncome = new JButton("Monthly Income");
        JButton coachCustomers = new JButton("Customers by Coach");
        JButton displayCoaches = new JButton("Coaches by No. of Customers");

        sidebar.add(addCoach);
        sidebar.add(editCoaches);

        sidebar.add(addEquipment);
        sidebar.add(editEquipment);

        sidebar.add(addCustomer);
        sidebar.add(editCustomer);

        sidebar.add(customerHistory);
        sidebar.add(subscriptions);
        sidebar.add(monthlyIncome);
        sidebar.add(coachCustomers);
        sidebar.add(displayCoaches);

        addCoach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int coachID;
                try {
                    coachID = (Gym.gym.coaches.getLast().getId() + 1);
                }
                catch (Exception exception){
                    coachID = 1;
                }
                JTextField coachName = new JTextField();
                String[] selection = {"M", "F"};
                JComboBox coachGender = new JComboBox(selection);
                JTextField coachAddress = new JTextField();
                JTextField coachPhoneNum = new JTextField();
                JTextField coachEmail = new JTextField();
                JTextField coachMaxHours = new JTextField();
                JPasswordField coachPassword = new JPasswordField();
                JButton getInput = new JButton("Add Coach");

                ResetMainArea();
                ResetTextArea();

                AddPanelsToMain(1);
                mainArea.add(new JLabel("Enter ID:"));
                mainArea.add(new JLabel(Integer.toString(coachID)));
                AddPanelsToMain(2);
                mainArea.add(new JLabel("Enter Name:"));
                mainArea.add(coachName);
                AddPanelsToMain(2);
                mainArea.add(new JLabel("Address:"));
                mainArea.add(coachAddress);
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
                mainArea.add(new JLabel("Password"));
                mainArea.add(coachPassword);
                AddPanelsToMain(2);
                mainArea.add(getInput);
                AddPanelsToMain(2+3*4);

                int finalCoachID = coachID;
                getInput.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        char gender = 'M';
                        if (coachGender.getSelectedIndex() == 1) gender = 'F';
                        try{
                            Coach coach = new Coach(finalCoachID, coachName.getText(),
                                    gender, coachAddress.getText(), coachPhoneNum.getText(), coachEmail.getText(),
                                    Integer.parseInt(coachMaxHours.getText()), coachPassword.getPassword());
                            Admin.addCoach(coach);
                            textAreaOutput = "Coach Added Successfully";
                            ResetMainArea();
                        } catch (Exception exception){
                            textAreaOutput = "Invalid Inputs";
                        }
                        mainTextArea.setText(textAreaOutput);
                    }
                });

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
                JPasswordField coachPassword = new JPasswordField();
                JButton getInput = new JButton("Edit Coach");
                JButton getCoachID = new JButton("Enter Coach Info");
                JButton deleteCoach = new JButton("Delete Coach");

                final int[] intCoachID = new int[1];

                ResetMainArea();
                ResetTextArea();

                AddPanelsToMain(1);
                mainArea.add(new JLabel("Enter ID:"));
                mainArea.add(coachID);
                mainArea.add(getCoachID);
                AddPanelsToMain(4*11);
                mainArea.revalidate();
                mainArea.repaint();

                getCoachID.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        boolean foundCoach = false;
                        try{
                            intCoachID[0] = Integer.parseInt(coachID.getText());
                            for (Coach coach : Gym.gym.coaches) {
                                if (coach.getId() == intCoachID[0]){
                                    ResetMainArea();
                                    ResetTextArea();
                                    AddPanelsToMain(5);
                                    mainArea.add(new JLabel("Enter Name:"));
                                    mainArea.add(coachName);
                                    AddPanelsToMain(2);
                                    mainArea.add(new JLabel("Enter Address"));
                                    mainArea.add(coachAddress);
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
                                    mainArea.add(new JLabel("Password"));
                                    mainArea.add(coachPassword);
                                    AddPanelsToMain(2);
                                    mainArea.add(getInput);
                                    mainArea.add(deleteCoach);
                                    AddPanelsToMain(1 + 3 * 4);
                                    mainArea.revalidate();
                                    mainArea.repaint();

                                    coachAddress.setText(coach.getAddress());
                                    coachName.setText(coach.getName());
                                    if (coach.getGender() == 'F') coachGender.setSelectedIndex(1);
                                    else coachGender.setSelectedIndex(0);
                                    coachPhoneNum.setText(coach.getPhoneNumber());
                                    coachEmail.setText(coach.getEmail());
                                    coachMaxHours.setText(Integer.toString(coach.getMaxWorkingHoursPerDay()));
                                    String password = "";
                                    for (char c : coach.getPassword()) password += c;
                                    coachPassword.setText(password);
                                    foundCoach = true;
                                    break;
                                }
                            }
                            if (!foundCoach) mainTextArea.setText("No Coach Found");
                        } catch (Exception exception){
                            mainTextArea.setText("Invalid Coach ID");
                        }
                        ;
                    }
                });

                getInput.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        char gender;
                        if (coachGender.getSelectedIndex() == 0) gender = 'M';
                        else gender = 'F';
                        try{
                            Coach editedCoach = new Coach(intCoachID[0], coachName.getText(), gender, coachAddress.getText(),
                                    coachPhoneNum.getText(), coachEmail.getText(), Integer.parseInt(coachMaxHours.getText()), coachPassword.getPassword());
                            Admin.editCoach(intCoachID[0], editedCoach);
                            mainTextArea.setText("Coach Edited Successfully");
                            ResetMainArea();
                        }
                        catch (Exception exception){
                            mainTextArea.setText("Invalid Input");
                        }
                    }
                });

                deleteCoach.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "Delete", JOptionPane.YES_NO_OPTION);
                        if (input == 0){

                            mainTextArea.setText(Admin.deleteCoach(intCoachID[0]));
                            ResetMainArea();
                        }
                    }
                });
            }
        });

        addEquipment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField equipmentName = new JTextField();
                JTextField equipmentQuantity = new JTextField();
                JButton getInput = new JButton("Add Equipment");
                int intEquipmentID;
                try {
                    intEquipmentID = Gym.gym.sportsEquipments.getLast().getEquipmentCode() + 1;
                }
                catch (Exception exception){
                    intEquipmentID = 1;
                }

                ResetMainArea();
                ResetTextArea();

                AddPanelsToMain(1);
                mainArea.add(new JLabel("Equipment ID:"));
                mainArea.add(new JLabel(Integer.toString(intEquipmentID)));
                AddPanelsToMain(2);
                mainArea.add(new JLabel("Equipment Name:"));
                mainArea.add(equipmentName);
                AddPanelsToMain(2);
                mainArea.add(new JLabel("Equipment Quantity:"));
                mainArea.add(equipmentQuantity);
                AddPanelsToMain(2);
                mainArea.add(getInput);
                AddPanelsToMain(2+8*4);

                mainArea.revalidate();
                mainArea.repaint();

                int finalIntEquipmentID = intEquipmentID;
                getInput.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Equipment equipment = new Equipment(equipmentName.getText(), finalIntEquipmentID,
                                    Integer.parseInt(equipmentQuantity.getText()));
                            Admin.addEquipment(equipment);
                            mainTextArea.setText("Equipment Added Successfully");
                            ResetMainArea();
                        }
                        catch (Exception exception){
                            mainTextArea.setText("Invalid Inputs");
                        };
                    }
                });
            }
        });

        editEquipment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField equipmentName = new JTextField();
                JTextField equipmentID = new JTextField();
                JTextField equipmentQuantity = new JTextField();
                JButton getInput = new JButton("Edit Equipment");
                JButton getEquipmentID = new JButton("Get ID");
                JButton deleteEquipment = new JButton("Delete Equipment");
                final int[] intEquipmentID = new int[1];

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
                        try{
                            for (Equipment equipment : Gym.gym.sportsEquipments){
                                if (Integer.parseInt(equipmentID.getText()) == equipment.getEquipmentCode()){
                                    intEquipmentID[0] = equipment.getEquipmentCode();
                                    equipmentName.setText(equipment.getEquipmentName());
                                    equipmentQuantity.setText(Integer.toString(equipment.getQuantity()));

                                    ResetMainArea();
                                    AddPanelsToMain(5);
                                    mainArea.add(new JLabel("Equipment Name:"));
                                    mainArea.add(equipmentName);
                                    AddPanelsToMain(2);
                                    mainArea.add(new JLabel("Equipment Quantity:"));
                                    mainArea.add(equipmentQuantity);
                                    AddPanelsToMain(2);
                                    mainArea.add(getInput);
                                    mainArea.add(deleteEquipment);
                                    AddPanelsToMain(1+8*4);
                                    mainArea.revalidate();
                                    mainArea.repaint();
                                    break;
                                }
                            }
                        }
                        catch (Exception exception) {
                            mainTextArea.setText("Invalid Input");
                        }

                    }
                });

                getInput.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Equipment editedEquipment = new Equipment(equipmentName.getText(),
                                    Integer.parseInt(equipmentID.getText()), Integer.parseInt(equipmentQuantity.getText()));
                            Admin.editEquipment(intEquipmentID[0], editedEquipment);
                            mainTextArea.setText("Equipment Edited Successfully");
                            ResetMainArea();
                        }
                        catch (Exception exception) {
                            mainTextArea.setText("Invalid Inputs");
                        }
                    }
                });

                deleteEquipment.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "Delete", JOptionPane.YES_NO_OPTION);
                        if (input == 0){
                            Admin.deleteEquipment(intEquipmentID[0]);
                            mainTextArea.setText("Equipment Deleted Successfully");
                            ResetMainArea();
                        }
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
                JTextField customerName = new JTextField();
                String[] genderSelection = {"M", "F"};
                JComboBox customerGender = new JComboBox(genderSelection);
                JTextField customerAddress = new JTextField();
                JTextField customerEmail = new JTextField();
                JTextField customerPhoneNum = new JTextField();
                JPasswordField customerPassword = new JPasswordField();
                int intCustomerID;
                try{
                    intCustomerID = Gym.gym.customers.getLast().getId() + 1;
                }
                catch (Exception exception){
                    intCustomerID = 1;
                }

                AddPanelsToMain(1);
                mainArea.add(new JLabel("Customer ID:"));
                mainArea.add(new JLabel(Integer.toString(intCustomerID)));
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
                mainArea.add(new JLabel("Password"));
                mainArea.add(customerPassword);
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
                AddPanelsToMain(6);

                mainArea.revalidate();
                mainArea.repaint();

                int finalIntCustomerID = intCustomerID;
                getInput.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {//Creating Membership
                            PlanType planType = PlanType.THREE_DAYS_PER_WEEK;
                            if (membershipPlan.getSelectedIndex() == 1) planType = PlanType.SIX_DAYS_PER_WEEK;
                            MembershipPlan customerMembership = new MembershipPlan(LocalDate.now(), planType,
                                    Integer.parseInt(numberOfMonths.getText()));

                            try {//Creating Subscription
                                Subscription subscription = new Subscription(finalIntCustomerID, Integer.parseInt(coachID.getText()),
                                        customerMembership);
                                try {
                                    char gender = 'M';
                                    if (customerGender.getSelectedIndex() == 1) gender = 'F';
                                    Customer newCustomer = new Customer(finalIntCustomerID, customerName.getText(), gender, customerAddress.getText(),
                                            customerPhoneNum.getText(), customerEmail.getText(), customerPassword.getPassword());
                                    newCustomer.setSubscription(subscription);
                                    mainTextArea.setText(Admin.addCustomer(newCustomer));
                                }
                                catch (Exception exception){
                                    mainTextArea.setText("Invalid Customer Details");
                                }
                            }
                            catch (Exception exception){
                                mainTextArea.setText("Invalid Subscription");
                            }
                        }
                        catch (Exception exception) {
                            mainTextArea.setText("Invalid Membership");
                        }
                    }
                });
            }
        });

        editCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResetMainArea();
                ResetTextArea();

                JButton getID = new JButton("Enter Customer Info");
                JButton deleteCustomer = new JButton("Delete Customer");
                JButton editSubscription = new JButton("Edit Customer Subscription");
                JButton editCustomerDetails = new JButton("Edit Customer Details");

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
                JPasswordField customerPassword = new JPasswordField();

                final int[] intCustomerID = new int[1];

                AddPanelsToMain(1);
                mainArea.add(new JLabel("Customer ID :"));
                mainArea.add(customerID);
                mainArea.add(getID);
                AddPanelsToMain(11*4);

                mainArea.revalidate();
                mainArea.repaint();

                getID.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try{
                            intCustomerID[0] = Integer.parseInt(customerID.getText());
                            boolean customerFound = false;
                            for (Customer customer : Gym.gym.customers){
                                if (customer.getId() == intCustomerID[0]){
                                    customerFound = true;
                                    ResetTextArea();
                                    ResetMainArea();
                                    AddPanelsToMain(5);
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
                                    mainArea.add(new JLabel("Password"));
                                    mainArea.add(customerPassword);
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
                                    mainArea.add(editCustomerDetails);
                                    mainArea.add(deleteCustomer);
                                    AddPanelsToMain(2);
                                    mainArea.add(editSubscription);
                                    AddPanelsToMain(2);

                                    customerName.setText(customer.getName());
                                    if (customer.getGender() == 'M') customerGender.setSelectedIndex(0);
                                    else customerGender.setSelectedIndex(1);
                                    customerAddress.setText(customer.getAddress());
                                    customerEmail.setText(customer.getEmail());
                                    customerPhoneNum.setText(customer.getPhoneNumber());
                                    String password = "";
                                    for (char a : customer.getPassword()) password += a;
                                    customerPassword.setText(password);
                                    coachID.setText(Integer.toString(customer.getCoachID()));
                                    if (customer.getSubscription().getMembershipPlan().getPlanType() == PlanType.THREE_DAYS_PER_WEEK) membershipPlan.setSelectedIndex(0);
                                    else membershipPlan.setSelectedIndex(1);
                                    numberOfMonths.setText(Integer.toString(customer.getSubscription().getMembershipPlan().getNumberOfMonths()));
                                    break;
                                }
                            }
                            if (!customerFound) mainTextArea.setText("Customer Not Found");
                        }
                        catch (Exception exception){
                            mainTextArea.setText("Invalid ID");
                        }
                    }
                });

                deleteCustomer.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "Delete", JOptionPane.YES_NO_OPTION);
                        if (input == 0){
                            mainTextArea.setText(Admin.deleteCustomer(intCustomerID[0]));
                            ResetMainArea();
                        }
                    }
                });

                editSubscription.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            PlanType planType = PlanType.THREE_DAYS_PER_WEEK;
                            if (membershipPlan.getSelectedIndex() == 1) planType = PlanType.SIX_DAYS_PER_WEEK;
                            MembershipPlan newMembership = new MembershipPlan(LocalDate.now(), planType, Integer.parseInt(numberOfMonths.getText()));
                            Subscription newSubscription = new Subscription(intCustomerID[0], Integer.parseInt(coachID.getText()), newMembership);
                            mainTextArea.setText(Admin.editCustomerSubscription(intCustomerID[0], newSubscription));
                        }
                        catch (Exception exception){
                            mainTextArea.setText("Invalid Subscription Details");
                        }
                    }
                });

                editCustomerDetails.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try{
                            char gender = 'M';
                            if (customerGender.getSelectedIndex() == 1) gender = 'F';
                            Customer newCustomer = new Customer(intCustomerID[0], customerName.getText(), gender,
                                    customerAddress.getText(), customerPhoneNum.getText(), customerEmail.getText(), customerPassword.getPassword());
                            mainTextArea.setText(Admin.editCustomerDetails(newCustomer));
                        }
                        catch (Exception exception){
                            mainTextArea.setText("Invalid Customer Details");
                        }
                    }
                });
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
                        mainTextArea.setText(Admin.displaySubscriptionHistory(Integer.parseInt(customerID.getText())));
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

                getMonthly.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ResetTextArea();
                        ResetTextArea();

                        try {
                            mainTextArea.setText(Admin.displayCustomersSubscribedInMonth(Integer.parseInt(month.getText()), Integer.parseInt(year.getText())));
                        }
                        catch (Exception exception){
                            mainTextArea.setText("Invalid Date");
                        }
                    }
                });

                getDaily.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ResetTextArea();
                        ResetTextArea();

                        try {
                            mainTextArea.setText(Admin.displayCustomersSubscribedInDay(Integer.parseInt(day.getText()), Integer.parseInt(month.getText()), Integer.parseInt(year.getText())));
                        }
                        catch (Exception exception){
                            mainTextArea.setText("Invalid Date");
                        }
                    }
                });
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
                        try {
                            LocalDate date = LocalDate.parse(dateString, formatter);
                            double income = Admin.displayIncomeInMonth(date);
                            mainTextArea.setText(income + " per month");
                        }
                        catch (Exception exception){
                            mainTextArea.setText("Invalid Date");
                        }

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
                        mainTextArea.setText(Admin.displayCustomersOfCoach(ID));
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
                textAreaOutput = Admin.SortCoachesByCustomers(Gym.gym.coaches);
                mainTextArea.setText(textAreaOutput);
            }
        });
    }

    public void coachRegistration(){
        ResetPanels();
        int coachID;
        try {
            coachID = (Gym.gym.coaches.getLast().getId() + 1);
        }
        catch (Exception exception){
            coachID = 1;
        }
        JTextField coachName = new JTextField();
        String[] selection = {"M", "F"};
        JComboBox coachGender = new JComboBox(selection);
        JTextField coachAddress = new JTextField();
        JTextField coachPhoneNum = new JTextField();
        JTextField coachEmail = new JTextField();
        JTextField coachMaxHours = new JTextField();
        JPasswordField coachPassword = new JPasswordField();
        JButton getInput = new JButton("Register");
        JTextField adminUsername = new JTextField();
        JPasswordField adminPassword = new JPasswordField();
        JButton back = new JButton("Return");

        ResetMainArea();
        ResetTextArea();


        topPanel.add(new JLabel("Admin Username: ", SwingConstants.RIGHT));
        topPanel.add(adminUsername);
        topPanel.add(new JLabel("Admin Username", SwingConstants.RIGHT));
        topPanel.add(adminPassword);
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());

        AddPanelsToMain(1);
        mainArea.add(new JLabel("Enter ID:"));
        mainArea.add(new JLabel(Integer.toString(coachID)));
        AddPanelsToMain(2);
        mainArea.add(new JLabel("Enter Name:"));
        mainArea.add(coachName);
        AddPanelsToMain(2);
        mainArea.add(new JLabel("Address:"));
        mainArea.add(coachAddress);
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
        mainArea.add(new JLabel("Password"));
        mainArea.add(coachPassword);
        AddPanelsToMain(2);
        mainArea.add(getInput);
        mainArea.add(back);
        AddPanelsToMain(1+3*4);

        int finalCoachID = coachID;
        getInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResetTextArea();
                char gender = 'M';
                if (coachGender.getSelectedIndex() == 1) gender = 'F';
                try{
                    if (Gym.gym.AdminLogin(adminUsername.getText(), adminPassword.getPassword())){
                        try{
                            Coach coach = new Coach(finalCoachID, coachName.getText(),
                                    gender, coachAddress.getText(), coachPhoneNum.getText(), coachEmail.getText(),
                                    Integer.parseInt(coachMaxHours.getText()), coachPassword.getPassword());
                            Admin.addCoach(coach);
                            textAreaOutput = "Registration Complete";
                            GUI.main(new String[]{});
                            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(sidebar);
                            frame.dispose();
                        } catch (Exception exception){
                            textAreaOutput = "Invalid Inputs";
                        }
                    }
                    else textAreaOutput = "Wrong Admin Password";
                }
                catch (Exception exception){
                    textAreaOutput = "Invalid Admin Details";
                }

                mainTextArea.setText(textAreaOutput);
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI.main(new String[]{});
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(sidebar);
                frame.dispose();
            }
        });

        mainArea.revalidate();
        mainArea.repaint();
    }

    public void customerRegistration(){
        ResetMainArea();
        ResetTextArea();

        JTextField adminUsername = new JTextField();
        JPasswordField adminPassword = new JPasswordField();
        JButton back = new JButton("Return");
        JButton getInput = new JButton("Add Customer");
        // Plan and Subscription
        JTextField coachID = new JTextField();
        JTextField numberOfMonths = new JTextField();
        String[] planSelection = {"3 Days per Week", "6 Days per Week"};
        JComboBox membershipPlan = new JComboBox<>(planSelection);

        //Customer Details
        JTextField customerName = new JTextField();
        String[] genderSelection = {"M", "F"};
        JComboBox customerGender = new JComboBox(genderSelection);
        JTextField customerAddress = new JTextField();
        JTextField customerEmail = new JTextField();
        JTextField customerPhoneNum = new JTextField();
        JPasswordField customerPassword = new JPasswordField();
        int intCustomerID;
        try{
            intCustomerID = Gym.gym.customers.getLast().getId() + 1;
        }
        catch (Exception exception){
            intCustomerID = 1;
        }

        topPanel.add(new JLabel("Admin Username: ", SwingConstants.RIGHT));
        topPanel.add(adminUsername);
        topPanel.add(new JLabel("Admin Username", SwingConstants.RIGHT));
        topPanel.add(adminPassword);
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());

        AddPanelsToMain(1);
        mainArea.add(new JLabel("Customer ID:"));
        mainArea.add(new JLabel(Integer.toString(intCustomerID)));
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
        mainArea.add(new JLabel("Password"));
        mainArea.add(customerPassword);
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
        mainArea.add(back);
        AddPanelsToMain(5);

        mainArea.revalidate();
        mainArea.repaint();

        int finalIntCustomerID = intCustomerID;
        getInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if (Gym.gym.AdminLogin(adminUsername.getText(), adminPassword.getPassword())){
                        try {//Creating Membership
                            PlanType planType = PlanType.THREE_DAYS_PER_WEEK;
                            if (membershipPlan.getSelectedIndex() == 1) planType = PlanType.SIX_DAYS_PER_WEEK;
                            MembershipPlan customerMembership = new MembershipPlan(LocalDate.now(), planType,
                                    Integer.parseInt(numberOfMonths.getText()));

                            try {//Creating Subscription
                                Subscription subscription = new Subscription(finalIntCustomerID, Integer.parseInt(coachID.getText()),
                                        customerMembership);
                                try {
                                    char gender = 'M';
                                    if (customerGender.getSelectedIndex() == 1) gender = 'F';
                                    Customer newCustomer = new Customer(finalIntCustomerID, customerName.getText(), gender, customerAddress.getText(),
                                            customerPhoneNum.getText(), customerEmail.getText(), customerPassword.getPassword());
                                    newCustomer.setSubscription(subscription);
                                    mainTextArea.setText(Admin.addCustomer(newCustomer));
                                    GUI.main(new String[]{});
                                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(sidebar);
                                    frame.dispose();
                                }
                                catch (Exception exception){
                                    mainTextArea.setText("Invalid Customer Details");
                                }
                            }
                            catch (Exception exception){
                                mainTextArea.setText("Invalid Subscription");
                            }
                        }
                        catch (Exception exception) {
                            mainTextArea.setText("Invalid Membership");
                        }
                    }
                    else mainTextArea.setText("Wrong Admin Password");
                }
                catch (Exception exception){
                    mainTextArea.setText("Invalid Admin Password");
                }
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI.main(new String[]{});
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(sidebar);
                frame.dispose();
            }
        });
    }
}