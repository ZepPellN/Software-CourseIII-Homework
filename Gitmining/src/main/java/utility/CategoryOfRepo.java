package utility;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CategoryOfRepo {
	All("All", new String[]{}, 0),
	API("API", new String[]{"api"}, 2),
	App("App", new String[]{"app", "apps", "application", "applications"}, 3),
	Json("Json", new String[]{"json"}, 6),
	OS("OS", new String[]{"os"}, 1),
	Linux("Linux", new String[]{"linux"}, 8),
	Mac("Mac", new String[]{"mac"}, 4),
	Server("Server", new String[]{"server", "servers"}, 10),
	Library("Library", new String[]{"library", "libraries"}, 11),
	Web("Web", new String[]{"web", "webs"}, 5),
	Plugin("Plugin", new String[]{"plugin", "plugins"}, 9),
	Template("Template", new String[]{"template", "templates"}, 12),
	Interface("Interface", new String[]{"interface", "interfaces"}, 13),
	Tool("Tool", new String[]{"tool", "tools"}, 7),	
	Others("Others", new String[]{}, 14);
	
	private String[] type;
	private String value;
	private int index;
	
	private CategoryOfRepo(String value, String[] type, int index){
		this.value = value;
		this.type = type;
		this.index = index;
	}
	
	public String getValue(){
		return value;
	}
		
	public int getIndex() {
		return index;
	}

	public static CategoryOfRepo setType(String value){
		if(value == null || value.equals("")){
			 return All;
		}
		switch (value) {
		case "All": return All;
		case "Mac": return Mac;
		case "API": return API;
		case "App": return App;
		case "Json": return Json;
		case "OS": return OS;
		case "Linux": return Linux;
		case "Server": return Server;
		case "Library": return Library;
		case "Web": return Web;
		case "Plugin": return Plugin;
		case "Template": return Template;
		case "Interface": return Interface;
		case "Tool": return Tool;
		default: return Others;
		}
	}
	
 	public static ArrayList<CategoryOfRepo> setTypes(String description){
		ArrayList<CategoryOfRepo> categories = new ArrayList<CategoryOfRepo>();
		
		if(description == null){
			categories.add(CategoryOfRepo.Others);
			return categories;
		}
		
		description = description.toLowerCase();
		Pattern pattern = null;
		Matcher matcher = null;
		
		for(CategoryOfRepo category : CategoryOfRepo.values()){
			for(String type : category.type){
				pattern = Pattern.compile("\\b" + type + "\\b");
				matcher = pattern.matcher(description);
				if(matcher.find()){
					categories.add(category);
					break;
				}
			}
		}
		
		if(categories.size() == 0){
			categories.add(CategoryOfRepo.Others);
		}
		
		return categories;
	}
}