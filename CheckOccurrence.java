import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

/*
 author Benji Malebana @ OIC 2023 May

 This subroutine checks if there is an occurrence when the patrol is being switched off.

 */
public class CheckOccurrence {
    private JLabel checkOccurrenceLabel;
    private JPanel CheckOccurrencePanel;
    private JButton yesButton;
    private JButton noButton;
    public CheckOccurrence() {
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControlForm.disposePatrolOnFrame();
                PatrolOnPanel.disposeCheckOccurrenceFrame();
            }
        });
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControlForm.constructIncidentPanel();
                ControlForm.disposePatrolOnFrame();
                PatrolOnPanel.disposeCheckOccurrenceFrame();
            }
        });
    }
    public JPanel getPanel(){
        return  CheckOccurrencePanel;
    }
}
