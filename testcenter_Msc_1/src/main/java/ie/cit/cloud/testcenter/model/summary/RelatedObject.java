package ie.cit.cloud.testcenter.model.summary;

public class RelatedObject {
	private int RelatedobjectID;
	private String RelatedObjectName;
	private String RelatedObjectValue;

	public RelatedObject()
	{		
	}
	public RelatedObject(int RelatedobjectID,String RelatedObjectName,String RelatedObjectValue)
	{		
		this.RelatedobjectID = RelatedobjectID;
		this.RelatedObjectName = RelatedObjectName;
		this.RelatedObjectValue= RelatedObjectValue;
	}
	public String getRelatedObjectName() {
		return RelatedObjectName;
	}
	public void setRelatedObjectName(String relatedObjectName) {
		RelatedObjectName = relatedObjectName;
	}
	public String getRelatedObjectValue() {
		return RelatedObjectValue;
	}
	public void setRelatedObjectValue(String relatedObjectValue) {
		RelatedObjectValue = relatedObjectValue;
	}
	public int getRelatedobjectID() {
		return RelatedobjectID;
	}
	public void setRelatedobjectID(int relatedobjectID) {
		RelatedobjectID = relatedobjectID;
	}
}
