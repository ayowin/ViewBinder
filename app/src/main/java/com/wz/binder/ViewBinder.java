package com.wz.binder;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ViewBinder {

    public static void bind(Context context,Class<?> rIdClass){
        try{
            if(context == null || rIdClass == null){
                return;
            }

            Class<?> contextClass = context.getClass();

            Field[] fields = contextClass.getDeclaredFields();
            if(fields != null){
                for (Field field : fields){
                    Bind bind = field.getAnnotation(Bind.class);
                    if(bind != null){
                        String idFieldName = null;
                        if(bind.value().trim().equals("")){
                            idFieldName = field.getName();
                        } else {
                            idFieldName = bind.value().trim();
                        }

                        Field[] fieldsId = rIdClass.getDeclaredFields();
                        if(fieldsId != null){
                            for (Field fieldId : fieldsId){
                                if(fieldId.getName().equals(idFieldName)){
                                    Object fieldIdObject = fieldId.get(null);

                                    Method findViewByIdMethod = contextClass.getMethod("findViewById",int.class);
                                    findViewByIdMethod.setAccessible(true);
                                    Object returnObject = findViewByIdMethod.invoke(context,fieldIdObject);

                                    field.setAccessible(true);
                                    field.set(context,returnObject);
                                }
                            }
                        }

                    }

                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
