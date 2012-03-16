package trees;

import java.lang.reflect.*;
import java.util.*;

public abstract class AbstractTreeNode {

	private static final HashSet<Class> BASIC;

	//Builds a set of the primitive types
	static {
		BASIC = new HashSet<Class>();
		BASIC.add(Integer.class);
		BASIC.add(Short.class);
		BASIC.add(Long.class);
		BASIC.add(Boolean.class);
		BASIC.add(Float.class);
		BASIC.add(Double.class);
		BASIC.add(Character.class);
	}

	//Returns a JSON style string of the current object
	public String toString() {
		//Get all the fields in the object
		Field[] fields = getClass().getDeclaredFields();
		//Create a buffer to build the string
		StringBuffer toRet = new StringBuffer();
		toRet.append("{");
		for(Field field : fields) {
			try {
				//If the field is private allow it to be accessed
				field.setAccessible(true);
				Object obj = field.get(this);
				//Add name and colon
				toRet.append(field.getName());
				toRet.append(" : ");
				if(obj != null)
					//If the object is an array
					if(obj.getClass().isArray()) {
						//Get the number of dimensions
						int dims = 1 + obj.getClass().getName().lastIndexOf('[');
						//Loop through and add all the values
						for(int n = 0;n < dims;n++) {
							toRet.append("[");
							int length = Array.getLength(obj);
							for(int i = 0;i < length;i++) {
								toRet.append(Array.get(obj, i).toString());
								toRet.append(", ");
							}
							//Remove the last space and comma
							toRet.delete(toRet.length()-2, toRet.length());
							toRet.append("]");
						}
					} else {
						//If a primitive, just add it to the buffer
						if(BASIC.contains(obj.getClass())) {
							toRet.append(obj.toString());
						//If a string, add quotes around
						} else if(obj.getClass() == String.class) {
							toRet.append("\"");
							toRet.append(obj.toString());
							toRet.append("\"");
						//If a collection of objects don't add brackets
						} else if(obj instanceof Collection) {
							toRet.append(obj.toString());
						//Default object behavior, print out the object with brackets around it
						} else {
							toRet.append("{");
							toRet.append(obj.toString());
							toRet.append("}");
						}
					}
				//null object
				else {
					toRet.append("null");
				}
				toRet.append(", ");
			} catch(IllegalAccessException e) {
				e.printStackTrace();				
			}
		}
		//Remove trailing space and comma again
		toRet.delete(toRet.length()-2, toRet.length());
		toRet.append("}");
		return toRet.toString();
	}
}