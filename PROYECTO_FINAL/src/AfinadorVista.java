import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AfinadorVista implements ActionListener,  BeatObserver, BPMObserver {
	AfinadorModelInterface model;
	InterfazControlador controller;
    JFrame viewFrame;
    JPanel viewPanel;
    BeatBar beatBar;
    JLabel bpmOutputLabel;
    JFrame controlFrame;
    JPanel controlPanel;
    JLabel bpmLabel;
    JTextField bpmTextField;
    JButton setBPMButton;
    JButton increaseBPMButton;
    JButton decreaseBPMButton;
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem startMenuItem;
    JMenuItem stopMenuItem;

    //agregaciones a nuestro controlador:
    JMenu menu1;
    JMenuItem instrucciones;
    JPanel PanelInstrucciones;
    JFrame FrameInstrucciones;
    JLabel manual,manual1;
    public AfinadorVista(InterfazControlador controller, AfinadorModelInterface model) {	
		this.controller = controller;
		this.model = model;
		model.registerObserver((BeatObserver)this);
		model.registerObserver((BPMObserver)this);
    }
    
    public void createView() {
		// Create all Swing components here
        viewPanel = new JPanel(new GridLayout(1, 2));
        viewFrame = new JFrame("View");
        viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewFrame.setSize(new Dimension(100, 80));
        bpmOutputLabel = new JLabel("APAGADO ", SwingConstants.CENTER);
		
        beatBar = new BeatBar();
		beatBar.setValue(0);
        JPanel bpmPanel = new JPanel(new GridLayout(2, 1));
		bpmPanel.add(beatBar);
        bpmPanel.add(bpmOutputLabel);
        viewPanel.add(bpmPanel);
        viewFrame.getContentPane().add(viewPanel, BorderLayout.CENTER);
        viewFrame.pack();
        viewFrame.setVisible(true);
	}
  
  
    public void createControls() {
		// Create all Swing components here
        JFrame.setDefaultLookAndFeelDecorated(true);
        controlFrame = new JFrame("Control");
        controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controlFrame.setSize(new Dimension(100, 80));

        controlPanel = new JPanel(new GridLayout(1, 2));

        menuBar = new JMenuBar();
        menu = new JMenu("Opciones");
        
        menu1= new JMenu("Ayuda");
        instrucciones = new JMenuItem("Instrucciones"); 
        menu1.add(instrucciones);
        instrucciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) { 
                FrameInstrucciones = new JFrame("Instrucciones de Uso");
                FrameInstrucciones.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                FrameInstrucciones.setSize(500,400);
                FrameInstrucciones.setVisible(true);
                JLabel label = new JLabel("My label");
                label.setText("<html><body width=''><h1 align=\"center\">Afinador Musical</h1><p>Haga click en &quot;Opciones&quot; y luego en &quot;Start&quot; para iniciar la aplicación. <br/>"+
                               " Donde dice &quot;Ingrese Nota &quot;, ingrese un valor de 0 a 6 donde:  <br/> <p></p>"+
                               "<p align=\"center\">0 = DO</p>  <br/> <p align=\"center\"> 1 = RE </p> <br/> <p align=\"center\">2 = MI </p> <br/> <p align=\"center\">3 = FA </p> <br/>"+
                               "<p align=\"center\">4 = SOL </p> <br/> <p align=\"center\"> 5 = LA (440hZ) </p> <br/> <p align=\"center\">6 = SI </p> <br/>"
                               +"con los botones &quot;&lt;&lt;&quot; y &quot;&gt;&gt;&quot; puede cambiar de nota<br/> haga click en &quot;Opciones&quot;, luego en &quot;Stop&quot; para detener la aplicación.");
                FrameInstrucciones.add(label);  
            }
        });
        
        startMenuItem = new JMenuItem("Start");
        menu.add(startMenuItem);
        startMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                controller.start1();
                updateBPM();
            }
        });
        stopMenuItem = new JMenuItem("Stop");
        menu.add(stopMenuItem); 
        stopMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                controller.stop1();
                
            }
        });
        JMenuItem exit = new JMenuItem("Quit");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        menu.add(exit);
        menuBar.add(menu);
        menuBar.add(menu1);
        controlFrame.setJMenuBar(menuBar);
        
        
        
        
        bpmTextField = new JTextField(2);
        bpmLabel = new JLabel("Ingrese Nota:", SwingConstants.RIGHT);
        setBPMButton = new JButton("Set");
        setBPMButton.setSize(new Dimension(10,40));
        increaseBPMButton = new JButton(">>");
        decreaseBPMButton = new JButton("<<");
        setBPMButton.addActionListener(this);
        increaseBPMButton.addActionListener(this);
        decreaseBPMButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

		buttonPanel.add(decreaseBPMButton);
		buttonPanel.add(increaseBPMButton);

        JPanel enterPanel = new JPanel(new GridLayout(1, 2));
        enterPanel.add(bpmLabel);
        enterPanel.add(bpmTextField);
        JPanel insideControlPanel = new JPanel(new GridLayout(3, 1));
        insideControlPanel.add(enterPanel);
        insideControlPanel.add(setBPMButton);
        insideControlPanel.add(buttonPanel);
        controlPanel.add(insideControlPanel);
        
        bpmLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        bpmOutputLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        controlFrame.getRootPane().setDefaultButton(setBPMButton);
        controlFrame.getContentPane().add(controlPanel, BorderLayout.CENTER);

        controlFrame.pack();
        controlFrame.setVisible(true);
    
        
    }

	public void enableStopMenuItem() {
    	stopMenuItem.setEnabled(true);
	}

	public void disableStopMenuItem() {
    	stopMenuItem.setEnabled(false);
	}

	public void enableStartMenuItem() {
    	startMenuItem.setEnabled(true);
	}

	public void disableStartMenuItem() {
    	startMenuItem.setEnabled(false);
	}

    public void actionPerformed(ActionEvent event) {
		if (event.getSource() == setBPMButton) {
			int bpm = Integer.parseInt(bpmTextField.getText());
        	controller.setBPM(bpm);
		} else if (event.getSource() == increaseBPMButton) {
			controller.increaseBPM();
		} else if (event.getSource() == decreaseBPMButton) {
			controller.decreaseBPM();
		}
		  
    }

	public void updateBPM() {
		int nota = model.getBPM();
		
		switch (nota) {                
            case (0): 
            	bpmOutputLabel.setText("NOTA: DO | FRECUENCIA : 261 Hz");
            break;
        
            case (1): 
            	bpmOutputLabel.setText("NOTA: RE | FRECUENCIA : 293 Hz");
            break;
        
            case (2):
            	bpmOutputLabel.setText("NOTA: MI | FRECUENCIA : 329 Hz");
            break;
        
            case (3):
            	bpmOutputLabel.setText("NOTA: FA | FRECUENCIA : 349 Hz");
            break;
            
            case (4):
            	bpmOutputLabel.setText("NOTA: SOL | FRECUENCIA : 391 Hz");
            break;
            
            case (5):
            	bpmOutputLabel.setText("NOTA: LA (REFERENCIA PARA AFINAR) | FRECUENCIA : 440 Hz");
            break;
            
            case (6):
            	bpmOutputLabel.setText("NOTA: SI | FRECUENCIA : 493 Hz");
            break;
        
            }
	}
  
	public void updateBeat() {
		if (beatBar != null) {
			 beatBar.setValue(100);
		}
	}
}
