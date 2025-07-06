# HTTP GET/POST
HTTPリクエストを送信して、レスポンスを受け取ります。
## 使い方
- GETリクエスト
```
httpget URL
```
- POSTリクエスト
```
httppost 引数構造体変数名 URL
```
## 使用例
```
# GETプロトコルでIPを取得
httpget https://ipinfo.io/ip
cmd say <r.content>

# POSTプロトコルでユーザー登録と、ステータスコード出力
setvar httpargs.name namae
httppost httpargs http://api.hoge.fuga/register
cmd say <r.statuscode>
```