import  java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;


public class calculator {
    int boardwidth = 360;
    int boardHeight = 540;

    Color customwhite = new Color(184,184,255);
    Color customlilac = new Color(248,247,255);
    Color customBLack = new Color(28,28,28);
    Color custompurple = new Color(58,80,107);

       String[] buttonValues = {
        "AC", "+/-", "%", "÷", 
        "7", "8", "9", "×", 
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", ".", "√", "="
    };
    String[] rightSymbols = {"÷", "×", "-", "+", "="};
    String[] topSymbols = {"AC", "+/-", "%"};





    JFrame frame = new JFrame("Calculator");
    JLabel displayLabel = new JLabel();
    JPanel displayPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
// A+B A-B A*B A/B
    String A = "0";
    String Op = null;
    String B = null;

    calculator(){
        // frame.setVisible(true);
        frame.setSize(boardwidth,boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        displayLabel.setBackground(customBLack);
        displayLabel.setForeground(Color.white);
        displayLabel.setFont(new Font("Arial",Font.PLAIN,80));
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);

        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displayLabel);
        frame.add(displayPanel,BorderLayout.NORTH);

        buttonPanel.setLayout(new GridLayout(5,4));
        buttonPanel.setBackground(customBLack);
        frame.add(buttonPanel);
        for(int i=0;i<buttonValues.length;i++){
            JButton button = new JButton();
            String buttonvalue = buttonValues[i];
            button.setFont(new Font("Arial",Font.PLAIN,30));
            button.setText(buttonvalue);
            button.setFocusable(false);
            button.setBorder(new LineBorder(customBLack));

            if (Arrays.asList(topSymbols).contains(buttonvalue)){
                button.setBackground(customwhite);
                button.setForeground(Color.black);

            }else if(Arrays.asList(rightSymbols).contains(buttonvalue)){
                button.setBackground(custompurple);
                button.setForeground(Color.white);

            }else{
                button.setBackground(customlilac);
                button.setForeground(Color.black);
            }
            buttonPanel.add(button);

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e ){
                    JButton button = (JButton) e.getSource();
                    String buttonvalue = button.getText();
                    if(Arrays.asList(rightSymbols).contains(buttonvalue)){
                        if(buttonvalue == "="){
                            if(A!=null){
                                B = displayLabel.getText();
                                double numA = Double.parseDouble(A);
                                double numB = Double.parseDouble(B);

                                if(Op == "+"){
                                    displayLabel.setText((removezerodecimal(numA+numB)));
                                }
                                else if(Op == "-"){
                                    displayLabel.setText(removezerodecimal(numA-numB));
                                }
                                else if(Op == "×"){
                                    displayLabel.setText(removezerodecimal(numA*numB));
                                }
                                else if(Op == "÷"){
                                    displayLabel.setText(removezerodecimal(numA/numB));
                                }

                                clearAll();

                            }

                        }
                        else if("÷×-+".contains(buttonvalue)){
                            if(Op == null){
                                A = displayLabel.getText();
                                displayLabel.setText("0");
                                B="0";
                            }
                            Op = buttonvalue;
                        }
                    }
                    else if(Arrays.asList(topSymbols).contains(buttonvalue)){
                        if(buttonvalue == "AC"){
                            clearAll();
                            displayLabel.setText("0");

                        }else if(buttonvalue == "+/-"){
                            double numdisplay = Double.parseDouble(displayLabel.getText());
                            numdisplay *= -1;
                            displayLabel.setText(removezerodecimal(numdisplay));

                        }else if(buttonvalue == "%"){
                             double numdisplay = Double.parseDouble(displayLabel.getText());
                            numdisplay /= 100;
                            displayLabel.setText(removezerodecimal(numdisplay));


                        }

                    }
                    else if(buttonvalue.equals("√")){

    double numdisplay = Double.parseDouble(displayLabel.getText());

    if(numdisplay >= 0){
        displayLabel.setText(removezerodecimal(Math.sqrt(numdisplay)));
    }else{
        displayLabel.setText("Error");
    }

}
                    else{ // digit or .
                        if(buttonvalue == "."){
                            if(!displayLabel.getText().contains(buttonvalue)){
                                displayLabel.setText(displayLabel.getText()+buttonvalue);

                            }

                        }
                        else if("0123456789".contains(buttonvalue)){
                            if(displayLabel.getText()=="0"){
                                displayLabel.setText(buttonvalue);
                            }else{
                                displayLabel.setText(displayLabel.getText()+buttonvalue);
                            }
                        }

                    }

                }
            });
             frame.setVisible(true);
        }
        

    }
    void clearAll(){
        A = "0";
        Op = null;
        B  = null;
    }

    String removezerodecimal(double  numdisplay){
        if(numdisplay %1==0){
            return Integer.toString((int) numdisplay);
        }
        return Double.toString(numdisplay);
    }
   
}
