import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class AfinadorControlador implements ControllerInterface, InterfazControlador {
    
	AfinadorModelInterface modelo;
    DJView vista;
    
    AfinadorVista vista1;
    int i;
    public static int iniciado = 0;
    
    public AfinadorControlador(AfinadorModelInterface modelo) {
        this.modelo = modelo;
        vista = new DJView(this,new AfinadorAdapter(modelo)); 
        vista.createView();
        vista.createControls();
        vista.disableStopMenuItem();
        vista.enableStartMenuItem();
        modelo.initialize();
    }
    
    public AfinadorControlador(AfinadorModelInterface modelo, int i){
    	this.i = i;
    	this.modelo = modelo;
    	vista1 = new AfinadorVista(this,modelo);
    	vista1.createView();
        vista1.createControls();
        vista1.disableStopMenuItem();
        vista1.enableStartMenuItem();
        modelo.initialize();
    }
    
    public AfinadorControlador(AfinadorModelInterface modelo, int i,StrategyVista view){
        this.i = i;
    	this.modelo = modelo;
        this.vista = view;
        vista.disableStopMenuItem();
        vista.enableStartMenuItem();
        modelo.initialize();
    }
    public void start(){
        modelo.on();
        vista.disableStartMenuItem();
        vista.enableStopMenuItem();
        iniciado = 1;
    };
    
    public void start1(){
        modelo.on();
        vista1.disableStartMenuItem();
        vista1.enableStopMenuItem();
        iniciado = 1;
    };
    
    public void stop(){
        modelo.off();
        vista.disableStopMenuItem();
        vista.enableStartMenuItem();
        iniciado = 0;
    };
    
    public void stop1(){
        modelo.off();
        vista1.disableStopMenuItem();
        vista1.enableStartMenuItem();
        iniciado = 0;
        vista1.bpmOutputLabel.setText("APAGADO ");
    };
    
    public void increaseBPM(){
        int nota = modelo.getBPM();
        if(nota < 6){
            modelo.setBPM(nota + 1);
        }
        else{
            modelo.setBPM(0);
        }
           
    };
    
    public void decreaseBPM(){;
        int nota = modelo.getBPM();
        if(nota > 0){
            modelo.setBPM(nota - 1);
        }
        else{
            modelo.setBPM(6);
        }
       
    }    
    public void setBPM(int nota){
        modelo.setBPM(nota);
    };
    
}
