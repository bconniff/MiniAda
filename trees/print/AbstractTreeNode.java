package trees.print;

import java.lang.reflect.*;
import java.util.*;

public abstract class AbstractTreeNode {

	private static final HashSet<Class> BASIC;

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

	public String toString() {
		Field[] fields = getClass().getDeclaredFields();
		StringBuffer toRet = new StringBuffer();
		toRet.append("{");
		for(Field field : fields) {
			try {
				field.setAccessible(true);
				Object obj = field.get(this);
				toRet.append(field.getName());
				toRet.append(" : ");
				if(obj != null)
					if(obj.getClass().isArray()) {
						int dims = 1 + obj.getClass().getName().lastIndexOf('[');
						for(int n = 0;n < dims;n++) {
							toRet.append("[");
							int length = Array.getLength(obj);
							for(int i = 0;i < length;i++) {
								toRet.append(Array.get(obj, i).toString());
								toRet.append(", ");
							}
							toRet.delete(toRet.length()-2, toRet.length());
							toRet.append("]");
						}
					} else {
						if(BASIC.contains(obj.getClass())) {
							toRet.append(obj.toString());
						} else if(obj.getClass() == String.class) {
							toRet.append("\"");
							toRet.append(obj.toString());
							toRet.append("\"");
						} else if(obj instanceof Collection) {
							toRet.append(obj.toString());
						} else {
							toRet.append("{");
							toRet.append(obj.toString());
							toRet.append("}");
						}
					}
				else {
					toRet.append("null");
				}
				toRet.append(", ");
			} catch(IllegalAccessException e) {
				e.printStackTrace();				
			}
		}
		toRet.delete(toRet.length()-2, toRet.length());
		toRet.append("}");
		return toRet.toString();
	}
}
