package com.acc.xlp.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PortUtils {

	private static JSONParser jsonParser;
	private static Logger logger = Logger.getRootLogger();

	public static JSONObject getConfigFor(String type) {

		if (type == null) {
			return new JSONObject();
		}

		if (jsonParser == null) {
			jsonParser = new JSONParser();
		}

		Object object = null;
		JSONObject configObject = null;
		try {
			object = jsonParser.parse(new InputStreamReader(PortUtils.class.getResourceAsStream("/" + type)));
			configObject = (JSONObject) object;
			logger.info("config for " + type + " found!");
		} catch (FileNotFoundException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		} catch (ParseException e) {
			logger.error(e);
		}

		return configObject;
	}

	public static Object constructEntityFor(JSONObject configObject, Row row) {

		if (configObject == null) {
			return null;
		}

		String className = configObject.get("entityName").toString();
		Object entity = null;
		try {
			// create instance of entity
			entity = Class.forName(className).getConstructor().newInstance();

			BeanInfo beanInfo = Introspector.getBeanInfo(entity.getClass());
			PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();

			// for all the fields, it has
			JSONObject auxObject;
			String column;
			JSONArray fieldsArray = (JSONArray) configObject.get("columnsList");
			for (int i = 0; i < fieldsArray.size(); i++) {
				auxObject = (JSONObject) fieldsArray.get(i);
				column = auxObject.keySet().iterator().next().toString();
				logger.info("json field: " + column);
				for (PropertyDescriptor descriptor : props) {
					String property = descriptor.getDisplayName();
					logger.info("object field: " + property);
					if (property.equals(column)) {
						String setter = descriptor.getWriteMethod().getName();
						Class<?> parameterType = descriptor.getPropertyType();
						Method setterMethod = entity.getClass().getDeclaredMethod(setter, parameterType);
						if (parameterType.isPrimitive()) {
							setterMethod.invoke(entity, Integer.parseInt(auxObject.get(column).toString()));
						} else {
							setterMethod.invoke(entity, auxObject.get(column));
						}
						break;
					}

				}
			}
		} catch (InstantiationException e) {
			logger.error(e);
		} catch (IllegalAccessException e) {
			logger.error(e);
		} catch (IllegalArgumentException e) {
			logger.error(e);
		} catch (InvocationTargetException e) {
			logger.error(e);
		} catch (NoSuchMethodException e) {
			logger.error(e);
		} catch (SecurityException e) {
			logger.error(e);
		} catch (ClassNotFoundException e) {
			logger.error(e);
		} catch (IntrospectionException e) {
			logger.error(e);
		}
		return entity;
	}
}
