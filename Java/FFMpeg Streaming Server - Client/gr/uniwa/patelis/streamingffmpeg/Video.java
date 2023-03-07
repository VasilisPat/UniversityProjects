package gr.uniwa.patelis.streamingffmpeg;

import java.io.Serializable;

public class Video implements Serializable {
    
    private String name;
    private int resolutionInt;
    private String shortResolution;
    private String extendedResolution;
    private String format;

    public Video() {
    }

    public Video(String name, int resolutionInt, String shortResolution, String extendedResolution, String format) {
        this.name = name;
        this.resolutionInt = resolutionInt;
        this.shortResolution = shortResolution;
        this.extendedResolution = extendedResolution;
        this.format = format;
    }
    
    // Getters
    public String getName() {
        return name;
    }

    public int getResolutionInt() {
        return resolutionInt;
    }

    public String getShortResolution() {
        return shortResolution;
    }

    public String getExtendedResolution() {
        return extendedResolution;
    }

    public String getFormat() {
        return format;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setResolutionInt(int resolutionInt) {
        this.resolutionInt = resolutionInt;
    }

    public void setShortResolution(String shortResolution) {
        this.shortResolution = shortResolution;
    }

    public void setExtendedResolution(String extendedResolution) {
        this.extendedResolution = extendedResolution;
    }

    public void setFormat(String format) {
        this.format = format;
    }
    
    public String getFileName(){
        return this.name + "-" + this.shortResolution + "." + this.format;
    }
    
}
