import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DialogoReemplaza extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private FramePrincipal frame;
	
	private JButton btnReemplazar;
	private JLabel labelPalabraTexto, labelPalabraReemplazo;
	private JTextField editPalabraTexto, editPalabraReemplazo;

	public DialogoReemplaza(FramePrincipal frame) {
		super();
		this.frame = frame;
		
		setBounds(100, 100, 300, 300);
		anadirComponentes();
		anadirListened();
	}

	public void anadirComponentes() {
		setLayout(new GridLayout(5,1));

		labelPalabraTexto = new JLabel("Palabra del texto");
		add(labelPalabraTexto);
		editPalabraTexto = new JTextField();
		add(editPalabraTexto);
		labelPalabraReemplazo = new JLabel("Reemplazo");
		add(labelPalabraReemplazo);
		editPalabraReemplazo = new JTextField();
		add(editPalabraReemplazo);

		btnReemplazar = new JButton("Reemplazar");
		add(btnReemplazar);
	}

	public void anadirListened() {
		
		btnReemplazar.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				String palabra = editPalabraTexto.getText().toString();
				String reemplazo = " "+editPalabraReemplazo.getText().toString()+" ";
				String textoOriginal = frame.getTextoArea();
				//Patter para escapar
				String textoReemplazado = textoOriginal.replaceAll(Pattern.quote(palabra), reemplazo);
				frame.setTextArea(textoReemplazado);
			}
		});

	
		this.addComponentListener(new ComponentListener() {			
			@Override
			public void componentShown(ComponentEvent e) {
				
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
				
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
				frame.encenderItemReemplace();
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
			}
		});
		
	}
}
