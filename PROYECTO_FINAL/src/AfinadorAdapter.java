
public class AfinadorAdapter implements BeatModelInterface {

    AfinadorModelInterface Afinador;
    
    public AfinadorAdapter(AfinadorModelInterface Afinador){
        this.Afinador = Afinador;
    }
    
    public void initialize() {}
  
    public void on() {}
  
    public void off() {}
   
    public int getBPM() {
        return 0;//Afinador.getHeartRate();
	}
  
    public void setBPM(int bpm) {}
   
    public void registerObserver(BeatObserver o) {
        Afinador.registerObserver(o);
    }
    
    public void removeObserver(BeatObserver o) {
        Afinador.removeObserver(o);
    }
     
    public void registerObserver(BPMObserver o) {
        //Afinador.registerObserver(o);
    }
  
    public void removeObserver(BPMObserver o) {
		//Afinador.removeObserver(o);
    }
}
