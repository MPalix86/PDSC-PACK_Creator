package model.interfaces;

import model.interfaces.events.TagEvenet;

public interface ActionOnXmlTagListener {
	
	public void tagAdded(TagEvenet e);
	public void tagIssBeingAdded(TagEvenet e);
	
	public void tagRemoved(TagEvenet e);
	public void tagisBeingRemoved(TagEvenet e);
	
	public void tagCutted(TagEvenet e);
	public void tagIsBeingRemoved(TagEvenet e);
	
	public void tagPasted(TagEvenet e);
	public void tagisBeingPasted(TagEvenet e);
	
	public void tagDropped(TagEvenet e);

	

}
