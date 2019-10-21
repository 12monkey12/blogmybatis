package com.yc.util;

import java.util.Iterator;
import java.util.List;

public class CollectionsUtil {

	public static void toArray(List list) {
		Iterator iterator = list.iterator();
		if (iterator.hasNext()) {
			System.out.println(iterator.next().toString());
		}
	}
}
