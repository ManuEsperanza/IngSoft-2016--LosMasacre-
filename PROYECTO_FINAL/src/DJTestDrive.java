//package headfirst.combined.djview;
  
public class DJTestDrive {

    public static void main (String[] args) throws Exception {
        BeatModelInterface model = new BeatModel();
		ControllerInterface controller = new BeatController(model);
    }
}
