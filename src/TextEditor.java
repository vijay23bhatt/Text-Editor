import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Insets;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;

public class TextEditor extends JFrame implements MouseListener {
	private  JTextArea area = new JTextArea();
	private JFileChooser dialog = new JFileChooser(System.getProperty("user.dir"));
	//JPanel middlePanel = new JPanel ();
	Color whiteColor = new Color(255, 255, 255);
	JScrollPane scrollPane = new JScrollPane(area);
	String answer;
	String pasteText;
	JToolBar toolBar = new JToolBar();
	JButton button = new JButton("button");
	JTextField width = new JTextField();
	JTextField height = new JTextField();
	Container contentPane = new Container();
	String fileName = " ";

	private String currentFile = "Untitled";
	private boolean changed = false;
	JList<String> rightList;
	
	public TextEditor() throws IOException{
	JMenuBar menuBar = new JMenuBar();
	setJMenuBar(menuBar);
	JMenu file = new JMenu("File");
	JMenu edit = new JMenu("Edit");
	menuBar.add(file);
	menuBar.add(edit);
    toolBar.add(button);
    toolBar.addSeparator();
    
    toolBar.add(new JButton("button 2"));
    
    toolBar.add(new JComboBox(new String[]{"A","B","C"}));
    toolBar.setRollover(true);
	contentPane.add(toolBar, BorderLayout.NORTH);
	
	JMenuItem save = new JMenuItem("Save");
	save.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			writeFile();
		}
	});
	
	JMenuItem exit = new JMenuItem("Exit");
	exit.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent ev) {
	           exit();
	    }
	});
	
	JMenuItem cut = new JMenuItem("Cut");
	cut.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			cut();
		}
		
	});
	
	JMenuItem copy = new JMenuItem("Copy");
	copy.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			copy();
		}
		
	});
	
	JMenuItem paste = new JMenuItem("Paste");
	paste.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			paste();
		}
	});
	
	JMenuItem newFile = new JMenuItem("New");
	newFile.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(area.getText().length() != 0 && changed == false){
			// TODO Auto-generated method stub
			    JDialog.setDefaultLookAndFeelDecorated(true);
			    int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm",
			        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			    if (response == JOptionPane.NO_OPTION) {
			      area.setText("");
			    } else if (response == JOptionPane.YES_OPTION) {
			      writeFile();
			    } 
		}
		}
	});
	file.add(newFile);
	
	

	JMenuItem open = new JMenuItem("Open");
	open.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			readFile();
		}
	});
	
	
	
	file.add(open);
	file.add(save);
	file.add(exit);
	area.setBackground(whiteColor);
	Font font = area.getFont();
	float size = font.getSize() + 2.0f;
	area.setFont( font.deriveFont(size));
	area.setMargin(new Insets(2, 2, 2, 2));
	
	
	edit.add(cut);
	edit.add(copy);
	edit.add(paste);
	setTitle("Untitled");
	area.addMouseListener(this);
	this.scrollPane.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	}
	
	public void mousePressed(MouseEvent e) 
	{
		if (e.isPopupTrigger()) //if the event shows the menu
	    {
	    String[] rightOption = {"Cut","Copy","Paste"};
	    }
	}
	
	public void readFile(){
		 JFileChooser fileChooser = new JFileChooser();
	        int returnValue = fileChooser.showOpenDialog(null);
	        if (returnValue == JFileChooser.APPROVE_OPTION) {
	          File selectedFile = fileChooser.getSelectedFile();
	          fileName = selectedFile.toString();
	          System.out.println(selectedFile.getName());
	        }
	        

	        try
            {
                FileReader reader = new FileReader(fileName);
                BufferedReader br = new BufferedReader(reader);
                area.read( br, null );
                area.setLineWrap(true);
                br.close();
                area.requestFocus();
            }
            catch(Exception e2) { System.out.println(e2); }
	}
	
	
	public void writeFile(){
		 try
         {
             FileWriter writer = new FileWriter(fileName);
             BufferedWriter bw = new BufferedWriter( writer );
             area.write( bw );
             bw.close();
             changed = true;
             area.requestFocus();
         }
         catch(Exception e2) {}
	}
	 
	public void cut(){
		answer = area.getSelectedText();
		int current = area.getCaretPosition() -2;
		pasteText = answer;
		area.setText(area.getText().replace(area.getSelectedText(),""));
		area.setCaretPosition(current);	
	}
	
	public void copy(){
		answer = area.getSelectedText();
		pasteText = answer;
	}
	
	public void paste(){
		area.insert(pasteText,area.getCaretPosition());
	}
	
	public void exit(){
		 System.exit(0);
	}
	
	public static void main(String[] args) throws IOException {
		TextEditor editor = new TextEditor();
		editor.add(editor.area);
		editor.setSize(600,600);
		editor.setVisible(true);
	}

	
	
    
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		mousePressed(arg0);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		mousePressed(e);
	}

}
