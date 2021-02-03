import javax.swing.*;

public class DisplayProfile {

    private JFrame mainFrame;
    private JLabel header;
    private JLabel[] travLabels;
    private JLabel[] travInfo;
    private TravProf currentProfile;


    public DisplayProfile(TravProf currentProfile){
        this.currentProfile = currentProfile;
        prepareGUI();
    }

    private void prepareGUI(){
        mainFrame = new JFrame("Traveler Profile");
        mainFrame.setSize(400,500);
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);

        //Add labels
        header = new JLabel("Traveler Profile");
        header.setBounds(30,20,300,40);
        mainFrame.add(header);

        setLabels();
    }

    private void setLabels(){
        String[] labelOptions = {
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
        String[] fieldOptions = {
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
            travLabels[i].setBounds(100,100+(i*20),150,20);
            travLabels[i].setVisible(true);
            mainFrame.add(travLabels[i]);
        }
        travInfo = new JLabel[labelOptions.length];
        for(int i = 0; i < fieldOptions.length; i++){
            travInfo[i] = new JLabel();
            travInfo[i].setText(fieldOptions[i]);
            travInfo[i].setBounds(250,100+(i*20),150,20);
            travInfo[i].setVisible(true);
            mainFrame.add(travInfo[i]);
        }
    }
}
