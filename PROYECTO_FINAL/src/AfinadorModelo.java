


import java.util.ArrayList;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Patch;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;

public class AfinadorModelo implements AfinadorModelInterface {
    
    Synthesizer sintetizador;
    MidiChannel canales;
    Soundbank bancoSonidos;
    Instrument listaInstrumentos[];
    Patch patch;
    int[] listaNotas;
    static int upperRange;
    
    ArrayList beatObservers = new ArrayList();
    ArrayList bpmObservers = new ArrayList();
    int nota = 0;
    int opcion = 0;
    
   
    public void setBPM(int nota) {
        this.nota = nota;
        
        
        if (AfinadorControlador.iniciado == 1){
        	notifyBPMObservers();
        	this.afinaInstrumento(opcion);
        	notifyBeatObservers();
        	
        }
        
    }

    
    public int getBPM() {
        return nota;
    }

    
    public void registerObserver(BeatObserver o) {
        beatObservers.add(o);
        System.out.println("registrando observador de beat");
    }
    
    public void registerObserver(BPMObserver o) {
        bpmObservers.add(o);
        System.out.println("registrando observador de bpm");
        }
  
    public void notifyBeatObservers() {
        for(int i = 0; i < beatObservers.size(); i++) {
            BeatObserver observer = (BeatObserver)beatObservers.get(i);
            observer.updateBeat();
	}
    }
    
    public void notifyBPMObservers() {
            for(int i = 0; i < bpmObservers.size(); i++) {
                BPMObserver observer = (BPMObserver)bpmObservers.get(i);
		observer.updateBPM();
            }
	}
 
    public void removeObserver(BeatObserver o) {
        int i = beatObservers.indexOf(o);
	if (i >= 0) {
            beatObservers.remove(i);
        }
    }
    
    public void removeObserver(BPMObserver o) {
        int i = bpmObservers.indexOf(o);
        if (i >= 0) {
            bpmObservers.remove(i);
        }
    }
    
    void beatEvent() {
        notifyBeatObservers();
    }

   
    public void initialize() {
        setUpMidi();
    }

    
    public void on() {
        setBPM(getBPM());
        this.afinaInstrumento(opcion);
    }

   
    public void off() {
        //secuencer.stop();
    }

    public void afinaInstrumento(int opcion) {       
        Instrument instrumento = listaInstrumentos[opcion];
        patch = instrumento.getPatch();
        canales.programChange(patch.getBank(), patch.getProgram());
        
        try 
        {   
            
            canales.noteOn(listaNotas[getBPM()], 100);           
            Thread.sleep(1000);
            canales.allNotesOff();
        } 
        catch (Exception exc) 
        {
            exc.printStackTrace();
        }
    }
    
    public void setUpMidi(){
        try 
        {
            listaNotas = new int[]{48, 50, 52, 53, 55, 57, 59};
            //Se obtiene el sintetizador midi
            sintetizador = MidiSystem.getSynthesizer();
            //Se abre el sintetizador para poder reproducir sonidos
            sintetizador.open();
            //Se obtienen los canales del sintetizador
            canales = sintetizador.getChannels()[1];
            //se carga el banco de sonidos
            bancoSonidos = sintetizador.getDefaultSoundbank();
            //se carga la lista de sonidos
            sintetizador.loadAllInstruments(bancoSonidos);
            //Se cargan los instrumentos
            listaInstrumentos = sintetizador.getLoadedInstruments();
            upperRange = listaInstrumentos.length -1 ;
            //this.afinaInstrumento(opcion);
        } 
        catch (Exception exc) 
        {
            exc.printStackTrace();
        }
    }

}
    
