package si.f5.stsaria.cakeLang.variables;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class OnClickGuiItemEvent extends Variables{
    public OnClickGuiItemEvent(String guiName, InventoryClickEvent e){
        this.set("guiname", guiName);
        this.set("guititle", e.getView().getTitle());
        String click = "";
        if (e.isLeftClick()) click += "left";
        if (e.isRightClick()) click += "right";
        if (e.isShiftClick()) click += "shift";
        this.set("click", click);
        this.concat("player", new PlayerV((Player) e.getWhoClicked()));
        this.concat("itemstack", new ItemStackV(e.getCurrentItem()));
        this.set("slot", String.valueOf(e.getSlot()));
    }
}
