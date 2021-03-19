# install graalvm

```
curl -LO  https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-21.0.0.2/graalvm-ce-java11-linux-amd64-21.0.0.2.tar.gz 

# unpack
tar -xzf graalvm-ce-java11-linux-amd64-21.0.0.2.tar.gz 

sudo mv graalvm-ce-java11-21.0.0.2 /usr/lib/jvm/
sudo ln -s /usr/lib/jvm/graalvm-ce-java11-21.0.0.2 /usr/lib/jvm/graalvm
sudo ln -s /usr/lib/jvm/graalvm/bin/gu /usr/local/bin
sudo update-alternatives --install /usr/bin/java java /usr/lib/jvm/graalvm/bin/java 2
sudo update-alternatives --config java

# install native-image in graalvm-ce-java11-linux-amd64-21.0.0.2/bin
sudo gu install native-image
sudo ln -s /usr/lib/jvm/graalvm/bin/native-image /usr/local/bin

# deps
sudo apt-get install build-essential libz-dev zlib1g-dev

# build
mkdir -p target/graalvm
lein uberjar
lein native

# build image. e.g.
native-image  -jar ~/repo/dda/k8s-mastodon-bot/target/uberjar/k8s-mastodon-bot-0.1.3-SNAPSHOT.jar ~/repo/dda/k8s-mastodon-bot/target/bot

# execute
cd ~/repo/dda/k8s-mastodon-bot/target/bot
./bot
```