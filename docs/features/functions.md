# 関数
※数学の関数とは違います

関数とは処理に名前を付けて、ひとまとまりにしたものです。なので、メソッドというべきかもしれません。

このプラグインでは、コマンドブロックを+y軸方向につなげることで複数の処理を一つの関数としてまとめることができます。（ライブラリについては別ページにて記載）
座標`10 10 10`が1行目としたら、`10 12 10`が3行目です。

コマンドブロックにリピートやチェーンをつけないでください。

関数名の後に引数と戻り値をつけることもできます。
## 使い方
- 関数の宣言
```
declfunc 関数名 ワールド名 x座標 y座標 z座標
```
- 関数の中身を出力
```
/cakelang catfunc 関数名
```
- 関数の呼び出し
```
関数名
```
- 関数を戻り値をつけて抜ける
```
exit 戻り値変数名
```
- 戻り値（結果）を保存する
```
saveresult 保存変数名
```
## 使用例
```
# 座標`20 30 10`が1行目とした関数hogeの宣言
/cakelang declfunc hoge world 20 30 10

# 関数hogeの実行
/cakelang hoge

# 関数内で関数を呼び出す場合はcakelangはいりません。
hoge
# だけです。

# 関数hogeの引数にhelloを入れる
hoge hello

# 関数hoge内
cmd say <args.0>

```
関数に戻り値をつけたいときは、最初にuuidか何かで変数idを決めておいて、それを関数の引数に送って、関数内で代入してください。