import java.util.logging.SimpleFormatter;
import java.util.logging.FileHandler;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.image.BufferedImage;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.IOException;
import java.util.Calendar;
import javax.swing.*;
import java.awt.*;

/*
  author Benji Malebana @ OIC 2023 May

  This is the main control panel, where different functions of the application gets controlled from.

*/

public class ControlForm {
    FileHandler logFileHandler = new FileHandler("C:\\Users\\Desktop\\IdeaProjects\\oic\\src\\OIC\\src\\control.log",true);
    private static final Logger logger = Logger.getLogger(IncidentReport.class.getName());
    private static JPanel patrolStartedFetchedPanel;
    private JButton incidentReportButton;
    private JButton patrolReportButton;
    private JLabel incidentReportLabel;
    private JButton shiftReportButton;
    private static JFrame patrolOnPanelFrame;
    private JLabel patrolReportLabel;
    private JLabel shiftReportLabel;
    private JPanel ControlFormPanel;
    private JButton patrolButton;
    private static JPanel fetchedPanel;
    private JButton entryButton;
    private JLabel patrolLabel;
    private JLabel dutyLabel;
    private JLabel entryLabel;
    private JButton dutyOnButton;
    private JButton dutyOffButton;


    public ControlForm() throws IOException {
        System.setProperty("java.util.logging.FileHandler.locking", "true");
        System.setProperty("java.util.logging.FileHandler.control.lck", "C:\\Users\\Desktop\\IdeaProjects\\Test\\src");
        logFileHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(logFileHandler);
        logger.setLevel(Level.ALL);

        incidentReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        patrolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(patrolStartedFetchedPanel==null){
                    try {
                        patrolStartedFetchedPanel = new PatrolOnPanel().getPanel();
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    patrolOnPanelFrame =new JFrame("Patrol Window");
                    Image icon = new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB_PRE);
                    patrolOnPanelFrame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            super.windowClosing(e);
                            patrolStartedFetchedPanel =null;
                        }
                    });
                    patrolOnPanelFrame.setIconImage(icon);
                    patrolOnPanelFrame.setContentPane(patrolStartedFetchedPanel);
                    patrolOnPanelFrame.pack();
                    patrolOnPanelFrame.setVisible(true);

                }
            }
        });
        dutyOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String username = IncidentReport.getUsername();
               Calendar calendar = Calendar.getInstance();
               int year = calendar.get(Calendar.YEAR);
               int month = calendar.get(Calendar.MONTH);
               int day = calendar.get(Calendar.DAY_OF_MONTH);
               int hour = calendar.get(Calendar.HOUR_OF_DAY);
               int minute = calendar.get(Calendar.MINUTE);
               int second = calendar.get(Calendar.SECOND);
               String dutyOnTime = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
               System.out.println(username + " Duty On Time : " + dutyOnTime);
            }
        });
        dutyOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = IncidentReport.getUsername();
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);
                String dutyOffTime = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
                System.out.println(username + " Duty Off Time : " + dutyOffTime);
            }
        });
    }

    public static void constructIncidentPanel(){
        if(fetchedPanel==null){
            try {
                fetchedPanel = new  IncidentFormPanel().getPanel();
            } catch (IOException ex) {
                logger.severe("Failed to open control panel");
                throw new RuntimeException(ex);
            }
            JFrame incidentPanelFrame =new JFrame("Capture Occurrence Details");
            Image icon = new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB_PRE);
            incidentPanelFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    fetchedPanel =null;
                }
            });
            incidentPanelFrame.setIconImage(icon);
            incidentPanelFrame.setContentPane(fetchedPanel);
            incidentPanelFrame.pack();
            incidentPanelFrame.setVisible(true);
        }
    }
    public JPanel getPanel(){
        return  ControlFormPanel;
    }

    public static void disposePatrolOnFrame(){
        patrolOnPanelFrame.dispose();
        patrolStartedFetchedPanel = null;
    }

    public static void repaintPatrolOnFrame(){
        patrolOnPanelFrame.repaint();
    }
}
