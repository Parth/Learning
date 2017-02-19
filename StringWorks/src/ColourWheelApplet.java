////////////////////////////////////////////////////////////////////////
//
// Colour wheel applet
//
// Written by Rolf Howarth, 6 December 1996
//
// Email: rolf@parallax.co.uk
//
// Copyright (C) 1996 Parallax Solutions Ltd. All rights reserved.
//
////////////////////////////////////////////////////////////////////////

import java.awt.*;
import java.applet.*;
import java.util.*;

/** TO-DO LIST
 **
 ** BUG: Low saturation values don't work under Windows
 ** BUG: Text fields: tabbing doesn't work under Windows
 ** Add RGB sliders?
 ** Use a better layout manager
 ** Generalise EditPanel so that HexEditPanel can share base class code
 ** Add more comments, split file into individual classes, ...
 **/

/*****************************************************************************
 ** ColourModel - stores the current selected colour Other screen elements are
 * observers of the model, calling our set methods when they want to change the
 * colour and updating themselves whenever we notify them that we've been
 * changed.
 ** 
 **/

class ColourModel extends Observable {
	private float brightness = (float) 1.0;
	private float hue = (float) 0.0;
	private float saturation = (float) 0.0;
	private Color color;

	public void setBrightness(float b) {
		if (b < 0.0)
			b = 0f;
		if (b >= 1.0)
			b = 1f;
		brightness = b;
		color = Color.getHSBColor(hue, saturation, brightness);
		setChanged();
	}

	public void setHue(float h) {
		if (h < 0.0)
			h = 0f;
		if (h >= 1.0)
			h = 0.999f;
		hue = h;
		color = Color.getHSBColor(hue, saturation, brightness);
		setChanged();
	}

	public void setSaturation(float s) {
		if (s < 0.0)
			s = 0f;
		if (s >= 1.0)
			s = 1f;
		saturation = s;
		color = Color.getHSBColor(hue, saturation, brightness);
		setChanged();
	}

	public void setRGB(int r, int g, int b) {
		if (r < 0)
			r = 0;
		if (r > 255)
			r = 255;
		if (g < 0)
			g = 0;
		if (g > 255)
			g = 255;
		if (b < 0)
			b = 0;
		if (b > 255)
			b = 255;
		float[] vals = new float[3];
		Color.RGBtoHSB(r, g, b, vals);
		hue = vals[0];
		saturation = vals[1];
		brightness = vals[2];
		color = new Color(r, g, b);
		setChanged();
	}

	public void setColor(Color c) {
		setRGB(c.getRed(), c.getGreen(), c.getBlue());
	}

	protected void setChanged() {
		super.setChanged();
		notifyObservers();
	}

	public Color getColor() {
		return color;
	}

	public float getHue() {
		return hue;
	}

	public float getSaturation() {
		return saturation;
	}

	public float getBrightness() {
		return brightness;
	}
}

/*****************************************************************************
 ** SliderBar - implementation of the H, S and B slider bars
 ** 
 **/

class SliderBar extends Canvas implements Observer {
	private Rectangle box;
	private ColourModel model;
	private boolean draggingBox = false;
	public static final int HUE = 1, SATURATION = 2, BRIGHTNESS = 3;
	private int type;
	Dimension offDimension;
	Image offImage;
	Graphics offGraphics = null;

	public SliderBar(ColourModel model, int type) {
		this.model = model;
		this.type = type;
		model.addObserver(this);
		setBackground(Color.black);
	}

	public void update(Observable o, Object arg) {
		repaint();
	}

	public synchronized void paint(Graphics g) {
		update(g);
	}

	public synchronized void update(Graphics g) {
		Dimension d = size();

		if ((offGraphics == null) || (d.width != offDimension.width)
				|| (d.height != offDimension.height)) {
			offDimension = d;
			offImage = createImage(d.width, d.height);
			offGraphics = offImage.getGraphics();
			// border of 4 all round, 8 at left
			box = new Rectangle(8, 4, d.width - 12, d.height - 8);
		}

		// Erase the previous image.
		offGraphics.setColor(getBackground());
		offGraphics.fillRect(0, 0, d.width, d.height);
		offGraphics.setColor(Color.black);

		// paint the brightness bar
		for (int i = 0; i < box.height; ++i) {
			float f = 1f - (float) i / box.height;
			switch (type) {
			case HUE:
				offGraphics.setColor(Color.getHSBColor(f,
						model.getSaturation(), model.getBrightness()));
				break;
			case SATURATION:
				offGraphics.setColor(Color.getHSBColor(model.getHue(), f,
						model.getBrightness()));
				break;
			case BRIGHTNESS:
				offGraphics.setColor(Color.getHSBColor(model.getHue(),
						model.getSaturation(), f));
				break;
			}
			offGraphics.drawLine(box.x, box.y + i, box.x + box.width - 1, box.y
					+ i);
		}
		offGraphics.setColor(Color.white);
		offGraphics.drawRect(box.x, box.y, box.width - 1, box.height - 1);

		// paint brightness cursor
		float f = 0;
		switch (type) {
		case HUE:
			f = model.getHue();
			break;
		case SATURATION:
			f = model.getSaturation();
			break;
		case BRIGHTNESS:
			f = model.getBrightness();
			break;
		}
		int yy = box.y + (int) (box.height * (1.0 - f));
		// offGraphics.drawRect(box.x, yy-2, box.width-1, 3);
		offGraphics.drawLine(box.x, yy, box.x + box.width - 1, yy);
		offGraphics.drawLine(box.x - 2, yy, box.x - 7, yy + 3);
		offGraphics.drawLine(box.x - 2, yy, box.x - 7, yy - 3);
		offGraphics.drawLine(box.x - 7, yy - 3, box.x - 7, yy + 3);

		g.drawImage(offImage, 0, 0, this);
	}

	public boolean mouseDrag(Event e, int x, int y) {
		if (draggingBox) {
			float f = 1f - (float) (y - box.y) / box.height;
			if (f < 0)
				f = 0f;
			if (f > 1f)
				f = 1f;
			switch (type) {
			case HUE:
				model.setHue(f);
				break;
			case SATURATION:
				model.setSaturation(f);
				break;
			case BRIGHTNESS:
				model.setBrightness(f);
				break;
			}
			return true;
		}
		return false;
	}

	public boolean mouseDown(Event e, int x, int y) {
		draggingBox = false;

		if (box.inside(x, y)) {
			draggingBox = true;
			float f = 1f - (float) (y - box.y) / box.height;
			switch (type) {
			case HUE:
				model.setHue(f);
				break;
			case SATURATION:
				model.setSaturation(f);
				break;
			case BRIGHTNESS:
				model.setBrightness(f);
				break;
			}
			return true;
		}
		float f = 0;
		switch (type) {
		case HUE:
			f = model.getHue();
			break;
		case SATURATION:
			f = model.getSaturation();
			break;
		case BRIGHTNESS:
			f = model.getBrightness();
			break;
		}
		int dy = y - box.y - (int) (box.height * (1f - f));
		if (x < box.x && x >= box.x - 8 && dy >= -4 && dy <= 4) {
			draggingBox = true;
			return true;
		}
		return false;
	}

	public Dimension preferredSize() {
		return new Dimension(30, 200);
	}

	public Dimension minimumSize() {
		return new Dimension(20, 20);
	}
}

/*****************************************************************************
 ** ColourWheel - the wheel itself. Because it takes a while to draw, the wheel
 * is drawn in an off screen buffer only when the window is resized. Most of the
 * complexity is in the trigonometry to plot the wheel and work out which colour
 * was selected if you click in it.
 ** 
 **/

class ColourWheel extends Canvas implements Observer {
	private Rectangle wheel;
	private ColourModel model;
	private int oldh = 0, oldw = 0;
	private Image wheelImage = null;
	Dimension offDimension;
	Image offImage;
	Graphics offGraphics = null;
	private boolean draggingWheel = false;

	public ColourWheel(ColourModel model) {
		this.model = model;
		model.addObserver(this);
		setBackground(Color.black);
	}

	private void resized() {
		int w = size().width;
		int h = size().height;
		if (h == oldh && w == oldw)
			return;
		oldh = h;
		oldw = w;
		System.out.println("Re-sized " + w + "," + h);

		int rw = w / 8;
		int rb = w / 16;
		if (rw > 20)
			rw = 20;
		// box = new Rectangle(w-rw-rb,h/20,rw,h*9/10);
		rw = 0;

		int r = h;
		if (h > w - rw - 2 * rb)
			r = w - rw - 2 * rb;
		else
			r = h;
		r = r * 9 / 10;
		wheel = new Rectangle((w - rw - r - 2 * rb) / 2, (h - r) / 2, r, r);

		int saturation_step = 1;
		int hue_step = 1;

		if (r < 350) {
			hue_step = 2;
		}
		if (r <= 280) {
			saturation_step = 3;
			hue_step = 2;
		}
		if (r <= 150) {
			saturation_step = 5;
			hue_step = 3;
		}
		System.out.println("r=" + r + ",h/s step=" + hue_step + ","
				+ saturation_step);

		// redraw wheel in separate image buffer
		if (wheelImage != null)
			wheelImage.flush();
		wheelImage = createImage(wheel.width, wheel.height);
		Graphics g = wheelImage.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, wheel.width, wheel.height);
		int s;
		int midx = wheel.width / 2;
		int midy = wheel.height / 2;
		// BUG: low saturation values don't work under Windows
		for (s = 100; s > 20; s -= saturation_step) {
			int arcw = wheel.width * s / 100;
			int arch = wheel.height * s / 100;
			float sat = s / 100F;
			for (h = 0; h <= 360; h += hue_step) {
				float hue = h / 360F;
				if (hue >= 1f)
					hue = 0f;
				Color c = Color.getHSBColor(hue, sat, 1F); // model.getBrightness());
				g.setColor(c);
				g.fillArc(midx - arcw / 2, midy - arch / 2, arcw, arch, h,
						hue_step);
			}
		}
	}

	public void update(Observable o, Object arg) {
		repaint();
	}

	public synchronized void paint(Graphics g) {
		update(g);
	}

	public synchronized void update(Graphics g) {
		Dimension d = size();

		if ((offGraphics == null) || (d.width != offDimension.width)
				|| (d.height != offDimension.height)) {
			offDimension = d;
			offImage = createImage(d.width, d.height);
			offGraphics = offImage.getGraphics();
			resized();
		}

		// Erase the previous image.
		offGraphics.setColor(Color.black);
		offGraphics.fillRect(0, 0, d.width, d.height);

		offGraphics.drawImage(wheelImage, wheel.x, wheel.y, this); // wheel.width,
																	// wheel.height,
																	// null);

		// draw circle around the current colour
		int midx = wheel.x + wheel.width / 2;
		int midy = wheel.y + wheel.height / 2;
		offGraphics.setColor(Color.white);
		int arcw = (int) (wheel.width * model.getSaturation() / 2);
		int arch = (int) (wheel.height * model.getSaturation() / 2);
		double th = model.getHue() * 2 * Math.PI;
		offGraphics.drawOval((int) (midx + arcw * Math.cos(th) - 3),
				(int) (midy - arch * Math.sin(th) - 3), 6, 6);

		g.drawImage(offImage, 0, 0, this);
	}

	public boolean mouseDrag(Event e, int x, int y) {
		if (draggingWheel) {
			int midx = wheel.x + wheel.width / 2;
			int midy = wheel.y + wheel.height / 2;
			double s, h;
			s = Math.sqrt((double) ((x - midx) * (x - midx) + (y - midy)
					* (y - midy)))
					/ (wheel.height / 2);
			h = -Math.atan2((double) (y - midy), (double) (x - midx))
					/ (2 * Math.PI);
			if (h < 0)
				h += 1.0;
			model.setHue((float) h);
			if (s > 1)
				s = 1.0;
			model.setSaturation((float) s);
			return true;
		}
		return false;
	}

	public boolean mouseDown(Event e, int x, int y) {
		draggingWheel = false;

		int midx = wheel.x + wheel.width / 2;
		int midy = wheel.y + wheel.height / 2;
		double s, h;
		s = Math.sqrt((double) ((x - midx) * (x - midx) + (y - midy)
				* (y - midy)))
				/ (wheel.height / 2);
		h = -Math.atan2((double) (y - midy), (double) (x - midx))
				/ (2 * Math.PI);
		if (h < 0)
			h += 1.0;
		if (s <= 1) {
			draggingWheel = true;
			model.setSaturation((float) s);
			model.setHue((float) h);
		}
		return true;
	}

	public Dimension preferredSize() {
		return new Dimension(200, 120);
	}

	public Dimension minimumSize() {
		return new Dimension(20, 20);
	}

}

/*****************************************************************************
 ** ColourPanel - the colour bar at the top. This is the only observer that
 * doesn't allow you to edit the colour in any way.
 ** 
 **/

class ColourPanel extends Canvas implements Observer {
	ColourModel model;

	public ColourPanel(ColourModel model) {
		this.model = model;
		model.addObserver(this);
		setBackground(Color.black);
	}

	public void update(Observable o, Object arg) {
		repaint(0);
	}

	public void paint(Graphics g) {
		g.setColor(model.getColor());
		g.fillRect(3, 3, size().width - 4, size().height - 4);
		g.setColor(Color.white);
		g.drawRect(0, 0, size().width - 1, size().height - 1);
	}

	public Dimension preferredSize() {
		return new Dimension(20, 20);
	}

	public Dimension minimumSize() {
		return new Dimension(5, 5);
	}
}

/*****************************************************************************
 ** ColourSwatch - allows you to temporarily save a selected colour.
 ** 
 **/

class ColourSwatch extends Canvas {
	ColourModel model;
	Color color;

	public ColourSwatch(ColourModel model, Color color) {
		this.model = model;
		this.color = color;
		setBackground(Color.black);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color c) {
		color = c;
		repaint();
	}

	public void paint(Graphics g) {
		g.setColor(color);
		g.fillRect(1, 1, size().width - 2, size().height - 2);
		g.setColor(Color.white);
		g.drawRect(0, 0, size().width - 1, size().height - 1);
	}

	public Dimension preferredSize() {
		return new Dimension(20, 20);
	}

	public Dimension minimumSize() {
		return new Dimension(5, 5);
	}
}

/*****************************************************************************
 ** EditPanel - base class for RGB and HSB triples of edit fields
 ** 
 **/

abstract class EditPanel extends Panel {
	protected TextField field1, field2, field3;
	private Label label;
	protected ColourModel model;

	protected EditPanel(ColourModel model, String label, int size) {
		this.model = model;
		model.addObserver((Observer) this);
		setBackground(Color.black);
		setForeground(Color.white);
		this.label = new Label(label);
		field1 = new TextField(size);
		field2 = new TextField(size);
		field3 = new TextField(size);
		field1.setEditable(false);
		field2.setEditable(false);
		field3.setEditable(false);
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		add(this.label);
		add(field1);
		add(field2);
		add(field3);
	}

	protected boolean changed = false;

	public void update(Observable o, Object arg) {
		changed = true;
		repaint();
	}

	public abstract void keyPress(Event evt);

	public boolean handleEvent(Event evt) {
		if (evt.id == Event.LOST_FOCUS
				&& (evt.target == field1 || evt.target == field2 || evt.target == field3)) {
			((TextField) evt.target).setEditable(false);
		}

		if ((evt.id == Event.MOUSE_DOWN || evt.id == Event.GOT_FOCUS)
				&& (evt.target == field1 || evt.target == field2 || evt.target == field3)) {
			((TextField) evt.target).setEditable(true);
		}

		if ((evt.id == Event.ACTION_EVENT || evt.id == Event.LOST_FOCUS || evt.id == Event.KEY_PRESS
				&& evt.key == 9)
				&& (evt.target == field1 || evt.target == field2 || evt.target == field3)) {
			keyPress(evt);
		}

		return super.handleEvent(evt);
	}
}

/*****************************************************************************
 ** RGBEditPanel
 ** 
 **/

class RGBEditPanel extends EditPanel implements Observer {
	public RGBEditPanel(ColourModel model) {
		super(model, "RGB:", 3);
	}

	public void paint(Graphics g) {
		if (changed) {
			changed = false;
			field1.setText("" + model.getColor().getRed());
			field2.setText("" + model.getColor().getGreen());
			field3.setText("" + model.getColor().getBlue());
		}
		super.paint(g);
	}

	public void keyPress(Event evt) {
		int r = 0, g = 0, b = 0;
		try {
			r = Integer.parseInt(field1.getText());
		} catch (NumberFormatException e) {
		}
		try {
			g = Integer.parseInt(field2.getText());
		} catch (NumberFormatException e) {
		}
		try {
			b = Integer.parseInt(field3.getText());
		} catch (NumberFormatException e) {
		}
		model.setRGB(r, g, b);
	}
}

/*****************************************************************************
 ** HSBEditPanel
 ** 
 **/

class HSBEditPanel extends EditPanel implements Observer {
	public HSBEditPanel(ColourModel model) {
		super(model, "HSB:", 4);
	}

	public void paint(Graphics g) {
		if (changed) {
			changed = false;
			field1.setText("" + fmtFloat3(model.getHue()));
			field2.setText("" + fmtFloat3(model.getSaturation()));
			field3.setText("" + fmtFloat3(model.getBrightness()));
		}
		super.paint(g);
	}

	private String fmtFloat3(float f) {
		int integral = (int) Math.floor(f);
		int frac = (int) ((f - integral) * 1000 + 0.5);
		String result = "" + integral + ".";
		if (frac < 100)
			result += "0";
		if (frac < 10)
			result += "0";
		result += frac;
		return result;
	}

	public void keyPress(Event evt) {
		if (evt.target == field1) {
			float h = Float.valueOf(field1.getText()).floatValue();
			model.setHue(h);
		}
		if (evt.target == field2) {
			float s = Float.valueOf(field2.getText()).floatValue();
			model.setSaturation(s);
		}
		if (evt.target == field3) {
			float b = Float.valueOf(field3.getText()).floatValue();
			model.setBrightness(b);
		}
	}
}

/*****************************************************************************
 ** HexEditPanel - display/edit hex RGB value Just a single edit field with
 * label, should be based on EditPanel but isn't
 ** 
 **/

class HexEditPanel extends Panel implements Observer {
	protected TextField field;
	private Label label;
	protected ColourModel model;

	protected HexEditPanel(ColourModel model) {
		this.model = model;
		model.addObserver((Observer) this);
		setBackground(Color.black);
		this.label = new Label("HEX:");
		field = new TextField(8);
		field.setEditable(false);
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		add(this.label);
		add(field);
	}

	protected boolean changed = false;

	public void update(Observable o, Object arg) {
		changed = true;
		repaint();
	}

	public void paint(Graphics g) {
		if (changed) {
			changed = false;
			Color c = model.getColor();
			int rgb = c.getRed() * 65536 + c.getGreen() * 256 + c.getBlue();
			String str = "000000" + Integer.toString(rgb, 16).toUpperCase();
			field.setText("#" + str.substring(str.length() - 6));
		}
		super.paint(g);
	}

	public boolean handleEvent(Event evt) {
		if (evt.id == Event.LOST_FOCUS && evt.target == field) {
			field.setEditable(false);
		}

		if ((evt.id == Event.MOUSE_DOWN || evt.id == Event.GOT_FOCUS)
				&& evt.target == field) {
			field.setEditable(true);
		}

		if ((evt.id == Event.ACTION_EVENT || evt.id == Event.LOST_FOCUS || evt.id == Event.KEY_PRESS
				&& evt.key == 9)
				&& evt.target == field) {
			String str = field.getText();
			if (str.length() > 0 && str.charAt(0) == '#')
				str = str.substring(1);
			int rgb = 0;
			try {
				rgb = Integer.parseInt(str, 16);
			} catch (NumberFormatException e) {
			}
			model.setRGB(rgb / 65536, (rgb % 65536) / 256, rgb % 256);
		}

		return super.handleEvent(evt);
	}
}

/**********************************************************************************
 ** ColourWheelApplet - the applet, can be invoked from command line as
 * application Essentially just creates the model and creates observer
 * components that mirror and (in most cases) let you edit the model. The only
 * complexity derives from handling the drag and drop of colours from the colour
 * panel to the swatches.
 ** 
 **/

public class ColourWheelApplet extends Applet {
	private ColourWheel selector;
	private ColourModel model = new ColourModel();
	private ColourPanel colourPanel;
	private RGBEditPanel rgbPanel;
	private HSBEditPanel hsbPanel;
	private HexEditPanel hexPanel;
	private ColourSwatch[] swatch = new ColourSwatch[8];
	private Panel swatchPanel;

	public static void main(String[] args) {
		Frame f = new Frame("Rolf's Colour Wheel");
		Applet a = new ColourWheelApplet();
		f.add("Center", a);
		f.resize(400, 300);
		f.pack();
		f.show();
		a.init();
		a.start();
	}

	public void init() {
		setBackground(Color.black);
		setForeground(Color.white);
		selector = new ColourWheel(model);
		colourPanel = new ColourPanel(model);
		swatch[0] = new ColourSwatch(model, Color.black);
		swatch[1] = new ColourSwatch(model, Color.white);
		swatch[2] = new ColourSwatch(model, Color.red);
		swatch[3] = new ColourSwatch(model, Color.yellow);
		swatch[4] = new ColourSwatch(model, Color.green);
		swatch[5] = new ColourSwatch(model, Color.cyan);
		swatch[6] = new ColourSwatch(model, Color.blue);
		swatch[7] = new ColourSwatch(model, Color.magenta);
		swatchPanel = new Panel();
		swatchPanel.setLayout(new GridLayout(8, 1));
		for (int i = 0; i < 8; ++i)
			swatchPanel.add(swatch[i]);

		Panel sliderPanel = new Panel();
		sliderPanel.setLayout(new FlowLayout());
		sliderPanel.add(new SliderBar(model, SliderBar.HUE));
		sliderPanel.add(new SliderBar(model, SliderBar.SATURATION));
		sliderPanel.add(new SliderBar(model, SliderBar.BRIGHTNESS));

		rgbPanel = new RGBEditPanel(model);
		hsbPanel = new HSBEditPanel(model);
		hexPanel = new HexEditPanel(model);

		Panel labels = new Panel();
		labels.setLayout(new GridLayout(2, 2));
		labels.add(rgbPanel);
		labels.add(hsbPanel);
		labels.add(hexPanel);
		Label logo = new Label("http://www.parallax.co.uk/~rolf/");
		logo.setForeground(Color.gray);
		labels.add(logo);

		setLayout(new BorderLayout(10, 10));
		add("Center", selector);
		add("South", labels);
		add("North", colourPanel);
		add("West", swatchPanel);
		add("East", sliderPanel);

		layout();
		model.setBrightness(1f);
	}

	private boolean swatchDrag = false;

	private ColourSwatch swatchLocate(int x, int y) {
		x -= swatchPanel.location().x;
		y -= swatchPanel.location().y;
		for (int i = 0; i < swatch.length; ++i) {
			if (swatch[i].inside(x, y))
				return swatch[i];
		}
		return null;
	}

	public boolean mouseDown(Event e, int x, int y) {
		Object o = locate(x, y);
		if (colourPanel.bounds().inside(x, y)) {
			swatchDrag = true;
			oldSwatch = null;
			Container f = this;
			while (f != null && !(f instanceof Frame))
				f = f.getParent();
			if (f != null)
				((Frame) f).setCursor(Frame.CROSSHAIR_CURSOR);
			return true;
		} else
			swatchDrag = false;

		ColourSwatch swatch = swatchLocate(x, y);
		if (swatch != null) {
			model.setColor(swatch.getColor());
			return true;
		}

		return false;
	}

	public boolean mouseUp(Event e, int x, int y) {
		if (swatchDrag) {
			Container f = this;
			while (f != null && !(f instanceof Frame))
				f = f.getParent();
			if (f != null)
				((Frame) f).setCursor(Frame.DEFAULT_CURSOR);
			return true;
		}
		return false;
	}

	private ColourSwatch oldSwatch = null;
	private Color oldColor = null;

	public boolean mouseDrag(Event e, int x, int y) {
		if (swatchDrag == false)
			return false;

		ColourSwatch swatch = swatchLocate(x, y);

		if (swatch != oldSwatch) {
			if (oldSwatch != null)
				oldSwatch.setColor(oldColor);
			if (swatch != null) {
				oldColor = swatch.getColor();
				swatch.setColor(model.getColor());
			}
			oldSwatch = swatch;
		}
		return true;
	}
}