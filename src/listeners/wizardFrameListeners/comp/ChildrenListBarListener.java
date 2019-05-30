package listeners.wizardFrameListeners.comp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import model.XmlTag;
import view.tagCustomizationFrame.TagCustomizationFrame;
import view.wizardFrame.comp.tagsListBar.TagsListBar;
import view.wizardFrame.comp.tagsListBar.comp.ListBarButton;

public class ChildrenListBarListener implements ActionListener{
	
	private TagsListBar childrenListBar;

	
	public ChildrenListBarListener(TagsListBar childrenListBar) {
		this.childrenListBar = childrenListBar;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ListBarButton btn = (ListBarButton) e.getSource();
		String command = e.getActionCommand();
		if (command.equals("addPackageChild")) {
			
			Class cl = btn.getTagClass();
			Constructor<?> cons = null;
			try {
				cons = cl.getConstructor(boolean.class, XmlTag.class, int.class);
			} catch (NoSuchMethodException | SecurityException e1) {
				e1.printStackTrace();
			}
			
			try {
				TagCustomizationFrame f = new TagCustomizationFrame((XmlTag) cons.newInstance(true,null,1) );
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException| InvocationTargetException e1) {
				e1.printStackTrace();
			}
		}
	}

}
