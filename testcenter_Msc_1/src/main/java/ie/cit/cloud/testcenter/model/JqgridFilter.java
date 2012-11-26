package ie.cit.cloud.testcenter.model;

import java.util.ArrayList;

public class JqgridFilter {

	private String source;
	private String groupOp;
	private ArrayList<Rule> rules;

	public JqgridFilter() {
		super();
	}

	public JqgridFilter(String source) {
		super();
		this.source = source;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @return the groupOp
	 */
	public String getGroupOp() {
		return groupOp;
	}

	/**
	 * @param groupOp the groupOp to set
	 */
	public void setGroupOp(String groupOp) {
		this.groupOp = groupOp;
	}

	/**
	 * @return the rules
	 */
	public ArrayList<Rule> getRules() {
		return rules;
	}

	/**
	 * @param rules the rules to set
	 */
	public void setRules(ArrayList<Rule> rules) {
		this.rules = rules;
	}

	public static class Rule {
		private String junction;
		private String field;
		private String op;
		private String data;

		public void setData(String data) {
			this.data = data;
		}

		public Rule() {}

		public Rule(String junction, String field, String op, String data) {
			super();
			this.junction = junction;
			this.field = field;
			this.op = op;
			this.data = data;
		}

		/**
		 * @return the junction
		 */
		public String getJunction() {
			return junction;
		}

		/**
		 * @param junction the junction to set
		 */
		public void setJunction(String junction) {
			this.junction = junction;
		}

		/**
		 * @return the field
		 */
		public String getField() {
			return field;
		}

		/**
		 * @param field the field to set
		 */
		public void setField(String field) {
			this.field = field;
		}

		/**
		 * @return the op
		 */
		public String getOp() {
			return op;
		}

		/**
		 * @param op the op to set
		 */
		public void setOp(String op) {
			this.op = op;
		}

		/**
		 * @return the data
		 */
		public String getData() {
			return data;
		}

		/**
		 * @param data the data to set
		 */

	}


}
