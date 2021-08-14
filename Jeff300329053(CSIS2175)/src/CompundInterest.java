import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.*;
/*
 * Created by JFormDesigner on Sat Aug 14 04:26:45 IST 2021
 */



/**
 * @author unknown
 */
public class CompundInterest extends JFrame {

    Connection1 con = new Connection1();
    Connection conObj = con.connect();

    Savings savings = null;

    String[] savingstype = {"Savings-Regular", "Savings-Deluxe"};

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        CompundInterest form1 = new CompundInterest();
        form1.setVisible(true);
        form1.returnsavingstable();
        
    }
    


    //Method for field validation
    public boolean fieldValidate()
    {
        String custnum = custnumber.getText();
        if(custnum.isEmpty())
        {
            JOptionPane.showMessageDialog(custnumber, "Customer number cannot be empty, try again");
            custnumber.setText("");
            return false;
        }
        else if(!isNumeric(custnum))
        {
            JOptionPane.showMessageDialog(custnumber, "Customer number cannot be text, try again");
            custnumber.setText("");
            return false;
        }
        String custname = customername.getText();
        if(custname.isEmpty())
        {
            JOptionPane.showMessageDialog(customername, "Customer name cannot be empty, try again");
            customername.setText("");
            return false;
        }
        else if(isNumeric(custname))
        {
            JOptionPane.showMessageDialog(customername, "Customer name cannot be a number, try again");
            customername.setText("");
            return false;
        }
        String initialdepstring = initdep.getText();
        double initialdep;
        if(initialdepstring.isEmpty())
        {
            JOptionPane.showMessageDialog(initdep, "Initial Deposit field cannot be empty, try again");
            initdep.setText("");
            return false;
        }
        else if(!isNumeric(initialdepstring))
        {
            JOptionPane.showMessageDialog(initdep, "Initial Deposit cannot be text, try again");
            initdep.setText("");
            return false;
        }
        else
        {
            initialdep = Double.parseDouble(initialdepstring);
        }
        String noofyearsstring = years.getText();
        int noofyears;
        if(noofyearsstring.isEmpty())
        {
            JOptionPane.showMessageDialog(years, "No of Years field cannot be empty, try again");
            years.setText("");
            return false;
        }
        else if(!isNumeric(noofyearsstring))
        {
            JOptionPane.showMessageDialog(years, "No of years cannot be text, try again");
            years.setText("");
            return false;
        }
        else
        {
            noofyears = Integer.parseInt(noofyearsstring);
        }

        String savingstype = (String) stype.getItemAt(stype.getSelectedIndex());

        savings = new Savings(custnum,custname,initialdep,noofyears, savingstype);


        return true;
    }
    public static boolean isNumeric(String value)
    {
        try{
            double a = Double.parseDouble(value);
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }


    public CompundInterest() throws SQLException, ClassNotFoundException {
        initComponents();
    }

    private void stypeActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void button1ActionPerformed(ActionEvent e) throws SQLException {
        boolean isValid = fieldValidate();
        if(isValid)
        {
            PreparedStatement ps = conObj.prepareStatement("Insert into savingstable (custno, custname, cdep, nyears, savtype) values (?,?,?,?,?) ", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, savings.getCustno());
            ps.setString(2, savings.getCustname());
            ps.setDouble(3, savings.getInitialdep());
            ps.setInt(4, savings.getNoofyears());
            ps.setString(5, savings.getSavingstype());

            int rs = ps.executeUpdate();

            returnsavingstable();
        }

        JOptionPane.showMessageDialog(custnumber, "Record added successfully");
    }

    //Update savings table
    public void returnsavingstable() throws SQLException {
        PreparedStatement ps =  conObj.prepareStatement("Select * from savingstable", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = ps.executeQuery();

        DefaultTableModel df = (DefaultTableModel)  table1.getModel();

        rs.last();

        int z = rs.getRow();

        rs.beforeFirst();

        String [][] array = new String[0][4];
        if(z > 0)
        {
            array = new String[z][5];
        }

        int j = 0;

        while (rs.next()){
            array[j][0] = rs.getString("custno");
            array[j][1] = rs.getString("custname");
            array[j][2] = String.valueOf(rs.getDouble("cdep"));
            array[j][3] = String.valueOf(rs.getInt("nyears"));
            array[j][4] = rs.getString("savtype");

            ++j;
        }


        String[] cols = {"Number", "Name", "Deposit", "Years", "Type of Savings"};

        DefaultTableModel model = new DefaultTableModel(array, cols);
        table1.setModel(model);


    }


    private void button2ActionPerformed(ActionEvent e) throws SQLException {
        PreparedStatement ps = conObj.prepareStatement("update savingstable set custno=?, custname=?, cdep=?, nyears=?, savtype=? where custno=?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ps.setString(1, savings.getCustno());
        ps.setString(2, savings.getCustname());
        ps.setDouble(3, savings.getInitialdep());
        ps.setInt(4, savings.getNoofyears());
        ps.setString(5, savings.getSavingstype());
        

        int rs = ps.executeUpdate();

        returnsavingstable();

        JOptionPane.showMessageDialog(custnumber, "Record updated successfully");
    }

    private void button1MouseClicked(MouseEvent e) {


        DefaultTableModel df = (DefaultTableModel) table1.getModel();

        int index = table1.getSelectedRow();
        custnumber.setText(df.getValueAt(index, 0).toString());
        customername.setText(df.getValueAt(index, 1).toString());
        initdep.setText(df.getValueAt(index,2).toString());
        years.setText(df.getValueAt(index, 3).toString());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        label1 = new JLabel();
        custnumber = new JTextField();
        label2 = new JLabel();
        customername = new JTextField();
        label3 = new JLabel();
        initdep = new JTextField();
        label4 = new JLabel();
        years = new JTextField();
        label5 = new JLabel();
        stype = new JComboBox();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        scrollPane2 = new JScrollPane();
        table2 = new JTable();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[253,fill]" +
            "[209,fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- label1 ----
        label1.setText("Enter the Customer Number: ");
        contentPane.add(label1, "cell 0 0");
        contentPane.add(custnumber, "cell 1 0");

        //---- label2 ----
        label2.setText("Enter the Customer Name: ");
        contentPane.add(label2, "cell 0 1");
        contentPane.add(customername, "cell 1 1");

        //---- label3 ----
        label3.setText("Enter the Initial Deposit: ");
        contentPane.add(label3, "cell 0 2");
        contentPane.add(initdep, "cell 1 2");

        //---- label4 ----
        label4.setText("Enter the number of years:");
        contentPane.add(label4, "cell 0 3");
        contentPane.add(years, "cell 1 3");

        //---- label5 ----
        label5.setText("Choose the type of savings");
        contentPane.add(label5, "cell 0 4");
        contentPane.add(stype, "cell 1 4");

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1, "cell 0 5");

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(table2);
        }
        contentPane.add(scrollPane2, "cell 1 5");

        //---- button1 ----
        button1.setText("Add");
        button1.addActionListener(e -> {
            try {
                button1ActionPerformed(e);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
            }
        });
        contentPane.add(button1, "cell 0 6");

        //---- button2 ----
        button2.setText("Edit");
        button2.addActionListener(e -> button2ActionPerformed(e));
        contentPane.add(button2, "cell 0 6");

        //---- button3 ----
        button3.setText("Delete");
        contentPane.add(button3, "cell 0 6");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel label1;
    private JTextField custnumber;
    private JLabel label2;
    private JTextField customername;
    private JLabel label3;
    private JTextField initdep;
    private JLabel label4;
    private JTextField years;
    private JLabel label5;
    private JComboBox stype;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JScrollPane scrollPane2;
    private JTable table2;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
