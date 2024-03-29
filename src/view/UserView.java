package view;

import business.UserController;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JPopupMenu user_popup;

    public UserView() {
        this.add(container);
        this.setTitle("Client Management");
        this.setSize(500, 500);
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getWidth()) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getHeight()) / 2;

        this.setLocation(x, y);
        this.setVisible(true);

        this.userController = new UserController();
        this.model_user = new DefaultTableModel();
        this.user_popup = new JPopupMenu();

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

        this.user_popup.add("Update").addActionListener(e -> {
            int selectedId = Integer.parseInt(table_user.getValueAt(table_user.getSelectedRow(), 0).toString());
            User selectedUser = this.userController.getById(selectedId);
            EditView editView = new EditView(selectedUser);

        });
        this.user_popup.add("Delete").addActionListener(e -> {
            int selectedId = Integer.parseInt(table_user.getValueAt(table_user.getSelectedRow(), 0).toString());
           // User selectedUser = this.userController.getById(selectedId);

        });

        this.table_user.setComponentPopupMenu(user_popup);

        button_user_new.addActionListener(e -> {
            EditView editView = new EditView(new User());
        });
    }
}
