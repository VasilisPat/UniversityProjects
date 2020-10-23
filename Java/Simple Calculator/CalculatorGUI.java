package simplecalc;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class CalculatorGUI extends JFrame {

    private Double leftPart = 0.00;
    private char operator = ' ';
    private JPanel gridPanel;
    private JLabel resultLabel;
    private JButton btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnP, btnM, btnA, btnS, btnE, btnD;

    public CalculatorGUI() {

        gridPanel = new JPanel(new GridLayout(4,4));
        resultLabel = new JLabel(" ", SwingConstants.RIGHT);

        btn0 = new JButton("0");
        btn1 = new JButton("1");
        btn2 = new JButton("2");
        btn3 = new JButton("3");
        btn4 = new JButton("4");
        btn5 = new JButton("5");
        btn6 = new JButton("6");
        btn7 = new JButton("7");
        btn8 = new JButton("8");
        btn9 = new JButton("9");
        btnP = new JButton("+");
        btnM = new JButton("-");
        btnA = new JButton("*");
        btnS = new JButton("/");
        btnE = new JButton("=");
        btnD = new JButton(".");
    }

    public void initUI(){

        this.add(resultLabel, BorderLayout.NORTH);
        resultLabel.setFont(new Font("Serif", Font.BOLD, 35));

        //First row
        gridPanel.add(btn7);
        gridPanel.add(btn8);
        gridPanel.add(btn9);
        gridPanel.add(btnP);
        //Second row
        gridPanel.add(btn4);
        gridPanel.add(btn5);
        gridPanel.add(btn6);
        gridPanel.add(btnM);
        //Third row
        gridPanel.add(btn1);
        gridPanel.add(btn2);
        gridPanel.add(btn3);
        gridPanel.add(btnA);
        //Fourth row
        gridPanel.add(btn0);
        gridPanel.add(btnD);
        gridPanel.add(btnE);
        gridPanel.add(btnS);
        this.add(gridPanel, BorderLayout.CENTER);
        this.pack();


        btn0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                if(resultLabel.getText().equals("N/A")){
                    resultLabel.setText("");
                }
                resultLabel.setText(resultLabel.getText().concat("0"));
            }
        });

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                if(resultLabel.getText().equals("N/A")){
                    resultLabel.setText("");
                }
                resultLabel.setText(resultLabel.getText().concat("1"));
            }
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                if(resultLabel.getText().equals("N/A")){
                    resultLabel.setText("");
                }
                resultLabel.setText(resultLabel.getText().concat("2"));
            }
        });

        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                if(resultLabel.getText().equals("N/A")){
                    resultLabel.setText("");
                }
                resultLabel.setText(resultLabel.getText().concat("3"));
            }
        });

        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                if(resultLabel.getText().equals("N/A")){
                    resultLabel.setText("");
                }
                resultLabel.setText(resultLabel.getText().concat("4"));
            }
        });

        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                if(resultLabel.getText().equals("N/A")){
                    resultLabel.setText("");
                }
                resultLabel.setText(resultLabel.getText().concat("5"));
            }
        });

        btn6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                if(resultLabel.getText().equals("N/A")){
                    resultLabel.setText("");
                }
                resultLabel.setText(resultLabel.getText().concat("6"));
            }
        });

        btn7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                if(resultLabel.getText().equals("N/A")){
                    resultLabel.setText("");
                }
                resultLabel.setText(resultLabel.getText().concat("7"));
            }
        });

        btn8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                if(resultLabel.getText().equals("N/A")){
                    resultLabel.setText("");
                }
                resultLabel.setText(resultLabel.getText().concat("8"));
            }
        });

        btn9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                if(resultLabel.getText().equals("N/A")){
                    resultLabel.setText("");
                }
                resultLabel.setText(resultLabel.getText().concat("9"));
            }
        });

        btnD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                if(resultLabel.getText().equals("N/A")){
                    resultLabel.setText("");
                }
                resultLabel.setText(resultLabel.getText().concat("."));
            }
        });

        btnP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                leftPart = Double.parseDouble(resultLabel.getText());
                operator = '+';
                resultLabel.setText(" ");
            }
        });

        btnM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                leftPart = Double.parseDouble(resultLabel.getText());
                operator = '-';
                resultLabel.setText(" ");
            }
        });

        btnA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                leftPart = Double.parseDouble(resultLabel.getText());
                operator = '*';
                resultLabel.setText(" ");
            }
        });

        btnS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                leftPart = Double.parseDouble(resultLabel.getText());
                operator = '/';
                resultLabel.setText(" ");
            }
        });

        btnE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                try{
                    Double result;
                    Double rightPart = Double.parseDouble(resultLabel.getText());
                    switch(operator){
                            case '+':
                                result = leftPart + rightPart;
                                resultLabel.setText(result.toString());
                                break;
                            case '-':
                                result = leftPart - rightPart;
                                resultLabel.setText(result.toString());
                                break;
                            case '*':
                                result = leftPart * rightPart;
                                resultLabel.setText(result.toString());
                                break;
                            case '/':
                                result = leftPart / rightPart;
                                resultLabel.setText(result.toString());
                                break;
                    }
                }catch(NullPointerException ex){
                    resultLabel.setText("N/A");
                }catch(NumberFormatException ex){
                    resultLabel.setText("N/A");
                }
            }
        });

        this.setLocationRelativeTo(null);
        this.setSize(300, 270);
        this.setVisible(true);
        this.setTitle("Calculator");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
}
