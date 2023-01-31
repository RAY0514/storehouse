import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class userlogin extends JFrame {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {//有連線需要中斷處理
                    userlogin frame = new userlogin();
                    frame.setVisible(true);//可見視窗
                } catch (Exception e) {//方法
                    e.printStackTrace();
                    //動作監測
                }
            }
        });
    }

    JFrame frame = new JFrame();
    //標籤
    JLabel label = new JLabel("Login");
    JLabel userlable = new JLabel("username");
    JLabel passwordlable = new JLabel("password");
    //輸入框
    JTextField UserField = new JTextField();
    JTextField PasswordField = new JTextField();
    //按鈕
    JButton loginbutton = new JButton("login");
    JButton createbutton = new JButton("createID");

    String username;
    String password;

    //PreparedStatement st;
    //ResultSet rs;
    public userlogin() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(500, 500);
        frame.setVisible(true);
        //標籤
        label.setBounds(150, 20, 100, 100);
        frame.add(label);
        userlable.setBounds(50, 100, 75, 25);
        frame.add(userlable);
        passwordlable.setBounds(50, 150, 75, 25);
        frame.add(passwordlable);
        //輸入框
        UserField.setBounds(125, 100, 200, 25);
        frame.add(UserField);
        PasswordField.setBounds(125, 150, 200, 25);
        frame.add(PasswordField);
        //按鈕
        loginbutton.setBounds(125, 250, 100, 25);
        frame.add(loginbutton);
        createbutton.setBounds(225, 250, 100, 25);
        frame.add(createbutton);
        createbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                createpage create = new createpage();
                create.setVisible(true);
            }
        });
        loginbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username = UserField.getText();
                password = PasswordField.getText();
                try {
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3307/swing_demo2",
                            "root", "123456");//連線
                    PreparedStatement st = (PreparedStatement) connection
                            .prepareStatement("Select name, password from student where name=? and password=?");
                    //要使用 PreparedStatement 的時候，我們的 Statement 物件就要改用 Connection 的 prepareStatement() 方法來取得
                    st.setString(1, username);
                    st.setString(2, password);
                    ResultSet rs = st.executeQuery();//
                    if (rs.next()) {
                        dispose();//dispose方法是關閉窗體
                        tutorial ah = new tutorial();
                        ah.setTitle("Welcome");
                        ah.setVisible(true);
                        JOptionPane.showMessageDialog(loginbutton, "You have successfully logged in");
                    } else {
                        JOptionPane.showMessageDialog(loginbutton, "Wrong Username & Password");
                    }
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        });
    }
}