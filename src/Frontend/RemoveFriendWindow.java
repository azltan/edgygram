package Frontend;

import Backend.Database;
import Backend.Friends.FriendsDatabase;
import Backend.Friends.FriendsManagement;
import Backend.User;
import Backend.UserDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class RemoveFriendWindow extends JFrame {
    private JPanel container;
    private JScrollPane friendsScrollPane;
    private JList<JPanel> friendsList;
    public RemoveFriendWindow(User user,NewsFeedWindow newsFeedWindow) {

        setContentPane(container);
        setVisible(true);
        setSize(300,400);
        setTitle("Remove Friend");
        setResizable(false);
        setLocationRelativeTo(newsFeedWindow);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        FriendsManagement friendsManagement= new FriendsManagement(new UserDatabase(),new FriendsDatabase());
        ArrayList<User> friend= friendsManagement.getFriends(user);
        ArrayList<String> friendsData=friendsManagement.displayList(friend);
        // Create the list of panels
        DefaultListModel<JPanel> panelModel = new DefaultListModel<>();
        for (String data : friendsData) {
            String[] friendDataArray = data.split(",");
            JPanel friendPanel = newsFeedWindow.createfriendPanel(friendDataArray[0], friendDataArray[1], friendDataArray[2]);
            panelModel.addElement(friendPanel);
        }
        // Create the JList and set custom render to handle displaying Jpanel
        friendsList = new JList<>(panelModel);
        friendsList.setCellRenderer(new CustomRender());
        friendsList.setBackground(new Color(34, 34, 34));
        // Add MouseListener to handle item clicks
        friendsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = friendsList.locationToIndex(e.getPoint()); // Get the clicked item's index
                if (index >= 0) {
                    String friendData = friendsData.get(index);
                    String[] friendDataArray = friendData.split(",");
                    String friendUsername = friendDataArray[1]; // Extract friend's username
                    // Show confirmation dialog
                    int choice = JOptionPane.showConfirmDialog(newsFeedWindow, "Do you want to remove " + friendUsername + " from your friends?", "Remove Friend", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        //get the friend user object and remove it
                        Database<User> database = new UserDatabase();
                        ArrayList<User> users=database.getAll();
                        User friend=null;
                        for(User userr : users ){
                            if (userr.getUsername().equals(friendUsername)) {
                                friend = userr;
                                break;
                            }
                        }
                        if (friend ==null){
                            JOptionPane.showMessageDialog(newsFeedWindow, "User not found");
                            return;
                        }
                        if(!friendsManagement.removeFriend(user, friend)){
                            JOptionPane.showMessageDialog(newsFeedWindow,"Couldn't remove friend","Error",JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        new RemoveFriendWindow(user,newsFeedWindow);
                        dispose();
                    }
                }
            }
        });
        // Set the JList inside the JScrollPane
        friendsScrollPane.setViewportView(friendsList);
        friendsScrollPane.revalidate();
        friendsScrollPane.repaint();
    }

    public static void main(String[] args) {
        UserDatabase userDatabase= new UserDatabase();
        ArrayList<User> users=userDatabase.getAll();
        new RemoveFriendWindow(users.get(0),new NewsFeedWindow(users.get(0)));
    }
}