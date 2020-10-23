package ordsys;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class About extends JFrame{

    private JButton backButton;
    private Color backgroundColor;
    private Font labelFont,textFont;
    private JPanel devLabelsPanel,backPanel;
    private JLabel nameLabel,devNameLabel,idLabel,devIdLabel,timeLabel,devTimeLabel,desktPicLabel;

    public About(){
        //Fonts Initialization
        labelFont = new Font("Sans-Serif", Font.BOLD, 18);
        textFont = new Font("Sans-Serif", Font.ITALIC, 18);
        //Background Color Initialization
        backgroundColor = new Color(204,204,255);
        //JButtons Initialization
        backButton = new JButton("Back");
        //JPanels Initialization
        devLabelsPanel = new JPanel();
        backPanel  = new JPanel();
        //JLabels Initialization
        nameLabel = new JLabel("Developer Name:");
        devNameLabel = new JLabel("Vasilis Patelis");
        idLabel = new JLabel("Developer ID:");
        devIdLabel = new JLabel("18390166");
        timeLabel = new JLabel("Developement Time:");
        devTimeLabel = new JLabel("24-31/05/2020");
        desktPicLabel = new JLabel();
        //Initialize UI
        initUI();
    }

    private void initUI(){
        this.setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));

        //Developer Name Panel
        devLabelsPanel.add(nameLabel);
        devLabelsPanel.add(devNameLabel);
        nameLabel.setFont(labelFont);
        devNameLabel.setFont(textFont);

        devLabelsPanel.add(idLabel);
        devLabelsPanel.add(devIdLabel);
        idLabel.setFont(labelFont);
        devIdLabel.setFont(textFont);

        devLabelsPanel.add(timeLabel);
        devLabelsPanel.add(devTimeLabel);
        timeLabel.setFont(labelFont);
        devTimeLabel.setFont(textFont);

        devLabelsPanel.setBackground(backgroundColor);
        devLabelsPanel.setLayout(new FlowLayout(FlowLayout.CENTER,15,2));
        this.add(devLabelsPanel);

        //Add Image to Label and Scaling to Fit
        ImageIcon inputImage = new ImageIcon(new ImageIcon("resources/DesktScreenshot.png").getImage().getScaledInstance(950,500,Image.SCALE_SMOOTH));
        desktPicLabel.setIcon(inputImage);
        desktPicLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        this.add(desktPicLabel);

        //Back Button Panel
        backPanel.add(backButton);
        backButton.setIcon(new ImageIcon("resources/return15px.png"));
        backPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,15,10));
        backPanel.setBackground(backgroundColor);
        this.add(backPanel);

        //Appearance Options
        this.setSize(1000,630);
        this.setTitle("About");
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.getContentPane().setBackground(backgroundColor);

        //Action Listeners
        backButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                FinalProject.startMenu.setVisible(true);
                About.this.dispose();
            }
        });

        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                int optEx = JOptionPane.showConfirmDialog(About.this,"Do you want to exit the app?","Exit",JOptionPane.YES_NO_OPTION);
                if(optEx==JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });

    }

}
