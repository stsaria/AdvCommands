package si.f5.stsaria.advCommands.manager;

import si.f5.stsaria.advCommands.EventType;
import si.f5.stsaria.advCommands.function.Function;
import si.f5.stsaria.advCommands.function.UserFunction;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class EventFunctions{
    private static final Map<EventType, UserFunction> eventFunctionMap = new HashMap<>();
    public static synchronized int set(String eventTypeStr, String functionName){
        EventType eventType = null;
        try{
            for (EventType type : EventType.values()) {
                if (type.getValue().equals(eventTypeStr)){
                    eventType = type;
                    throw new Exception("Baka");
                }
            }
            return 1;
        } catch (Exception ignore) {}
        Function function = Functions.get(functionName);
        if (function == null) return 2;
        if (function instanceof UserFunction) eventFunctionMap.put(eventType, (UserFunction) function);
        return 0;
    }
    public static synchronized UserFunction get(EventType eventType){
        AtomicReference<UserFunction> function = new AtomicReference<>(null);
        eventFunctionMap.forEach((t, f) -> {if (t.equals(eventType)) function.set(f);});
        return function.get();
    }
}
