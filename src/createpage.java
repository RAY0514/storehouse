import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class createpage extends JFrame {



    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    createpage frame = new createpage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    JFrame frame=new JFrame();

    //標籤
    JLabel IDlable= new JLabel("ID");
    JLabel namelable= new JLabel("name");
    JLabel passwordlable = new JLabel("password");

    //輸入框
    JTextField IDfield= new JTextField();
    JTextField namefield= new JTextField();
    JTextField passwordfield= new JTextField();

    //按鈕
    JButton resetbutton = new JButton("reset");
    JButton createbutton = new JButton("creare");


    String idfield;
    String Namefield;
    String PassWordfield;
    String msg;
    String query;
    Statement sta;




    public createpage(){

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.setSize(500,500);
        frame.setLayout(null);
        frame.setVisible(true);



        //標籤
        IDlable.setBounds(50,100,75,25);
        //frame.add(IDlable);
        namelable.setBounds(50,150,75,25);
        frame.add(namelable);
        passwordlable.setBounds(50,200,75,25);
        frame.add(passwordlable);

        //輸入框
        IDfield.setBounds(125,100,200,25);
        //frame.add(IDfield);
        namefield.setBounds(125,150,200,25);
        frame.add(namefield);
        passwordfield.setBounds(125,200,200,25);
        frame.add(passwordfield);


        //按鈕
        createbutton.setBounds(125,250,100,25);
        createbutton.setFocusable(false);
        frame.add(createbutton);
        // createbutton.addActionListener();
        resetbutton.setBounds(225,250,100,25);
        resetbutton.setFocusable(false);
        //resetbutton.addActionListener();
        frame.add(resetbutton);

        resetbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ex) {
                if (ex.getSource()==resetbutton){
                    IDfield.setText("");
                    namefield.setText("");
                    passwordfield.setText("");


                }

            }
        });





        createbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //取得文本

                idfield  = IDfield.getText();
                Namefield = namefield.getText();
                PassWordfield = passwordfield.getText();
                msg += "\n";

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/swing_demo2", "root", "123456");

                    query = "INSERT INTO student values('" + Namefield + "','" + PassWordfield + "' )";

                    sta = connection.createStatement();
                    int x = sta.executeUpdate(query);
                    if (x == 0) {
                        JOptionPane.showMessageDialog(createbutton, "This is alredy exist");
                    } else {
                        JOptionPane.showMessageDialog(createbutton,
                                "Welcome, " + msg + "Your account is sucessfully created");
                        dispose();
                        userlogin userlogin=new userlogin();
                        userlogin.setVisible(true);
                    }
                    connection.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });






    }


}
