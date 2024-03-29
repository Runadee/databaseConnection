package view;

import business.UserController;
import entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditView extends JFrame {

    private JPanel container;
    private JTextField field_name;
    private JTextField field_mail;
    private JPasswordField field_password;
    private JComboBox<User.Type> combo_type;
    private JRadioButton radio_female;
    private JRadioButton radio_male;
    private JCheckBox check_box;
    private JButton save_button;
    private JLabel label_name;
    private JLabel label_mail;
    private JLabel label_password;
    private JLabel client_type;
    private JLabel label_gender;
    private User user;
    private DefaultComboBoxModel<User.Type> comboBoxModel;
    private UserController userController;

    public EditView(User user) {
        this.add(container);
        this.setTitle("Add / Edit Client ");
        this.setSize(300, 400);

        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getWidth()) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getHeight()) / 2;

        this.setLocation(x, y);
        this.setVisible(true);

        this.user = user;
        this.userController = new UserController();

        this.comboBoxModel = new DefaultComboBoxModel<>(User.Type.values());
        this.combo_type.setModel(comboBoxModel);


        // User nesnesi boş ise yeni ekleme işlemi , user_id = 0
        // User nesnesi dolu ise düzenleme işlemi , user_id != 0

        if (this.user.getId() != 0) {

            this.field_name.setText(this.user.getName());
            this.field_mail.setText(this.user.getMail());
            this.field_password.setText(this.user.getPassword());
            this.combo_type.getModel().setSelectedItem(this.user.getType());

            if (this.user.getGender() == User.Gender.FEMALE) {
                this.radio_female.setSelected(true);
            } else {
                this.radio_male.setSelected(true);
            }
        }


        check_box.addActionListener(e -> {
            if (this.check_box.isSelected()) {
                this.save_button.setEnabled(true);
            } else {
                this.save_button.setEnabled(false);
            }
        });

        save_button.addActionListener(e -> {
            if (    this.field_mail.getText().isEmpty() ||
                    this.field_name.getText().isEmpty() ||
                    this.field_password.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields.",
                        "Missing data",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                this.user.setName(this.field_name.getText());
                this.user.setPassword(this.field_password.getText());
                this.user.setMail(this.field_mail.getText());
                this.user.setType((User.Type) this.combo_type.getSelectedItem());

                if (this.radio_female.isSelected()) {
                    this.user.setGender(User.Gender.FEMALE);
                } else {
                    this.user.setGender(User.Gender.MALE);
                }

                if (this.user.getId() != 0) {

                    if (this.userController.update(this.user)) {
                        JOptionPane.showMessageDialog(null,
                                "Data updated",
                                "Succeed",
                                JOptionPane.INFORMATION_MESSAGE);

                        dispose();
                    }


                } else {
                    if (this.userController.save(this.user)) {
                        JOptionPane.showMessageDialog(null,
                                "New Data Added",
                                "Succeed",
                                JOptionPane.INFORMATION_MESSAGE);

                        dispose();
                    }
                }

            }

        });


    }
}
