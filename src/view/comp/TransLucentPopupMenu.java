package view.comp;

import java.awt.Window;

import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

public class TransLucentPopupMenu extends JPopupMenu {

    {
        // need to disable that to work
        setLightWeightPopupEnabled(false);
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible == isVisible())
            return;
        super.setVisible(visible);
        if (visible) {
            // attempt to set tranparency
            try {
                Window w = SwingUtilities.getWindowAncestor(this);
                w.setOpacity(0.800F);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
