package ADA_Cine;

import APIindira.Cartelera;
import APIindira.Pelicula;
import APIxavi.Reserva;
import APIxavi.ReservaAPI;
import APIindira.Horario;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import java.awt.Color;

public class cine_GUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Cartelera cartelera; 
    private JComboBox<Pelicula> comboBoxPeli;
    private JComboBox<Horario> comboBoxHorarios;
    private JTextArea textArea; 
    private ReservaAPI reservaAPI;
    private JList<Reserva> list;
    private DefaultListModel<Reserva> listModel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                cine_GUI frame = new cine_GUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public cine_GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 804, 502);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 64, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("LATI´S ");
        lblNewLabel.setForeground(new Color(247, 202, 26));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 50));
        lblNewLabel.setBounds(262, 11, 206, 55);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("PELICULA");
        lblNewLabel_1.setFont(new Font("Rockwell", Font.PLAIN, 20));
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setBounds(38, 73, 147, 22);
        contentPane.add(lblNewLabel_1);

        comboBoxPeli = new JComboBox<>();
        comboBoxPeli.setBounds(38, 97, 252, 22);
        contentPane.add(comboBoxPeli);
        
        
        cartelera = new Cartelera();
        cargarPeliculas();
        reservaAPI = new ReservaAPI();

        comboBoxPeli.addActionListener(e -> cargarHorarios());

        JButton btnAgregarPelicula = new JButton("AGREGAR PELICULA");
        btnAgregarPelicula.setFont(new Font("Rockwell Condensed", Font.PLAIN, 20));
        btnAgregarPelicula.addActionListener(e -> agregarPelicula());
        btnAgregarPelicula.setBounds(38, 309, 219, 23);
        contentPane.add(btnAgregarPelicula);

        JButton btnEliminarPelicula = new JButton("ELIMINAR PELICULA");
        btnEliminarPelicula.setFont(new Font("Rockwell Condensed", Font.PLAIN, 20));
        btnEliminarPelicula.addActionListener(e -> eliminarPelicula());
        btnEliminarPelicula.setBounds(38, 347, 219, 23);
        contentPane.add(btnEliminarPelicula);

        JButton btnModificarPelicula = new JButton("MODIFICAR PELICULA");
        btnModificarPelicula.setFont(new Font("Rockwell Condensed", Font.PLAIN, 20));
        btnModificarPelicula.addActionListener(e -> modificarPelicula());
        btnModificarPelicula.setBounds(38, 381, 219, 23);
        contentPane.add(btnModificarPelicula);

        comboBoxHorarios = new JComboBox<>();
        comboBoxHorarios.setBounds(447, 97, 199, 22);
        contentPane.add(comboBoxHorarios);

        JLabel lblNewLabel_2 = new JLabel("RESERVA HORARIO");
        lblNewLabel_2.setForeground(new Color(255, 255, 255));
        lblNewLabel_2.setFont(new Font("Rockwell", Font.PLAIN, 20));
        lblNewLabel_2.setBounds(447, 77, 241, 14);
        contentPane.add(lblNewLabel_2);

        JButton btnCrearReserva = new JButton("CREAR RESERVA");
        btnCrearReserva.setFont(new Font("Rockwell Condensed", Font.PLAIN, 20));
        btnCrearReserva.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		crearReserva();
        	}
        });
        btnCrearReserva.setBounds(447, 130, 199, 23);
        contentPane.add(btnCrearReserva);

        JButton btnVerReservas = new JButton("VER RESERVAS");
        btnVerReservas.setFont(new Font("Rockwell Condensed", Font.PLAIN, 20));
        btnVerReservas.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		verReservas();
        	}
        });
        btnVerReservas.setBounds(423, 265, 252, 22);
        contentPane.add(btnVerReservas);

        JButton btnModificarReserva = new JButton("MODIFICAR RESERVA");
        btnModificarReserva.setFont(new Font("Rockwell Condensed", Font.PLAIN, 20));
        btnModificarReserva.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		modificarReserva();
        	}
        });
        btnModificarReserva.setBounds(447, 164, 199, 23);
        contentPane.add(btnModificarReserva);

        JButton btnCancelarReserva = new JButton("CANCELAR RESERVA");
        btnCancelarReserva.setFont(new Font("Rockwell Condensed", Font.PLAIN, 20));
        btnCancelarReserva.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		cancelarReserva(); 
        	}
        });
        btnCancelarReserva.setBounds(447, 198, 199, 23);
        contentPane.add(btnCancelarReserva);
        
        JButton btnModificarDescripcion = new JButton("MODIFICAR DESCRIPCION");
        btnModificarDescripcion.setFont(new Font("Rockwell Condensed", Font.PLAIN, 20));
        btnModificarDescripcion.addActionListener(e -> modificarDescripcion());
        btnModificarDescripcion.setBounds(38, 226, 219, 23);
        contentPane.add(btnModificarDescripcion);
        
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(38, 150, 219, 65); 
        contentPane.add(scrollPane);
        
        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        list.setBounds(391, 298, 320, 137);
        contentPane.add(list);
        
        JLabel lblNewLabel_3 = new JLabel("Cinema VIP");
        lblNewLabel_3.setForeground(new Color(247, 202, 26));
        lblNewLabel_3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
        lblNewLabel_3.setBounds(327, 61, 88, 14);
        contentPane.add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("Usuario : Gerente");
        lblNewLabel_4.setForeground(new Color(255, 255, 255));
        lblNewLabel_4.setFont(new Font("Franklin Gothic Book", Font.ITALIC, 12));
        lblNewLabel_4.setBounds(10, 11, 124, 14);
        contentPane.add(lblNewLabel_4);
    }

    private void cargarPeliculas() {
        comboBoxPeli.removeAllItems();
        for (Pelicula pelicula : cartelera.getPeliculas()) {
            comboBoxPeli.addItem(pelicula);
        }
    }

    private void cargarHorarios() {
        comboBoxHorarios.removeAllItems();
        Pelicula seleccionada = (Pelicula) comboBoxPeli.getSelectedItem();
        if (seleccionada != null) {
            for (Horario horario : seleccionada.getHorarios()) {
                comboBoxHorarios.addItem(horario);
            }
            textArea.setText(seleccionada.getDescripcion()); 
        }
    }

    private void agregarPelicula() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la película:");
        if (nombre != null && !nombre.trim().isEmpty()) {
            String genero = JOptionPane.showInputDialog("Ingrese el género:");
            if (genero != null && !genero.trim().isEmpty()) {
                int duracion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la duración en minutos:"));
                String descripcion = JOptionPane.showInputDialog("Ingrese la descripción:");
                Horario h1 = new Horario(JOptionPane.showInputDialog("Ingrese la primera hora:"), 1);
                Horario h2 = new Horario(JOptionPane.showInputDialog("Ingrese la segunda hora:"), 1);
                cartelera.agregarPeliculaConHorarios(nombre, genero, duracion, descripcion, h1, h2);
                cargarPeliculas();
            }
        }
    }

    private void eliminarPelicula() {
        Pelicula seleccionada = (Pelicula) comboBoxPeli.getSelectedItem();
        if (seleccionada != null) {
            cartelera.eliminarPelicula(seleccionada.getNombre());
            cargarPeliculas();
            comboBoxHorarios.removeAllItems(); 
            textArea.setText(""); 
        }
    }

    private void modificarPelicula() {
        Pelicula seleccionada = (Pelicula) comboBoxPeli.getSelectedItem();
        if (seleccionada != null) {
            String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre:", seleccionada.getNombre());
            String nuevoGenero = JOptionPane.showInputDialog("Ingrese el nuevo género:", seleccionada.getGenero());
            int nuevaDuracion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva duración en minutos:", seleccionada.getDuracion()));
            String nuevaDescripcion = JOptionPane.showInputDialog("Ingrese la nueva descripción:", seleccionada.getDescripcion());
            Pelicula nuevaPelicula = new Pelicula(nuevoNombre, nuevoGenero, nuevaDuracion, nuevaDescripcion);
            for (Horario h : seleccionada.getHorarios()) {
                nuevaPelicula.agregarHorario(h); 
            }
            cartelera.modificarPelicula(seleccionada.getNombre(), nuevaPelicula);
            cargarPeliculas();
        }
    }

    private void modificarDescripcion() {
        Pelicula seleccionada = (Pelicula) comboBoxPeli.getSelectedItem();
        if (seleccionada != null) {
            String nuevaDescripcion = textArea.getText(); 
            seleccionada.setDescripcion(nuevaDescripcion);
            JOptionPane.showMessageDialog(this, "Descripción actualizada con éxito.");
        }
    }
    
 // Método para crear una reserva
    private void crearReserva() {
        String nombreCliente = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
        if (nombreCliente != null && !nombreCliente.trim().isEmpty()) {
            int sala = 1; // Suponiendo que todas las reservas son para la misma sala
            Horario horarioSeleccionado = (Horario) comboBoxHorarios.getSelectedItem();
            if (horarioSeleccionado != null) {
                int numeroPersonas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de personas:"));
                Reserva reserva = new Reserva(nombreCliente, sala, horarioSeleccionado.getHora(), numeroPersonas);
                reservaAPI.agregarReserva(reserva); // Guardar reserva
                JOptionPane.showMessageDialog(this, "Reserva creada con éxito.");
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un horario válido.");
            }
        }
    }

    // Método para ver reservas
    private void verReservas() {
        listModel.clear(); // 
        for (Reserva reserva : reservaAPI.getReservas()) {
            listModel.addElement(reserva); 
        }
        if (listModel.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay reservas.");
        }
    }

    // Método para modificar una reserva
    private void modificarReserva() {
        String nombreCliente = JOptionPane.showInputDialog("Ingrese el nombre del cliente para modificar:");
        for (Reserva reserva : reservaAPI.getReservas()) {
            if (reserva.getNombreCliente().equalsIgnoreCase(nombreCliente)) {
                // Mostrar la información de la reserva
                JOptionPane.showMessageDialog(this, "Reserva actual:\n"
                    + "Nombre: " + reserva.getNombreCliente() + "\n"
                    + "Horario: " + reserva.getHorario() + "\n"
                    + "Número de personas: " + reserva.getNumeroPersonas());
                
                // Solicitar nuevo nombre
                String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre:", reserva.getNombreCliente());
                
                // Mostrar horarios disponibles
                StringBuilder horariosDisponibles = new StringBuilder("Horarios disponibles:\n");
                Pelicula peliculaSeleccionada = (Pelicula) comboBoxPeli.getSelectedItem(); // Obtener la película seleccionada
                if (peliculaSeleccionada != null) {
                    for (Horario horario : peliculaSeleccionada.getHorarios()) {
                        horariosDisponibles.append(horario.getHora()).append("\n"); // Agregar cada horario a la lista
                    }
                }
                String nuevoHorario = JOptionPane.showInputDialog(horariosDisponibles.toString() + "Ingrese el nuevo horario:");
                
                // Solicitar nuevo número de personas
                int nuevoNumeroPersonas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo número de personas:", reserva.getNumeroPersonas()));
                
                // Crear la nueva reserva modificada
                Reserva reservaModificada = new Reserva(nuevoNombre, reserva.getSala(), nuevoHorario, nuevoNumeroPersonas);
                reservaAPI.modificarReserva(reserva, reservaModificada);
                JOptionPane.showMessageDialog(this, "Reserva modificada con éxito.");
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Reserva no encontrada.");
    }

    // Método para cancelar una reserva
    private void cancelarReserva() {
        String nombreCliente = JOptionPane.showInputDialog("Ingrese el nombre del cliente para cancelar:");
        for (Reserva reserva : reservaAPI.getReservas()) {
            if (reserva.getNombreCliente().equalsIgnoreCase(nombreCliente)) {
                reservaAPI.eliminarReserva(reserva);
                JOptionPane.showMessageDialog(this, "Reserva cancelada con éxito.");
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Reserva no encontrada.");
    }
}
