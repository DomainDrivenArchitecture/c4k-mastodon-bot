# install graalvm
curl -LO  https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-21.0.0.2/graalvm-ce-java11-linux-amd64-21.0.0.2.tar.gz 

# unpack
tar -xzf graalvm-ce-java11-linux-amd64-21.0.0.2.tar.gz 

# install native-image in graalvm-ce-java11-linux-amd64-21.0.0.2/bin
./gu install native-image

# deps
sudo apt-get install build-essential libz-dev zlib1g-dev

# build image. e.g.
./bin/native-image  -jar ~/repo/dda/k8s-mastodon-bot/target/uberjar/k8s-mastodon-bot-0.1.3-SNAPSHOT.jar ~/repo/dda/k8s-mastodon-bot/target/bot
