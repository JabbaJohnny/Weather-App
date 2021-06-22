import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    static JFrame frame = new JFrame("Weather App");
    static JPanel panel = new JPanel();
    static JTextField textField = new JTextField();
    static Container container = frame.getContentPane();
    static JLabel label = new JLabel("Your city:");
    static JButton button = new JButton("Check weather!");
    static JLabel tempDisplay = new JLabel();


    public GUI(){
        setup();
        buttonSetup();
    }

    public static void makeGUI(){

    }


    private static void setup(){
        ImageIcon icon = new ImageIcon("icon.png");
        Image logo = icon.getImage();

        panel.add(label);
        panel.add(textField);
        panel.add(button);
        panel.add(tempDisplay);
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setSize(500, 500);
        frame.setIconImage(logo);
        container.setBackground(Color.LIGHT_GRAY);
        textField.setColumns(10);
        textField.setSize(300, 100);

    }

    private void buttonSetup(){
        button.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(App.checkIfCityCorrect(textField.getText())){
                tempDisplay.setText("There is " + App.getTemperature(textField.getText()) + "°C in " + textField.getText() + " right now.");
            }else{
                tempDisplay.setText("You typed wrong city name. Try again!");
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
