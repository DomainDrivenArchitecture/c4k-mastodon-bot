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
cd ~/repo/dda/c4k-mastodon-bot
lein uberjar
mkdir -p target/graalvm
lein native

# execute
./target/graalvm/c4k-mastodon-bot -h
./target/graalvm/c4k-mastodon-bot src/test/resources/valid-config.edn src/test/resources/valid-auth.edn 
./target/graalvm/c4k-mastodon-bot src/test/resources/invalid-config.edn src/test/resources/invalid-auth.edn
```