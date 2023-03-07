package gr.uniwa.patelis.streamingffmpeg;

import com.google.common.io.Files;
import fr.bmartel.speedtest.SpeedTestReport;
import fr.bmartel.speedtest.SpeedTestSocket;
import fr.bmartel.speedtest.inter.ISpeedTestListener;
import fr.bmartel.speedtest.model.SpeedTestError;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StreamingClient implements Runnable {
    
    private static StreamingClient streamClient;

    // Logger related variables
    protected enum LogLevel {DEBUG, ERROR, INFO};
    private final Logger log = LogManager.getLogger(StreamingClient.class);
    
    // Video related variables
    private List<Video> videosList = new ArrayList<>();
    private final String[] videoFormats = {"avi","mp4","mkv"};
    private final String[] videoProtocols = {"Default","TCP","UDP","RTP/UDP"};
    
    // Socket related variables
    private Socket socket;
    private Thread clientThread;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private final int SERVER_PORT = 4321;
    private final int SOCKET_TIMEOUT = 5000;
    private final int SERVER_VIDEO_PORT = 54321;
    private final String SERVER_IP = "127.0.0.1";
    private final String FFPLAY_PATH = "D:\\University_Apps\\ffmpeg\\bin\\ffplay.exe";
    private final File SDP_FILE = new File(System.getProperty("user.dir") + "\\video.sdp");
    
    // Speedtest related variables
    private SpeedTestSocket speedTestSocket;
    private BigDecimal speedTestDownloadRate;
    private final String SPEED_TEST_URL = "http://speedtest.ftp.otenet.gr/files/test10Mb.db";
    
    // UI related variables
    private JTextArea clientTextArea;
    private static JFrame clientFrame;
    private JScrollPane clientTextAreaScrollPane;
    private JLabel clientLabel, videoFormatLabel, videoProtocolLabel;
    private JComboBox formatComboBox, protocolComboBox, videoListComboBox;
    private JButton startPlaybackButton, connectToServerButton, disconnectFromServerButton;
    private JPanel clientTitlePanel, clientButtonsPanel, clientVideoPanel, clientTextAreaPanel;
    
    public StreamingClient() {
        initializeUIComponents();
        clientThread = new Thread(this);
        
        // Add components to frame
        clientTitlePanel.add(clientLabel);
        clientLabel.setFont(new Font("Sans-Serif", Font.BOLD, 22));
        clientTitlePanel.setAlignmentX(clientTitlePanel.CENTER_ALIGNMENT);
        clientFrame.add(clientTitlePanel);
        
        clientTextArea.setEditable(false);
        clientTextArea.setFont(new Font("Sans-Serif", Font.PLAIN, 14));
        clientTextAreaPanel.add(clientTextAreaScrollPane);
        clientFrame.add(clientTextAreaPanel);
        
        disconnectFromServerButton.setEnabled(false);
        clientButtonsPanel.add(videoFormatLabel);
        clientButtonsPanel.add(formatComboBox);
        clientButtonsPanel.add(connectToServerButton);
        clientButtonsPanel.add(disconnectFromServerButton);
        clientFrame.add(clientButtonsPanel);
        
        startPlaybackButton.setEnabled(false);
        protocolComboBox.setEnabled(false);
        videoListComboBox.setEnabled(false);
        clientVideoPanel.add(videoProtocolLabel);
        clientVideoPanel.add(protocolComboBox);
        clientVideoPanel.add(videoListComboBox);
        clientVideoPanel.add(startPlaybackButton);
        clientFrame.add(clientVideoPanel);
        
        // Action listerners for buttons
        connectToServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                // Disable components used to connect to server
                connectToServerButton.setEnabled(false);
                formatComboBox.setEnabled(false);
                clientThread.start();
                // Enable components used in server communication and termination
                disconnectFromServerButton.setEnabled(true);
                protocolComboBox.setEnabled(true);
            }
        });
        
        disconnectFromServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try{
                    closeServerConnection();
                }catch(IOException ex){
                    AppLogger(LogLevel.ERROR, ex.getMessage());
                }
                gr.uniwa.patelis.streamingffmpeg.StreamingClient.clientFrame.dispose();
                System.exit(0);
            }
        });
        
        startPlaybackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try{
                    String selectedProtocol = protocolComboBox.getSelectedItem().toString();
                    Video selectedVideo = videosList.get(videoListComboBox.getSelectedIndex());
                    // Send selected protocol and video to Streaming Server
                    outputStream.writeObject(selectedProtocol);
                    outputStream.writeObject(selectedVideo);
                    // Start video stream
                    initiateVideoPlayback(selectedProtocol, selectedVideo);
                }catch(IOException | InterruptedException | ClassNotFoundException ex){
                    AppLogger(LogLevel.ERROR, ex.getMessage());
                }
            }
        });
        
        clientFrame.setSize(550,400);   
        clientFrame.setResizable(false);
        clientFrame.setLocationRelativeTo(null);
        clientFrame.setTitle("Streaming Client");
        clientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clientFrame.setLayout(new BoxLayout(clientFrame.getContentPane(), BoxLayout.Y_AXIS));
        clientFrame.addWindowListener(new WindowAdapter(){
           @Override
           public void windowClosing(WindowEvent e){
                gr.uniwa.patelis.streamingffmpeg.StreamingClient.clientFrame.dispose();
                System.exit(0);                
           }
        });
        clientFrame.setVisible(true);
    }
    
    @Override
    public void run() {
        runSpeedTest();
    }
    
    // Method to log and append to text area given message 
    private void AppLogger(LogLevel logLevel, String message) {
        switch(logLevel){
            case DEBUG:
                log.debug(message);
                message = "[DEBUG] " + message;
                break;
            case ERROR:
                log.error(message);
                message = "[ERROR] " + message;
                break;
            case INFO:
                log.info(message);
                message = "[INFO] " + message;
                break;
        }
        clientTextArea.append(message + "\n");
        
    }
    
    // Method to terminate server connection
    private void closeServerConnection() throws IOException{
        if(outputStream!=null){
            outputStream.close();
        }
        if(inputStream!=null){
            inputStream.close();
        }
        if(socket!=null){
            socket.close();
        }
        AppLogger(LogLevel.DEBUG, "Closing Streaming Server connection and exiting");
    }
    
    private void initializeUIComponents(){
        clientFrame = new JFrame("Streaming Client");
        clientTitlePanel = new JPanel();
        clientButtonsPanel = new JPanel();
        clientVideoPanel = new JPanel();
        clientTextAreaPanel  = new JPanel();
        clientTextArea = new JTextArea(10, 45);
        clientTextAreaScrollPane = new JScrollPane(clientTextArea);
        videoListComboBox = new JComboBox();
        formatComboBox = new JComboBox(videoFormats);
        protocolComboBox = new JComboBox(videoProtocols);
        clientLabel = new JLabel("Streaming Client");
        videoFormatLabel = new JLabel("Video Format: ");
        videoProtocolLabel = new JLabel("Video Protocol: ");
        startPlaybackButton = new JButton("Start Playback");
        connectToServerButton = new JButton("Connect to Server");
        disconnectFromServerButton = new JButton("Disconnect from Server");
    }
    
    private void initializeServerConnection() throws IOException, ClassNotFoundException{
        AppLogger(LogLevel.INFO, "Trying to connect to Streaming Server " + SERVER_IP + ":" + SERVER_PORT);
        socket = new Socket(SERVER_IP, SERVER_PORT);
        
        if(socket!=null){
            AppLogger(LogLevel.DEBUG, "Connected to Streaming Server");
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());

            // Send speed test result and selected format to server
            outputStream.writeObject(speedTestDownloadRate);
            outputStream.writeObject(formatComboBox.getSelectedItem().toString());
            AppLogger(LogLevel.INFO, "Sent connection speedrate and selected video format to Streaming Server");

            // Receive suitable videos list from server
            videosList = (List<Video>) inputStream.readObject();
            for(Video video: videosList){
                videoListComboBox.addItem(video.getFileName());
            }
            AppLogger(LogLevel.INFO, "Received videos list from Streaming Server");
            
            // Enable playback related components
            videoListComboBox.setEnabled(true);
            startPlaybackButton.setEnabled(true);
        }

    }
    
    private void initiateVideoPlayback(String selectedProtocol, Video selectedVideo) throws IOException, InterruptedException, ClassNotFoundException {
        Process proc;
        ProcessBuilder procBuild;
        List<String> arguments = new ArrayList<>();
        
        arguments.add(FFPLAY_PATH);
        
        if(selectedProtocol.equals("Default")){
            selectedProtocol = (String) inputStream.readObject();
        }
        
        //Append playback protocol to process builder arguments
        switch(selectedProtocol){
            case "TCP":
                arguments.add(String.format("tcp://%s:%d", SERVER_IP, SERVER_VIDEO_PORT));
                break;
            case "UDP":
                arguments.add(String.format("udp://%s:%d", SERVER_IP, SERVER_VIDEO_PORT));
                break;
            case "RTP/UDP":
                arguments.add("-protocol_whitelist");
                arguments.add("file,rtp,udp");
                arguments.add("-i");
                arguments.add(SDP_FILE.toString());
                break;  
        }
        
        // Receive video.sdp file from Streaming Server if RTP/UDP is selected as playback protocol
        if(selectedProtocol.equals("RTP/UDP")){
            Files.write(FileUtils.readFileToByteArray((File) inputStream.readObject()), SDP_FILE);
        }
        
        procBuild = new ProcessBuilder(arguments);
        procBuild.inheritIO();
        
        AppLogger(LogLevel.INFO, "Starting playback of " + selectedVideo.getFileName() + " using protocol " + selectedProtocol + " on server address " 
                + SERVER_IP + ":" + SERVER_VIDEO_PORT);
        proc = procBuild.start();
        proc.waitFor();
        AppLogger(LogLevel.INFO, "Video playback finished");
        
        // Exit Streaming Client after video playback is finished
        closeServerConnection();
        gr.uniwa.patelis.streamingffmpeg.StreamingClient.clientFrame.dispose();
        System.exit(0);
    }
    
    private void runSpeedTest() {
        speedTestSocket = new SpeedTestSocket();
        speedTestSocket.setDownloadSetupTime(5000);
        speedTestSocket.setSocketTimeout(SOCKET_TIMEOUT);
        speedTestSocket.addSpeedTestListener(new ISpeedTestListener() {
            @Override
            public void onCompletion(SpeedTestReport report) {
                speedTestDownloadRate = (BigDecimal) report.getTransferRateBit();
                AppLogger(LogLevel.INFO, String.format("Rate in Mbps: %.1f ", speedTestDownloadRate.doubleValue() * 0.000001));
                try{
                    initializeServerConnection();
                }catch(IOException | ClassNotFoundException ex) {
                    AppLogger(LogLevel.ERROR, ex.getMessage());
                }
            }

            @Override
            public void onError(SpeedTestError speedTestError, String errorMessage) {
                AppLogger(LogLevel.ERROR, errorMessage);
            }

            @Override
            public void onProgress(float percent, SpeedTestReport report) { }
        });
        AppLogger(LogLevel.DEBUG, "Starting connection speed test...");
        speedTestSocket.startDownload(SPEED_TEST_URL);
    }
    
    public static void main(String args[]) {
        streamClient = new StreamingClient();
    }
    
}
