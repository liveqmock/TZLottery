package com.xsc.lottery.util;

import java.util.Map;

import org.apache.commons.collections.MapUtils;

public class MapUtil
{
	public static Boolean checkKey(Map map,String key){
		if(map==null){
			return false;
		}
		if(map.containsKey(key)&&MapUtils.getObject(map, key)!=null&&!"".equals(MapUtils.getString(map, key))){
			return true;
		}
		return false;
	}
	
	public static String allAttrToString(Map map){
		StringBuffer params = new StringBuffer("");
		if(map!=null&&map.size()!=0){
			Object[] s = map.keySet().toArray();
			params.append(s[0].toString()+"="+MapUtils.getString(map,s[0]));
			for(int i = 1; i < map.size(); i++) {
				params.append("&"+s[i].toString()+"="+MapUtils.getString(map,s[i]));
			}
		}
		return params.toString();
	}

}
