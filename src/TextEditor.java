import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicBorders.MarginBorder;

public class TextEditor extends JFrame implements MouseListener {
	private  JTextArea area = new JTextArea();
	private JFileChooser dialog = new JFileChooser(System.getProperty("user.dir"));
	//JPanel middlePanel = new JPanel ();
	Color whiteColor = new Color(255, 255, 255);
	JScrollPane scrollPane = new JScrollPane(area);
	String answer;
	String pasteText;

	

	private String currentFile = "Untitled";
	private boolean changed = false;
	JList<String> rightList;
	
	public TextEditor(){
	JMenuBar menuBar = new JMenuBar();
	setJMenuBar(menuBar);
	JMenu file = new JMenu("File");
	JMenu edit = new JMenu("Edit");
	menuBar.add(file);
	menuBar.add(edit);
	
	JMenuItem save = new JMenuItem("Save");
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
	file.add(new JMenuItem("New"));
	file.add(new JMenuItem("Open"));
	file.add(save);
	file.add(exit);
	area.setBackground(whiteColor);
	Font font = area.getFont();
	float size = font.getSize() + 2.0f;
	area.setFont( font.deriveFont(size) );
	area.setMargin(new Insets(5, 5, 5, 5));
	
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
	
	public static void main(String[] args) {
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
