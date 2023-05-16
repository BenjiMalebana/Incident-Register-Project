import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

/*
author Benji Malebana @ OIC 2023 Apr
 */
public class ForgotPasswordOTPPanel {
    private JTextField otpTextField;
    private JPanel EnterOTPPanel;
    private JButton submitButton;
    private JLabel otpLabel;

    public ForgotPasswordOTPPanel(){
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public JPanel getPanel(){
        return EnterOTPPanel;
    }
}
