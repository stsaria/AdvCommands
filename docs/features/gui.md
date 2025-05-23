# GUI
ゲームのメニューのようなインベントリGUIを作成します。
## 使用例
```
# GUIの緑の染料をクリックしたらStart!!!!って表示する
# 関数newEmpItemStack内
itemstack itemstacks.<i> barrier 64 n/a
# 関数clickGuiItem内
if <event.itemstack.displayname=startButtonName> nop else exit
cmd say Start!!!!
# 関数clickHandItem内
if <event.itemstack.displayname=menuOpenItemName> nop else exit
opengui menuGui <event.player.name>
# 関数main内
declfunc newEmpItemStack world 12 10 10
declfunc clickGuiItem world 14 10 10
seteventfunc onclickguiitem clickGuiItem
declfunc clickHandItem world 16 10 10
seteventfunc onclickhanditem clickHandItem
setvarG startButtonName Start!!
setvarG menuOpenItemName Game Menu
for 9 newEmpItemStack
itemstack itemstacks.4 green_dye 1 <startButtuonName>
itemstack menuOpenItem compass 1 <menuOpenItemName>
newgui menuGui itemstacks StartGUI
# チャットコマンド内(lups0は私のプレイヤー名です)
/advcmd declfunc main world 10 10 10
/advcmd main
/advcmd give menuOpenItem lups0
```