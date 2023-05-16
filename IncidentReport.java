import java.awt.event.*;
import java.util.logging.SimpleFormatter;
import java.util.logging.FileHandler;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.io.*;

/*
 author Benji Malebana @ OIC 2023 Apr

  This is the main subroutine for the application. The application has the following functionalities:
 * captures the duty on/off when security officers comes on/off duty
 *Logs the patrol and captures any incident/occurrence that gets picked up during the patrol
 *Allows on-duty officers to capture any entry that might arise on behalf of the officer not on-site.
 *Allows the security manager to pull three types of reports
    - Patrol Report
    - Shift Report
    - Incident/Occurrence Report
 */
public class IncidentReport extends Container {
    private File authFile = new File("C:\\Users\\Desktop\\IdeaProjects\\oic\\src\\OIC\\src\\OICIncidentRegisterAuth.txt");
    FileHandler logFileHandler = new FileHandler("C:\\Users\\Desktop\\IdeaProjects\\oic\\src\\OIC\\src\\authenticator.log",true);
    private static final Logger logger = Logger.getLogger(IncidentReport.class.getName());
    private JButton forgotPasswordButton;
    private JTextField usernameTextField;
    private JPanel IncidentReportPanel;
    private JPanel fetchedControlPanel;
    private JPanel otpFetchedPanel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JButton exitButton;
    private JButton loginButton;
    private int min = 1000;
    private int max = 9999;
    private JLabel logo;
    private JPasswordField passwordField;
    private static String username;

    public IncidentReport() throws IOException {

        System.setProperty("java.util.logging.FileHandler.locking", "true");
        System.setProperty("java.util.logging.FileHandler.authenticator.lck", "C:\\Users\\Desktop\\IdeaProjects\\Test\\src");
        logFileHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(logFileHandler);
        logger.setLevel(Level.ALL);

            loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    authenticate();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        forgotPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(otpFetchedPanel==null){
                    otpFetchedPanel = new ForgotPasswordOTPPanel().getPanel();
                    JFrame otpFrame = new JFrame("Enter OTP to Reset Password");
                    Image icon = new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB_PRE);
                    otpFrame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            super.windowClosing(e);
                            otpFetchedPanel=null;
                        }
                    });
                    otpFrame.setIconImage(icon);
                    otpFrame.setContentPane(otpFetchedPanel);
                    otpFrame.pack();
                    otpFrame.setVisible(true);
                    System.out.println("Random OTP: " + getOTP());
                }
            }
        });
    }
    public int getOTP(){
        Random random = new Random();
        return random.nextInt(max - min +1);
    }
    public void authenticate() throws IOException {
        username = usernameTextField.getText();
        String password = passwordField.getText();
        String authSearch = usernameTextField.getText()+passwordField.getText();
        String authString;
        boolean found = false;
        FileReader authFileReader = new FileReader(authFile);
        BufferedReader authFileBufferedReader = new BufferedReader(authFileReader);

        while((authString=authFileBufferedReader.readLine())!=null) if (authString.equals(authSearch)) {
            found = true;
            usernameTextField.setText("");
            passwordField.setText("");
        }

        if(found){
            logger.info("User " + username + " logged in.");
            constructControlPanel();
        }else {
            JOptionPane.showMessageDialog(null, "The username and password combination is incorrect, please check " +
                    "and try again, alternatively click " + "Forgot Password");
            logger.severe("User " + username + " failed to log in.");
        }
    }

    public void constructControlPanel() throws IOException {
        if(fetchedControlPanel == null){
            fetchedControlPanel = new ControlForm().getPanel();
            JFrame controlFrame = new JFrame("Control Panel");
            Image icon = new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB_PRE);
            controlFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    fetchedControlPanel =null;
                }
            });
            controlFrame.setIconImage(icon);
            controlFrame.setContentPane(fetchedControlPanel);
            controlFrame.pack();
            controlFrame.setVisible(true);
            }
    }
    public static String getUsername(){
        return username;
    }

    public static void main(String[] args) throws IOException {
        JFrame frame =new JFrame("OIC Security Incident Register");
        Image icon = new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB_PRE);
        frame.setIconImage(icon);
        frame.setContentPane(new IncidentReport().IncidentReportPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
