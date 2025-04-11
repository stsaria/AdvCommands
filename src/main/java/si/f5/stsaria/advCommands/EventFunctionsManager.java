package si.f5.stsaria.advCommands;

import si.f5.stsaria.advCommands.function.Function;
import si.f5.stsaria.advCommands.function.UserFunction;

import java.util.*;

public class EventFunctionsManager {
    private static final Map<EventType, UserFunction> eventFunctionMap = new HashMap<>();
    public static int setEventFunction(String eventTypeStr, String functionName){
        EventType eventType;
        Function function;
        switch (eventTypeStr){
            case "onkill":
                eventType = EventType.ON_KILL;
                break;
            case "onmove":
                eventType = EventType.ON_MOVE;
                break;
            case "onjoin":
                eventType = EventType.ON_JOIN;
                break;
            case "onplaceblock":
                eventType = EventType.ON_PLACE_BLOCK;
                break;
            case "onbreakblock":
                eventType = EventType.ON_BREAK_BLOCK;
                break;
            case "onchat":
                eventType = EventType.ON_CHAT;
                break;
            default:
                return 1;
        }
        function = FunctionsManager.getFunction(functionName);
        if (function == null) return 2;
        if (!(function instanceof UserFunction)) return 3;
        synchronized (EventFunctionsManager.class){
            eventFunctionMap.put(eventType, (UserFunction) function);
        }
        return 0;
    }
    public static List<UserFunction> getEventFunctions(EventType eventType){
        ArrayList<UserFunction> functions = new ArrayList<>();
        synchronized (EventFunctionsManager.class) {
            eventFunctionMap.forEach((t, f) -> {if (t.equals(eventType)) functions.add(f);});
        }
        return functions;
    }
}
