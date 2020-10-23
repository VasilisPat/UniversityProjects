package ordsys;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class MainMenu extends JFrame{

    private JMenuBar menuBar;
    private String fileSavePath="";
    private Color backgroundColor;
    private TextArea screenTextArea;
    private JMenu menuFile,menuWindows,menuHelp;
    private JLabel pathLabel,filePathLabel,titleLabel;
    private JPanel filePanel,listButtonsPanel,menuButtonsPanel,titlePanel;
    private JButton newOrderButton,openButton,saveButton,saveAsButton,statisticsButton,aboutButton,exitButton;
    private JMenuItem openMenuItem,saveMenuItem,saveAsMenuItem,exitMenuItem,newOrderMenuItem,statisticsMenuItem,aboutMenuItem;

    public MainMenu(){
        //Background Color Initialization
        backgroundColor = new Color(204,204,255);
        //JMenuBar Initialization
        menuBar = new JMenuBar();
        //JMenus Initialization
        menuFile = new JMenu("File");
        menuWindows = new JMenu("Window");
        menuHelp = new JMenu("Help");
        //JMenuItems Initialization
        openMenuItem = new JMenuItem("Open");
        saveMenuItem = new JMenuItem("Save");
        saveAsMenuItem = new JMenuItem("Save As");
        exitMenuItem = new JMenuItem("Exit");
        newOrderMenuItem = new JMenuItem("New Order");
        statisticsMenuItem = new JMenuItem("Statistics");
        aboutMenuItem = new JMenuItem("About");
        //Text Area Initialization
        screenTextArea = new TextArea();
        screenTextArea.setEditable(false);
        //JLabels Initialization
        titleLabel = new JLabel("Orders List");
        pathLabel = new JLabel("Saved File Path:");
        filePathLabel = new JLabel(fileSavePath);
        //JButtons Initialization
        newOrderButton = new JButton("New Order");
        openButton = new JButton("Open");
        saveButton = new JButton("Save");
        saveAsButton = new JButton("Save As");
        statisticsButton = new JButton("Statstics");
        aboutButton = new JButton("About");
        exitButton = new JButton("Exit");
        //JPanels Initialization
        titlePanel = new JPanel();
        filePanel = new JPanel();
        listButtonsPanel = new JPanel();
        menuButtonsPanel = new JPanel();
        //Initialize UI
        initUI();
    }

    @Override
    //Overriding method to update text area after new input from NewOrder
    public void setVisible(boolean b) {
        super.setVisible(b);
        updateTextArea();
    }

    private void initUI(){
        this.setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));

        //Menu Bar
        menuFile.add(openMenuItem);
        menuFile.add(saveMenuItem);
        menuFile.add(saveAsMenuItem);
        menuFile.add(exitMenuItem);
        menuBar.add(menuFile);
        menuWindows.add(newOrderMenuItem);
        menuWindows.add(statisticsMenuItem);
        menuBar.add(menuWindows);
        menuHelp.add(aboutMenuItem);
        menuBar.add(menuHelp);
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        saveAsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        newOrderMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        statisticsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        aboutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        menuBar.setBorder(BorderFactory.createLineBorder(new Color(155,155,255)));
        menuBar.setBackground(new Color(155,155,255));
        this.setJMenuBar(menuBar);

        //Title Panel
        titlePanel.add(titleLabel);
        titleLabel.setFont(new Font("Sans-Serif", Font.BOLD, 25));
        titlePanel.setBackground(backgroundColor);
        titlePanel.setAlignmentX(titlePanel.CENTER_ALIGNMENT);
        this.add(titlePanel);

        screenTextArea.setFont(new Font("Sans-Serif", Font.PLAIN, 15));
        this.add(screenTextArea);

        //File Path Panel
        filePanel.add(pathLabel);
        filePanel.add(filePathLabel);
        pathLabel.setFont(new Font("Sans-Serif", Font.BOLD, 15));
        filePathLabel.setFont(new Font("Sans-Serif", Font.ITALIC, 15));
        filePanel.setBackground(backgroundColor);
        filePanel.setAlignmentX(filePanel.CENTER_ALIGNMENT);
        this.add(filePanel);

        //Order List Edit Buttons Panel
        newOrderButton.setIcon(new ImageIcon("resources/add15px.png"));
        openButton.setIcon(new ImageIcon("resources/open15px.png"));
        saveButton.setIcon(new ImageIcon("resources/csv15px.png"));
        saveAsButton.setIcon(new ImageIcon("resources/csv15px.png"));
        listButtonsPanel.add(newOrderButton);
        listButtonsPanel.add(openButton);
        listButtonsPanel.add(saveButton);
        listButtonsPanel.add(saveAsButton);
        listButtonsPanel.setBackground(backgroundColor);
        this.add(listButtonsPanel);

        //Menu Buttons Panel
        statisticsButton.setIcon(new ImageIcon("resources/statistics15px.png"));
        aboutButton.setIcon(new ImageIcon("resources/info15px.png"));
        exitButton.setIcon(new ImageIcon("resources/exit15px.png"));
        menuButtonsPanel.add(statisticsButton);
        menuButtonsPanel.add(aboutButton);
        menuButtonsPanel.add(exitButton);
        menuButtonsPanel.setBackground(backgroundColor);
        this.add(menuButtonsPanel);

        //Appearance Options
        this.setSize(650,420);
        this.setTitle("Main Window");
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.getContentPane().setBackground(backgroundColor);

        //MenuBar Action Listeners
        openMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                openAction();
            }
        });
        saveMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                saveAction();
            }
        });
        saveAsMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                saveAsAction();
            }
        });
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                exitAction();
            }
        });
        newOrderMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                newOrderAction();
            }
        });
        statisticsMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                statisticsAction();
            }
        });
        aboutMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                aboutAction();
            }
        });

        //Buttons Action Listeners
        newOrderButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                newOrderAction();
            }
        });
        openButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                openAction();
            }
        });
        saveButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                saveAction();
            }
        });
        saveAsButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                saveAsAction();
            }
        });
        statisticsButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                statisticsAction();
            }
        });
        aboutButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                aboutAction();
            }
        });
        exitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                exitAction();
            }
        });

        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                exitAction();
            }
        });

    }

    //Functions for actions listeners
    private void newOrderAction(){
        NewOrder newWindow = new NewOrder();
        MainMenu.this.setVisible(false);
    }

    private void openAction(){
        final JFileChooser fileCh = new JFileChooser();
        int retVal = fileCh.showOpenDialog(MainMenu.this);
        if(retVal==JFileChooser.APPROVE_OPTION){
            String inputFilePath = fileCh.getSelectedFile().getPath();
            //Checking for the file extension
            String fileExtension = inputFilePath.substring(inputFilePath.length()-4,inputFilePath.length());
            if(inputFilePath!=null && !inputFilePath.isEmpty() && fileExtension.equals(".csv")){
                loadFromFile(inputFilePath);
            }else{
                JOptionPane.showMessageDialog(openButton,"File type not accepted!","Warning",JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void saveAction(){
        if(fileSavePath.isEmpty()){
            saveAsAction();
        }else{
            saveToFile();
        }
    }

    private void saveAsAction(){
        final JFileChooser fileCh = new JFileChooser();
        fileCh.setDialogTitle("Save As");
        fileCh.setCurrentDirectory(new java.io.File("."));
        fileCh.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int retVal = fileCh.showSaveDialog(MainMenu.this);
        if(retVal==JFileChooser.APPROVE_OPTION){
            fileSavePath = fileCh.getSelectedFile().getPath();
            fileSavePath += "\\OrdersExport.csv";
            if(fileSavePath!=null && !fileSavePath.isEmpty()){
                saveToFile();
            }
       }
    }

    private void statisticsAction(){
        Statistics newWindow = new Statistics();
        MainMenu.this.setVisible(false);
    }

    private void aboutAction(){
        About newWindow = new About();
        MainMenu.this.setVisible(false);
    }

    private void exitAction(){
        int optEx = JOptionPane.showConfirmDialog(MainMenu.this,"Do you want to exit the app?","Exit",JOptionPane.YES_NO_OPTION);
        if(optEx==JOptionPane.YES_OPTION){
            int optS = JOptionPane.showConfirmDialog(MainMenu.this,"Do you want to save list before exiting?","Save Before Exit",JOptionPane.YES_NO_OPTION);
            if(optS==JOptionPane.YES_OPTION){
                saveToFile();
            }
            System.exit(0);
        }
    }


    //Function to update text area based on array list of orders
    private void updateTextArea(){
        screenTextArea.setText(null);
        if(!FinalProject.orderList.isEmpty()){
            for(Order ordTmp: FinalProject.orderList){
                screenTextArea.append(ordTmp.toScreen() + "\n");
            }
        }
    }

    //Function to load data to array list of orders from file
    private void loadFromFile(String inputPath){
        try{
            Order ordTmp;
            String line;
            String[] token;
            BufferedReader inputFile = new BufferedReader(new FileReader(inputPath));
            while(inputFile.ready()){
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
                dateFormat.setLenient(false);
                line = inputFile.readLine();
                token = line.split(";");
                if(token.length==8){
                    ordTmp = new Order(token[1],dateFormat.parse(token[2]),token[3],token[4],Integer.parseInt(token[5]),Double.parseDouble(token[6]),Double.parseDouble(token[7]));
                    FinalProject.orderList.add(ordTmp);
                }
            }
            inputFile.close();
            updateTextArea();
        }catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(saveButton,"File not found!","Warning",JOptionPane.WARNING_MESSAGE);
        }catch(IOException ex){
            JOptionPane.showMessageDialog(saveButton,"Can't import data from file!","Warning",JOptionPane.WARNING_MESSAGE);
        }catch(ParseException ex){
            JOptionPane.showMessageDialog(saveButton,"Wrong date format, should be dd/MM/yy","Warning",JOptionPane.WARNING_MESSAGE);
        }
    }

    //Function to save data from array list of orders to file
    private void saveToFile(){
        if(FinalProject.orderList.isEmpty()){
            fileSavePath=""; //Resets file path in case of error
            JOptionPane.showMessageDialog(saveButton,"List is empty, can't save to file!","Warning",JOptionPane.WARNING_MESSAGE);
        }else{
            try{
                filePathLabel.setText(fileSavePath);
                BufferedWriter expFile = new BufferedWriter(new FileWriter(fileSavePath,false)); //Overwrites file and prints al the contents of the list if "Save" is pressed
                for(Order ordTmp: FinalProject.orderList){
                    expFile.write(ordTmp.toString());
                }
                expFile.close();
                JOptionPane.showMessageDialog(saveButton,FinalProject.orderList.size() + " records saved to " + fileSavePath,"Save Complete", JOptionPane.INFORMATION_MESSAGE);
            }catch (IOException ex) {
                fileSavePath=""; //Resets file path in case of error
                JOptionPane.showMessageDialog(saveButton,"Can't save list to file!","Warning",JOptionPane.WARNING_MESSAGE);
            }
        }
    }

}
