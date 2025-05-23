# HTTP GET/POST
HTTPリクエストを送信して、レスポンスを受け取ります。
## 使い方
- GETリクエスト
```
httpget レスポンス代入変数名 URL
```
- POSTリクエスト
```
httppost レスポンス代入変数名 引数構造体変数名 URL
```
## 使用例
```
# GETプロトコルでIPを取得
httpget ip https://ipinfo.io/ip
cmd say <ip>

# POSTプロトコルでユーザー登録と、ステータスコード出力
setvarG httpargs.name namae
httppost res httpargs http://api.hoge.fuga/register
cmd say <res.statuscode>
```