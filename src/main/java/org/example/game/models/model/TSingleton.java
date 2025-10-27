package org.example.game.models.model;

public class TSingleton<T> {
    private static volatile Object _instance;
    private static final Object _initLock = new Object();

    @SuppressWarnings("unchecked")
    public static <T> T getInstance(Class<T> tClass) {
        if(_instance == null) {
            synchronized (_initLock) {
                if(_instance == null) {
                    try{
                        _instance = tClass.newInstance();
                    }catch (InstantiationException | IllegalAccessException e){
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return (T) _instance;
    }
}
