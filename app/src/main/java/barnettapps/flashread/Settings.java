package barnettapps.flashread;

public class Settings {

    private int maxSpeed;
    private int minSpeed;
    private boolean cloudProcessing;

    // Codes
    private static final int RESULT_LOAD_IMG = 14353;
    private static final int RESULT_GET_PHOTOPICKER = 12722;



    // Bundle Keys
    private static final String IMAGE_URI="IMAGE_URI";

    public Settings() {
        this.maxSpeed = 2000;
        this.minSpeed = 50;
        this.cloudProcessing = false;
    }

    public boolean getCloudProcessing(){
        return this.cloudProcessing;
    }
    public void setCloudPreocessing(boolean cloudProcessing){this.cloudProcessing = cloudProcessing;}

    public int getMaxSpeed() {
        return maxSpeed;
    }
    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
    public int getMinSpeed() {
        return minSpeed;
    }
    public void setMinSpeed(int minSpeed) {
        this.minSpeed = minSpeed;
    }

    public int getRESULT_LOAD_IMG(){return RESULT_LOAD_IMG;}
    public int getRESULT_GET_PHOTOPICKER(){return RESULT_GET_PHOTOPICKER;}

    public String getIMAGE_URI(){return IMAGE_URI;}

}
