# GUI
Create inventory GUIs like game menus.

## Usage Example
```
# When clicking the green dye in the GUI, display "Start!!!!"
# Inside function newEmpItemStack
itemstack barrier 64 n/a
exit r
# Inside function clickGuiItem
if <event.itemstack.displayname=startButtonName> nop else exit
cmd say Start!!!!
# Inside function clickHandItem
if <event.itemstack.displayname=menuOpenItemName> nop else exit
opengui menuGui <event.player.name>
# Inside function main
declfunc newEmpItemStack world 12 10 10
declfunc clickGuiItem world 14 10 10
seteventfunc onclickguiitem clickGuiItem
declfunc clickHandItem world 16 10 10
seteventfunc onclickhanditem clickHandItem
setvarG startButtonName Start!!
setvarG menuOpenItemName Game Menu
for 9 newEmpItemStack
itemstack itemstacks.4 green_dye 1 <startButtonName>
itemstack menuOpenItem compass 1 <menuOpenItemName>
newgui menuGui itemstacks StartGUI
# In chat command (lups0 is my player name)
/advcmd declfunc main world 10 10 10
/advcmd main
/advcmd give menuOpenItem lups0
```