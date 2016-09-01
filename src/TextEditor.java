import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Menu;
import java.awt.Point;
import java.awt.Scrollbar;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class TextEditor extends JFrame implements MouseListener {
	private  JTextArea area = new JTextArea();
	private JFileChooser dialog = new JFileChooser(System.getProperty("user.dir"));
	//JPanel middlePanel = new JPanel ();
	JScrollPane scrollPane = new JScrollPane(area);

	

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
	JMenuItem cut = new JMenuItem("Cut");
	JMenuItem copy = new JMenuItem("Copy");
	JMenuItem paste = new JMenuItem("Paste");
	file.add(new JMenuItem("New"));
	file.add(new JMenuItem("Open"));
	file.add(save);
	file.add(exit);
	
	edit.add(cut);
	edit.add(copy);
	edit.add(paste);
	setTitle("Untitled");
	area.addMouseListener(this);
	//middlePanel.add(scrollPane);
	//this.middlePanel.setBorder( new TitledBorder( new EtchedBorder(), "Display Area" ));
	this.scrollPane.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	}
	
	public void mousePressed(MouseEvent e) 
	{
	   // if(e.getButton() == MouseEvent.BUTTON3)
		if (e.isPopupTrigger()) //if the event shows the menu
	    {
	    String[] rightOption = {"Cut","Copy","Paste"};
	    rightList = new JList<String>(rightOption);
	    rightList.setSelectedIndex(rightList.locationToIndex(e.getPoint())); //select the item
	    //JPopupMenu.show(rightList, e.getX(), e.getY());   
	    }
	}
	 
	
	
	public static void main(String[] args) {
		TextEditor editor = new TextEditor();
		editor.add(editor.area);
		editor.setSize(600,600);
		editor.setVisible(true);
	
	}

	
	/*
	
   

    //Add Textarea in to middle panel
    middlePanel.add ( scroll );

    // My code
    JFrame frame = new JFrame ();
    frame.add ( middlePanel );
    frame.pack ();
    frame.setLocationRelativeTo ( null );
    frame.setVisible ( true );*/
    
    
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
