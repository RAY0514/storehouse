import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.logging.Level;

public class tutorial extends JFrame {
    private JLabel number;
    private JPanel panel;
    private JLabel pcs;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTable table1;
    private JLabel address;
    private JButton addNewDataButton;
    private JButton resetButton;
    private JButton deletButton;
    private JButton reorganizeButton;
    private JButton updateButton;

    public void update() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/swing_demo2", "root", "123456");
            Statement st = connection.createStatement();
            String query = "select * from catalog";
            ResultSet rs = st.executeQuery(query);//執行後放到結果集
            //結果集         //執行
            /*
            結果集(ResultSet)是資料中查詢結果返回的一種物件，可以說結果集是一個儲存查詢結果的物件，但是結
            果集並不僅僅具有儲存的功能，他同時還具有操縱資料的功能，可能完成對資料的更新等。
            */
            ResultSetMetaData rsmd = rs.getMetaData();//取得數據 //rsmd數據
            DefaultTableModel mode1 = (DefaultTableModel) table1.getModel();
            //獲取列名//找多少列
            int cols = rsmd.getColumnCount();
            String[] colname = new String[cols];
            for (int i = 0; i < cols; i++)
                colname[i] = rsmd.getCatalogName(i + 1);
            mode1.setColumnIdentifiers(colname);
            String number, name, pcs, address;
            while (rs.next()) {
                number = rs.getString(1);
                name = rs.getString(2);
                pcs = rs.getString(3);
                address = rs.getString(4);
                String[] row = {number, name, pcs, address};
                mode1.addRow(row);
            }
            st.close();
            connection.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {
        tutorial tutorial = new tutorial();
    }

    public tutorial() {
        setSize(700, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setContentPane(panel);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/swing_demo2", "root", "123456");
            Statement st = connection.createStatement();
            String query = "select * from catalog";
            ResultSet rs = st.executeQuery(query);//執行後放到結果集
            //結果集         //執行

                    /*
                    結果集(ResultSet)是資料中查詢結果返回的一種物件，可以說結果集是一個儲存查詢結果的物件，但是結
                    果集並不僅僅具有儲存的功能，他同時還具有操縱資料的功能，可能完成對資料的更新等。
                    */
            ResultSetMetaData rsmd = rs.getMetaData();//取得數據 //rsmd數據
            DefaultTableModel mode1 = (DefaultTableModel) table1.getModel();
            //獲取列名//找多少列
            int cols = rsmd.getColumnCount();
            String[] colname = new String[cols];
            for (int i = 0; i < cols; i++)
                colname[i] = rsmd.getCatalogName(i + 1);
            mode1.setColumnIdentifiers(colname);
            String number, name, pcs, address;
            while (rs.next()) {
                number = rs.getString(1);
                name = rs.getString(2);
                pcs = rs.getString(3);
                address = rs.getString(4);
                String[] row = {number, name, pcs, address};
                mode1.addRow(row);
            }
            st.close();
            connection.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        reorganizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/swing_demo2", "root", "123456");

                    Statement st = connection.createStatement();

                    String query = "select * from catalog";
                    ResultSet rs = st.executeQuery(query);//執行後放到結果集
                    //結果集         //執行

                    ResultSetMetaData rsmd = rs.getMetaData();//取得數據 //rsmd數據
                    DefaultTableModel mode1 = (DefaultTableModel) table1.getModel();
                    mode1.getDataVector().clear();
                    //獲取列名//找多少列
                    int cols = rsmd.getColumnCount();
                    String[] colname = new String[cols];
                    for (int i = 0; i < cols; i++)
                        colname[i] = rsmd.getCatalogName(i + 1);
                    mode1.setColumnIdentifiers(colname);
                    String number, name, pcs, address;
                    while (rs.next()) {
                        number = rs.getString(1);
                        name = rs.getString(2);
                        pcs = rs.getString(3);
                        address = rs.getString(4);
                        String[] row = {number, name, pcs, address};
                        mode1.addRow(row);
                    }
                    st.close();
                    connection.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
                resetButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        textField1.setText("");
                        textField2.setText("");
                        textField3.setText("");
                        textField4.setText("");
                    }
                });

                addNewDataButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String Number = textField1.getText();
                        String Name = textField2.getText();
                        String Pcs = textField3.getText();
                        String Address = textField4.getText();
                        String msg = " \n";
                        try {
                            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/swing_demo2", "root", "123456");
                            String query = "INSERT INTO catalog values('" + Number + "','" + Name + "','" + Pcs + "','" +
                                    Address + "' )";
                            Statement sta = connection.createStatement();
                            int x = sta.executeUpdate(query);
                            if (x == 0) {
                                JOptionPane.showMessageDialog(addNewDataButton, "This is alredy exist");
                            } else {
                                JOptionPane.showMessageDialog(addNewDataButton, "Your account is sucessfully created");
                            }
                            connection.close();
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                });

                updateButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String Number = textField1.getText();
                        String Name = textField2.getText();
                        String Pcs = textField3.getText();
                        String Address = textField4.getText();
                        try {
                            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/swing_demo2", "root", "123456");
                            String query = "update  catalog set name = '" + Name + "' , pcs = '" + Pcs + "',address='" +
                                    Address + "' where number='" + Number + "' ";
                            Statement sta = connection.createStatement();
                            sta.executeUpdate(query);
                            connection.close();
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                });

                table1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        DefaultTableModel mode1 = (DefaultTableModel) table1.getModel();
                        int selectedrows = table1.getSelectedRow();
                        textField1.setText(mode1.getValueAt(selectedrows, 0).toString());
                        textField2.setText(mode1.getValueAt(selectedrows, 1).toString());
                        textField3.setText(mode1.getValueAt(selectedrows, 2).toString());
                        textField4.setText(mode1.getValueAt(selectedrows, 3).toString());
                    }
                });
        deletButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Number = textField1.getText();
                String Name = textField2.getText();
                String Pcs = textField3.getText();
                String Address = textField4.getText();
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/swing_demo2", "root", "123456");
                    String query = "delete from catalog  where number='" + Number + "' ";
                    Statement sta = connection.createStatement();
                    sta.executeUpdate(query);
                    connection.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }
        }
