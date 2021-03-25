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
cd ~/repo/dda/k8s-mastodon-bot
mkdir -p target/graalvm
lein uberjar
lein native

# execute
./target/graalvm/k8s-mastodon-bot src/main/resources/config.edn src/main/resources/auth.edn 
```