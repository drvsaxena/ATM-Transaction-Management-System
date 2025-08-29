package ASimulatorSystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Signup3 extends JFrame implements ActionListener {

    JRadioButton r1, r2, r3, r4;
    JCheckBox c1, c2, c3, c4, c5, c6, c7;
    JButton submit, cancel;
    String formno;

    Signup3(String formno) {
        this.formno = formno;
        setLayout(null);
        setTitle("NEW ACCOUNT APPLICATION - PAGE 3");

        JLabel l1 = new JLabel("Page 3: Account Details");
        l1.setFont(new Font("Raleway", Font.BOLD, 22));
        l1.setBounds(250, 40, 400, 40);
        add(l1);

        // -------- Account Type --------
        JLabel type = new JLabel("Account Type:");
        type.setFont(new Font("Raleway", Font.BOLD, 18));
        type.setBounds(100, 120, 200, 30);
        add(type);

        r1 = new JRadioButton("Saving Account");
        r1.setFont(new Font("Raleway", Font.PLAIN, 16));
        r1.setBackground(Color.WHITE);
        r1.setBounds(100, 160, 200, 30);
        add(r1);

        r2 = new JRadioButton("Fixed Deposit Account");
        r2.setFont(new Font("Raleway", Font.PLAIN, 16));
        r2.setBackground(Color.WHITE);
        r2.setBounds(350, 160, 250, 30);
        add(r2);

        r3 = new JRadioButton("Current Account");
        r3.setFont(new Font("Raleway", Font.PLAIN, 16));
        r3.setBackground(Color.WHITE);
        r3.setBounds(100, 200, 200, 30);
        add(r3);

        r4 = new JRadioButton("Recurring Deposit Account");
        r4.setFont(new Font("Raleway", Font.PLAIN, 16));
        r4.setBackground(Color.WHITE);
        r4.setBounds(350, 200, 300, 30);
        add(r4);

        ButtonGroup accountGroup = new ButtonGroup();
        accountGroup.add(r1);
        accountGroup.add(r2);
        accountGroup.add(r3);
        accountGroup.add(r4);

        // -------- Card Number --------
        JLabel card = new JLabel("Card Number:");
        card.setFont(new Font("Raleway", Font.BOLD, 18));
        card.setBounds(100, 260, 200, 30);
        add(card);

        JLabel number = new JLabel("XXXX-XXXX-XXXX-1234");
        number.setFont(new Font("Raleway", Font.BOLD, 18));
        number.setBounds(330, 260, 300, 30);
        add(number);

        JLabel carddetail = new JLabel("Your 16 Digit Card Number");
        carddetail.setFont(new Font("Raleway", Font.PLAIN, 12));
        carddetail.setBounds(100, 290, 300, 20);
        add(carddetail);

        // -------- PIN --------
        JLabel pin = new JLabel("PIN:");
        pin.setFont(new Font("Raleway", Font.BOLD, 18));
        pin.setBounds(100, 330, 200, 30);
        add(pin);

        JLabel pnumber = new JLabel("XXXX");
        pnumber.setFont(new Font("Raleway", Font.BOLD, 18));
        pnumber.setBounds(330, 330, 300, 30);
        add(pnumber);

        JLabel pindetail = new JLabel("Your 4 Digit Password");
        pindetail.setFont(new Font("Raleway", Font.PLAIN, 12));
        pindetail.setBounds(100, 360, 300, 20);
        add(pindetail);

        // -------- Services --------
        JLabel services = new JLabel("Services Required:");
        services.setFont(new Font("Raleway", Font.BOLD, 18));
        services.setBounds(100, 410, 200, 30);
        add(services);

        c1 = new JCheckBox("ATM Card");
        c1.setBackground(Color.WHITE);
        c1.setFont(new Font("Raleway", Font.PLAIN, 16));
        c1.setBounds(100, 450, 200, 30);
        add(c1);

        c2 = new JCheckBox("Internet Banking");
        c2.setBackground(Color.WHITE);
        c2.setFont(new Font("Raleway", Font.PLAIN, 16));
        c2.setBounds(350, 450, 200, 30);
        add(c2);

        c3 = new JCheckBox("Mobile Banking");
        c3.setBackground(Color.WHITE);
        c3.setFont(new Font("Raleway", Font.PLAIN, 16));
        c3.setBounds(100, 490, 200, 30);
        add(c3);

        c4 = new JCheckBox("EMAIL Alerts");
        c4.setBackground(Color.WHITE);
        c4.setFont(new Font("Raleway", Font.PLAIN, 16));
        c4.setBounds(350, 490, 200, 30);
        add(c4);

        c5 = new JCheckBox("Cheque Book");
        c5.setBackground(Color.WHITE);
        c5.setFont(new Font("Raleway", Font.PLAIN, 16));
        c5.setBounds(100, 530, 200, 30);
        add(c5);

        c6 = new JCheckBox("E-Statement");
        c6.setBackground(Color.WHITE);
        c6.setFont(new Font("Raleway", Font.PLAIN, 16));
        c6.setBounds(350, 530, 200, 30);
        add(c6);

        c7 = new JCheckBox("I hereby declare that the details entered are correct.");
        c7.setBackground(Color.WHITE);
        c7.setFont(new Font("Raleway", Font.PLAIN, 14));
        c7.setBounds(100, 580, 600, 30);
        add(c7);

        // -------- Buttons --------
        submit = new JButton("Submit");
        submit.setBounds(220, 640, 120, 35);
        submit.setBackground(new Color(0, 153, 255));
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("Raleway", Font.BOLD, 16));
        submit.setFocusPainted(false);
        submit.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(400, 640, 120, 35);
        cancel.setBackground(Color.RED);
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("Raleway", Font.BOLD, 16));
        cancel.setFocusPainted(false);
        cancel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        cancel.addActionListener(this);
        add(cancel);

        getContentPane().setBackground(Color.WHITE);
        setSize(800, 750);
        setLocation(300, 0);
        setVisible(true);
    }

    // 🔹 SHA-256 PIN Hashing
    private String hashPIN(String pin) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(pin.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String atype = null;
            if (r1.isSelected()) atype = "Saving Account";
            else if (r2.isSelected()) atype = "Fixed Deposit Account";
            else if (r3.isSelected()) atype = "Current Account";
            else if (r4.isSelected()) atype = "Recurring Deposit Account";

            // Facilities
            String facility = "";
            if (c1.isSelected()) facility += " ATM Card";
            if (c2.isSelected()) facility += " Internet Banking";
            if (c3.isSelected()) facility += " Mobile Banking";
            if (c4.isSelected()) facility += " EMAIL Alerts";
            if (c5.isSelected()) facility += " Cheque Book";
            if (c6.isSelected()) facility += " E-Statement";

            if (!c7.isSelected()) {
                JOptionPane.showMessageDialog(null, "Please confirm the declaration!");
                return;
            }

            // Generate card & pin
            Random ran = new Random();
            String cardno = "" + Math.abs((ran.nextLong() % 90000000L) + 5040936000000000L);
            String pin = "" + Math.abs((ran.nextLong() % 9000L) + 1000L);

            String hashedPIN = hashPIN(pin);

            try {
                Conn c = new Conn();

                // Insert signup3
                String query1 = "INSERT INTO signup3 (formno, accountType, cardnumber, pin, facility) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement ps1 = c.c.prepareStatement(query1);
                ps1.setString(1, formno);
                ps1.setString(2, atype);
                ps1.setString(3, cardno);
                ps1.setString(4, hashedPIN);
                ps1.setString(5, facility);
                ps1.executeUpdate();

                // Insert login
                String query2 = "INSERT INTO login (formno, cardnumber, pin) VALUES (?, ?, ?)";
                PreparedStatement ps2 = c.c.prepareStatement(query2);
                ps2.setString(1, formno);
                ps2.setString(2, cardno);
                ps2.setString(3, hashedPIN);
                ps2.executeUpdate();

                JOptionPane.showMessageDialog(null, "Card Number: " + cardno + "\n PIN: " + pin);

                setVisible(false);
                new Deposit(pin).setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (ae.getSource() == cancel) {
            setVisible(false);
            new Login().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Signup3("").setVisible(true);
    }
}
