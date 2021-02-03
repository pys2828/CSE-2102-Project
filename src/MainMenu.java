import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class MainMenu {
    private JFrame mainFrame;
    private JLabel header, subHeader;
    private JButton btnSelect;
    private JRadioButton[] jr_buttonOptions;
    private File newFile;
    private String filename;
    private TravProfDB database;

    public MainMenu(String filename) throws IOException, ClassNotFoundException  {
        this.filename = filename;
        database = new TravProfDB(filename);
        //testProf();
        newFile = new File(filename);
        prepareGUI();
    }

    private void prepareGUI() throws IOException, ClassNotFoundException {
        mainFrame = new JFrame("Main Menu");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400,400);
        mainFrame.setLayout(null);
        initDB();

        //Add labels
        header = new JLabel("Integrated Travel System");
        header.setBounds(30,20,300,40);
        header.setVisible(true);
        mainFrame.add(header);

        subHeader = new JLabel("Main Menu");
        subHeader.setBounds(30,50,300,40);
        subHeader.setVisible(true);
        mainFrame.add(subHeader);

        //Add radio buttons
        String[] menuOptions = {
                "Create Profile",
                "Delete Profile",
                "Update Profile",
                "Find/Display Profile",
                "Display All Profiles"
        };
        
        mainFrame.setVisible(true);

        jr_buttonOptions = new JRadioButton[menuOptions.length];
        for(int i = 0; i < menuOptions.length; i++){
            jr_buttonOptions[i] = new JRadioButton();
            jr_buttonOptions[i].setText(menuOptions[i]);
            jr_buttonOptions[i].setBounds(100,100+(i*20),250,20);
            jr_buttonOptions[i].setVisible(true);
            mainFrame.add(jr_buttonOptions[i]);
        }

        //Add selection button
        btnSelect = new JButton("Select");
        btnSelect.setBounds(100, 250,200,20);
        btnSelect.setVisible(true);
        mainFrame.add(btnSelect);

        btnSelect.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if(jr_buttonOptions[0].isSelected()){
                    CreateProfile newProfile = new CreateProfile(database);
                }
                if(jr_buttonOptions[1].isSelected()){
                    DeleteProfile deleteProfile = new DeleteProfile(database);
                }
                if(jr_buttonOptions[2].isSelected()){
                    UpdateProfile updateProfile = new UpdateProfile(database);
                }
                if(jr_buttonOptions[3].isSelected()){
                    FindProfile findProfile = new FindProfile(database);
                }
                if(jr_buttonOptions[4].isSelected()){
                    DisplayAllProfiles displayAllProfiles = new DisplayAllProfiles(database);
                }
            }
        });

    }

    public void initDB() throws IOException, ClassNotFoundException {
        if (newFile.length() == 0) {
            System.out.println("File is empty.");
        } else {
            database.initializeDatabase(filename);
            System.out.println("Database is initialized.");
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException  {
        MainMenu menu = new MainMenu("TravelerDataBase.dat");
    }

    /*
    private void testProf(){
        TravProf[] travProfs = new TravProf[10];
        String[] travAgentID = {"TA1","TA2","TA1","TA2","TA1","TA2","TA1","TA2","TA1","TA2"};
        String[] firstName = {"Abel", "Anna", "Andrew", "Amanda","Adam","Andrea","Alec","Alyssa","Arthur","Alicia"};
        String[] lastName = {"Abramo","Blake","Carlson","Decker","Ellis","Fitzgerald","Garcia","Hughes","Illes","Johnson"};
        String[] address = {"321 Zero", "432 One", "543 Two","654 Three","765 Four","876 Five","987 Six","098 Seven","123 Eight","234 Nine","345 Ten"};
        String[] phone = {"789", "456", "123", "963","852","741","478","159","236","846"};
        float[] tripCost = {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000};
        String[] travelType = {"Business","Pleasure","Business","Pleasure","Business","Pleasure","Business","Pleasure","Business","Pleasure"};
        String[] paymentType = {"Credit","Check","Debit","Invoice","Credit","Check","Debit","Invoice","Credit","Check"};
        String[] mdContact = {"Dr. Doctor","Dr. Bronner","Dr. Doctor","Dr. Bronner","Dr. Doctor","Dr. Bronner","Dr. Doctor","Dr. Bronner","Dr. Doctor","Dr. Bronner"};
        String[] mdPhone = {"789", "456", "123", "963","852","741","478","159","236","846"};
        String[] algType = {"None","Food","Medication","Other","None","Food","Medication","Other","None","Food"};
        String[] illType = {"None","Heart","Diabetes","Asthma","Other","None","Heart","Diabetes","Asthma","Other"};
        MedCond[] medCondInfo = new MedCond[10];
        for(int i = 0; i<10;i++){
            medCondInfo[i] = new MedCond(mdContact[i],mdPhone[i],algType[i],illType[i]);
            travProfs[i] = new TravProf(travAgentID[i],firstName[i],lastName[i],address[i],phone[i],tripCost[i],travelType[i],paymentType[i],medCondInfo[i]);
            database.insertNewProfile(travProfs[i]);
        }
    }

     */
}