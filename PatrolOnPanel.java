import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import javax.swing.*;
import java.awt.*;

/*
  author Benji Malebana @ OIC 2023 May

  When the patrol is on, it gets switched off by this subroutine.

*/
public class PatrolOnPanel {
    private JTextField patrolElapsedTimeTextField;
    private JTextField patrolStartTimeTextField;
    private JTextField patrolEndTimeTextField;
    private JLabel patrolElapsedTimeLabel;
    private static JPanel wasThereOccurrencePanel;
    private static JFrame wasThereOccurrenceFrame;
    private JLabel patrolStartTimeLabel;
    private JLabel patrolEndTimeLabel;
    private JButton patrolOffButton;
    private JPanel PatrolOnPanel;
    private JLabel patrolOnLabel;
    private long startTime;
     public PatrolOnPanel() throws InterruptedException {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        String patrolStartTime = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
        patrolStartTimeTextField.setText(patrolStartTime);
        startTime = System.currentTimeMillis();

        patrolOffButton.addActionListener(new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent e) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);
                String patrolStartTime = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
                patrolEndTimeTextField.setText(patrolStartTime);

                long endTime = System.currentTimeMillis();
                long elapsedTime = endTime - startTime;
                long hours = elapsedTime / (1000 * 60 * 60);
                long minutes = (elapsedTime / (1000 * 60)) % 60;
                long seconds = (elapsedTime / 1000) % 60;
                String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
                patrolElapsedTimeTextField.setText(time);
                patrolOnLabel.setText("Patrol is Off");
                String username = IncidentReport.getUsername();

                if(wasThereOccurrencePanel==null){

                    wasThereOccurrencePanel = new CheckOccurrence().getPanel();
                    wasThereOccurrenceFrame = new JFrame("Occurrence?");
                    Image icon = new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB_PRE);
                    wasThereOccurrenceFrame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            super.windowClosing(e);
                            wasThereOccurrencePanel = null;
                        }
                    });
                    wasThereOccurrenceFrame.setIconImage(icon);
                    wasThereOccurrenceFrame.setContentPane(wasThereOccurrencePanel);
                    wasThereOccurrenceFrame.pack();
                    wasThereOccurrenceFrame.setVisible(true);
                }
            }
        });
    }
    public JPanel getPanel(){

        return PatrolOnPanel;
    }
    public static void disposeCheckOccurrenceFrame(){
        wasThereOccurrenceFrame.dispose();
        wasThereOccurrencePanel = null;
    }
}
