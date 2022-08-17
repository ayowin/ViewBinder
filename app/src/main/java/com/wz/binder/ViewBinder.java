package com.wz.binder;

import android.content.Context;
import android.view.View;

import androidx.fragment.app.Fragment;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ViewBinder {

    /**
     * bind for Context
     * @param context，Context instance, such as Application,Activity,Service ...
     * @param rIdClass R.id.class
     */
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

    public static void bind(Fragment fragment, Class<?> rIdClass){
        try{
            if(fragment == null || rIdClass == null){
                return;
            }

            Class<?> fragmentClass = fragment.getClass();

            View view = fragment.getView();
            Class<?> viewClass = view.getClass();

            Field[] fields = fragmentClass.getDeclaredFields();
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

                                    Method findViewByIdMethod = viewClass.getMethod("findViewById",int.class);
                                    findViewByIdMethod.setAccessible(true);
                                    Object returnObject = findViewByIdMethod.invoke(view,fieldIdObject);

                                    field.setAccessible(true);
                                    field.set(fragment,returnObject);
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
