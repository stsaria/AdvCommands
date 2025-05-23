# Unix Time
UTC 1970/1/1 00:00からの、経過秒数

ゲーム開始から何秒経ったかの計算などに使用できます。
## 使い方
- いまのUnix time
```
<unixtime>
```
## 使用例
```
setvar start <unixtime>
waitrun 1000 cmd say <<unixtime>-start>
# こうすると、1と表示されるはずです。
```