# AdvCommands
AdvCommandsは、従来のマインクラフトコマンドに複数の機能を追加するプラグインのプロジェクトです。
## これが必要な理由
今までのマインクラフトコマンドで作れるものはたくさんあります。
その殆どは、`execute`という条件分岐ができるコマンドとコマンドブロックのリピートを組み合わせて多用しています。

しかし、`execute`少し分かりづらく、リピートしていることで考えることが増えるほか、パフォーマンスも低下してしまいます。

このプラグインはこれらをわかりやすいものに置き換えることをします。
## 始めてみましょう
このプラグインをspigot系のサーバーで導入して、起動します。

起動したらコマンド`/advCmd cmd say Hello world`を実行してみてください。
そうしたら、`Hello world`が出力されます。いや、されることを祈っています。
## 追加される機能
- 変数※
- 関数
- 繰り返し
- イベントハンドラ
- ランダムUUID※
- 四則演算&累乗※
- 比較
- 条件分岐

※関数内使用可能

順を追って説明します。
### 変数
変数を使えば、具体的な値を抽象的な名前にし、わかりやすくコードを書くことができます。
`setVar 変数名 値`で変数を宣言します。
`<変数名>`または`;変数名;`で値を埋め込みます。
`;変数名;`のほうが早く埋め込まれます。
※setVarは関数以外（チャットコマンド）で使えますが、変数の埋め込みはできません。
```
関数内変数bakaにahoを代入
setVar baka aho
グローバル変数(どこからもアクセス可)zaemonにdaisukiを代入
setVarG zaemon daisuki

cmd say <baka>
これでahoと表示されます。
```
### 関数
※数学の関数とは違います

関数というのは、何らかの処理をひとまとまりにしたものです。
この関数があることによって同じ処理を何回も書いたりすることがないのでプログラムのサイズも少なくなり、可読性が上がります。

このプラグインでは、コマンドブロックを+y軸方向につなげることで複数の処理を一つの関数としてまとめることができます。
座標`10 10 10`が1行目としたら、`10 12 10`が3行目です。

コマンドブロックにリピートやチェーンをつけないでください。

関数のあとに引数をつけることもできます。（戻り値はつけれません）

```
座標`20 30 10`が1行目とした関数hogeの宣言
/advcmd declFunc hoge world 20 30 10

関数hogeの実行
/advcmd runFunc hoge

関数内で関数を呼び出す場合はrunFuncはいりません。
hoge
だけです。

関数hogeの引数にhelloを入れる
hoge hello

関数hoge内
cmd say <args.0>
```
### 繰り返し
ある処理を特定の回数で繰り替えします。

これは`scoreboard`と`execute`などを使えばできますが、このプラグインではもっと簡略化します。

```
10回関数hogeを実行する
for 10 hoge
```
### イベントハンドラ
プラグインでは何らかのイベント（移動やブロック設置など）が発生すると特定の関数を実行できます。
これによって毎tickで確認する従来のコマンドではなく、何かが起きたら実行してもらうという方式だと可読性が上がります。

イベントハンドラにはイベントに関する変数が含まれます
詳しくはEventVariables.mdを参照してください。

```
キルイベントでhogeという関数を実行するスケジュールを登録
addEvent onKill hoge

例:移動したプレイヤーにmove!と送る
チャットコマンド
/advCmd addEvent onMove fuga
関数fuga内
cmd tell {player.name} move!
```
### ランダムUUID
`#randuuid#`とすることによってそこがランダムなUUID(ハイフン無し)に置き換わります。
たとえば、このプラグインには関数の戻り地がありません。
そこでランダムUUIDを使用して、結果を保存する変数名を予定することができます。
```
関数hoge内
setVar returnId #randuuid#
piyo <returnId>
関数piyo内
setVarG return<args.0> hello
関数hoge内
cmd say <return;returnId;>
これでhelloと表示されます。
```
### 四則演算&累乗
今まで`scoreboard`などで行っていた四則演算と累乗をかんたんに実装できます。
`<1+1>`のように変数と同じように囲ってその中に式を書きます。

書いたところが演算結果に置き換わります。

※1+1+2のような3つ以上の項目には対応していません。変数を使ってください。

演算例:
```
足し算
<1+1>
引き算
<2-1>
掛け算
<5*4>
割り算
<9/3>
割り算（あまり）
<8/5>
累乗
<2^10>
```
### 比較
不等号と等号が真か偽を判断します。

真の場合は`true`、
偽の場合は`false`に置き換えます
```
等号
<5=5>
不等号
<1<2>
不等号
<1>2>
```
### 条件分岐
あたえられた文字列が`true`なら最初の関数を実行し、`false`ならelseのあとの関数を実行します。

比較も使用できます。
```
真なら関数hogeを実行し偽なら関数pandaを実行する。（常にhogeが実行される）
if true hoge else panda
逆
if false panda else hoge
不定式を使ってみる
if <2>1> nisu else nice
```