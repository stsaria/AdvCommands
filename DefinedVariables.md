# 定義済み変数
## 通常変数
### location
- `<world>` : ワールド名
- `<x>` : x座標
- `<y>` : y座標
- `<z>` : z座標
### player
- `<name>` : プレイヤー名(mcid)
- `<displayname>` : 表示名（\<と\>は取り除き済み）
- `<location.*>` -> location
### block
- `<materialname>` : マテリアル名（例:polished_andesite）
- `<location.*>` -> location
### itemstack
- `<displayname>` : アイテム表示名
- `<materialname>` : マテリアル名
- `<amount>` : スタック数
## 関数実行時変数
- `<args.n>` : n個目の引数
- `<funcfirstblock>` : 関数の最初のブロック（line 1）-> block
- `<funcnowlineblock>` : 関数の現在実行しているラインのブロック -> block
## イベント変数
### onkill
- `<player.*>` -> player
- `<killer.*>` -> player
### onmove
- `<player.*>` -> player
- `<from.*>` : もとにいる位置 -> location
- `<to.*>` : 移動後の位置 -> location
### onjoin
- `<player.*>` -> player
### onplaceblock
- `<player.*>` -> player
- `<block.*>` -> block
- `<placedblock.*>` -> block
### onbreakblock
- `<player.*>` -> player
- `<block.*>` -> block
### onchat
- `<message>` : メッセージ（\<と\>は取り除き済み）
- `<player.*>` -> player
### onclickguiitem
- `<guiname>` : GUIインベントリ名
- `<guititle>` : GUIインベントリタイトル
- `<itemstack.*>` -> itemstack
### onclickhanditem
- `<player>` : GUIインベントリ名
- `<guititle>` : GUIインベントリタイトル
- `<itemstack.*>` -> itemstack
### onleave
- `<player.*>` -> player
## for時"ユーザー関数"変数
- `<i>` : 現在の往復回数（0から数えた）