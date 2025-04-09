# 定義済み変数
## 通常変数
### location
- world : ワールド名
- x : x座標
- y : y座標
- z : z座標
### player
- name : プレイヤー名(mcid)
- displayName : 表示名
- location.* -> location
### block
- materialName : マテリアル名（例:polished_andesite）
- location.* -> location
## 関数実行時変数
- args.n : n個目の引数
- funcFirstBlock : 関数の最初のブロック（line 1）-> block
- funcNowLineBlock : 関数の現在実行しているラインのブロック -> block
## イベント変数
### onKill
- player.* -> player
- killer.* -> player
### onMove
- player.* -> player
- from.* : もとにいる位置 -> location
- to.* : 移動後の位置 -> location
### onJoin
- player.* -> player
### onPlaceBlock
- player.* -> player
- block.* -> block
- placedBlock.* -> block
### onBreakBlock
- player.* -> player
- block.* -> block
### onChat
- message : メッセージ（\<と\>は取り除き済み）
- player.* -> player