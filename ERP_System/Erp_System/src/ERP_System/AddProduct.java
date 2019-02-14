package ERP_System;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddProduct extends JFrame implements ActionListener {

    JLabel l1, l2, l3, l4, l5;
    JButton btn;
    JTextField pid, pname, quantity, price, tax;
    String name;
    int id, quant, pr, tx, cid;

    public AddProduct(int cid) {
        this.cid = cid;
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setSize(400, 650);
        setVisible(true);
        setLayout(gbl);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBackground(Color.WHITE);
        l1 = new JLabel("Product ID");
        l2 = new JLabel("Product Name");
        l3 = new JLabel("Quantity");
        l4 = new JLabel("Price");
        l5 = new JLabel("Tax Percent");

        btn = new JButton("Add Product");
        btn.addActionListener(this);

        pid = new JTextField(10);
        pname = new JTextField(10);
        quantity = new JTextField(10);
        price = new JTextField(10);
        tax = new JTextField(10);

        gbc.weighty = 0.001;

        //First Column
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(l1, gbc);
        gbc.gridy = 1;
        add(l2, gbc);
        gbc.gridy = 2;
        add(l3, gbc);
        gbc.gridy = 3;
        add(l4, gbc);
        gbc.gridy = 4;
        add(l5, gbc);

        //Second Column
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(pid, gbc);
        gbc.gridy = 1;
        add(pname, gbc);
        gbc.gridy = 2;
        add(quantity, gbc);
        gbc.gridy = 3;
        add(price, gbc);
        gbc.gridy = 4;
        add(tax, gbc);

        //Button
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(btn, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(cid);
        id = Integer.parseInt(pid.getText());
        name = pname.getText();
        quant = Integer.parseInt(quantity.getText());
        pr = Integer.parseInt(price.getText());
        tx = Integer.parseInt(tax.getText());
        System.out.println(name);
        String query = "Insert into Product (P_ID,C_ID,P_Name,Price,Tax,Quantity) values ( ? , ? , ? , ? , ? , ?)";
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://src\\ERP_System\\Database\\ERPdb.accdb");
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1,id);
            stmt.setInt(2,cid);
            System.out.println(stmt);
            stmt.setString(3, name); // error is triggerred by this statement
            System.out.println(stmt);
            stmt.setInt(4,pr);
            stmt.setInt(5,tx);
            stmt.setInt(6,quant);
            System.out.println("Hello3");
            ResultSet rs;
            stmt.execute();    
            System.out.println("Hello3");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }
}