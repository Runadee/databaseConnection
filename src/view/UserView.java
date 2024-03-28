package view;

import business.UserController;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UserView extends JFrame {
    private JPanel container;
    private JTabbedPane tab_menu;
    private JPanel panel_user;
    private JTable table_user;
    private JScrollPane scroll_user;
    private JButton button_user_new;
    private UserController userController;
    private DefaultTableModel model_user;

    public UserView() {
        this.add(container);
        this.setTitle("Kullanıcı Yönetimi");
        this.setSize(500, 500);
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getWidth()) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getHeight()) / 2;

        this.setLocation(x, y);
        this.setVisible(true);

        this.userController = new UserController();
        this.model_user = new DefaultTableModel();

        // TableModel  (tabloya verileri işlemek için )
        // Column başlıkları oluştur
        // Modele satırları aktar

        Object[] columnUser = {"ID" , "NAME" , "TYPE" , "GENDER" , "MAIL", "PASSWORD"};
        this.model_user.setColumnIdentifiers(columnUser);

        ArrayList<User> users = this.userController.findAll();
        for (User user : users ) {
            Object[] row = {
                   user.getId(),
                   user.getName(),
                   user.getType(),
                   user.getGender(),
                   user.getMail(),
                   user.getPassword()
            };

            this.model_user.addRow(row);
        }

        // Modeli tabloya ata
        this.table_user.setModel(this.model_user);
        this.table_user.setEnabled(false);
        this.table_user.getTableHeader().setReorderingAllowed(false);

        this.table_user.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectedRow = table_user.rowAtPoint(e.getPoint());
                table_user.setRowSelectionInterval(selectedRow, selectedRow);
            }
        });

    }
}
