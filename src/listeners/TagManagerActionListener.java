package listeners;

import business.Session;
import model.PDSC.PDSCConditionsChecker;
import model.interfaces.ActionOnXmlTagListener;
import model.interfaces.events.TagEvenet;
import model.xml.XmlTag;

public class TagManagerActionListener implements ActionOnXmlTagListener{
	
	Session session = Session.getInstance();

	@Override
	public void tagAdded(TagEvenet e) {
		XmlTag tag  = (XmlTag) e.getSource();
		if(session.getSelectedPDSCDoc() != null) {
			if(tag.getName().equals("components")) {
				PDSCConditionsChecker checker = session.getSelectedPDSCDoc().getPDSCConditionsChecker();
				checker.addComponents(tag);
			}
		}
	}

	@Override
	public void tagIssBeingAdded(TagEvenet e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tagRemoved(TagEvenet e) {
		XmlTag tag  = (XmlTag) e.getSource();
		if(session.getSelectedPDSCDoc() != null) {
			if(tag.getName().equals("Components")) {
				PDSCConditionsChecker checker = session.getSelectedPDSCDoc().getPDSCConditionsChecker();
				checker.removeComponents(tag);
			}
		}
	}

	@Override
	public void tagisBeingRemoved(TagEvenet e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tagCutted(TagEvenet e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tagIsBeingRemoved(TagEvenet e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tagPasted(TagEvenet e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tagisBeingPasted(TagEvenet e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tagDropped(TagEvenet e) {
		// TODO Auto-generated method stub
		
	}



}
