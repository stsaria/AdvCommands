package si.f5.stsaria.advCommands.variables;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Objects;

public class OnClickGuiItemEvent extends Variables{
    public OnClickGuiItemEvent(String guiName, InventoryClickEvent e){
        this.set("guiname", guiName);
        this.set("guititle", e.getView().getTitle());
        String click = "";
        if (e.isLeftClick()) click += "left";
        if (e.isRightClick()) click += "right";
        if (e.isShiftClick()) click += "shift";
        this.set("click", click);
        new PlayerV((Player) e.getWhoClicked()).getVariableMap().forEach((n, v) -> this.set("player."+n, v));
        new ItemStackV(Objects.requireNonNull(e.getCurrentItem())).getVariableMap().forEach((n, v) -> this.set("itemstack."+n, v));
    }
}
