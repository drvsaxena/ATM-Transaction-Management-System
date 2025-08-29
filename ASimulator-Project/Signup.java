package ASimulatorSystem;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Random;

public class Signup extends JFrame implements ActionListener {

    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14, l15;
    JTextField t1, t2, t3, t4, t5, t6, t7;
    JRadioButton r1, r2, r3, r4, r5;
    JButton next;
    JComboBox<String> c1, c2, c3;

    Random ran = new Random();
    long first4 = (ran.nextLong() % 9000L) + 1000L;
    String first = " " + Math.abs(first4);

    Signup() {
        super("APPLICATION FORM");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("ASimulatorSystem/icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l11 = new JLabel(i3);
        l11.setBounds(20, 20, 100, 100);
        add(l11);

        l1 = new JLabel("APPLICATION FORM NO." + first);
        l1.setBounds(160, 20, 600, 40);
        l1.setFont(new Font("Raleway", Font.BOLD, 38));
        add(l1);

        l2 = new JLabel("Page 1: Personal Details");
        l2.setBounds(290, 80, 600, 30);
        l2.setFont(new Font("Raleway", Font.BOLD, 22));
        add(l2);

        l3 = new JLabel("Name:");
        l3.setFont(new Font("Raleway", Font.BOLD, 20));
        l3.setBounds(100, 140, 100, 30);
        add(l3);

        t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.PLAIN, 18));
        t1.setBounds(300, 140, 400, 30);
        add(t1);

        l4 = new JLabel("Father's Name:");
        l4.setFont(new Font("Raleway", Font.BOLD, 20));
        l4.setBounds(100, 190, 200, 30);
        add(l4);

        t2 = new JTextField();
        t2.setFont(new Font("Raleway", Font.PLAIN, 18));
        t2.setBounds(300, 190, 400, 30);
        add(t2);

        l5 = new JLabel("Date of Birth:");
        l5.setFont(new Font("Raleway", Font.BOLD, 20));
        l5.setBounds(100, 240, 200, 30);
        add(l5);

        String date[] = new String[31];
        for (int i = 1; i <= 31; i++) date[i-1] = "" + i;
        c1 = new JComboBox<>(date);
        c1.setBounds(300, 240, 60, 30);
        add(c1);

        String month[] = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        c2 = new JComboBox<>(month);
        c2.setBounds(370, 240, 100, 30);
        add(c2);

        String year[] = new String[70];
        for (int i = 1951; i <= 2020; i++) year[i-1951] = "" + i;
        c3 = new JComboBox<>(year);
        c3.setBounds(480, 240, 100, 30);
        add(c3);

        l6 = new JLabel("Gender:");
        l6.setFont(new Font("Raleway", Font.BOLD, 20));
        l6.setBounds(100, 290, 200, 30);
        add(l6);

        r1 = new JRadioButton("Male");
        r2 = new JRadioButton("Female");
        r3 = new JRadioButton("Other");
        ButtonGroup bg = new ButtonGroup();
        bg.add(r1); bg.add(r2); bg.add(r3);

        r1.setBounds(300, 290, 80, 30);
        r2.setBounds(400, 290, 80, 30);
        r3.setBounds(500, 290, 100, 30);
        r1.setBackground(Color.WHITE);
        r2.setBackground(Color.WHITE);
        r3.setBackground(Color.WHITE);

        add(r1); add(r2); add(r3);

        l7 = new JLabel("Email Address:");
        l7.setFont(new Font("Raleway", Font.BOLD, 20));
        l7.setBounds(100, 340, 200, 30);
        add(l7);

        t3 = new JTextField();
        t3.setFont(new Font("Raleway", Font.PLAIN, 18));
        t3.setBounds(300, 340, 400, 30);
        add(t3);

        l8 = new JLabel("Marital Status:");
        l8.setFont(new Font("Raleway", Font.BOLD, 20));
        l8.setBounds(100, 390, 200, 30);
        add(l8);

        r4 = new JRadioButton("Married");
        r5 = new JRadioButton("Unmarried");
        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(r4); bg2.add(r5);

        r4.setBounds(300, 390, 100, 30);
        r5.setBounds(420, 390, 120, 30);
        r4.setBackground(Color.WHITE);
        r5.setBackground(Color.WHITE);

        add(r4); add(r5);

        l9 = new JLabel("Address:");
        l9.setFont(new Font("Raleway", Font.BOLD, 20));
        l9.setBounds(100, 440, 200, 30);
        add(l9);

        t4 = new JTextField();
        t4.setFont(new Font("Raleway", Font.PLAIN, 18));
        t4.setBounds(300, 440, 400, 30);
        add(t4);

        l10 = new JLabel("City:");
        l10.setFont(new Font("Raleway", Font.BOLD, 20));
        l10.setBounds(100, 490, 200, 30);
        add(l10);

        t5 = new JTextField();
        t5.setFont(new Font("Raleway", Font.PLAIN, 18));
        t5.setBounds(300, 490, 400, 30);
        add(t5);

        l11 = new JLabel("State:");
        l11.setFont(new Font("Raleway", Font.BOLD, 20));
        l11.setBounds(100, 540, 200, 30);
        add(l11);

        t6 = new JTextField();
        t6.setFont(new Font("Raleway", Font.PLAIN, 18));
        t6.setBounds(300, 540, 400, 30);
        add(t6);

        l12 = new JLabel("Pin Code:");
        l12.setFont(new Font("Raleway", Font.BOLD, 20));
        l12.setBounds(100, 590, 200, 30);
        add(l12);

        t7 = new JTextField();
        t7.setFont(new Font("Raleway", Font.PLAIN, 18));
        t7.setBounds(300, 590, 400, 30);
        add(t7);

        next = new JButton("NEXT ➝");
        next.setFont(new Font("Raleway", Font.BOLD, 18));
        next.setBackground(new Color(34,167,240));
        next.setForeground(Color.WHITE);
        next.setFocusPainted(false);
        next.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));

        // Hover effect
        next.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                next.setBackground(new Color(52,152,219));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                next.setBackground(new Color(34,167,240));
            }
        });

        next.setBounds(620, 640, 120, 40);
        add(next);

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(850, 800);
        setLocation(400, 50);
        setVisible(true);

        next.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        String formno = first;
        String name = t1.getText();
        String fname = t2.getText();
        String dob = c1.getSelectedItem() + "/" + c2.getSelectedItem() + "/" + c3.getSelectedItem();
        String gender = null;
        if (r1.isSelected()) gender = "Male";
        else if (r2.isSelected()) gender = "Female";
        else if (r3.isSelected()) gender = "Other";

        String email = t3.getText();
        String marital = null;
        if (r4.isSelected()) marital = "Married";
        else if (r5.isSelected()) marital = "Unmarried";

        String address = t4.getText();
        String city = t5.getText();
        String state = t6.getText();
        String pin = t7.getText();

        try {
            if (name.equals("") || fname.equals("") || email.equals("") || pin.equals("")) {
                JOptionPane.showMessageDialog(null, "All fields are required");
            } else {
                Conn c1 = new Conn();
                String query = "INSERT INTO signup(formno, name, father_name, dob, gender, email, marital_status, address, city, pincode,state) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pstmt = c1.c.prepareStatement(query);
                pstmt.setString(1, formno);
                pstmt.setString(2, name);
                pstmt.setString(3, fname);
                pstmt.setString(4, dob);
                pstmt.setString(5, gender);
                pstmt.setString(6, email);
                pstmt.setString(7, marital);
                pstmt.setString(8, address);
                pstmt.setString(9, city);
                pstmt.setString(10, state);
                pstmt.setString(11, pin);

                pstmt.executeUpdate();
                new Signup2(formno).setVisible(true);
                setVisible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Signup().setVisible(true);
    }
}
