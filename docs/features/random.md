# ランダム
乱数と、ランダムな文字列、ランダムなリストシャッフル
## 使い方
- 範囲を指定した乱数を生成
```
randint 範囲開始 範囲終了
shuffled シャッフルする変数名
```
- ランダムなUUIDを生成（マクロのような）
```
<randuuid>
```
## 使用例
```
# ランダムなプレイヤーに、あたり！と送る
players players
length players
setvar playersLen <r>
randint 0 <playersLen-1>
setvar playerInt <r>
copyvar players.<playerInt> hitPlayer
cmd tellraw <hitPlayer.name> "あたり！"

# ランダムなUUIDを使って、戻り値を再現
# 関数hoge内
setvar returnId <randuuid>
piyo <returnId>
cmd say <return<returnId>>
# 関数piyo内
runinglobal setvar return<args.0> hello
# これでhelloと表示されます。
```