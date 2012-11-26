package ie.cit.cloud.testcenter.display;


import java.util.LinkedHashSet;
import java.util.Set;

public class RelatedObjectList {

	private Set<RelatedObject> relatedObjects;
	public RelatedObjectList()
	{

	}
	public RelatedObjectList(Set<RelatedObject> relatedObjectList)
	{

	}
	
	public Set<RelatedObject> getRelatedObjects() {
		return relatedObjects;
	}

	public void setRelatedObjects(Set<RelatedObject> relatedObjectSet) {
		this.relatedObjects = relatedObjectSet;
	}

}
