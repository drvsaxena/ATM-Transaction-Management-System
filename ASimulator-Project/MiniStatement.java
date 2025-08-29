package ASimulatorSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.sql.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;


import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;

public class MiniStatement extends JFrame implements ActionListener {
    JButton exitBtn, exportPdfBtn;
    JLabel bankNameLbl, cardLbl, balanceLbl;
    JTable table;
    DefaultTableModel model;
    String pin;
    int currentBalance = 0;
    String cardNumber = "";

    MiniStatement(String pin) {
        this.pin = pin;
        super.setTitle("ATM");
        getContentPane().setBackground(Color.WHITE);
        setSize(550, 650);
        setLocation(20, 20);
        setLayout(null);

        // Bank name
        bankNameLbl = new JLabel("Slip", JLabel.CENTER);
        bankNameLbl.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
        bankNameLbl.setBounds(175, 20, 200, 30);
        add(bankNameLbl);

        // Card Number
        cardLbl = new JLabel();
        cardLbl.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        cardLbl.setBounds(20, 70, 500, 20);
        add(cardLbl);

        // Balance
        balanceLbl = new JLabel();
        balanceLbl.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        balanceLbl.setBounds(20, 500, 400, 20);
        add(balanceLbl);

        // Table for transactions
        String[] columns = {"Date", "Type", "Amount"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        table.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 12));
        table.getTableHeader().setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 120, 490, 350);
        add(scrollPane);

        // Export to PDF button
        exportPdfBtn = new JButton("Export to PDF");
        exportPdfBtn.setBackground(new Color(220, 20, 60));
        exportPdfBtn.setForeground(Color.WHITE);
        exportPdfBtn.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        exportPdfBtn.setBounds(20, 530, 150, 40);
        exportPdfBtn.addActionListener(this);
        add(exportPdfBtn);

        // Exit button
        exitBtn = new JButton("Exit");
        exitBtn.setBackground(new Color(128, 128, 128));
        exitBtn.setForeground(Color.WHITE);
        exitBtn.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        exitBtn.setBounds(410, 530, 100, 40);
        exitBtn.addActionListener(this);
        add(exitBtn);

        // Load data
        loadData(pin);
        setVisible(true);
    }

    private void loadData(String pin) {
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM login WHERE pin = '" + pin + "'");
            while (rs.next()) {
                cardNumber = rs.getString("cardnumber");
                cardLbl.setText("Card Number: " + cardNumber.substring(0, 4) + "XXXXXXXX" + cardNumber.substring(12));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            currentBalance = 0;
            Conn c1 = new Conn();
            ResultSet rs = c1.s.executeQuery("SELECT * FROM bank WHERE pin = '" + pin + "' ORDER BY date DESC");
            while (rs.next()) {
                // Add row to table
                model.addRow(new Object[]{
                    rs.getString("date"),
                    rs.getString("type"),
                    "Rs. " + rs.getString("amount")
                });

                // Calculate balance
                if (rs.getString("type").equals("Deposit")) {
                    currentBalance += Integer.parseInt(rs.getString("amount"));
                } else {
                    currentBalance -= Integer.parseInt(rs.getString("amount"));
                }
            }
            balanceLbl.setText("Your Current Balance is Rs " + currentBalance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Export to PDF method
    private void exportToPDF() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save PDF File");
        
        // Set default filename with timestamp
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String defaultName = "Statement_" + cardNumber.substring(12) + "_" + timestamp + ".pdf";
        fileChooser.setSelectedFile(new java.io.File(defaultName));
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Files (*.pdf)", "pdf");
        fileChooser.setFileFilter(filter);

        int userSelection = fileChooser.showSaveDialog(this);
        
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            java.io.File fileToSave = fileChooser.getSelectedFile();
            
            // Ensure file has .pdf extension
            if (!fileToSave.getName().toLowerCase().endsWith(".pdf")) {
                fileToSave = new java.io.File(fileToSave.getParentFile(), fileToSave.getName() + ".pdf");
            }
            
            try {
                Document document = new Document();
                PdfWriter.getInstance(document, new java.io.FileOutputStream(fileToSave));
                document.open();

                // Bank header
                com.itextpdf.text.Font titleFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 18, com.itextpdf.text.Font.BOLD);
                Paragraph title = new Paragraph("ATM", titleFont);
                title.setAlignment(Element.ALIGN_CENTER);
                document.add(title);

                Paragraph subtitle = new Paragraph("Slip", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 14, com.itextpdf.text.Font.BOLD));
                subtitle.setAlignment(Element.ALIGN_CENTER);
                document.add(subtitle);
                
                document.add(new Paragraph(" ")); // Empty line

                // Account details
                document.add(new Paragraph("Card Number: " + cardNumber.substring(0, 4) + "XXXXXXXX" + cardNumber.substring(12)));
                document.add(new Paragraph("Statement Date: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())));
                document.add(new Paragraph("Current Balance: Rs " + currentBalance));
                document.add(new Paragraph(" ")); // Empty line

                // Transaction table
                PdfPTable pdfTable = new PdfPTable(3);
                pdfTable.setWidthPercentage(100);

                // Table headers
                PdfPCell headerCell1 = new PdfPCell(new Phrase("Date", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 12, com.itextpdf.text.Font.BOLD)));
                PdfPCell headerCell2 = new PdfPCell(new Phrase("Transaction Type", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 12, com.itextpdf.text.Font.BOLD)));
                PdfPCell headerCell3 = new PdfPCell(new Phrase("Amount", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 12, com.itextpdf.text.Font.BOLD)));
                
                headerCell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                headerCell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
                headerCell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
                
                headerCell1.setPadding(8);
                headerCell2.setPadding(8);
                headerCell3.setPadding(8);
                
                pdfTable.addCell(headerCell1);
                pdfTable.addCell(headerCell2);
                pdfTable.addCell(headerCell3);

                // Table data
                for (int row = 0; row < table.getRowCount(); row++) {
                    for (int col = 0; col < table.getColumnCount(); col++) {
                        Object value = table.getValueAt(row, col);
                        PdfPCell cell = new PdfPCell(new Phrase(value != null ? value.toString() : ""));
                        cell.setPadding(5);
                        pdfTable.addCell(cell);
                    }
                }

                document.add(pdfTable);
                document.add(new Paragraph(" ")); // Empty line
                
                // Footer
                Paragraph footer = new Paragraph("Generated on: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
                footer.setAlignment(Element.ALIGN_RIGHT);
                footer.setFont(new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 10, com.itextpdf.text.Font.ITALIC));
                document.add(footer);
                
                document.close();

                JOptionPane.showMessageDialog(this, 
                    "PDF file exported successfully!\nLocation: " + fileToSave.getAbsolutePath(), 
                    "Export Successful", 
                    JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, 
                    "Error exporting to PDF: " + e.getMessage(), 
                    "Export Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == exitBtn) {
            this.setVisible(false);
        } else if (ae.getSource() == exportPdfBtn) {
            exportToPDF();
        }
    }

    public static void main(String[] args) {
        new MiniStatement("").setVisible(true);
    }
}
