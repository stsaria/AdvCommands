# ランダム
乱数と、ランダムな文字列
## 使い方
- 範囲を指定した乱数を生成（変数名にグローバル変数として代入）
```
randint 代入変数名 範囲開始 範囲終了
```
- ランダムなUUIDを生成（マクロのような）
```
<randuuid>
```
## 使用例
```
# ランダムなプレイヤーに、あたり！と送る
players players
length playersLen players
randint playerInt 0 <playersLen-1>
copyvar players.<playersInt> hitPlayer
cmd tellraw <hitPlayer.name> "あたり！"

# ランダムなUUIDを使って、戻り値を再現
# 関数hoge内
setvar returnId <randuuid>
piyo <returnId>
# 関数piyo内
setvarG return<args.0> hello
# 関数hoge内
cmd say <return<returnId>>
# これでhelloと表示されます。
```