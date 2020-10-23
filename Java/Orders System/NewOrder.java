package ordsys;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class NewOrder extends JFrame {

    private Color backgroundColor;
    private JButton addButton,backButton;
    private JPanel buttonsPanel,orderIdPanel,orderDatePanel,clientNamePanel,itemNamePanel,unitCountPanel,netPricePanel,taxPercentagePanel;
    private JLabel orderIdLabel,orderDateLabel,clientNameLabel,itemNameLabel,unitCountLabel,netPriceLabel,taxPercentageLabel;
    private JTextField orderIdTextField,orderDateTextField,clientNameTextField,itemNameTextField,unitCountsTextField,netPriceTextField,taxPercentageTextField;

    public NewOrder() {
        //Background Color Initialization
        backgroundColor = new Color(204,204,255);
        //JButtons Initialization
        addButton = new JButton("Add Order");
        backButton = new JButton("Back");
        //JLabels Initialization
        orderIdLabel = new JLabel("Order ID:");
        orderDateLabel = new JLabel("Order Date:");
        clientNameLabel = new JLabel("Client Name:");
        itemNameLabel = new JLabel("Item Name:");
        unitCountLabel = new JLabel("Unit Count:");
        netPriceLabel = new JLabel("Net Price:");
        taxPercentageLabel = new JLabel("Tax %:");
        //JText Fields Initialization
        orderIdTextField = new JTextField(15);
        orderDateTextField = new JTextField(15);
        clientNameTextField = new JTextField(15);
        itemNameTextField = new JTextField(15);
        unitCountsTextField = new JTextField(15);
        netPriceTextField = new JTextField(15);
        taxPercentageTextField = new JTextField(15);
        //JPanels Initialization
        orderIdPanel = new JPanel();
        orderDatePanel = new JPanel();
        clientNamePanel = new JPanel();
        itemNamePanel = new JPanel();
        unitCountPanel = new JPanel();
        netPricePanel = new JPanel();
        taxPercentagePanel = new JPanel();
        buttonsPanel = new JPanel();
        //Initialize UI
        initUI();
    }

    private void initUI(){
        this.setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));

        //Order ID Panel
        orderIdPanel.add(orderIdLabel);
        orderIdPanel.add(orderIdTextField);
        orderIdPanel.setLayout(new FlowLayout(FlowLayout.LEFT,20,5));
        orderIdPanel.setBackground(backgroundColor);
        this.add(orderIdPanel);

        //Order Date Panel
        orderDatePanel.add(orderDateLabel);
        orderDatePanel.add(orderDateTextField);
        orderDatePanel.setLayout(new FlowLayout(FlowLayout.LEFT,13,5));
        orderDatePanel.setBackground(backgroundColor);
        this.add(orderDatePanel);

        //Client Name Panel
        clientNamePanel.add(clientNameLabel);
        clientNamePanel.add(clientNameTextField);
        clientNamePanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,5));
        clientNamePanel.setBackground(backgroundColor);
        this.add(clientNamePanel);

        //Item's Name Panel
        itemNamePanel.add(itemNameLabel);
        itemNamePanel.add(itemNameTextField);
        itemNamePanel.setLayout(new FlowLayout(FlowLayout.LEFT,14,5));
        itemNamePanel.setBackground(backgroundColor);
        this.add(itemNamePanel);

        //Units Count Panel
        unitCountPanel.add(unitCountLabel);
        unitCountPanel.add(unitCountsTextField);
        unitCountPanel.setLayout(new FlowLayout(FlowLayout.LEFT,16,5));
        unitCountPanel.setBackground(backgroundColor);
        this.add(unitCountPanel);

        //Net Price Panel
        netPricePanel.add(netPriceLabel);
        netPricePanel.add(netPriceTextField);
        netPricePanel.setLayout(new FlowLayout(FlowLayout.LEFT,19,5));
        netPricePanel.setBackground(backgroundColor);
        this.add(netPricePanel);

        //Tax Panel
        taxPercentagePanel.add(taxPercentageLabel);
        taxPercentagePanel.add(taxPercentageTextField);
        taxPercentagePanel.setLayout(new FlowLayout(FlowLayout.LEFT,28,5));
        taxPercentagePanel.setBackground(backgroundColor);
        this.add(taxPercentagePanel);

        //Buttons Panel
        backButton.setIcon(new ImageIcon("resources/return15px.png"));
        buttonsPanel.add(backButton);
        addButton.setIcon(new ImageIcon("resources/add15px.png"));
        buttonsPanel.add(addButton);
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,6,5));
        buttonsPanel.setBackground(backgroundColor);
        this.add(buttonsPanel);

        //Appearance Options
        this.setSize(310,360);
        this.setTitle("New Order");
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.getRootPane().setDefaultButton(addButton);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.getContentPane().setBackground(backgroundColor);


        //Action Listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                StringBuilder warning = new StringBuilder();
                boolean empty = false;
                //Checking for empty fields and highlighting them with a red borer
                if(orderIdTextField==null || orderIdTextField.getText().isEmpty()){
                    warning.append("Order ID, ");
                    orderIdTextField.setBorder(new LineBorder(Color.RED, 1));
                    empty = true;
                }
                if(orderDateTextField==null || orderDateTextField.getText().isEmpty()){
                    warning.append("Order Date, ");
                    orderDateTextField.setBorder(new LineBorder(Color.RED, 1));
                    empty = true;
                }
                if(clientNameTextField==null || clientNameTextField.getText().isEmpty()){
                    warning.append("Client Name, ");
                    clientNameTextField.setBorder(new LineBorder(Color.RED, 1));
                    empty = true;
                }
                if(itemNameTextField==null || itemNameTextField.getText().isEmpty()){
                    warning.append("Item Name, ");
                    itemNameTextField.setBorder(new LineBorder(Color.RED, 1));
                    empty = true;
                }
                if(unitCountsTextField==null || unitCountsTextField.getText().isEmpty()){
                    warning.append("Unit Counts, ");
                    unitCountsTextField.setBorder(new LineBorder(Color.RED, 1));
                    empty = true;
                }
                if(netPriceTextField==null || netPriceTextField.getText().isEmpty()){
                    warning.append("Net Price, ");
                    netPriceTextField.setBorder(new LineBorder(Color.RED, 1));
                    empty = true;
                }
                if(taxPercentageTextField==null || taxPercentageTextField.getText().isEmpty()){
                    warning.append("Tax, ");
                    taxPercentageTextField.setBorder(new LineBorder(Color.RED, 1));
                    empty = true;
                }
                if(empty==true){
                    warning.append("can't be empty.");
                    JOptionPane.showMessageDialog(addButton,warning.toString(),"Warning",JOptionPane.WARNING_MESSAGE);
                }else{
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
                    dateFormat.setLenient(false);
                    try{
                        Order ordTmp  = new Order();
                        ordTmp.setOrderId(orderIdTextField.getText());
                        ordTmp.setOrderDate(dateFormat.parse(orderDateTextField.getText()));
                        ordTmp.setClientName(clientNameTextField.getText());
                        ordTmp.setItemName(itemNameTextField.getText());
                        ordTmp.setUnitCount(Integer.parseInt(unitCountsTextField.getText()));
                        ordTmp.setNetItemPrice(Double.parseDouble(netPriceTextField.getText()));
                        ordTmp.setTaxPercentage(Double.parseDouble(taxPercentageTextField.getText()));
                        FinalProject.orderList.add(ordTmp);
                        JOptionPane.showMessageDialog(addButton,"Entry added successfully.","Success",JOptionPane.INFORMATION_MESSAGE);
                        FinalProject.startMenu.setVisible(true);
                        NewOrder.this.dispose();
                    }catch(NumberFormatException ex){
                        //Exception if a string is entered in a field which requires a number
                        JOptionPane.showMessageDialog(addButton,"Can't enter word in number field","Warning",JOptionPane.WARNING_MESSAGE);
                    }catch (ParseException ex){
                        //Exception if wrong date format is entered
                        JOptionPane.showMessageDialog(addButton,"Wrong date format, should be dd/MM/yy","Warning",JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                FinalProject.startMenu.setVisible(true);
                NewOrder.this.dispose();
            }
        });

        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                int opt = JOptionPane.showConfirmDialog(NewOrder.this,"Do you want to exit the app?","Exit",JOptionPane.YES_NO_OPTION);
                if(opt==JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });

    }

}
