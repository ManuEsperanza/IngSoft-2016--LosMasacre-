//package headfirst.combined.djview;
  
public class HeartTestDrive {

    public static void main (String[] args) {
        HeartModel heartModel = HeartModel.getInstance();
        ControllerInterface model = new HeartController(heartModel);
    }
}
