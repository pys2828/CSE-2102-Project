import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CreateProfile {
    private JFrame mainFrame;
    private JLabel[] labels;
    private JTextField[] fields;
    private JLabel header;
    private JComboBox jcb6, jcb7, jcb10, jcb11;
    private TravProfDB db;


    //create a constructor
    public CreateProfile(TravProfDB database){
        db = database;
        prepareGUI();
    }

    //prepares the mainframe's GUI with all the inputs necessary for creating a profile
    private void prepareGUI(){
        mainFrame = new JFrame("Create Profile");
        mainFrame.setSize(400,400);
        mainFrame.setLayout(null);

        header = new JLabel("Create Profile");
        header.setBounds(100,20,300,20);
        mainFrame.add(header);

        //add label 1

        String[] options = {
                "Traveler ID: ",
                "First Name: ",
                "Last Name: ",
                "Address: ",
                "Phone: ",
                "Trip Cost: ",
                "Travel Type: ",
                "Payment Type: ",
                "Doctor's Name: ",
                "Doctor's Phone: ",
                "Allergy Type: ",
                "Illness Type: ",
        };

        labels = new JLabel[options.length];
        for(int i = 0; i < options.length; i++)
        {
            labels[i] = new JLabel(options[i]);
            labels[i].setBounds(20, 60+(i*20), 140, 20);
            mainFrame.add(labels[i]);
        }

        //TEXTFIELDS

        String[] travellist = {"Business", "Pleasure"};
        String[] paylist = {"Credit", "Check", "Debit","Invoice"};
        String[] alglist = {"None", "Food", "Medication", "Other"};
        String[] illlist = {"None", "Heart", "Diabetes", "Asthma", "Other"};

        fields = new JTextField[options.length];
        for (int j = 0; j < options.length; j++)
        {
            int y = 60 + (j * 20);
            if (j != 6 & j != 7 & j != 10 & j != 11)
            {
                fields[j] = new JTextField();
                fields[j].setBounds(140, y, 120, 20);
                mainFrame.add(fields[j]);
            }
            else
                //JCOMBOBOXES
            {
                if (j == 6)
                {
                    jcb6 = new JComboBox(travellist);
                    jcb6.setBounds(140, y, 120, 20);
                    mainFrame.add(jcb6);
                }
                else if (j == 7)
                {
                    jcb7 = new JComboBox(paylist);
                    jcb7.setBounds(140, y, 120, 20);
                    mainFrame.add(jcb7);
                }
                else if (j == 10)
                {
                    jcb10 = new JComboBox(alglist);
                    jcb10.setBounds(140, y, 120, 20);
                    mainFrame.add(jcb10);
                }
                else if (j == 11)
                {
                    jcb11 = new JComboBox(illlist);
                    jcb11.setBounds(140, y, 120, 20);
                    mainFrame.add(jcb11);
                }
            }
        }

        //add button
        JButton btn = new JButton("Submit");
        btn.setBounds(100,330,100,30);
        mainFrame.add(btn);

        mainFrame.setVisible(true);



        //add listeners to button
        btn.addActionListener(e -> {

            //getting the textfield and jcombobox values
            String str0 = fields[0].getText();
            String str1 = fields[1].getText();
            String str2 = fields[2].getText();
            String str3 = fields[3].getText();
            String str4 = fields[4].getText();
            String str5 = fields[5].getText();
            String str6;
            int str6ind = jcb6.getSelectedIndex();
            if (str6ind == 0)
            {str6 = "Business";}
            else {str6 = "Pleasure";}
            int str7ind = jcb7.getSelectedIndex();
            String str7;
            switch(str7ind)
            {
                case 0:
                    str7 = "Credit";
                    break;
                case 1:
                    str7 = "Check";
                    break;
                case 2:
                    str7 = "Debit";
                    break;
                default:
                    str7 = "Invoice";

            }
            String str8 = fields[8].getText();
            String str9 = fields[9].getText();
            int str10ind = jcb10.getSelectedIndex();
            String str10;
            switch (str10ind)
            {
                case 0:
                    str10 = "None";
                    break;
                case 1:
                    str10 = "Food";
                    break;
                case 2:
                    str10 = "Medication";
                    break;
                default:
                    str10 = "Other";
            }
            int str11ind = jcb11.getSelectedIndex();
            String str11;
            switch (str11ind)
            {
                case 0:
                    str11 = "None";
                    break;
                case 1:
                    str11 = "Heart";
                    break;
                case 2:
                    str11 = "Diabetes";
                    break;
                case 3:
                    str11 = "Asthma";
                    break;
                default:
                    str11 = "Other";
            }

            //create the new profile and indicate that it's been created. Dispose of the mainframe.
            MedCond medcon = createMedCond(str8, str9, str10, str11);
            try {
                TravProf profile = createTravProf(str0, str1, str2, str3, str4, Float.parseFloat(str5), str6, str7, medcon);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            //call display profile
            try {
                db.writeAllTravProf(db.fileName);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            prepare2GUI();
            mainFrame.dispose();

        });

    }

    public MedCond createMedCond (String mdContact, String mdPhone, String algType, String illType)
    {
        return new MedCond(mdContact, mdPhone, algType, illType);
    }

    public TravProf createTravProf(String s0, String s1, String s2, String s3, String s4, Float s5, String s6, String s7, MedCond medcon) throws IOException {
        TravProf prof = new TravProf(s0, s1, s2, s3, s4, s5, s6, s7, medcon);
        db.insertNewProfile(prof);
        return prof;

    }

    private void prepare2GUI() {

        JFrame createframe = new JFrame("Create Profile");
        createframe.setSize(400, 400);
        createframe.setLayout(null);

        JLabel delheader = new JLabel("Create Profile");
        delheader.setBounds(150, 20, 300, 20);
        createframe.add(delheader);

        JLabel success = new JLabel("Profile Created!");
        success.setBounds(140, 60, 300, 20);
        createframe.add(success);

        JButton btn = new JButton("OK");
        btn.setBounds(140, 100, 100, 20);
        createframe.add(btn);

        createframe.setVisible(true);

        btn.addActionListener(e -> {
            createframe.dispose();

        });

    }
}
