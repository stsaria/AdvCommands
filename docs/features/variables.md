# 変数
変数とは、値に名前付けをするものです。
特に最近のプログラムでは、意味のない名前（x,y,zなど）ではなく、意味のある名前（userNameなど）を基本的に付けます。
## 使い方
- 変数の設定
```
setvar 変数名 値
```
- 変数をコピー（配下の要素(player.nameのように)をすべてコピーする）
```
copyvar コピー元変数名 コピー先変数名
```
- 変数の削除
```
delvar 変数名
```
- 変数の埋め込み
```
<変数名>
```
## 使用例
```
# 関数内変数bakaにahoを代入
setvar baka aho
# bakaのコピーkasuを作成
copyvar baka kasu
# 表示
cmd say <baka>
# これでahoと表示されます。
cmd say <baka!>
# こうすると、変数としてではなく、ただの<baka>として実行されます。

# グローバル変数(どこからもアクセス可)zaemonにdaisukiを代入
setvarG zaemon daisuki
# 関数内変数bakaを削除
delvar baka
# グローバル変数zaemonを削除
delvarG zaemon
```