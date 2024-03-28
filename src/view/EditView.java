package view;

import entity.User;

import javax.swing.*;
import java.awt.*;

public class EditView extends JFrame{

    private JPanel container;
    private JTextField textField1;
    private JTextField textField2;
    private JPasswordField passwordField1;
    private JComboBox comboBox1;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JCheckBox checkBox1;
    private JButton button1;
    private JLabel label_name;
    private User user;

    public EditView(User user) {
        this.add(container);
        this.setTitle("Add / Edit Client ");
        this.setSize(500,500);

        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getWidth()) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getHeight()) / 2;

        this.setLocation(x, y);
        this.setVisible(true);

        this.user = user;

        // User nesnesi boş ise yeni ekleme işlemi , user_id = 0
        // User nesnesi dolu ise düzenleme işlemi , user_id != 0
    }
}
