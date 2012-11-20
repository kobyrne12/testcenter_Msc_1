package ie.cit.cloud.testcenter.display;

public class RelatedObject {
	private int RelatedobjectID;
	private String RelatedObjectName;
	private String RelatedObjectValue;
	private long relatedObjectPathID;
	private String relatedObjectPathName;
	public RelatedObject()
	{		
	}
	/**
	 * @param relatedobjectID
	 * @param relatedObjectName
	 * @param relatedObjectValue
	 * @param relatedObjectPathID
	 * @param relatedObjectPathName
	 */
	public RelatedObject(int relatedobjectID, String relatedObjectName,
			String relatedObjectValue, long relatedObjectPathID,
			String relatedObjectPathName) {
		RelatedobjectID = relatedobjectID;
		RelatedObjectName = relatedObjectName;
		RelatedObjectValue = relatedObjectValue;
		this.relatedObjectPathID = relatedObjectPathID;
		this.relatedObjectPathName = relatedObjectPathName;
	}
	/**
	 * @return the relatedobjectID
	 */
	public int getRelatedobjectID() {
		return RelatedobjectID;
	}
	/**
	 * @param relatedobjectID the relatedobjectID to set
	 */
	public void setRelatedobjectID(int relatedobjectID) {
		RelatedobjectID = relatedobjectID;
	}
	/**
	 * @return the relatedObjectName
	 */
	public String getRelatedObjectName() {
		return RelatedObjectName;
	}
	/**
	 * @param relatedObjectName the relatedObjectName to set
	 */
	public void setRelatedObjectName(String relatedObjectName) {
		RelatedObjectName = relatedObjectName;
	}
	/**
	 * @return the relatedObjectValue
	 */
	public String getRelatedObjectValue() {
		return RelatedObjectValue;
	}
	/**
	 * @param relatedObjectValue the relatedObjectValue to set
	 */
	public void setRelatedObjectValue(String relatedObjectValue) {
		RelatedObjectValue = relatedObjectValue;
	}
	/**
	 * @return the relatedObjectPathID
	 */
	public long getRelatedObjectPathID() {
		return relatedObjectPathID;
	}
	/**
	 * @param relatedObjectPathID the relatedObjectPathID to set
	 */
	public void setRelatedObjectPathID(long relatedObjectPathID) {
		this.relatedObjectPathID = relatedObjectPathID;
	}
	/**
	 * @return the relatedObjectPathName
	 */
	public String getRelatedObjectPathName() {
		return relatedObjectPathName;
	}
	/**
	 * @param relatedObjectPathName the relatedObjectPathName to set
	 */
	public void setRelatedObjectPathName(String relatedObjectPathName) {
		this.relatedObjectPathName = relatedObjectPathName;
	}
	  
}
