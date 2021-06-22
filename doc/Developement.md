# Project Setup

## dev environment

```
sudo apt install npm
sudo npm install -g npx

# maybe
sudo npm install -g shadow-cljs

# in project root to retrieve all dependencies
npm install --ignore-scripts
npx shadow-cljs compile test
```

## create frontend script

```
npx shadow-cljs release frontend
```

## install kubectl

```
sudo -i
curl -s https://packages.cloud.google.com/apt/doc/apt-key.gpg | apt-key add -
echo "deb http://apt.kubernetes.io/ kubernetes-xenial main" \
  | tee -a /etc/apt/sources.list.d/kubernetes.list
apt update && apt install kubectl
kubectl completion bash >> /etc/bash_completion.d/kubernetes
```

## install kubeval

```
wget https://github.com/instrumenta/kubeval/releases/latest/download/kubeval-linux-amd64.tar.gz
tar xf kubeval-linux-amd64.tar.gz
sudo cp kubeval /usr/local/bin

## remote access to k8s
```

## remote access to k8s

```
scp -r root@devops.test.meissa-gmbh.de:/home/k8s/.kube ~/
ssh -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no root@devops.test.meissa-gmbh.de -L 8002:localhost:8002 -L 6443:192.168.5.1:6443

# add in /etc/hosts "127.0.0.1 kubernetes"

# change in ~/.kube/config 192.168.5.1 -> kubernetes

kubectl get pods
```

## deploy mastodon-bot

```
java -jar target/uberjar/k8s-mastodon-bot-standalone.jar myconfig.edn myauth.edn | kubectl apply --dry-run=client -f -
java -jar target/uberjar/k8s-mastodon-bot-standalone.jar myconfig.edn myauth.edn | kubectl apply -f -
```