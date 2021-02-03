import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;

public class DeleteProfile {

    private JFrame mainFrame;
    private JLabel label1, label2;
    private JTextField field1, field2;
    private JLabel header;
    private TravProfDB db;

    public DeleteProfile(TravProfDB database) {

        db = database;
        prepareGUI();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Delete Profile");
        mainFrame.setSize(400, 400);
        mainFrame.setLayout(null);

        header = new JLabel("Delete Profile");
        header.setBounds(150, 20, 300, 20);
        mainFrame.add(header);

        label1 = new JLabel("Traveler ID: ");
        label1.setBounds(100, 60, 100, 20);
        mainFrame.add(label1);

        label2 = new JLabel("Last Name: ");
        label2.setBounds(100, 85, 100, 20);
        mainFrame.add(label2);

        field1 = new JTextField();
        field1.setBounds(180, 60, 100, 20);
        mainFrame.add(field1);

        field2 = new JTextField();
        field2.setBounds(180, 85, 100, 20);
        mainFrame.add(field2);

        JButton jb = new JButton("Delete");
        jb.setBounds(140, 130, 100, 30);
        mainFrame.add(jb);

        mainFrame.setVisible(true);

        jb.addActionListener(e -> {


            //get the textField value

            String str0 = field1.getText();
            String str1 = field2.getText();

            boolean del = false;
            try {
                del = delTravProf(str0, str1);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            prepare2GUI(del);
            mainFrame.dispose();


        });
    }

    private void prepare2GUI(boolean valid) {

        JFrame delframe = new JFrame("Delete Profile");
        delframe.setSize(400, 400);
        delframe.setLayout(null);

        JLabel delheader = new JLabel("Delete Profile");
        delheader.setBounds(150, 20, 300, 20);
        delframe.add(delheader);

        JLabel success;
        if (valid) {
            success = new JLabel("Profile Deleted!");
        } else {
            success = new JLabel("Profile Not Found.");
        }
        success.setBounds(140, 60, 300, 20);
        delframe.add(success);

        JButton btn = new JButton("OK");
        btn.setBounds(140, 100, 100, 20);
        delframe.add(btn);

        delframe.setVisible(true);

        btn.addActionListener(e -> {

            delframe.dispose();


        });

    }

    public boolean delTravProf(String ID, String lastname) throws IOException {
        if (db.deleteProfile(ID, lastname)) {
            db.writeAllTravProf(db.fileName);
            return true;
        } else {
            return false;
        }
    }
}
