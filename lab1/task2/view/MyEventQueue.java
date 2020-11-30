package task2.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventObject;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import javax.swing.AbstractButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.Document;

@SuppressWarnings("rawtypes")
public class MyEventQueue implements KeyListener, ActionListener, MouseListener, ChangeListener {
	private Queue<EventObject> queue = new LinkedList<EventObject>();

	/** [Internal] */
	public synchronized void addEvent(EventObject e) {
		queue.offer(e);
		notify();
	}

	/** listen to events from a component */
	public void listenTo(Component jc, String s) {
		addName(jc, s);
		jc.addKeyListener(this);
		if (jc instanceof AbstractButton)
			((AbstractButton) jc).addActionListener(this);
		else if (jc instanceof JComboBox)
			((JComboBox) jc).addActionListener(this);
		else if (jc instanceof JSlider)
			((JSlider) jc).addChangeListener(this);
		else if (jc instanceof JPanel)
			((JPanel) jc).addMouseListener(this);
		else if (jc instanceof JLabel)
			((JLabel) jc).addMouseListener(this);
		else if (jc instanceof JComponent)
			((JComponent) jc).addMouseListener(this);
		else
			System.out.println("Don't know how to listen to " + jc);
	}

	public synchronized EventObject waitEvent() {
		EventObject r = null;
		for (;;) {
			r = queue.poll();
			if (r != null)
				return r;
			try {
				wait();
			} catch (Exception e) {
				System.out.println("catch in method waitEvent()");
				return null;
			}
		}
	}

	/** register a name with a component that may generate events */
	public static void addName(Object o, String nm) {
		if (o instanceof JComponent)
			((JComponent) o).putClientProperty("name", nm);
		else if (o instanceof Document)
			((Document) o).putProperty("name", nm);
		else if (o instanceof Component)
			((Component) o).setName(nm);
		else {
			names.put(o, nm);
//			 System.out.println("Don't know how to add name to "+o);
		}
	}

	private static Map<Object, String> names = new HashMap<Object, String>();

	/** Return the name associated with the component that generated the event */
	public static String getName(EventObject e) {
		Object o = e.getSource();
		// XAux.pl("source "+o);
		if (o instanceof JComponent) {
			Object v = ((JComponent) o).getClientProperty("name");
			if (v != null)
				return (String) v;
		} else if (o instanceof Document) {
			Object v = ((Document) o).getProperty("name");
			if (v != null)
				return (String) v;
		} else if (o instanceof Component) {
			Object v = ((Component) o).getName();
			if (v != null)
				return (String) v;
		} else if (names.containsKey(o))
			return names.get(o);

		// System.out.println("cannot find source "+o);
		String s = o.toString();
		if (s.length() < 20 && s.indexOf("[") < 0)
			return s;
		return "";
	}

	// Access to Mouse events
	//
	/** Check whether an event is a event */
	public static boolean isMouseEvent(EventObject e) {
		return e != null && e instanceof MouseEvent;
	}

	/** Check whether an event is a event */
	public static boolean isMousePressed(EventObject e) {
		return isMouseEvent(e) && ((MouseEvent) e).getID() == MouseEvent.MOUSE_PRESSED;
	}

	/** Check whether an event is a event */
	public static boolean isMouseClicked(EventObject e) {
		return isMouseEvent(e) && ((MouseEvent) e).getID() == MouseEvent.MOUSE_CLICKED;
	}

	/** Check whether an event is a event */
	public boolean isMouseReleased(EventObject e) {
		return isMouseEvent(e) && ((MouseEvent) e).getID() == MouseEvent.MOUSE_RELEASED;
	}

	/** Check whether an event is a event */
	public static int getMouseX(EventObject e) {
		if (!isMouseEvent(e))
			return 0;
		return ((MouseEvent) e).getX();
	}

	/** Check whether an event is a event */
	public static int getMouseY(EventObject e) {
		if (!isMouseEvent(e))
			return 0;
		return ((MouseEvent) e).getY();
	}

	/** Check whether an event is a event */
	public int getMouseButton(EventObject e) {
		if (!isMouseEvent(e))
			return 0;
		return ((MouseEvent) e).getButton();
	}

	/** Check whether an event is a event */
	public int getMouseClickCount(EventObject e) {
		if (!isMouseEvent(e))
			return 0;
		return ((MouseEvent) e).getClickCount();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		addEvent(arg0);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		addEvent(arg0);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		addEvent(arg0);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		addEvent(arg0);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		addEvent(arg0);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		addEvent(arg0);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		addEvent(arg0);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		addEvent(arg0);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		addEvent(arg0);
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		Object o = arg0.getSource();
		if (o instanceof JSlider)
			if (((JSlider) o).getValueIsAdjusting())
				return;
		addEvent(arg0);

	}

}
