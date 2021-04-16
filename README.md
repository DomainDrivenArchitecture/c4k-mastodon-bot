# k8s-mastodon-bot
[![Clojars Project](https://img.shields.io/clojars/v/dda/k8s-mastodon-bot.svg)](https://clojars.org/dda/k8s-mastodon-bot) [![pipeline status](https://gitlab.com/domaindrivenarchitecture/k8s-mastodon-bot/badges/master/pipeline.svg)](https://gitlab.com/domaindrivenarchitecture/k8s-mastodon-bot/-/commits/master) 

[<img src="https://domaindrivenarchitecture.org/img/delta-chat.svg" width=20 alt="DeltaChat"> chat over e-mail](mailto:buero@meissa-gmbh.de?subject=community-chat) | [<img src="https://meissa-gmbh.de/img/community/Mastodon_Logotype.svg" width=20 alt="team@social.meissa-gmbh.de"> team@social.meissa-gmbh.de](https://social.meissa-gmbh.de/@team) | [Website & Blog](https://domaindrivenarchitecture.org)

## Purpose

k8s-mastodon-bot generates a k8s cron-job for your mastodon-bot. All inputs are validated, generaterd yaml will be wellformed, indenet and escaped.

## Try out

Click on the image to try out live in your browser:

[![Try it out](/doc/tryItOut.png "Try out yourself")](https://domaindrivenarchitecture.org/pages/dda-provision/k8s-mastodon-bot/)

Your input will stay in your browser. No server interaction is required.

You will also be able to try out on cli:
```
target/graalvm/k8s-mastodon-bot src/test/resources/valid-config.edn src/test/resources/valid-auth.edn | kubeval -
target/graalvm/k8s-mastodon-bot src/test/resources/valid-config.edn src/test/resources/valid-auth.edn | kubectl apply -f -
```

## Integration

We provide a bunch of artefacts:
* compiled binary, you may use it in your cli or ci
* a standalone uberjar, you may use it in your cli or ci
* a clj library, you may use it as api in your clj project 
* a cljs web frontend, you may use it as self-service UI

## License

Copyright © 2021 meissa GmbH
Licensed under the [Apache License, Version 2.0](LICENSE) (the "License")
Pls. find licenses of our subcomponents [here](doc/SUBCOMPONENT_LICENSE)