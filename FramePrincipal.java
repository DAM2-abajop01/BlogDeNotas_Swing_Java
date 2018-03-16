import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FramePrincipal {
	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu menuFile, menuEdit;
	private JMenuItem itemCargar, itemGuardar, itemSalir, itemGuardarComo, itemReemplazar, itemCopiar, itemPegar;
	private JTextArea textArea;
	private JToolBar toolbar;
	private JButton btnGuardar, btnCargar, btnGuardarComo, btnCopiar, btnPegar, btnBuscar;
	private JTextField textoBusqueda;
	private JPanel panelBusqueda;
	private boolean vista = false;
	private Image img;
	private int finUltimaBusqueda = 0;
	ManejarFichero manejadorFichero = new ManejarFichero();

	/*
	 * Constructores
	 */
	public FramePrincipal() {
		frame = new JFrame("");
		frame.setBounds(200, 100, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void iniciar() {
		iniciarComponentes();
		iniciarListened();
		frame.setVisible(true);
	}
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	// Variable de la implementcion de Ismael
	private JMenu menuIsmael;
	private JMenuItem itemFuente;
	private JButton btnFuente;
	private JMenuItem itemSeleccionarTodo, itemSeleccionarTodoYCopiar, itemHora;
	private JButton btnSeleccionarTodo, btnHora;
	private JMenu subMenuRecientes;
	private ArrayList<JMenuItem> itemSubMenuRecientes;
	private ArrayList<String> rutasRecientes;
	//Item cargar hay que meter metodos

	public void componentesimplementacionIsmael() {
		// En el menu bar Ismael
		menuIsmael = new JMenu("Ismael");
		menuBar.add(menuIsmael);

		// SUBMENU
		subMenuRecientes = new JMenu("Recientes");
		itemSubMenuRecientes = new ArrayList<>();
		rutasRecientes = new ArrayList<>();
		try {
			rutasRecientes = manejadorFichero.obtenerLineasDelFichero();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!rutasRecientes.isEmpty()) {
			for (int i = 0; i < rutasRecientes.size(); i++) {
				JMenuItem textoSubmenu = new JMenuItem(rutasRecientes.get(i));
				itemSubMenuRecientes.add(textoSubmenu);
				subMenuRecientes.add(textoSubmenu);
			}
		}
		menuIsmael.add(subMenuRecientes);

		menuIsmael.addSeparator();

		// Fuente
		itemFuente = new JMenuItem("Fuente");
		itemFuente.setEnabled(false);
		itemFuente.setMnemonic(KeyEvent.VK_F);
		itemFuente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
		menuIsmael.add(itemFuente);

		menuIsmael.addSeparator();

		itemSeleccionarTodo = new JMenuItem("Selec.Todo");
		itemSeleccionarTodo.setEnabled(false);
		itemSeleccionarTodo.setMnemonic(KeyEvent.VK_N);
		itemSeleccionarTodo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		menuIsmael.add(itemSeleccionarTodo);

		itemSeleccionarTodoYCopiar = new JMenuItem("Selec All+Copy");
		itemSeleccionarTodoYCopiar.setEnabled(false);
		itemSeleccionarTodoYCopiar.setMnemonic(KeyEvent.VK_M);
		itemSeleccionarTodoYCopiar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
		menuIsmael.add(itemSeleccionarTodoYCopiar);

		menuIsmael.addSeparator();

		itemHora = new JMenuItem("Hora");
		itemHora.setEnabled(true);
		itemHora.setMnemonic(KeyEvent.VK_H);
		itemHora.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
		menuIsmael.add(itemHora);

	}

	/**
	 * En listener item cargar -> itemFuente.setEnabled(true);
	 * 
	 * componentesimplementacionIsmael(); antes de panel busqueda
	 */

	public void toolbarImplementacionIsmael() {
		// ToolBar Fuente
		btnFuente = new JButton("Fuente");
		btnFuente.setEnabled(false);
		try {
			img = ImageIO.read(getClass().getResource("img/copy.png"));
			img = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
			btnFuente.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		toolbar.add(btnFuente);

		btnSeleccionarTodo = new JButton("Selec.Todo");
		btnSeleccionarTodo.setEnabled(false);
		try {
			img = ImageIO.read(getClass().getResource("img/copy.png"));
			img = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
			btnSeleccionarTodo.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		toolbar.add(btnSeleccionarTodo);

		btnHora = new JButton("Hora");
		try {
			img = ImageIO.read(getClass().getResource("img/copy.png"));
			img = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
			btnHora.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		toolbar.add(btnHora);
	}

	public void listenedImplementacionIsmael() {
		// Fuente
		btnFuente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				itemFuente.doClick();
			}
		});

		itemFuente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DialogoFuente dialo = new DialogoFuente(FramePrincipal.this);
				dialo.setVisible(true);
			}
		});

		btnSeleccionarTodo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				itemSeleccionarTodo.doClick();
			}
		});

		itemSeleccionarTodo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.requestFocus();

				textArea.selectAll();
			}
		});
		itemSeleccionarTodoYCopiar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.requestFocus();
				textArea.selectAll();
				textArea.copy();
			}
		});

		btnHora.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				itemHora.doClick();
			}
		});
		itemHora.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Date fechaActual = new Date();
				int inicio = textArea.getSelectionStart();
				String cadenaOriginal = textArea.getText();
				String parte01 = cadenaOriginal.substring(0, inicio);
				String parte02 = " " + fechaActual + " ";
				String parte03 = cadenaOriginal.substring(inicio, cadenaOriginal.length());
				String resultado = parte01 + parte02 + parte03;
				textArea.setText(resultado);

			}
		});

		// Listado del submenu
		if (!itemSubMenuRecientes.isEmpty()) {
			for (int i = 0; i < itemSubMenuRecientes.size(); i++) {
				itemSubMenuRecientes.get(i).addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							String ruta = e.toString().substring(e.toString().indexOf("cmd=") + 4,
									e.toString().indexOf("when=") - 1);
							manejadorFichero.setRutaFichero(new File(ruta));
							String cadena = manejadorFichero.cargarFicher();
							textArea.setText(cadena);

							textArea.setFont(new Font("Verdana", Font.PLAIN, 12));
							itemGuardar.setEnabled(true);
							itemGuardarComo.setEnabled(true);
							textArea.setEnabled(true);
							btnGuardar.setEnabled(true);
							btnGuardarComo.setEnabled(true);
							itemReemplazar.setEnabled(true);
							btnCopiar.setEnabled(true);
							btnPegar.setEnabled(true);
							// Listened Ismael
							btnFuente.setEnabled(true);
							itemFuente.setEnabled(true);
							btnSeleccionarTodo.setEnabled(true);
							itemSeleccionarTodo.setEnabled(true);
							itemSeleccionarTodoYCopiar.setEnabled(true);

							textArea.requestFocus();

						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
			}
		}
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}


	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public void iniciarComponentes() {
		frame.setLayout(new BorderLayout());
		// Barra d menu
		menuBar = new JMenuBar();
		// Anadir barra al frame
		frame.setJMenuBar(menuBar);
		// -------------------------------------------------------------
		// MENU PRINCIPAL
		menuFile = new JMenu("File");
		menuBar.add(menuFile);
		// ITEM MENU PRINCIPAL
		itemCargar = new JMenuItem("Cargar");
		itemCargar.setMnemonic(KeyEvent.VK_Q);
		itemCargar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		menuFile.add(itemCargar);

		itemGuardar = new JMenuItem("Guardar");
		itemGuardar.setEnabled(false);
		itemGuardar.setMnemonic(KeyEvent.VK_G);
		itemGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
		menuFile.add(itemGuardar);

		menuFile.addSeparator();

		itemGuardarComo = new JMenuItem("Guardar como...");
		itemGuardarComo.setEnabled(false);
		itemGuardarComo.setMnemonic(KeyEvent.VK_U);
		itemGuardarComo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
		menuFile.add(itemGuardarComo);

		menuFile.addSeparator();

		itemSalir = new JMenuItem("Salir");
		itemSalir.setMnemonic(KeyEvent.VK_S);
		itemSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		menuFile.add(itemSalir);
		// -------------------------------------------------------------

		// MENU EDITAR
		menuEdit = new JMenu("Edit");
		menuBar.add(menuEdit);
		itemReemplazar = new JMenuItem("Reemplazar");
		itemReemplazar.setEnabled(false);
		itemReemplazar.setMnemonic(KeyEvent.VK_R);
		itemReemplazar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
		menuEdit.add(itemReemplazar);

		menuEdit.addSeparator();

		itemCopiar = new JMenuItem("Copiar");
		itemCopiar.setMnemonic(KeyEvent.VK_C);
		itemCopiar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		menuEdit.add(itemCopiar);

		itemPegar = new JMenuItem("Pegar");
		itemPegar.setMnemonic(KeyEvent.VK_P);
		itemPegar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		menuEdit.add(itemPegar);

		// -------------------------------------------------------------

		// TOLBAR
		toolbar = new JToolBar();
		toolbar.setFloatable(true);

		btnCargar = new JButton("Cargar");
		try {
			img = ImageIO.read(getClass().getResource("img/fileopen.png"));
			img = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
			btnCargar.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}

		btnGuardar = new JButton("Guardar");
		btnGuardar.setEnabled(false);
		try {
			img = ImageIO.read(getClass().getResource("img/filesave.png"));
			img = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
			btnGuardar.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}

		btnGuardarComo = new JButton("GuardarComo...");
		btnGuardarComo.setEnabled(false);
		try {
			img = ImageIO.read(getClass().getResource("img/SaveAs.png"));
			img = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
			btnGuardarComo.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}

		btnCopiar = new JButton("Copy");
		btnCopiar.setEnabled(false);

		try {
			img = ImageIO.read(getClass().getResource("img/copy.png"));
			img = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
			btnCopiar.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}

		btnPegar = new JButton("Paste");
		btnPegar.setEnabled(false);
		try {
			img = ImageIO.read(getClass().getResource("img/paste.png"));
			img = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
			btnPegar.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}

		componentesimplementacionIsmael();

		// Panel busqueda
		panelBusqueda = new JPanel();
		panelBusqueda.setLayout(new GridBagLayout());
		GridBagConstraints data = new GridBagConstraints();
		data.ipadx = 100;

		textoBusqueda = new JTextField(15);
		textoBusqueda.setMaximumSize(new Dimension(0, 40));
		btnBuscar = new JButton("");
		try {
			img = ImageIO.read(getClass().getResource("img/search.png"));
			img = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
			btnBuscar.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}

		panelBusqueda.add(textoBusqueda, data);
		panelBusqueda.add(btnBuscar);

		toolbar.add(btnCargar);
		toolbar.add(btnGuardar);
		toolbar.add(btnGuardarComo);
		toolbar.add(btnCopiar);
		toolbar.add(btnPegar);
		toolbar.add(Box.createHorizontalGlue());

		toolbarImplementacionIsmael();

		toolbar.add(panelBusqueda);

		frame.add(toolbar, BorderLayout.NORTH);
		// -------------------------------------------------------------

		// ELEMENTOS DEL FRAME
		textArea = new JTextArea("Escribe aqui");
		textArea.setEnabled(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		frame.add(textArea);
		// Scrol del texto
		frame.add(new JScrollPane(textArea));

	}

	public void iniciarListened() {
		btnBuscar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO BIEN SOlO FALTA EL JPANEL

				int inicio = -1, fin = -1;
				textArea.requestFocus();

				String palabraBusqueda = textoBusqueda.getText();
				String textoArea = textArea.getText();

				inicio = textoArea.indexOf(palabraBusqueda, finUltimaBusqueda);
				fin = inicio + palabraBusqueda.length();

				finUltimaBusqueda = fin;

				if (inicio == -1) {
				} else {
					vista = true;
				}
				System.out.println("Inicio: " + inicio + " /Fin:" + fin + " / fin ultimabusqueda: " + finUltimaBusqueda
						+ " / vista: " + vista);

				if (inicio == -1) {
					if (vista == true) {
						JOptionPane.showMessageDialog(frame, "No hay más palabras.");
						vista = false;
					} else {
						JOptionPane.showMessageDialog(frame, "No hay esa palabra en el texto.");
						vista = false;
					}
				} else {
					textArea.moveCaretPosition(inicio);
					textArea.select(inicio, fin);
				}

			}
		});

		btnCopiar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				itemCopiar.doClick();
			}
		});

		btnPegar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				itemPegar.doClick();
			}
		});

		itemCopiar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.copy();
			}
		});

		itemPegar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.paste();
			}
		});
		itemReemplazar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				itemReemplazar.setEnabled(false);
				DialogoReemplaza dialo = new DialogoReemplaza(FramePrincipal.this);
				dialo.setVisible(true);
			}
		});

		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				itemGuardar.doClick();
			}
		});
		btnCargar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				itemCargar.doClick();
			}
		});

		btnGuardarComo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				itemGuardarComo.doClick();
			}
		});

		itemGuardarComo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser(manejadorFichero.getRutaFichero());
				chooser.setFileFilter(new FileNameExtensionFilter("Fichero de texto", "txt"));
				chooser.setAcceptAllFileFilterUsed(false);
				int opcion = chooser.showSaveDialog(frame);
				if (opcion == JFileChooser.APPROVE_OPTION) {
					File rutaGuardar = chooser.getSelectedFile().getAbsolutePath().endsWith("txt")
							? chooser.getSelectedFile()
							: new File(chooser.getSelectedFile().getAbsolutePath() + ".txt");

					manejadorFichero.setRutaFichero(rutaGuardar);
					itemGuardar.doClick();
				}
			}
		});

		itemCargar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JFileChooser chooser = new JFileChooser(manejadorFichero.getRutaFichero());
				chooser.setFileFilter(new FileNameExtensionFilter("Fichero de texto", "txt"));
				chooser.setAcceptAllFileFilterUsed(false);
				int opcion = chooser.showOpenDialog(frame);
				if (opcion == JFileChooser.APPROVE_OPTION) {
					manejadorFichero.setRutaFichero(chooser.getSelectedFile());
					try {
						textArea.setText(manejadorFichero.cargarFicher());
						itemGuardar.setEnabled(true);
						itemGuardarComo.setEnabled(true);
						textArea.setEnabled(true);
						btnGuardar.setEnabled(true);
						btnGuardarComo.setEnabled(true);
						itemReemplazar.setEnabled(true);
						btnCopiar.setEnabled(true);
						btnPegar.setEnabled(true);
						// Listened Ismael
						btnFuente.setEnabled(true);
						itemFuente.setEnabled(true);
						btnSeleccionarTodo.setEnabled(true);
						itemSeleccionarTodo.setEnabled(true);
						itemSeleccionarTodoYCopiar.setEnabled(true);

						manejadorFichero.guardarFicheroRecientes(manejadorFichero.getRutaFichero().getPath());

						textArea.requestFocus();
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(frame, "Error al cargar");
					}

				}
			}
		});

		itemGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					manejadorFichero.guardarFichero(textArea.getText());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(frame, "Error al guardar");
				}

			}
		});
		itemSalir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		listenedImplementacionIsmael();
	}

	/*
	 * Devuelve la cadena que hay en el texto
	 */

	public String getTextoArea() {
		return textArea.getText();
	}

	public void setTextArea(String textArea) {
		this.textArea.setText(textArea);
	}

	public void encenderItemReemplace() {
		itemReemplazar.setEnabled(true);
	}

}
