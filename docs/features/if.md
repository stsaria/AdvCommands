# 条件分岐
あたえられた文字列が`true`なら最初の関数を実行し、`false`ならelseのあとの関数を実行します。
## 使い方
- `true`なら最初の関数を実行し、`false`ならelseのあとの関数を実行
```
if trueまたはfalse true時の関数 else false時の関数
```
- `true`なら文を実行
```
trueif trueまたはfalse true時の文
```
## 使用例
```
# 真なら関数hogeを実行し偽なら関数pandaを実行する。（常にhogeが実行される）
if true hoge else panda
# 逆
if false panda else hoge
# 不等式を使ってみる
if <2>1> nisu else nice
# if(trueif)内で代入した値を使う
setvar a null
trueif true setvar a aaa
tesfunc <a>
```
## ヒント
- 実行時には実行元の変数群を参照することができます。ですが、実行先で代入してもそれは元に適用されることはありません。
- `if`は関数を実行することはできますが、その後に引数はつけられません。