package org.skylight1.neny.android.database.model;

public enum Grade {
	GRADE_A("A"), GRADE_B("B"), GRADE_C("C"), GRADE_PENDING("Z"), NOT_YET_GRADED("");

	private String code;

	private Grade(final String aCode) {
		code = aCode;
	}

	/**
	 * Returns the grade associated with the code, or else null if the code is not that of a valid grade, e.g., "X".
	 * 
	 * @param aCode
	 * @return
	 */
	public static Grade findByCode(final String aCode) {
		for (final Grade grade : values()) {
			if (grade.code.equals(aCode)) {
				return grade;
			}
		}
		return null;
	}

	/**
	 * Returns the grade associated with the name, or else null if the name is not that of a valid grade.
	 * 
	 * @param sName
	 * @return
	 */
     public static Grade findByName(final String sName)	{
    	 for (final Grade grade : values()) {
    		 if (grade.name().equals(sName)) {
    			 return grade;
    		 }
    	 }
    	 return null;
     }	

}
