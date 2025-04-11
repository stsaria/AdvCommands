This README is in Japanese. The English README is [here](README-EN.md). (It is left to machine translation, so there is a possibility that something may have gone wrong.)
# AdvCommands
AdvCommandsは、従来のマインクラフトコマンドに複数の機能を追加するプラグインのプロジェクトです。
## これが必要な理由
今までのマインクラフトコマンドで作れるものはたくさんあります。
その殆どは、`execute`という条件分岐ができるコマンドとコマンドブロックのリピートを組み合わせて多用しています。

しかし、`execute`少し分かりづらく、リピートしていることで考えることが増えるほか、パフォーマンスも低下してしまいます。

このプラグインはこれらをわかりやすいものに置き換えることをします。
## 始めてみましょう
このプラグインをspigot系のサーバーで導入して、起動します。（コマンドブロックも有効化してください）

起動したらコマンド`/advcmd cmd say Hello world`を実行してみてください。
そうしたら、`Hello world`が出力されます。いや、されることを祈っています。
## 追加される機能
- 変数(\<v\>, setvar, delvar)
- 関数(f, declfunc, catfunc)
- 繰り返し(for)
- イベントハンドラ(seteventfunc)
- ランダムUUID(\<randuuid\>)
- UnixTime(\<unixtime\>)
- 四則演算&累乗(\<x[+-*/%^]y\>)
- 比較(\<x[<>=]y\>)
- 条件分岐(if)
- 遅延(waitrun)

詳しくはDefinedFunctions.mdとDefinedVariables.mdを参照してください。

※関数内使用可能

順を追って説明します。
### 変数
変数を使えば、具体的な値を抽象的な名前にし、わかりやすくコードを書くことができます。
`setvar 変数名 値`で変数を宣言します。
`<変数名>`で値を埋め込みます。
```
関数内変数bakaにahoを代入
setvar baka aho
表示
cmd say <baka>
これでahoと表示されます。

グローバル変数(どこからもアクセス可)zaemonにdaisukiを代入
setvarG zaemon daisuki
関数内変数bakaを削除
delvar baka
グローバル変数zaemonを削除
delvarG zaemon
```
グローバル変数は関数内変数に同じ名前がある場合に無視されます。
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
/advcmd declfunc hoge world 20 30 10

関数hogeの実行
/advcmd runfunc hoge

関数内で関数を呼び出す場合はrunFuncはいりません。
hoge
だけです。

関数hogeの引数にhelloを入れる
hoge hello

関数hoge内
cmd say <args.0>
```
関数に戻り値をつけたいときは、最初にuuidか何かで変数idを決めておいて、それを関数の引数に送って、関数内で代入してください。
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

```
キルイベントでhogeという関数を実行するスケジュールを登録
seteventfunc onKill hoge

例:移動したプレイヤーにmove!と送る
チャットコマンド
/advcmd seteventfunc onMove fuga
関数fuga内
cmd tell {player.name} move!
```
### ランダムUUID
`<randuuid>`とすることによってそこがランダムなUUID(ハイフン無し)に置き換わります。
たとえば、ランダムUUIDを使用して、結果を保存する変数名を予定することができます。
```
関数hoge内
setvar returnId #randuuid#
piyo <returnId>
関数piyo内
setvarG return<args.0> hello
関数hoge内
cmd say <return<returnId>>
これでhelloと表示されます。
```
### UnixTime
`<unixtime>`とすることによってそこが現在のUnixTimeに置き換わります。
ゲーム開始から何秒経ったかの計算などに使用できます。
```
setvar start <unixtime>
waitrun 1000 cmd say <<unixtime>-start>
こうすると、1と表示されるはずです。
```
### 四則演算&累乗
今まで`scoreboard`などで行っていた四則演算と累乗をかんたんに実装できます。
`<1+1>`のように変数と同じように囲ってその中に式を書きます。

書いたところが演算結果に置き換わります。

※1+1+2のような3つ以上は`<<1+1>+<2+3>>`というふうに書いてください。

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
<8%5>
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
### 遅延
**バックグランドで**特定のミリ秒数遅延したあとに、特定の関数を実行します。
```
一秒後（1000ミリ秒後）にこんにちはと表示する
waitrun 1000 cmd say こんにちは
```
※バックグランドなので、例えば`for 10 waitrun 1000 cmd say hello`こうしたときに、`hello`は1秒後にほぼ同時に10回表示されます。
## 注意点
- 自分の変数の値に自分の変数の参照を代入しないでください。二度と終わらなくなります。
- ↑関数も同じです。
