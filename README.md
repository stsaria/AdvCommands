This README is in Japanese. The English README is [here](README-EN.md). (It is left to machine translation, so there is a possibility that something may have gone wrong.)
# AdvCommands
AdvCommandsは、従来のマインクラフトコマンドに複数の機能を追加するプラグインのプロジェクトです。
## これが必要な理由
今までのマインクラフトコマンドで作れるものはたくさんあります。
その殆どは、`execute`という条件分岐ができるコマンドとコマンドブロックのリピートを組み合わせて多用しています。

しかし、`execute`少し分かりづらく、リピートしていることで考えることが増えるほか、パフォーマンスも低下してしまいます。

このプラグインはこれらをわかりやすいものに置き換えることをします。
## 始めてみましょう
このプラグインをspigot系のサーバーで導入して、起動します。（コマンドブロックも有効化してください）

起動したらコマンド`/advcmd cmd say Hello world`を実行してみてください。
そうしたら、`Hello world`が出力されます。いや、されることを祈っています。
## 追加される機能
- [変数](docs/features/variables.md)
- [関数](docs/features/functions.md)
- [繰り返し](docs/features/for.md)
- [イベントハンドラ](docs/features/eventHandler.md)
- [ランダム](docs/features/random.md)
- [UnixTime](docs/features/unixTime.md)
- [四則演算&累乗](docs/features/basicOperationsAndPowers.md)
- [真・偽](docs/features/trueAndFalse.md)
- [条件分岐](docs/features/if.md)
- [遅延実行](docs/features/waitAndRun.md)
- [GUI](docs/features/gui.md)
- [HTTP](docs/features/http.md)
- [独自コマンド](docs/features/userDefinedCommand.md)
