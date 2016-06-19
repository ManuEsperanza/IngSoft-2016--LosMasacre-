
public class TripleTestDrive {
    public static void main (String[] args) {
        AfinadorModelInterface modelo = new AfinadorModelo();
        ControllerInterface controlador = new AfinadorControlador(modelo,1);
        
        HeartModel heartModel = HeartModel.getInstance();
        ControllerInterface model = new HeartController(heartModel);
        
        BeatModelInterface model1 = new BeatModel();
        ControllerInterface controller = new BeatController(model1);
    }
}
