import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindProfile {

    private JFrame mainFrame;
    private JLabel label1, label2, label3;
    private JTextField field1, field2;
    private JComboBox field3;
    private JLabel header;
    private TravProfDB db;

    public FindProfile(TravProfDB database) {

        db = database;
        prepareGUI();
    }

    private void prepareGUI()
    {
        mainFrame = new JFrame("Find Profile");
        mainFrame.setSize(400,400);
        mainFrame.setLayout(null);

        header = new JLabel("Find Profile");
        header.setBounds(150, 20, 300, 20);
        mainFrame.add(header);

        label1 = new JLabel("Travel Agent ID: ");
        label1.setBounds(50, 60, 150, 20);
        mainFrame.add(label1);

        label2 = new JLabel("Last Name: ");
        label2.setBounds(50, 85, 150, 20);
        mainFrame.add(label2);

        field1 = new JTextField();
        field1.setBounds(180, 60, 140, 20);
        mainFrame.add(field1);

        field2 = new JTextField();
        field2.setBounds(180, 85, 140, 20);
        mainFrame.add(field2);

        JButton jb = new JButton("Find");
        jb.setBounds(140, 145, 100, 30);
        mainFrame.add(jb);

        mainFrame.setVisible(true);

        jb.addActionListener(e -> {

            String str1 = field1.getText();
            String str2 = field2.getText();
            boolean valid = findProfile(str1, str2);
            if (valid == false)
            {
                prepare2GUI();
            }
            mainFrame.dispose();
        });
    }

    public boolean findProfile(String ID, String lastname) {
        TravProf tprof = db.findProfile(ID, lastname);
        if (tprof != null) {
            DisplayProfile display = new DisplayProfile(tprof);
            return true;
        } else {
            return false;
        }
    }

    private void prepare2GUI()
    {
        JFrame findFrame = new JFrame("Find Profile");
        findFrame.setSize(400,400);
        findFrame.setLayout(null);

        header = new JLabel("Find Profile");
        header.setBounds(150, 20, 300, 20);
        findFrame.add(header);

        JLabel success = new JLabel("Profile was not found.");
        success.setBounds(90, 60, 300, 20);
        findFrame.add(success);

        JButton btn = new JButton("OK");
        btn.setBounds(140, 100, 100, 20);
        findFrame.add(btn);

        findFrame.setVisible(true);

        btn.addActionListener(e -> findFrame.dispose());
    }
}
