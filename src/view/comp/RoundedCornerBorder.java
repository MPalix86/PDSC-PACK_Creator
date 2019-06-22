package view.comp;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Objects;

import javax.swing.JPopupMenu;
import javax.swing.border.AbstractBorder;

public class RoundedCornerBorder extends AbstractBorder {
	
	
	 protected Color color = Color.LIGHT_GRAY;
	  protected static final int ARC = 12;
	  
	  
	  @Override public void paintBorder(
	      Component c, Graphics g, int x, int y, int width, int height) {
	    Graphics2D g2 = (Graphics2D) g.create();
	    g2.setColor(color);
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                        RenderingHints.VALUE_ANTIALIAS_ON);
	    int r = ARC;
	    int w = width  - 1;
	    int h = height - 1;

	    Area round = new Area(new RoundRectangle2D.Float(x, y, w, h, r, r));
	    if (c instanceof JPopupMenu) {
	      g2.setPaint(c.getBackground());
	      g2.fill(round);
	    } else {
	      Container parent = c.getParent();
	      if (Objects.nonNull(parent)) {
	        g2.setPaint(parent.getBackground());
	        Area corner = new Area(new Rectangle2D.Float(x, y, width, height));
	        corner.subtract(round);
	        g2.fill(corner);
	      }
	    }
	    g2.setPaint(c.getForeground());
	    g2.draw(round);
	    g2.dispose();
	  }
	  @Override public Insets getBorderInsets(Component c) {
	    return new Insets(4, 8, 4, 8);
	  }
	  @Override public Insets getBorderInsets(Component c, Insets insets) {
	    insets.set(4, 8, 4, 8);
	    return insets;
	  }
	}

	class RoundedCornerBorder1 extends RoundedCornerBorder {
	  //http://ateraimemo.com/Swing/RoundedComboBox.html
	  @Override public void paintBorder(
	      Component c, Graphics g, int x, int y, int width, int height) {
	    Graphics2D g2 = (Graphics2D) g.create();
	    g2.setColor(color);
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                        RenderingHints.VALUE_ANTIALIAS_ON);
	    int r = ARC;
	    int w = width  - 1;
	    int h = height - 1;

	    Area round = new Area(new RoundRectangle2D.Float(x, y, w, h, r, r));
	    Rectangle b = round.getBounds();
	    b.setBounds(b.x, b.y + r, b.width, b.height - r);
	    round.add(new Area(b));

	    Container parent = c.getParent();
	    if (Objects.nonNull(parent)) {
	      g2.setPaint(parent.getBackground());
	      Area corner = new Area(new Rectangle2D.Float(x, y, width, height));
	      corner.subtract(round);
	      g2.fill(corner);
	    }

	    g2.setPaint(c.getForeground());
	    g2.draw(round);
	    g2.dispose();
	  }
	}

	class RoundedCornerBorder2 extends RoundedCornerBorder {
	  @Override public void paintBorder(
	      Component c, Graphics g, int x, int y, int width, int height) {
	    Graphics2D g2 = (Graphics2D) g.create();
	    g2.setColor(color);
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                        RenderingHints.VALUE_ANTIALIAS_ON);
	    int r = ARC;
	    int w = width  - 1;
	    int h = height - 1;

	    Path2D.Float p = new Path2D.Float();
	    p.moveTo(x, y);
	    p.lineTo(x, y + h - r);
	    p.quadTo(x, y + h, x + r, y + h);
	    p.lineTo(x + w - r, y + h);
	    p.quadTo(x + w, y + h, x + w, y + h - r);
	    p.lineTo(x + w, y);
	    p.closePath();
	    Area round = new Area(p);

	    g2.setPaint(c.getBackground());
	    g2.fill(round);

	    g2.setPaint(c.getForeground());
	    g2.draw(round);
	    g2.setPaint(c.getBackground());
	    g2.drawLine(x + 1, y, x + width - 2, y);
	    g2.dispose();
	  }
	}
