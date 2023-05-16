import javax.swing.text.BadLocationException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import javax.swing.*;

/*
 author Benji Malebana @ OIC 2023 Apr

The subroutine allows the users to capture any occurrence/incident that gets picked up while patrolling.

 */
public class IncidentFormPanel {
    private JTextArea incidentDescriptionTextArea;
    private JTextField occurrenceTextField;
    private JButton attachmentButton;
    private JTextField dateTextField;
    private JSpinner prioritySpinner;
    private JLabel occurrenceLabel;
    private JButton calendarButton;
    private JSpinner zoneSpinner;
    private JPanel IncidentPanel;
    private JLabel incidentLabel;
    private JButton reportButton;
    private JButton submitButton;
    private JLabel priorityLabel;
    private JLabel DateLabel;
    private JLabel zoneLabel;

    public IncidentFormPanel() throws IOException {

            //incidentTextArea = new JTextArea();
            calendarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);
                String incidentTime = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
                dateTextField.setText(incidentTime);
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileWriter storeIncidentFileWriter;
                BufferedWriter storeIncidentBufferedWriter;
               try {
                    storeIncidentFileWriter = new FileWriter("C:\\Users\\Desktop\\IdeaProjects\\oic\\src\\OIC\\src\\Incidents.txt",true);
                    storeIncidentBufferedWriter = new BufferedWriter(storeIncidentFileWriter);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                try {
                    if(dateTextField.getText().length() != 0 && incidentDescriptionTextArea.getText().length() !=0) {
                        storeIncidentBufferedWriter.write(dateTextField.getText() + "&" + incidentDescriptionTextArea.getText(0, incidentDescriptionTextArea.getText().length()));
                        storeIncidentBufferedWriter.write("%");
                        storeIncidentBufferedWriter.newLine();
                        storeIncidentBufferedWriter.close();
                        dateTextField.setText("");
                        incidentDescriptionTextArea.setText("");

                    }else{
                        JOptionPane.showMessageDialog(null, "Please check and make sure that both the date and description are populated before you submit!");
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                    } catch (BadLocationException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        attachmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameFileChooser = new JFrame("File Chooser");
                JFileChooser fileChooser = new JFileChooser();
                if(fileChooser.showOpenDialog(frameFileChooser) == JFileChooser.APPROVE_OPTION){
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                    Path sourcePath = Paths.get(filePath);
                    String filename = sourcePath.getFileName().toString();
                    Path targetPath = Paths.get("C:\\Users\\Desktop\\IdeaProjects\\oic\\src\\OIC\\src\\Evidence\\"+ sourcePath.getFileName().toString());
                    try {
                        Files.copy(sourcePath, targetPath);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Please note that the file " + filename + " already exist.");
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }
    public JPanel getPanel(){

        return IncidentPanel;
    }
}
