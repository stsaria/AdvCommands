# 定義済み関数
※ここで書いている\<〇〇\>はわかりやすくするために書いてあるだけなので、実際にはその囲いはありません。
- `nop` : 何もしない
- `cmd <マイクラコマンド>` : サーバーコンソールを通じてマイクラコマンドを実行します。
- `declFunc <関数名> <ワールド名> <x座標(最初のコマンドブロックの)> <y座標> <z座標>` : 新しい関数を定義します。
- `catFunc <関数名>` : 関数の中身を出力します。
- `exit` : 意図的にエラーを出して関数を終了させます。
- `seteventfunc <onkill,onmove,onplaceblock,onbreakblock,onjoin,onchat> <関数名>` : 特定のイベント時には特定の関数を実行する。
- `setvar <変数名> <変数値>` : 変数を定義または上書きします。
- `delvar <変数名>` : 変数を削除します。
- `for <繰り返し回数> <関数名>` : 特定の回数、関数を実行します。
- `if <true,false> <関数1名> else <関数2名>` : trueであれば関数1を実行して、falseであれば関数2を実行します。
- `waitrun <遅延ミリ秒> <関数名>` : 特定のミリ秒遅延したあとに関数を実行します。