import javax.swing.*;
import java.awt.event.ActionEvent;


public class DisplayAllProfiles {

    private JFrame mainFrame;
    private final TravProfDB database;
    private JButton btnNext;
    private String[] labelOptions;
    private String[] fieldOptions;
    private JLabel[] travLabels;
    private JLabel[] travInfo;

    String ID;
    private JLabel lblSuccess = new JLabel();


    public DisplayAllProfiles(TravProfDB database){
        this.database = database;
        authentication();
    }

    private void authentication(){
        JFrame subFrame = new JFrame("Display All Profiles");
        subFrame.setSize(400,400);
        subFrame.setLayout(null);
        subFrame.setVisible(true);

        JLabel header = new JLabel("Display All Profiles");
        header.setBounds(30,20,300,40);
        subFrame.add(header);

        JLabel lblTaID = new JLabel("Travel Agent ID: ");
        lblTaID.setBounds(100, 60, 100, 20);
        subFrame.add(lblTaID);

        JTextField fldTaID = new JTextField();
        fldTaID.setBounds(200, 60, 140, 20);
        subFrame.add(fldTaID);

        JButton btnFind = new JButton("Find");
        btnFind.setBounds(100,100,100,20);
        subFrame.add(btnFind);

        lblSuccess.setBounds(100,150,100,20);


        btnFind.addActionListener(e-> {
            ID = fldTaID.getText();
            subFrame.dispose();
            prepareGUI(database.findFirstProfile());

        });
    }

    private void prepareGUI(TravProf currentProfile){
        mainFrame = new JFrame("Display All Profiles");
        mainFrame.setSize(400,600);
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);

        //Add labels
        JLabel header = new JLabel("Display All Profiles");
        header.setBounds(30,20,300,40);
        mainFrame.add(header);

        JLabel lblSuccess = new JLabel();
        lblSuccess.setBounds(100,400,100,20);



        if(currentProfile != null) {
            if(currentProfile.getTravAgentID().equals(ID)){
                setLabels(currentProfile);
                btnNext = new JButton("Next");
                btnNext.setBounds(100,450,200,20);
                btnNext.setVisible(true);
                mainFrame.add(btnNext);
                btnNext.addActionListener(e -> {
                    mainFrame.removeAll();
                    mainFrame.dispose();
                    prepareGUI(database.findNextProfile());

                });
            } else{
                mainFrame.dispose();
                prepareGUI(database.findNextProfile());

            }
        } else{
            btnNext = new JButton("Exit");
            btnNext.setBounds(100,450,200,20);
            btnNext.setVisible(true);
            mainFrame.add(btnNext);
            lblSuccess.setText("End of file.");
            lblSuccess.setBounds(100,470,100,20);
            mainFrame.add(lblSuccess);

            btnNext.addActionListener(e -> {
                mainFrame.dispose();
            });
        }

    }

    private void setLabels(TravProf currentProfile){

        labelOptions = new String[]{
                "Travel Agent ID: ",
                "First Name: ",
                "Last Name: ",
                "Address: ",
                "Phone Number: ",
                "Trip Cost: ",
                "Travel Type: ",
                "Payment Type: ",
                "Doctor Name: ",
                "Allergy Type: ",
                "Illness Type: "
        };
        fieldOptions = new String[]{
                currentProfile.getTravAgentID(),
                currentProfile.getFirstName(),
                currentProfile.getLastName(),
                currentProfile.getAddress(),
                currentProfile.getPhone(),
                String.valueOf(currentProfile.getTripCost()),
                currentProfile.getTravelType(),
                currentProfile.getPaymentType(),
                currentProfile.getMedCondInfo().getMdContact(),
                currentProfile.getMedCondInfo().getAlgType(),
                currentProfile.getMedCondInfo().getIllType()
        };

        travLabels = new JLabel[labelOptions.length];
        for(int i = 0; i < labelOptions.length; i++){

            travLabels[i] = new JLabel();
            travLabels[i].setText(labelOptions[i]);
            travLabels[i].setBounds(50,100+(i*20),150,20);
            travLabels[i].setVisible(true);
            mainFrame.add(travLabels[i]);
        }
        travInfo = new JLabel[labelOptions.length];
        for(int i = 0; i < fieldOptions.length; i++){
            travInfo[i] = new JLabel();
            travInfo[i].setText(fieldOptions[i]);
            travInfo[i].setBounds(200,100+(i*20),150,20);
            travInfo[i].setVisible(true);
            mainFrame.add(travInfo[i]);
        }
    }
}
