package gr.uniwa.patelis.streamingffmpeg;

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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;

public class StreamingServer implements Runnable {
    
    // Logger related variables
    protected enum LogLevel {DEBUG, ERROR, INFO};
    private final Logger log = LogManager.getLogger(StreamingServer.class);
    
    // Video related variables
    private List<String> videoFormats, videoResolutions;
    private HashMap<String, Integer> videoResolutionsInt;
    private HashMap<String, String> videoResolutionsExtended;
    private HashMap<String, Integer> videoResolutionSpeed240p;
    private HashMap<String, Integer> videoResolutionSpeed360p;
    private HashMap<String, Integer> videoResolutionSpeed480p;
    private HashMap<String, Integer> videoResolutionSpeed720p;
    private HashMap<String, Integer> videoResolutionSpeed1080p;
    private SortedMap<String, HashMap<String, Integer>> videoResolutionSpeed;
    private final String videoDirectory = System.getProperty("user.dir") + "\\videos\\";

    // FFMpeg related variables
    private FFmpeg ffmpeg = null;
    private FFprobe ffprobe = null;
    private FFmpegExecutor executor = null;
    private final String FFMPEG_PATH = "D:\\University_Apps\\ffmpeg\\bin\\ffmpeg.exe";
    private final String FFPROBE_PATH = "D:\\University_Apps\\ffmpeg\\ffprobe.exe";
    private final String SDP_FILE = System.getProperty("user.dir") + "\\video.sdp";
    
    // Socket related variables
    private Socket socket;
    private Thread serverThread;
    private ServerSocket serverSocket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private final int SERVER_SOCKET_PORT = 4321;
    private final int SERVER_VIDEO_PORT = 54321;
    private final String SERVER_IP = "127.0.0.1";

    // UI related variables
    private JLabel serverLabel;
    private JTextArea serverTextArea;
    private static JFrame serverFrame;
    private JScrollPane serverTextAreaScrollPane;
    private JButton startServerButton, stopServerButton;
    private JPanel serverTitlePanel, serverButtonsPanel, serverTextAreaPanel;
    
    
    public StreamingServer() {
        initializeUIComponents();
        serverThread = new Thread(this);
        
        // Add components to frame
        serverTitlePanel.add(serverLabel);
        serverLabel.setFont(new Font("Sans-Serif", Font.BOLD, 22));
        serverTitlePanel.setAlignmentX(serverTitlePanel.CENTER_ALIGNMENT);
        serverFrame.add(serverTitlePanel);
        
        serverTextArea.setEditable(false);
        serverTextArea.setFont(new Font("Sans-Serif", Font.PLAIN, 14));
        serverTextAreaPanel.add(serverTextAreaScrollPane);
        serverFrame.add(serverTextAreaPanel);
        
        serverButtonsPanel.add(startServerButton);
        serverButtonsPanel.add(stopServerButton);
        serverFrame.add(serverButtonsPanel);
        
        // Action listerners for buttons
        startServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                serverThread.start();
                startServerButton.setEnabled(false);
            }
        });
        
        stopServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try{
                    terminateSocketServer();
                }catch(IOException ex){
                    AppLogger(LogLevel.ERROR, ex.getMessage());
                }
                gr.uniwa.patelis.streamingffmpeg.StreamingServer.serverFrame.dispose(); 
                System.exit(0); 
            }
        });
        
        serverFrame.setSize(550,400);   
        serverFrame.setResizable(false);
        serverFrame.setLocationRelativeTo(null);
        serverFrame.setTitle("Streaming Server");
        serverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        serverFrame.setLayout(new BoxLayout(serverFrame.getContentPane(), BoxLayout.Y_AXIS));
        serverFrame.addWindowListener(new WindowAdapter(){
           @Override
           public void windowClosing(WindowEvent e){
               try{
                    terminateSocketServer();
                }catch(IOException ex){
                    AppLogger(LogLevel.ERROR, ex.getMessage());
                }
                gr.uniwa.patelis.streamingffmpeg.StreamingServer.serverFrame.dispose(); 
               System.exit(0);                
           }
        });
        serverFrame.setVisible(true);
    }
    
    @Override
    public void run() {
        initializeResolutionComponents();
        try{
            startVideoConversion();
            listenForClientConnection();
        }catch(ClassNotFoundException | IOException | InterruptedException ex) {
            AppLogger(LogLevel.ERROR, ex.getMessage());
        }
    }
    
    // Method to log and append given message to text area
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
        serverTextArea.append(message + "\n");
    }
    
    // Method to terminate socket server
    private void terminateSocketServer() throws IOException{
        if(outputStream!=null){
            outputStream.close();
        }
        if(inputStream!=null){
            inputStream.close();
        }
        if(socket!=null){
            socket.close();
        }
        if(serverSocket!=null){
            serverSocket.close();
        }
        AppLogger(LogLevel.DEBUG, "Closing streaming server\n");
    }
    
    // Method to initialize components used in UI
    private void initializeUIComponents(){
        serverFrame = new JFrame("Streaming Server");
        serverLabel = new JLabel("Streaming Server");
        serverTitlePanel = new JPanel();
        serverButtonsPanel = new JPanel();
        serverTextAreaPanel  = new JPanel();
        serverTextArea = new JTextArea(14, 45);
        serverTextAreaScrollPane = new JScrollPane(serverTextArea);
        stopServerButton = new JButton("Stop Server");
        startServerButton = new JButton("Start Server"); 
    }
    
    private void initializeResolutionComponents(){
        videoFormats = new ArrayList<>();
        videoResolutions = new ArrayList<>();
        videoResolutionsInt = new HashMap<>();
        videoResolutionsExtended = new HashMap<>();
        videoResolutionSpeed240p = new HashMap<>();
        videoResolutionSpeed360p = new HashMap<>();
        videoResolutionSpeed480p = new HashMap<>();
        videoResolutionSpeed720p = new HashMap<>();
        videoResolutionSpeed1080p = new HashMap<>();
        videoResolutionSpeed = new TreeMap<>();
        
        videoFormats.add("avi");
        videoFormats.add("mp4");
        videoFormats.add("mkv");
        
        videoResolutions.add("240p");
        videoResolutions.add("360p");
        videoResolutions.add("480p");
        videoResolutions.add("720p");
        videoResolutions.add("1080p");
        
        videoResolutionsInt.put("240p",240);
        videoResolutionsInt.put("360p",360);
        videoResolutionsInt.put("480p",480);
        videoResolutionsInt.put("720p",720);
        videoResolutionsInt.put("1080p",1080);
        
        videoResolutionsExtended.put("240p","426x240");
        videoResolutionsExtended.put("360p","640x360");
        videoResolutionsExtended.put("480p","854x480");
        videoResolutionsExtended.put("720p","1280x720");
        videoResolutionsExtended.put("1080p","1920x1080");
        
        videoResolutionSpeed240p.put("Maximum",700);
        videoResolutionSpeed240p.put("Recommended",400);
        videoResolutionSpeed240p.put("Minimum",300);
        
        videoResolutionSpeed360p.put("Maximum",1000);
        videoResolutionSpeed360p.put("Recommended",750);
        videoResolutionSpeed360p.put("Minimum",400);
        
        videoResolutionSpeed480p.put("Maximum",2000);
        videoResolutionSpeed480p.put("Recommended",1000);
        videoResolutionSpeed480p.put("Minimum",500);
        
        videoResolutionSpeed720p.put("Maximum",4000);
        videoResolutionSpeed720p.put("Recommended",2500);
        videoResolutionSpeed720p.put("Minimum",1500);
        
        videoResolutionSpeed1080p.put("Maximum",6000);
        videoResolutionSpeed1080p.put("Recommended",4500);
        videoResolutionSpeed1080p.put("Minimum",3000);
        
        videoResolutionSpeed.put(videoResolutions.get(0),videoResolutionSpeed240p);
        videoResolutionSpeed.put(videoResolutions.get(1),videoResolutionSpeed360p);
        videoResolutionSpeed.put(videoResolutions.get(2),videoResolutionSpeed480p);
        videoResolutionSpeed.put(videoResolutions.get(3),videoResolutionSpeed720p);
        videoResolutionSpeed.put(videoResolutions.get(4),videoResolutionSpeed1080p);
    }
    
    private List<Video> findSuitableVideos(List<Video> videosList, String requestedVideoFormat, BigDecimal clientSpeedRate){
        List<Video> suitableVideos = new ArrayList<>();
        List<String> suitableResolutions = new ArrayList<>();
        
        // Find suitable resolutions based on speedtest
        for(SortedMap.Entry<String,HashMap<String,Integer>> entrySM: videoResolutionSpeed.entrySet()){
            if(entrySM.getValue().get("Recommended")<= Math.floor(clientSpeedRate.doubleValue() * 0.0001)){
                suitableResolutions.add(entrySM.getKey());
            }
        }
        
        // Find suitable videos based on suitable resolutions and requested format
        for(Video video: videosList) {
            
            if(video.getFormat().equals(requestedVideoFormat)){
                
                for(String resolution: suitableResolutions){
                    
                    if(videoResolutionsInt.get(resolution)<=video.getResolutionInt()){
                        suitableVideos.add(video);
                        break;
                    }
                }
            }
        }
        
        AppLogger(LogLevel.DEBUG, "Suitable videos list created");
        
        return suitableVideos;
    }
    
    private void initiateVideoPlayback(Video video, String protocol) throws IOException, InterruptedException {
        Process proc;
        ProcessBuilder procBuild;
        String format = "";
        List<String> arguments = new ArrayList<>();
        String resolution = video.getShortResolution();
        
        arguments.add(FFMPEG_PATH);
        
        // Append video file name to process builder arguments
        arguments.add("-i");
        arguments.add(videoDirectory + video.getFileName());
        
        // Append video format to process builder arguments
        switch(video.getFormat()){
            case "avi":
                format = video.getFormat();
                break;
            case "mp4":
                format = "mp4";
                break;
            case "mkv":
                format = "matroska";
                break;
        }
        
        //Append playback protocol to process builder arguments
        switch(protocol){
            case "TCP":
                arguments.add("-f");
                arguments.add(format);
                arguments.add(String.format("tcp://%s:%d?listen", SERVER_IP, SERVER_VIDEO_PORT));
                break;
            case "UDP":
                arguments.add("-f");
                arguments.add(format);
                arguments.add(String.format("udp://%s:%d?pkt_size=%d", SERVER_IP, SERVER_VIDEO_PORT,188));
                break;
            case "RTP/UDP":
                arguments.add("-an");
                arguments.add("-c:v");
                arguments.add("copy");
                arguments.add("-f");
                arguments.add("rtp");
                arguments.add("-sdp_file");
                arguments.add(SDP_FILE);
                arguments.add(String.format("rtp://%s:%d", SERVER_IP, SERVER_VIDEO_PORT));
                break;
            default:
                switch(resolution){
                    case "240p":
                        outputStream.writeObject("TCP");
                        initiateVideoPlayback(video,"TCP");
                        break;
                    case "360p":
                    case "480p":
                        outputStream.writeObject("UDP");
                        initiateVideoPlayback(video,"UDP");
                        break;
                    case "720p":
                    case "1080p":
                        outputStream.writeObject("RTP/UDP");
                        initiateVideoPlayback(video,"RTP/UDP");
                        break;
                }
        }
        
        // Send video.sdp file to Streaming Client if RTP/UDP is selected as playback protocol
        if(protocol.equals("RTP/UDP")){
            File sdpFile = new File(SDP_FILE);
            outputStream.writeObject(sdpFile);
        }
        
        procBuild = new ProcessBuilder(arguments);
        procBuild.inheritIO();
        
        AppLogger(LogLevel.INFO, "Starting playback of " + video.getFileName() + " using protocol " + protocol);
        proc = procBuild.start();
        proc.waitFor();
        AppLogger(LogLevel.INFO, "Video playback finished");
    }
    
    // Method to retrieve videos list from storage
    private List<Video> retrieveVideosList() throws InterruptedException{
        List<Video> videosList = new ArrayList();
        File[] rawVideos = new File(videoDirectory).listFiles();
        
        // Check if video directory exists
        if(!new File(videoDirectory).exists()){
            AppLogger(LogLevel.ERROR, "Couldn't file video directory, exiting..");
            Thread.sleep(2500);
            System.exit(-1);
        }
        
        // Check if video directory is empty
        if(rawVideos.length == 0){
            AppLogger(LogLevel.ERROR, "Video directory is empty, exiting..");
            Thread.sleep(2500);
            System.exit(-2);
        }
        
        // Split video filename to name, resolution and format
        for(File rawVideo: rawVideos){
            String[] split1 = rawVideo.getName().split("-");
            String[] split2 = split1[1].split("\\.");
            
            String inputName = split1[0];
            String inputResolution = split2[0];
            String inputFormat = split2[1];
            videosList.add(new Video(inputName, videoResolutionsInt.get(inputResolution), inputResolution, 
                    videoResolutionsExtended.get(inputResolution), inputFormat));
        }
        
        return videosList;
    }
    
    private void listenForClientConnection() throws IOException, ClassNotFoundException, InterruptedException {
        Video requestedVideo;
        BigDecimal clientSpeedRate;
        List<Video> videosList = retrieveVideosList();
        String requestedVideoFormat, requestedVideoProtocol;
        serverSocket = new ServerSocket(SERVER_SOCKET_PORT);
        
        while(true) {
            AppLogger(LogLevel.DEBUG, "Listening for client requests\n");
            socket = serverSocket.accept();
            
            if(socket.isBound()){
                AppLogger(LogLevel.INFO, "New client connected");
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                inputStream = new ObjectInputStream(socket.getInputStream());

                clientSpeedRate = (BigDecimal) inputStream.readObject();
                AppLogger(LogLevel.INFO,String.format("Speedtest result received: %.1f ", clientSpeedRate.doubleValue() * 0.000001));

                requestedVideoFormat = (String) inputStream.readObject();
                AppLogger(LogLevel.INFO,"Requested format received: " + requestedVideoFormat);
                
                outputStream.writeObject(findSuitableVideos(videosList, requestedVideoFormat, clientSpeedRate));

                requestedVideoProtocol = (String) inputStream.readObject();
                AppLogger(LogLevel.INFO,"Requested protocol received: " + requestedVideoProtocol);

                requestedVideo = (Video) inputStream.readObject();
                AppLogger(LogLevel.INFO,"Requested video received: " + requestedVideo.getFileName() + "\n");

                initiateVideoPlayback(requestedVideo, requestedVideoProtocol);
            }
            
        }
    }

    private void startVideoConversion() throws InterruptedException {
        try{
            AppLogger(LogLevel.DEBUG, "Initialising FFMpeg");
            ffmpeg = new FFmpeg(FFMPEG_PATH);
            ffprobe = new FFprobe(FFPROBE_PATH);
            
            AppLogger(LogLevel.DEBUG,"Creating the FFMpegExecutor\n");
            executor = new FFmpegExecutor(ffmpeg, ffprobe);
        }catch(IOException e){
            AppLogger(LogLevel.ERROR,e.getMessage());
        }
        
        Video currentConversionVideo = new Video();
        
        for(Video video : retrieveVideosList()){
            AppLogger(LogLevel.INFO, "Raw video found: " + video.getFileName());

            for(String format : videoFormats) {
                
                for(String resolution : videoResolutions) {
                    
                    currentConversionVideo.setName(video.getName());
                    currentConversionVideo.setResolutionInt(videoResolutionsInt.get(resolution));
                    currentConversionVideo.setShortResolution(resolution);
                    currentConversionVideo.setExtendedResolution(videoResolutionsExtended.get(resolution));
                    currentConversionVideo.setFormat(format);
                    
                    // Check if video type-resolution combo exists and convert if not
                    if(!new File(videoDirectory + currentConversionVideo.getFileName()).exists()){
                        
                        // Check if resolution to be converted is bigger than the resolution of the original video
                        if(currentConversionVideo.getResolutionInt()<=video.getResolutionInt()){
                            AppLogger(LogLevel.INFO, "Converting " + video.getFileName() + 
                                    " to ." + currentConversionVideo.getFormat() + " format with " +
                                    currentConversionVideo.getShortResolution() + " resolution");

                            AppLogger(LogLevel.DEBUG, "Creating the transcoding of " + currentConversionVideo.getFileName());
                            FFmpegBuilder builder = (new FFmpegBuilder()
                                    .setInput(videoDirectory + video.getFileName())
                                    .addOutput(videoDirectory + currentConversionVideo.getFileName()))
                                    .setVideoResolution(currentConversionVideo.getExtendedResolution())
                                    .done();

                            AppLogger(LogLevel.DEBUG, "Starting the transcoding");
                            executor.createJob(builder).run();

                            AppLogger(LogLevel.DEBUG, "Transcoding finished");
                            AppLogger(LogLevel.INFO,currentConversionVideo.getFileName() + " created\n");
                        }
                    }
                }
            }
        }
    }
    
    public static void main(String args[]) {
        new StreamingServer();
    }
    
}
