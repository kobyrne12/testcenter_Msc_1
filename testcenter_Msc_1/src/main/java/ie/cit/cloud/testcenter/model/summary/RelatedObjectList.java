package ie.cit.cloud.testcenter.model.summary;

import java.util.Collection;

public class RelatedObjectList {

	private Collection<RelatedObject> relatedObjects;
	public RelatedObjectList()
	{

	}
	public RelatedObjectList(Collection<RelatedObject> relatedObjectList)
	{

	}
	
	public Collection<RelatedObject> getRelatedObjects() {
		return relatedObjects;
	}

	public void setRelatedObjects(Collection<RelatedObject> relatedObjects) {
		this.relatedObjects = relatedObjects;
	}

}
