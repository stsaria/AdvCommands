# 定義済み関数
※ここで書いている\<〇〇\>はわかりやすくするために書いてあるだけなので、実際にはその囲いはありません。

result*で戻り値を表して、書きます。

- `nop` : 何もしない。 -> result = null
- `cmd <マイクラコマンド>` : サーバーコンソールを通じてマイクラコマンドを実行します。 -> result = null
- `declfunc <関数名> <ワールド名> <x座標(最初のコマンドブロックの)> <y座標> <z座標>` : 新しい関数を定義します。 -> result = null
- `catfunc <関数名>` : 関数の中身を返します。 -> result = 関数の中身
- `exit` : 関数を終了させます。（関数内のみ使用可） -> result = 未定義
- `exit　<戻り値変数名>` : 戻り値をつけて関数を終了させます。（関数内のみ使用可） -> result = 未定義
- `seteventfunc <イベントタイプ> <関数名>` : 特定のイベント時には特定の関数を実行する。 -> result = null
- `setvar <変数名> <変数値>` : 変数を定義または上書きします。 -> result = null
- `delvar <変数名>` : 変数を削除します。 -> result = null
- `for <繰り返し回数> <文>` : 特定の回数、文を実行します。
- `if <true,false> <関数1名> else <関数2名>` : trueであれば関数1を実行して、falseであれば関数2を実行します。 -> result.* = 実行した関数の結果
- `waitrun <遅延ミリ秒> <関数名>` : 特定のミリ秒遅延したあとに関数を実行します。 -> result = null
- `randint <範囲開始> <範囲終了>` : 範囲内のランダムな数字を返します。 -> result = 乱数
- `itemstack <マテリアル名> <スタック数> <アイテム表示名>` : 指定された表示名・マテリアル名（spigotのマテリアルEnumを小文字にしたもの）（例:acacia_boat）・スタック数から作ったアイテムスタックを返します。 -> result.* = itemstack
- `newgui <GUI名> <アイテムスタック配列変数（例:hoge.0 -> itemstack, hoge.1）（グローバル変数のみ）> <GUI表示名>` : インベントリGUIを作成します。 -> result = null
- `opengui <GUI名> <プレイヤー名>` : プレイヤーにインベントリGUIを表示します。 -> result = null
- `give <アイテムスタック変数> <プレイヤー名>` : プレイヤーにアイテムスタックを送ります。 -> result = null
- `length <変数名>` : `変数名.0`・`変数名.1`のような整数での順番がある変数の要素数を数えて、返します。（途中で途切れていたり、0から始まらない場合は正確に数えられません） -> result = 配列の長さ
- `trueif <true,false> <コード>` : `true`の場合しか扱わない`if`です。コードはいくら長くても問題ありません。 -> result.* = 実行した関数の結果
- `bungeemove <サーバーID> <プレイヤー名>` : BungeeCordでプレイヤーを移動させます。 -> result = null
- `teams` : チームリストを返します。 -> result.n.* = n個目のteam
- `size <変数名>` : `変数名.a`・`変数名.hogeee`のような変数の要素数を数えて、返します。 -> result = 構造体の要素数
- `players` : プレイヤーリストを返します。 -> result.n.*  = n個目のplayer
- `cancel` : 関数を終了して、イベント時には可能な場合にイベントをキャンセルする。（関数内のみ使用可） -> result = 未定義
- `skip` : 関数の次の行を無視する。（関数内のみ使用可） -> result = 未定義
- `strtolist <文字列>` : 文字列を一文字ずつの文字列リストに変換して、返します。 -> result.n = n個目の文字
- `shuffled <変数名>` : リストをシャッフルする。（破壊的変更） -> result = null
- `addenchant <元アイテムスタック変数> <エンチャント名> <エンチャントレベル>` : アイテムスタックにエンチャントを追加する。 -> result = null
- `jsontostruct <Json文字列>` : Json文字列を構造体に変換する。 -> result.* = json構造体
- `structtojson <構造体変数名>` : 構造体をJson文字列に変換する。 -> result = json文字列
- `ismatch <マッチ元変数名> <正規表現文字列>` : 代入変数名に対する値に、もしマッチしていたらtrueを、マッチしていなければ、falseを返します。 -> result = マッチするか(true or false)
- `replace <置換元変数名> <置換正規表現変数名> <置換後変数名>` : 文字列を正規表現で置換して返します。 -> result = 置換後の文字列
- `split <区切り元文字列変数名> <区切り文字列>` : 文字列を区切って返します。 -> result.n = n個目の区切られた文字列
- `httpget <URL>` : HTTP GETリクエストを送り、httpresponseを返します。 -> result.* = httpresponse
- `httppost <引数構造体変数名> <URL>` : HTTP POSTリクエストを送り、httpresponseを返します。 -> result.* = httpresponse
- `variables` : 変数の一覧配列を生成し、返します。 -> result.n = `変数名 -> 値`のような表現の文字列
- `exportfunc <関数名>` : 関数の内容を独自の形式に変換し、返します。 -> result = 変換後文字列
- `importfunc <関数の内容>` : 関数を読み込みます。 -> result = null
- `toint <少数または整数>` : 値を整数に変換し、返します。 -> result = 変換後の整数
- `getname <構造体> <値>` : 構造体（配列も含む）の値に対しての変数名を探して、返します。二つある場合は先に代入されたものを返します。 -> result = 発見した変数名　発見できなかったのならnull
- `delgui <GUI名>` : GUIを削除します。 -> result = null
- `closegui <プレイヤー名>` : プレイヤーが表示しているGUiを閉じます。 -> result = null
- `runinglobal <文>` : グローバル変数スコープ上で文を実行します。 -> result.* = 実行した関数の結果
- `localtoglobal　<コピー元関数内変数名> <コピー先グローバル変数名>` : 今の関数内変数をグローバル変数にコピーします。 -> result = null
- `globaltolocal　<コピー元グローバル変数名> <コピー先関数内変数名>` : グローバル変数を今の関数内変数にコピーします。 -> result = null
- `deepequal <比較対象1> <比較対象2>` : 対象の変数の配下すべてが同じ場合にtrueを、そうでない場合にfalseを返します。 -> result = 同じであるか(true or false)
- `saveresult <保存先変数名>` : 戻り値を保存します。（関数内のみ使用可） -> result = 未定義