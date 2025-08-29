package ASimulatorSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Signup2 extends JFrame implements ActionListener {
    
    JTextField panText, aadharText;
    JRadioButton syes, sno, eyes, eno;
    JButton next;
    JComboBox<String> religionCombo, categoryCombo, incomeCombo, educationCombo, occupationCombo;
    String formno;
    
    Signup2(String formno) {
        this.formno = formno;
        
        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 2");
        setLayout(null);
        
        JLabel additionalDetails = new JLabel("Page 2: Additional Details");
        additionalDetails.setFont(new Font("Segoe UI", Font.BOLD, 22));
        additionalDetails.setBounds(250, 50, 400, 30);
        add(additionalDetails);
        
        JLabel religion = new JLabel("Religion:");
        religion.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        religion.setBounds(100, 120, 150, 30);
        add(religion);
        
        String[] valReligion = {"Hindu", "Muslim", "Sikh", "Christian", "Other"};
        religionCombo = new JComboBox<>(valReligion);
        religionCombo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        religionCombo.setBounds(300, 120, 400, 30);
        add(religionCombo);
        
        JLabel category = new JLabel("Category:");
        category.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        category.setBounds(100, 170, 150, 30);
        add(category);
        
        String[] valCategory = {"General", "OBC", "SC", "ST", "Other"};
        categoryCombo = new JComboBox<>(valCategory);
        categoryCombo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        categoryCombo.setBounds(300, 170, 400, 30);
        add(categoryCombo);
        
        JLabel income = new JLabel("Income:");
        income.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        income.setBounds(100, 220, 150, 30);
        add(income);
        
        String[] valIncome = {"Null", "<1,50,000", "<2,50,000", "<5,00,000", "Upto 10,00,000"};
        incomeCombo = new JComboBox<>(valIncome);
        incomeCombo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        incomeCombo.setBounds(300, 220, 400, 30);
        add(incomeCombo);
        
        JLabel education = new JLabel("Educational Qualification:");
        education.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        education.setBounds(100, 270, 250, 30);
        add(education);
        
        String[] valEducation = {"Non-Graduate", "Graduate", "Post-Graduate", "Doctorate", "Other"};
        educationCombo = new JComboBox<>(valEducation);
        educationCombo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        educationCombo.setBounds(300, 310, 400, 30);
        add(educationCombo);
        
        JLabel occupation = new JLabel("Occupation:");
        occupation.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        occupation.setBounds(100, 360, 200, 30);
        add(occupation);
        
        String[] valOccupation = {"Salaried", "Self-Employed", "Business", "Student", "Retired", "Other"};
        occupationCombo = new JComboBox<>(valOccupation);
        occupationCombo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        occupationCombo.setBounds(300, 360, 400, 30);
        add(occupationCombo);
        
        JLabel pan = new JLabel("PAN Number:");
        pan.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        pan.setBounds(100, 410, 200, 30);
        add(pan);
        
        panText = new JTextField();
        panText.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        panText.setBounds(300, 410, 400, 30);
        add(panText);
        
        JLabel aadhar = new JLabel("Aadhar Number:");
        aadhar.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        aadhar.setBounds(100, 460, 200, 30);
        add(aadhar);
        
        aadharText = new JTextField();
        aadharText.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        aadharText.setBounds(300, 460, 400, 30);
        add(aadharText);
        
        JLabel senior = new JLabel("Senior Citizen:");
        senior.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        senior.setBounds(100, 510, 200, 30);
        add(senior);
        
        syes = new JRadioButton("Yes");
        syes.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        syes.setBounds(300, 510, 100, 30);
        add(syes);
        
        sno = new JRadioButton("No");
        sno.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        sno.setBounds(450, 510, 100, 30);
        add(sno);
        
        ButtonGroup seniorGroup = new ButtonGroup();
        seniorGroup.add(syes);
        seniorGroup.add(sno);
        
        JLabel existing = new JLabel("Existing Account:");
        existing.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        existing.setBounds(100, 560, 200, 30);
        add(existing);
        
        eyes = new JRadioButton("Yes");
        eyes.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        eyes.setBounds(300, 560, 100, 30);
        add(eyes);
        
        eno = new JRadioButton("No");
        eno.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        eno.setBounds(450, 560, 100, 30);
        add(eno);
        
        ButtonGroup existingGroup = new ButtonGroup();
        existingGroup.add(eyes);
        existingGroup.add(eno);
        
        next = new JButton("Next");
        next.setFont(new Font("Segoe UI", Font.BOLD, 16));
        next.setBackground(new Color(66, 133, 244));
        next.setForeground(Color.WHITE);
        next.setFocusPainted(false);
        next.setBounds(620, 620, 100, 40);
        next.addActionListener(this);
        add(next);
        
        getContentPane().setBackground(Color.WHITE);
        
        setSize(850, 750);
        setLocation(350, 10);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        String religion = (String) religionCombo.getSelectedItem();
        String category = (String) categoryCombo.getSelectedItem();
        String income = (String) incomeCombo.getSelectedItem();
        String education = (String) educationCombo.getSelectedItem();
        String occupation = (String) occupationCombo.getSelectedItem();
        String pan = panText.getText();
        String aadhar = aadharText.getText();
        String seniorCitizen = (syes.isSelected()) ? "Yes" : "No";
        String existingAccount = (eyes.isSelected()) ? "Yes" : "No";
        
        try {
            Conn c = new Conn();
            String query = "INSERT INTO signup2 (formno, religion, category, income, education, occupation, pan, aadhar, seniorcitizen, existingaccount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = c.c.prepareStatement(query);
            ps.setString(1, formno);
            ps.setString(2, religion);
            ps.setString(3, category);
            ps.setString(4, income);
            ps.setString(5, education);
            ps.setString(6, occupation);
            ps.setString(7, pan);
            ps.setString(8, aadhar);
            ps.setString(9, seniorCitizen);
            ps.setString(10, existingAccount);
            
            ps.executeUpdate();
            
            setVisible(false);
            new Signup3(formno).setVisible(true);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        new Signup2("").setVisible(true);
    }
}
