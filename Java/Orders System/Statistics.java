package ordsys;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
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


public class Statistics extends JFrame{

    private JButton backButton;
    private Color backgroundColor;
    private Font labelFont,textFont;
    private JPanel totalOrdersPanel,totalNetCostPanel,totalMixedCostPanel,cheapOrderPanel,expCostPanel,backPanel;
    private JLabel orderLabel,orderNumberLabel,netCostLabel,orderNetCostLabel,mixedCostLabel,orderMixedCostLabel,expenOrderLabel,expenOrderCodeLabel,cheapOrderLabel,cheapOrderCodeLabel;

    public Statistics(){
        //Fonts Initialization
        labelFont = new Font("Sans-Serif", Font.BOLD, 15);
        textFont = new Font("Sans-Serif", Font.ITALIC, 15);
        //Background Color Initialization
        backgroundColor = new Color(204,204,255);
        //JButton Initialization
        backButton = new JButton("Back");
        //JPanels Initialization
        totalOrdersPanel = new JPanel();
        totalNetCostPanel = new JPanel();
        totalMixedCostPanel = new JPanel();
        cheapOrderPanel = new JPanel();
        expCostPanel = new JPanel();
        backPanel = new JPanel();
        //JLabels Initialization
        orderLabel = new JLabel("Total Orders:");
        orderNumberLabel = new JLabel();
        netCostLabel = new JLabel("Total Net Cost of Orders:");
        orderNetCostLabel = new JLabel();
        mixedCostLabel = new JLabel("Total Mixed Cost of Orders:");
        orderMixedCostLabel = new JLabel();
        expenOrderLabel = new JLabel("Most Expensive Order Code:");
        expenOrderCodeLabel = new JLabel();
        cheapOrderLabel = new JLabel("Cheaper Order Code:");
        cheapOrderCodeLabel = new JLabel();
        //Initialize UI
        initUI();
    }

    private void initUI(){
        this.setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));

        //Total Orders Panel
        totalOrdersPanel.add(orderLabel);
        totalOrdersPanel.add(orderNumberLabel);
        orderLabel.setFont(labelFont);
        orderNumberLabel.setFont(textFont);
        totalOrdersPanel.setBackground(backgroundColor);
        totalOrdersPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(totalOrdersPanel);

        //Total Net Cost Panel
        totalNetCostPanel.add(netCostLabel);
        totalNetCostPanel.add(orderNetCostLabel);
        netCostLabel.setFont(labelFont);
        orderNetCostLabel.setFont(textFont);
        totalNetCostPanel.setBackground(backgroundColor);
        totalNetCostPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(totalNetCostPanel);

        //Total Mixed Cost Panel
        totalMixedCostPanel.add(mixedCostLabel);
        totalMixedCostPanel.add(orderMixedCostLabel);
        mixedCostLabel.setFont(labelFont);
        orderMixedCostLabel.setFont(textFont);
        totalMixedCostPanel.setBackground(backgroundColor);
        totalMixedCostPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(totalMixedCostPanel);

        //Most Expensive Order Panel
        expCostPanel.add(expenOrderLabel);
        expCostPanel.add(expenOrderCodeLabel);
        expenOrderLabel.setFont(labelFont);
        expenOrderCodeLabel.setFont(textFont);
        expCostPanel.setBackground(backgroundColor);
        expCostPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(expCostPanel);

        //Cheaper Order Panel
        cheapOrderPanel.add(cheapOrderLabel);
        cheapOrderPanel.add(cheapOrderCodeLabel);
        cheapOrderLabel.setFont(labelFont);
        cheapOrderCodeLabel.setFont(textFont);
        cheapOrderPanel.setBackground(backgroundColor);
        cheapOrderPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(cheapOrderPanel);

        //Back Button Panel
        backPanel.add(backButton);
        backButton.setIcon(new ImageIcon("resources/return15px.png"));
        backPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,15,4));
        backPanel.setBackground(backgroundColor);
        this.add(backPanel);

        //Execute functions to assign results to labels when frame opens
        calcTotalOrders();
        calcTotalCost();
        calcExpenOrders();
        calcCheapOrders();

        //Appearance Options
        this.setSize(350,250);
        this.setTitle("Statitics");
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.getContentPane().setBackground(new Color(204,204,255));

        //Action Listeners
        backButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                FinalProject.startMenu.setVisible(true);
                Statistics.this.dispose();
            }
        });

        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                int optEx = JOptionPane.showConfirmDialog(Statistics.this,"Do you want to exit the app?","Exit",JOptionPane.YES_NO_OPTION);
                if(optEx==JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });

    }

    //Function to Calculate Total Number of Orders
    private void calcTotalOrders(){
        int i=0;
        if(!FinalProject.orderList.isEmpty()){
            for(Order ordTmp: FinalProject.orderList){
                i++;
            }
        }
        orderNumberLabel.setText(i + " Orders");
    }

    //Function to Calculate Total Cost of Orders
    private void calcTotalCost(){
        Double nCost=0.00,mCost=0.00;
        if(!FinalProject.orderList.isEmpty()){
            for(Order ordTmp: FinalProject.orderList){
                nCost += ordTmp.getNetItemPrice();
                mCost += ((ordTmp.getUnitCount()*ordTmp.getNetItemPrice()) + (ordTmp.getUnitCount()*ordTmp.getNetItemPrice()*ordTmp.getTaxPercentage()/100)); //Calculate total order price and tax
            }
        }
        orderNetCostLabel.setText(nCost + "$");
        orderMixedCostLabel.setText(mCost + "$");

    }

    //Function to Find Code of Cheaper Order
    private void calcExpenOrders(){
        String orderId="-";
        Double mCost=0.00,maxPrice=0.00;
        if(!FinalProject.orderList.isEmpty()){
            for(Order ordTmp: FinalProject.orderList){
                mCost = (ordTmp.getUnitCount()*ordTmp.getNetItemPrice()) + (ordTmp.getUnitCount()*ordTmp.getNetItemPrice()*ordTmp.getTaxPercentage()/100); //Calculate total order price and tax
                if(mCost>maxPrice){
                   maxPrice = mCost;
                   orderId = ordTmp.getOrderId();
                }
            }
        }
        expenOrderCodeLabel.setText(orderId);
    }

    //Function to Find Code of Most Expensive Order
    private void calcCheapOrders(){
        String orderId="-";
        Double mCost=0.00,minPrice=100000000.00;
        if(!FinalProject.orderList.isEmpty()){
            for(Order ordTmp: FinalProject.orderList){
                mCost = (ordTmp.getUnitCount()*ordTmp.getNetItemPrice()) + (ordTmp.getUnitCount()*ordTmp.getNetItemPrice()*ordTmp.getTaxPercentage()/100); //Calculate total order price and tax
                if(mCost<minPrice){
                   minPrice = mCost;
                   orderId = ordTmp.getOrderId();
                }
            }
        }
        cheapOrderCodeLabel.setText(orderId);
    }

}
