import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UpdateProfile {
    private JFrame mainFrame;
    private JLabel label1, label2, label3;
    private JTextField field1, field2;
    private JComboBox field3;
    private JLabel header;
    private TravProfDB db;

    public UpdateProfile(TravProfDB database)
    {

        db = database;
        prepareGUI();
    }

    private void prepareGUI()
    {
        mainFrame = new JFrame("Update Profile");
        mainFrame.setSize(400,400);
        mainFrame.setLayout(null);

        header = new JLabel("Update Profile");
        header.setBounds(150, 20, 300, 20);
        mainFrame.add(header);

        label1 = new JLabel("Traveler ID: ");
        label1.setBounds(100, 60, 100, 20);
        mainFrame.add(label1);

        label2 = new JLabel("Last Name: ");
        label2.setBounds(100, 85, 100, 20);
        mainFrame.add(label2);

        label3 = new JLabel("Update Field: ");
        label3.setBounds(100, 110, 100, 20);
        mainFrame.add(label3);

        field1 = new JTextField();
        field1.setBounds(180, 60, 140, 20);
        mainFrame.add(field1);

        field2 = new JTextField();
        field2.setBounds(180, 85, 140, 20);
        mainFrame.add(field2);

        String[] fields = {"Address","Phone", "Trip Cost", "Travel Type", "Payment Type", "Doctor Name",
                "Doctor Phone", "Allergy Type", "Illness Type"};
        field3 = new JComboBox(fields);
        field3.setBounds(180, 110, 140, 20);
        mainFrame.add(field3);

        JButton jb = new JButton("Find");
        jb.setBounds(140, 145, 100, 30);
        mainFrame.add(jb);

        mainFrame.setVisible(true);

        jb.addActionListener(e -> {


            //get the textField value

            String str1 = field1.getText();
            String str2 = field2.getText();
            int str3ind = field3.getSelectedIndex() + 1;
            String str3;
            switch(str3ind)
            {
                case 1:
                    str3 = "Address";
                    break;
                case 2:
                    str3 = "Phone";
                    break;
                case 3:
                    str3 = "Trip Cost";
                    break;
                case 4:
                    str3 = "Travel Type";
                    break;
                case 5:
                    str3 = "Payment Type";
                    break;
                case 6:
                    str3 = "Doctor Name";
                    break;
                case 7:
                    str3 = "Doctor Phone";
                    break;
                case 8:
                    str3 = "Allergy Type";
                    break;
                default:
                    str3 = "Illness Type";
            }



            prepare2GUI(str3ind, str1, str2, str3);
            mainFrame.dispose();
    });
    }

    private void prepare2GUI(int int1, String s1, String s2, String upd) {
        JFrame updframe = new JFrame("Update Profile");
        updframe.setSize(400, 400);
        updframe.setLayout(null);

        JLabel delheader = new JLabel("Update Profile");
        delheader.setBounds(150, 20, 300, 20);
        updframe.add(delheader);

        JLabel label1 = new JLabel("Traveler ID: " + s1);
        label1.setBounds(100, 60, 200, 20);
        updframe.add(label1);

        JLabel label2 = new JLabel("Last Name: " + s2);
        label2.setBounds(100, 85, 200, 20);
        updframe.add(label2);

        JLabel label3 = new JLabel(upd + ": ");
        label3.setBounds(100, 110, 200, 20);
        updframe.add(label3);

        String[] travellist = {"Business", "Pleasure"};
        String[] paylist = {"Credit", "Check", "Debit","Invoice"};
        String[] alglist = {"None", "Food", "Medication", "Other"};
        String[] illlist = {"None", "Heart", "Diabetes", "Asthma", "Other"};

        //GUI 2 PREPARATION DONE FOR TRAVID AND LASTNAME

        //GUI PREP FOR UPDATING VARIABLE
        JTextField txtfield = new JTextField();
        JComboBox combo = new JComboBox();
        //true = textfield and false = jcombobox
        boolean choice = true;
        switch (int1) {
            case 1:
                txtfield.setBounds(160, 110, 100, 20);
                updframe.add(txtfield);
                break;
            case 2:
                txtfield.setBounds(145, 110, 100, 20);
                updframe.add(txtfield);
                break;
            case 3:
                txtfield.setBounds(170, 110, 100, 20);
                updframe.add(txtfield);
                break;
            case 6:
                txtfield.setBounds(190, 110, 100, 20);
                updframe.add(txtfield);
                break;
            case 7:
                txtfield.setBounds(191, 110, 100, 20);
                updframe.add(txtfield);
                break;
            case 4:
                combo = new JComboBox(travellist);
                combo.setBounds(180, 110, 130, 20);
                updframe.add(combo);
                choice = false;
                break;
            case 5:
                combo = new JComboBox(paylist);
                combo.setBounds(190, 110, 100, 20);
                updframe.add(combo);
                choice = false;
                break;

            case 10:
                combo = new JComboBox(alglist);
                combo.setBounds(185, 110, 120, 20);
                updframe.add(combo);
                choice = false;
                break;
            default:
                combo = new JComboBox(illlist);
                combo.setBounds(180, 110, 120, 20);
                updframe.add(combo);
                choice = false;
        }

        //set up submit button
        JButton btn = new JButton("Submit");
        btn.setBounds(140, 140, 100, 20);
        updframe.add(btn);

        updframe.setVisible(true);

        //GUI PREP ALL DONE

        //String finalStrupd = strupd;
        boolean finalChoice = choice;
        JComboBox finalCombo = combo;
        btn.addActionListener(e -> {

            boolean valid = false;
            if (finalChoice == true)
            {
                String strupdate = txtfield.getText();
                try {
                    valid = updateProfile(int1, s1, s2, strupdate);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            else
            {
                int ind = finalCombo.getSelectedIndex();
                String strupd;
                if (int1 == 5)
                {
                    switch (ind) {
                        case 0:
                            strupd = "Credit";
                            break;
                        case 1:
                            strupd = "Check";
                            break;
                        case 2:
                            strupd = "Debit";
                            break;
                        default:
                            strupd = "Invoice";
                    }
                }
                else if (int1 == 4)
                {
                    if (ind == 0) {
                        strupd = "Business";
                    } else {
                        strupd = "Pleasure";
                    }
                }
                else if (int1 == 10)
                {
                    switch (ind) {
                        case 0:
                            strupd = "None";
                            break;
                        case 1:
                            strupd = "Food";
                            break;
                        case 2:
                            strupd = "Medication";
                            break;
                        default:
                            strupd = "Other";
                    }
                }
                else
                {
                    switch (ind) {
                        case 0:
                            strupd = "None";
                            break;
                        case 1:
                            strupd = "Heart";
                            break;
                        case 2:
                            strupd = "Diabetes";
                            break;
                        case 3:
                            strupd = "Asthma";
                            break;
                        default:
                            strupd = "Other";
                    }
                }

                try {
                    valid = updateProfile(int1, s1, s2, strupd);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }

            prepare3GUI(valid);
            updframe.dispose();

        });

    }

    private void prepare3GUI(boolean valid)
    {
        JFrame updateframe = new JFrame("Update Profile");
        updateframe.setSize(400, 400);
        updateframe.setLayout(null);

        JLabel delheader = new JLabel("Update Profile");
        delheader.setBounds(150, 20, 300, 20);
        updateframe.add(delheader);

        JLabel success;
        if (valid) {
            success = new JLabel("Profile Updated!");
        } else {
            success = new JLabel("Profile Not Found.");
        }
        success.setBounds(140, 60, 300, 20);
        updateframe.add(success);

        JButton btn = new JButton("OK");
        btn.setBounds(140, 100, 100, 20);
        updateframe.add(btn);

        updateframe.setVisible(true);

        btn.addActionListener(e -> {
            updateframe.dispose();

        });
    }

    public boolean updateProfile(int int1, String ID, String lastname, String upd) throws IOException {
        TravProf tprof = db.findProfile(ID, lastname);
        if (tprof == null) {
            return false;
        } else {

            switch (int1) {
                case 1:
                    tprof.updateAddress(upd);
                    break;
                case 2:
                    tprof.updatePhone(upd);
                    break;
                case 3:
                    try {tprof.updateTripCost(Float.parseFloat(upd));}
                    catch (NumberFormatException nfe) {prepareExepGUI();}
                    break;
                case 4:
                    tprof.updateTravelType(upd);
                    break;
                case 5:
                    tprof.updatePaymentType(upd);
                    break;
                case 6:
                    tprof.getMedCondInfo().setMdContact(upd);
                    break;
                case 7:
                    tprof.getMedCondInfo().setMdPhone(upd);
                    break;
                case 8:
                    tprof.getMedCondInfo().setAlgType(upd);
                    break;
                default:
                    tprof.getMedCondInfo().setIllType(upd);
            }

            db.writeAllTravProf(db.fileName);
            return true;

        }

        }

    private void prepareExepGUI()
    {
        JFrame excepframe = new JFrame("Update Profile");
        excepframe.setSize(400, 400);
        excepframe.setLayout(null);

        JLabel delheader = new JLabel("Update Profile");
        delheader.setBounds(150, 20, 300, 20);
        excepframe.add(delheader);

        JLabel success = new JLabel("A number was not inserted for trip cost.");
        success.setBounds(90, 60, 300, 20);
        excepframe.add(success);

        JButton btn2 = new JButton("OK");
        btn2.setBounds(140, 100, 100, 20);
        excepframe.add(btn2);

        excepframe.setVisible(true);

        btn2.addActionListener(e -> excepframe.dispose());
    }
}
