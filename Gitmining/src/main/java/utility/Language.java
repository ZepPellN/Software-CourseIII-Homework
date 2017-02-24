package utility;

public enum Language {
	Java("Java", 4), Ruby("Ruby", 6), Python("Python", 8),
	C("C", 1), JavaScript("JavaScript", 9), Perl("Perl", 5),
	PHP("PHP", 2), HTML("HTML", 3), 
	Shell("Shell", 7), Others("Others", 10), All("All", 0);
	
	private String type;
	private int index;
	private Language(String type, int index){
		this.type = type;
		this.index = index;
	}
	
	public String getType(){
		return type;
	}
	
	public int getIndex() {
		return index;
	}

	public static Language setType(String value){
		if(value == null || value.equals("")){
			return All;
		}
		switch(value){
		case "Java": return Java;
		case "Ruby": return Ruby;
		case "Python": return Python;
		case "C": return C;
		case "JavaScript": return JavaScript;
		case "Perl": return Perl;
		case "PHP": return PHP;
		case "HTML": return HTML;
		case "Shell": return Shell;
		case "All": return All;
		default: return Others;
		}
	}
}
