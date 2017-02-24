package utility;

public enum OwnerType{
	User("User"), Organization("Organization"), Others("Others");
	
	private String type;
	
	private OwnerType(String type){
		this.type = type;
	}
	
	public String getType(){
		return type;
	}
	
	public static OwnerType setType(String value){
		switch(value){
		case "User": return User;
		case "Organization": return Organization;
		default: return Others;
		}
	}
}
