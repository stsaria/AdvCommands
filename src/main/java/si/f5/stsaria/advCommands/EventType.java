package si.f5.stsaria.advCommands;

public enum EventType {
    ON_KILL("onkill"),
    ON_MOVE("onmove"),
    ON_JOIN("onjoin"),
    ON_PLACE_BLOCK("onplaceblock"),
    ON_BREAK_BLOCK("onbreakblock"),
    ON_CHAT("onchat"),
    ON_CLICK_GUI_ITEM("onclickguiitem"),
    ON_CLICK_HAND_ITEM("onclickhanditem"),
    ON_LEAVE("onleave"),
    ON_DROP("ondrop"),
    ON_DAMAGE("ondamage"),
    ON_DAMAGE_BY_ENTITY("ondamagebyentity");

    private final String name;
    EventType(String name){
        this.name = name;
    }
    public String getValue(){
        return this.name;
    }
}
