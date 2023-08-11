import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calculation extends JFrame implements ActionListener {

    JLabel heightLabel, weightLabel, BMILabel, weightCategoryLabel, errorLabel;
    JTextField heightTextfield, weightTextfield;
    JButton calculateButton, resetButton;

    calculation() {
        setBounds(375,80,700,600);
        setTitle("BMI Calculator");
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        heightLabel =  new JLabel("Enter your height in centimeters: ");
        heightLabel.setBounds(70,70,350,30);
        heightLabel.setFont(new Font("Candara",Font.PLAIN,25));
        add(heightLabel);

        heightTextfield = new JTextField();
        heightTextfield.setBounds(490,67,70,30);
        heightTextfield.setFont(new Font("Candara",Font.PLAIN,25));
        add(heightTextfield);

        weightLabel = new JLabel("Enter your weight in Kilograms: ");
        weightLabel.setBounds(70,150,350,30);
        weightLabel.setFont(new Font("Candara",Font.PLAIN,25));
        add(weightLabel);

        weightTextfield = new JTextField();
        weightTextfield.setBounds(490,147,70,30);
        weightTextfield.setFont(new Font("Candara",Font.PLAIN,25));
        add(weightTextfield);

        calculateButton = new JButton("Calculate BMI");
        calculateButton.setBounds(235,220,200,55);
        calculateButton.setFont(new Font("Candara",Font.PLAIN,25));
        calculateButton.setFocusable(false);
        calculateButton.addActionListener(this);
        add(calculateButton);

        BMILabel = new JLabel("");
        BMILabel.setBounds(250,310,350,30);
        BMILabel.setFont(new Font("Candara",Font.PLAIN,25));
        BMILabel.setVisible(false);
        add(BMILabel);

        weightCategoryLabel = new JLabel();
        weightCategoryLabel.setBounds(120,350,500,30);
        weightCategoryLabel.setFont(new Font("Candara",Font.PLAIN,25));
        weightCategoryLabel.setVisible(false);
        add(weightCategoryLabel);

        errorLabel = new JLabel("Invalid input. Please make sure you are entering the correct height and weight.");
        errorLabel.setBounds(50,327,600,30);
        errorLabel.setFont(new Font("Candara",Font.PLAIN,18));
        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);
        add(errorLabel);

        resetButton = new JButton("Reset");
        resetButton.setBounds(285,410,100,40);
        resetButton.setFont(new Font("Candara",Font.PLAIN,25));
        resetButton.setFocusable(false);
        resetButton.setVisible(false);
        resetButton.addActionListener(this);
        add(resetButton);


        setVisible(true);
    }

    public void bmiCalculation() {
        try {
            double height = Double.parseDouble(heightTextfield.getText());
            double weight = Double.parseDouble(weightTextfield.getText());
            double bmiValue = (weight / (height * height) * 10000);
            double roundedBMI = Math.round(bmiValue * 10.0) / 10.0;
            String category = "";

            if (roundedBMI < 18.0) {
                category = "underweight";
                weightCategoryLabel.setBounds(95,350,500,30);
                weightCategoryLabel.setForeground(Color.blue);
            } else if (roundedBMI >= 18 && roundedBMI <= 25) {
                category = "healthy";
                weightCategoryLabel.setBounds(125,350,500,30);
                weightCategoryLabel.setForeground(Color.decode("#27ae60"));
            } else if (roundedBMI > 25 && roundedBMI <= 30) {
                category = "overweight";
                weightCategoryLabel.setBounds(105,350,500,30);
                weightCategoryLabel.setForeground(Color.decode("#eb4d4b"));
            } else if (roundedBMI > 30) {
                category = "obese";
                weightCategoryLabel.setBounds(120,350,500,30);
                weightCategoryLabel.setForeground(Color.red);
            }

            BMILabel.setText("Your BMI is " + roundedBMI + ".");
            weightCategoryLabel.setText("Your weight falls in the '" + category + "' category.");

            BMILabel.setVisible(true);
            weightCategoryLabel.setVisible(true);
            resetButton.setVisible(true);

        } catch (Exception e) {
            errorLabel.setVisible(true);
            resetButton.setVisible(true);
        }
    }

    public void actionPerformed (ActionEvent e) {

        if (e.getSource() == calculateButton) {
            BMILabel.setVisible(false);
            weightCategoryLabel.setVisible(false);
            errorLabel.setVisible(false);
            bmiCalculation();
        }

        if (e.getSource() == resetButton) {
            heightTextfield.setText("");
            weightTextfield.setText("");
            BMILabel.setVisible(false);
            weightCategoryLabel.setVisible(false);
            errorLabel.setVisible(false);
            resetButton.setVisible(false);
        }
    }
}
