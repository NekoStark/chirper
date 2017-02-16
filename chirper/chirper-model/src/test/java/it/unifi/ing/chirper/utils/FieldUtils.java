package it.unifi.ing.chirper.utils;

import java.lang.reflect.Field;

public class FieldUtils {

	public static void assignField( Object obj, String fieldName,  Object value ) throws RuntimeException {
		try {
			Field field = obj.getClass().getDeclaredField( fieldName );
			field.setAccessible( true );
			field.set( obj, value );
		} catch (NoSuchFieldException e) {
			throw new RuntimeException( e );
		} catch (IllegalAccessException e) {
			throw new RuntimeException( e );
		}
	}
}
