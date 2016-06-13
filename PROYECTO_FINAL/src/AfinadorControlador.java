
public class AfinadorControlador implements ControllerInterface {
    AfinadorModelInterface modelo;
    DJView vista;
    public static int iniciado = 0;
    
    public AfinadorControlador(AfinadorModelInterface modelo) {
        this.modelo = modelo;
        vista = new DJView(this,new AfinadorAdapter(modelo)); //REVISAR SI NO ANDA
        vista.createView();
        vista.createControls();
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
    
    public void stop(){
        modelo.off();
        vista.disableStopMenuItem();
        vista.enableStartMenuItem();
        iniciado = 0;
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
