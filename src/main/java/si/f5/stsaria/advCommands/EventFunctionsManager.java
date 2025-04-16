package si.f5.stsaria.advCommands;

import si.f5.stsaria.advCommands.function.Function;
import si.f5.stsaria.advCommands.function.UserFunction;

import java.util.*;

public class EventFunctionsManager {
    private static final Map<EventType, UserFunction> eventFunctionMap = new HashMap<>();
    public static synchronized int set(String eventTypeStr, String functionName){
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
            case "onclickguiitem":
                eventType = EventType.ON_CLICK_GUI_ITEM;
                break;
            case "onclickhanditem":
                eventType = EventType.ON_CLICK_HAND_ITEM;
                break;
            case "onleave":
                eventType = EventType.ON_LEAVE;
                break;
            default:
                return 1;
        }
        function = FunctionsManager.get(functionName);
        if (function == null) return 2;
        if (!(function instanceof UserFunction)) return 3;
        eventFunctionMap.put(eventType, (UserFunction) function);
        return 0;
    }
    public static synchronized List<UserFunction> get(EventType eventType){
        ArrayList<UserFunction> functions = new ArrayList<>();
        eventFunctionMap.forEach((t, f) -> {if (t.equals(eventType)) functions.add(f);});
        return functions;
    }
}
