
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class StrategyVista extends DJView {
    StrategyVista StrategyVistavista = this;
    String [] modelos = {"","Beat Model","Heart Model","Afinador Modelo"};

    public StrategyVista(ControllerInterface controller, BeatModelInterface model) {
        super(controller, model);
    }
    public StrategyVista(){
        super();
        this.CreateView();
    }
    
    public void CreateView(){
        viewPanel = new JPanel(new GridLayout(1, 2));
        viewFrame = new JFrame("View");
        viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewFrame.setSize(new Dimension(100, 80));
        bpmOutputLabel = new JLabel("ELIJA UN MODELO ", SwingConstants.CENTER);
	beatBar = new BeatBar();
        beatBar.setValue(0);
        JPanel bpmPanel = new JPanel(new GridLayout(2, 1));
	JComboBox listaModelos = new JComboBox(modelos);
        bpmPanel.add(beatBar);
        bpmPanel.add(bpmOutputLabel);
        bpmPanel.add(listaModelos);
        viewPanel.add(bpmPanel);
        viewFrame.getContentPane().add(viewPanel, BorderLayout.CENTER);
        viewFrame.pack();
        viewFrame.setVisible(true);
        StrategyVistavista.createControls();
        listaModelos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String lista = (String) listaModelos.getSelectedItem();
                
                    if (lista == "Beat Model"){
                         if(controller!=null) {
                            controller.stop();
                            model.removeObserver((BeatObserver)StrategyVistavista);
                            model.removeObserver((BPMObserver)StrategyVistavista);
                         }  
                        BeatModel modelbeat = new BeatModel();
                        BeatController controllerbeat = new BeatController(modelbeat,StrategyVistavista);
                        setControlador(controllerbeat);
                        setModelo(modelbeat);
                        model.registerObserver((BeatObserver)StrategyVistavista);
                        model.registerObserver((BPMObserver)StrategyVistavista);
                    
                    }   
                    if(lista == "Heart Model"){
                        if(controller!=null) {
                            controller.stop();
                            model.removeObserver((BeatObserver)StrategyVistavista);
                            model.removeObserver((BPMObserver)StrategyVistavista);
                         }
                        HeartModel ModelHeart = HeartModel.getInstance();
                        HeartController controllerHeart = new HeartController(ModelHeart,StrategyVistavista);    
                        setControlador(controllerHeart);
                        setModelo(new HeartAdapter(ModelHeart));
                        model.registerObserver((BeatObserver)StrategyVistavista);
                        model.registerObserver((BPMObserver)StrategyVistavista);                            
                    }   
                    if (lista == "Afinador Modelo"){
                         if(controller!=null) {
                            controller.stop();
                            model.removeObserver((BeatObserver)StrategyVistavista);
                            model.removeObserver((BPMObserver)StrategyVistavista);
                         }
                        AfinadorModelo modeloAfinador = new AfinadorModelo();
                        AfinadorControlador controladorAfinador = new AfinadorControlador(modeloAfinador,1,StrategyVistavista);
                        setControlador(controladorAfinador);
                        setModelo(new AfinadorAdapter(modeloAfinador));
                        model.registerObserver((BeatObserver)StrategyVistavista);
                        model.registerObserver((BPMObserver)StrategyVistavista);
                    }          
                }        
                
        });
        
        
   }
   public void setControlador(ControllerInterface controller) {
        this.controller = controller;
    }

   public void setModelo(BeatModelInterface model) {
        this.model = model;
    } 


   public void createControls() {
		// Create all Swing components here
        JFrame.setDefaultLookAndFeelDecorated(true);
        controlFrame = new JFrame("Control");
        controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controlFrame.setSize(new Dimension(100, 80));

        controlPanel = new JPanel(new GridLayout(1, 2));

        menuBar = new JMenuBar();
        menu = new JMenu("DJ Control");
        startMenuItem = new JMenuItem("Start");
        menu.add(startMenuItem);
        startMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                controller.start();
            }
        });
        stopMenuItem = new JMenuItem("Stop");
        menu.add(stopMenuItem); 
        stopMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                controller.stop();
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
        controlFrame.setJMenuBar(menuBar);

        bpmTextField = new JTextField(2);
        bpmLabel = new JLabel("Enter BPM:", SwingConstants.RIGHT);
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
		if (model != null) {
			int bpm = model.getBPM();
			if (bpm == 0) {
				if (bpmOutputLabel != null) {
        			bpmOutputLabel.setText("offline");
				}
			} else {
				if (bpmOutputLabel != null) {
        			bpmOutputLabel.setText("Current BPM: " + model.getBPM());
				}
			}
		}
	}
  
	public void updateBeat() {
		if (beatBar != null) {
			 beatBar.setValue(100);
		}
	}
}
