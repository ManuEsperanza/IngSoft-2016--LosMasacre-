//package headfirst.combined.djview;
  
public class HeartController implements ControllerInterface {
    HeartModelInterface model;
    DJView view;
  
    public HeartController(HeartModelInterface model) {
        this.model = model;
        view = new DJView(this, new HeartAdapter(model));
        view.createView();
        view.createControls();
        view.disableStopMenuItem();
        view.disableStartMenuItem();
    }
    
    public HeartController(HeartModelInterface model,StrategyVista view) {
        this.model = model;
        this.view = view;
        view.disableStopMenuItem();
	view.disableStartMenuItem();
    }
  
    public void start() {}
 
    public void stop() {}
    
    public void increaseBPM() {
        HeartModel.getInstance();
        view.updateBPM();
    }
	
	
    public void decreaseBPM() {}
  
    public void setBPM(int bpm) {}
}



