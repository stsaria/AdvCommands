package si.f5.stsaria.advCommands;

import si.f5.stsaria.advCommands.function.Function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventFunctionsManager {
    private static final Map<EventType, Function> eventFunctionMap = new HashMap<>();
    public static int setEventFunction(String eventTypeStr, String functionName){
        EventType eventType;
        Function function;
        switch (eventTypeStr){
            case "onKill":
                eventType = EventType.ON_KILL;
                break;
            case "onMove":
                eventType = EventType.ON_MOVE;
                break;
            case "onJoin":
                eventType = EventType.ON_JOIN;
                break;
            case "onPlaceBlock":
                eventType = EventType.ON_PLACE_BLOCK;
                break;
            case "onBreakBlock":
                eventType = EventType.ON_BREAK_BLOCK;
                break;
            case null, default:
                return 1;
        }
        if (FunctionsManager.getFunction(functionName) == null) return 2;
        function = FunctionsManager.getFunction(functionName);
        synchronized (EventFunctionsManager.class){
            eventFunctionMap.put(eventType, function);
        }
        return 0;
    }
    public static List<Function> getEventFunctions(EventType eventType){
        ArrayList<Function> functions = new ArrayList<>();
        synchronized (EventFunctionsManager.class) {
            eventFunctionMap.forEach((t, f) -> {if (t.equals(eventType)) functions.add(f);});
        }
        return functions;
    }
}
