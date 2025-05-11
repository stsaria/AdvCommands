# 定義済み変数
## 通常変数
### location
- `<world>` : ワールド名
- `<x>` : x座標
- `<y>` : y座標
- `<z>` : z座標
- `<blockx>` : ブロックのx座標
- `<blocky>` : ブロックのy座標
- `<blockz>` : ブロックのz座標
- `<yaw>` : プレイヤーが向いている方向(角 度単位)（横）
- `<pitch>` : プレイヤーが向いている方向(角 度単位)（縦）

角度(yaw)は右に向くほど増加します。
角度(pitch)は正面が0度で、下を向くほど増加します。
### entity
- `<name>` : エンティティ名
- `<entityid>` : エンティティタイプのID
- `<uuid>` : エンティティUUID
- `<isdead>` : 死んでいるか
### player
- `<name>` : プレイヤー名(mcid)
- `<displayname>` : 表示名（\<と\>は取り除き済み）
- `<location.*>` -> location
- `<health>` : 体力
- `<foodlevel>` : 満腹度
- `<level>` : 経験値レベル
- `<uuid>` : MinecraftアカウントのUUID
- `<isdead>` : 死んでいるか
### block
- `<materialname>` : マテリアル名（例:polished_andesite）
- `<location.*>` -> location
### enchant
- `<name>` : エンチャント名（例:power）
- `<level>` : エンチャントレベル
### itemstack
- `<displayname>` : アイテム表示名
- `<materialname>` : マテリアル名
- `<amount>` : スタック数
- `<enchants.n>` : n個目のエンチャント (n -> enchant)
### team
- `<name>` : チーム名
- `<displayname>` : チーム表示名
- `<playernames.n>` : n人目のプレイヤー名
- `<onlineplayers.n>` : n人目のプレイヤー（オンラインプレイヤーのみ）
### httpresponse
- `statuscode` : ステータスコード（例:200(success), 404(not found)）
- `content` : レスポンス内容
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
- `<guiname>` : GUIインベントリ名（プレイヤーデフォルトだとdefault）
- `<guititle>` : GUIインベントリタイトル
- `<itemstack.*>` -> itemstack
### onclickhanditem
- `<player>` : GUIインベントリ名
- `<guititle>` : GUIインベントリタイトル
- `<itemstack.*>` -> itemstack
### onleave
- `<player.*>` -> player
### ondamage
- `<type>` : playerまたはentity(playerはentityを継承しているが別物とする)
- `<player.*>` : typeがplayerの時だけ -> player
- `<entity.*>` : typeがentityの時だけ -> entity
### ondamagebyentity
- `<type>` : playerまたはentity(playerはentityを継承しているが別物とする)
- `<player.*>` : typeがplayerの時だけ -> player
- `<entity.*>` : typeがentityの時だけ -> entity
- `<damagertype>` : playerまたはentity(playerはentityを継承しているが別物とする)
- `<damagerplayer.*>` : typeがplayerの時だけ -> player
- `<damagerentity.*>` : typeがentityの時だけ -> entity
## for時"ユーザー関数"変数
- `<i>` : 現在の往復回数（0から数えた）
## そのほか
- `<randuuid>` : ランダムなUUID(ハイフンなし)
- `<unixtime>` : 今のUNIXタイム
- `<nl>` : 改行