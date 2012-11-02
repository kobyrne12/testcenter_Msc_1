/**
 * 
 */
package ie.cit.cloud.testcenter.display;

/**
 * @author byrnek1
 *
 */


public class ProjectsDisplay {

	private String name;
	private String index;
	private boolean hidden;
	private int width;
	private String align;	
	private boolean sortable;
	private boolean resizable; 
	private boolean search; 
	private String sorttype; 
	private String jsonmap;
	private boolean key;
	private String formatter;
	private String unformat;	
	
	public ProjectsDisplay(String name) {
		this(name,name,false,20,"center",true,true, true, "number",null,false,"","");		
	}
	public ProjectsDisplay(String name,int width) {
		this(name,name,false,width,"center",true,true, true, "number",null,false,"","");		
	}
	public ProjectsDisplay(String name,boolean hidden) {
		this(name,name,hidden,20,"center",true,true, true, "number",null,false,"","");		
	}
	public ProjectsDisplay(String name,String formatter, String unformat) {
		this(name,name,false,20,"center",true,true, true, "number",null,false,formatter,unformat);		
	}
	public ProjectsDisplay(String name,String formatter, String unformat, int width) {
		this(name,name,false,width,"center",true,true, true, "number",null,false,formatter,unformat);		
	}
	public ProjectsDisplay(String name, String index,boolean hidden, int width, String align,
			boolean sortable, boolean resizable, boolean search, String sorttype, String jsonmap,
			boolean key, String formatter, String unformat) 
	{	
		this.name = name;
		this.index = index;
		this.hidden = hidden;
		this.width = width;
		this.align = align;
		this.sortable = sortable;
		this.resizable = resizable;
		this.search = search;
		this.sorttype = sorttype;
		this.jsonmap = jsonmap;
		this.key = key;   
		this.formatter = formatter;  
		this.unformat = unformat;  
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the index
	 */
	public String getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(String index) {
		this.index = index;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the align
	 */
	public String getAlign() {
		return align;
	}

	/**
	 * @param align the align to set
	 */
	public void setAlign(String align) {
		this.align = align;
	}

	/**
	 * @return the sortable
	 */
	public boolean getSortable() {
		return sortable;
	}

	/**
	 * @param sortable the sortable to set
	 */
	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}

	/**
	 * @return the resizable
	 */
	public boolean getResizable() {
		return resizable;
	}

	/**
	 * @param resizable the resizable to set
	 */
	public void setResizable(boolean resizable) {
		this.resizable = resizable;
	}

	/**
	 * @return the search
	 */
	public boolean getSearch() {
		return search;
	}

	/**
	 * @param search the search to set
	 */
	public void setSearch(boolean search) {
		this.search = search;
	}

	/**
	 * @return the sorttype
	 */
	public String getSorttype() {
		return sorttype;
	}

	/**
	 * @param sorttype the sorttype to set
	 */
	public void setSorttype(String sorttype) {
		this.sorttype = sorttype;
	}

	/**
	 * @return the jsonmap
	 */
	public String getJsonmap() {
		return jsonmap;
	}

	/**
	 * @param jsonmap the jsonmap to set
	 */
	public void setJsonmap(String jsonmap) {
		this.jsonmap = jsonmap;
	}

	/**
	 * @return the key
	 */
	public boolean getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(boolean key) {
		this.key = key;
	}

	/**
	 * @return the hidden
	 */
	public boolean getHidden() {
		return hidden;
	}

	/**
	 * @param hidden the hidden to set
	 */
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	/**
	 * @return the formatter
	 */
	public String getFormatter() {
		return formatter;
	}
	/**
	 * @param formatter the formatter to set
	 */
	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}
	/**
	 * @return the unformat
	 */
	public String getUnformat() {
		return unformat;
	}
	/**
	 * @param unformat the unformat to set
	 */
	public void setUnformat(String unformat) {
		this.unformat = unformat;
	}



}