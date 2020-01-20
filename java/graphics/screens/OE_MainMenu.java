/**
*===================   OPERATION EXTINCTION   ======================
*===================       DO NOT ALTER       ======================
* @author Kyle M. Morris
* @version 0.0.1
*
*
*/
package graphics.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import graphics.OE_ScreenConstants;
/**
 * The Main Menu of Operation Extinction, a <code>JFrame</code> subtype.
 * <p> Implements the <code>Menu</code> interface.</p>
 * @author Kyle M. Morris
 * @since 0.0.1
 * @see oegrafx.screens.Menu
 */
public class OE_MainMenu extends JFrame implements Menu {
    // Frame
    private JFrame _frame;
    // Panels
    private OE_GraphicPane _mainPanel, _randCardPane, _updatePane;
    protected JPanel _buttonPane, _lowerPane;
    // Buttons
    private JButton _start, _deckedit, _help, _creator, _quit;
    private JButton _bugreport;
    private JButton _settings, _profile;
    // Labels
    private JLabel _logo;
    // Constants
    private Dimension _size = new Dimension(OE_ScreenConstants._X / 2, 2 * (OE_ScreenConstants._Y / 3));
    private String _root = new File("").getAbsolutePath();
    private int _wChisle = (int) _size.getWidth();
    private int _hChisle = (int) _size.getHeight();

    // ================================ ACTION LISETENER
    private ActionListener buttonAction = new ActionListener(){
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == _quit){
                exitMain();
            }
        }
    };
    // ================================ CONSTRUCTOR
    public OE_MainMenu() {
        // Create the buttons
        _start = new JButton("START");
        _deckedit = new JButton("DECKEDIT");
        _help = new JButton("HELP");
        _creator = new JButton("CARD CREATOR");
        _quit = new JButton("QUIT");
        _bugreport = new JButton("BUG");
        _settings = new JButton("SET");
        _profile = new JButton("PRO");
        // Add action listeners
        _quit.addActionListener(buttonAction);

        // ================================ GENERATE PANELS
        // main panel setup
        String pathToImage = (_root.concat("/graphics/mainBackground.jpg"));
        _mainPanel = new OE_GraphicPane(pathToImage);

        // panel for main buttons
        _buttonPane = new JPanel(new GridBagLayout());
        GridBagConstraints gConst = new GridBagConstraints();
        _buttonPane.setOpaque(false);
        gConst.weightx = 0.0;
        gConst.weighty = _hChisle/360;
        gConst.ipadx = _wChisle/48;
        gConst.ipady = _hChisle/36;
        gConst.gridx = 0;
        gConst.gridy = 0;
        gConst.fill = GridBagConstraints.HORIZONTAL;
        _buttonPane.add(_start, gConst);
        gConst.gridy = 1;
        _buttonPane.add(_deckedit, gConst);
        gConst.gridy = 2;
        _buttonPane.add(_creator, gConst);
        gConst.gridy = 3;
        _buttonPane.add(_help, gConst);
        gConst.gridy = 4;
        _buttonPane.add(_quit, gConst);

        // panel for random card
        pathToImage = (_root.concat("/graphics/cardTest2.png"));
        _randCardPane = new OE_GraphicPane(pathToImage);
        _randCardPane.setPreferredSize(new Dimension(180, 229));
        // 652 x 916    original size
        // 163 x 229    original size / 4

        // panel for game updates
        pathToImage = (_root.concat("/graphics/updateTest.png"));
        _updatePane = new OE_GraphicPane(pathToImage);
        _updatePane.setPreferredSize(new Dimension(180, 100));

        // label for icon
        pathToImage = (_root.concat("/graphics/logo.png"));
        _logo = new JLabel(new ImageIcon(pathToImage));
        _logo.setOpaque(false);

        // panel for the bottom
        _lowerPane = new JPanel(new GridBagLayout());
        _lowerPane.setOpaque(false);
        _lowerPane.setSize(600, 100);
        GridBagConstraints lConst = new GridBagConstraints();
        lConst.fill = GridBagConstraints.HORIZONTAL;
        lConst.weightx = _wChisle/1920;
        lConst.ipady = _hChisle/18;
        _lowerPane.add(_bugreport, lConst);
        lConst.gridx = 1;
        _lowerPane.add(_settings, lConst);
        lConst.gridx = 2;
        _lowerPane.add(_profile, lConst);

        // add it all to main panel
        GridBagConstraints mConst = new GridBagConstraints();
        _mainPanel.setLayout(new GridBagLayout());
        mConst.fill = GridBagConstraints.NONE;
        mConst.ipady = _hChisle/18;
        mConst.gridx = 0;
        mConst.gridy = 1;
        mConst.insets = new Insets(_hChisle/8, 0, 0, 0);
        _mainPanel.add(_buttonPane, mConst, JLayeredPane.FRAME_CONTENT_LAYER);
    
        double h = _wChisle/1.6;
        int dh = (int)h;
        mConst.insets = new Insets(_hChisle/72, dh, 0, 0);
        _mainPanel.add(_randCardPane, mConst, JLayeredPane.FRAME_CONTENT_LAYER);

        mConst.insets = new Insets(_hChisle/72, -dh, 0, 0);
        _mainPanel.add(_updatePane, mConst, JLayeredPane.FRAME_CONTENT_LAYER);

        mConst.gridy = 0;
        mConst.insets = new Insets(0, 0, 0, 0);
        _mainPanel.add(_logo, mConst, JLayeredPane.FRAME_CONTENT_LAYER);

        mConst.ipady = _hChisle/24;
        h = _wChisle/19.2;
        dh = (int)h;
        mConst.ipadx = dh;
        mConst.gridy = 2;
        mConst.insets = new Insets(_hChisle/10 - 2, -_hChisle, 0, 0);
        _mainPanel.add(_lowerPane, mConst, JLayeredPane.FRAME_CONTENT_LAYER);

        // ================================ GENERATE FRAME & ADD CONTENT
        _frame = new JFrame("Operation Extinction - " + OE_ScreenConstants._Gversion + " - Main Menu");
        _frame.setMinimumSize(_size);
        _frame.setMaximumSize(OE_ScreenConstants._screenSize);
        _frame.setContentPane(_mainPanel);
        _frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        _frame.setResizable(false);
        _frame.pack();
        _frame.setLocationRelativeTo(null);
        _frame.setVisible(true);
    }

    // ================================ BUTTON ACTIONS
    protected void exitMain() {
        int rv = JOptionPane.showConfirmDialog(_frame, "Are you sure you want to quit?", "Quit", JOptionPane.YES_NO_OPTION);
        if(rv == JOptionPane.YES_OPTION){
            _frame.dispose();
            this.dispose();
        }
    }
}
