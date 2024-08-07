# c4k-mastodon-bot
[![Clojars Project](https://img.shields.io/clojars/v/dda/c4k-mastodon-bot.svg)](https://clojars.org/dda/c4k-mastodon-bot) [![pipeline status](https://gitlab.com/domaindrivenarchitecture/c4k-mastodon-bot/badges/master/pipeline.svg)](https://gitlab.com/domaindrivenarchitecture/c4k-mastodon-bot/-/commits/master) 

[<img src="https://domaindrivenarchitecture.org/img/delta-chat.svg" width=20 alt="DeltaChat"> chat over e-mail](mailto:buero@meissa-gmbh.de?subject=community-chat) | [<img src="https://meissa.de/images/parts/contact/mastodon36_hue9b2464f10b18e134322af482b9c915e_5501_filter_14705073121015236177.png" width=20 alt="M"> meissa@social.meissa-gmbh.de](https://social.meissa-gmbh.de/@meissa) | [Blog](https://domaindrivenarchitecture.org) | [Website](https://meissa.de)

## Purpose

c4k-mastodon-bot generates a c4k cron-job for your mastodon-bot. All inputs are validated, generaterd yaml will be wellformed, indenet and escaped.

## Rational

There are many comparable solutions for creating c4k deployments like helm or kustomize. Why do we need another one?
* We like the simplicity of kustomize. Yaml in, yaml out, the ability to lint the result and the option to split large yaml files into objects. But a simple overwriting per environment may not be enough ...
* We like helm packages. A package encapsulates the setup for an application. On the one hand, but on the other hand we don't like the idea of having to program and debug in a template language. We can program much better in real programming languages.

Our c4k-* tools combine the advantages of both approaches:
* Packages for one application
* Programming in clojure
* yaml / edn as input and output, no more magic
* good validation, integration as api, cli or in the browser

## Try out

Click on the image to try out live in your browser:

[![Try it out](/doc/tryItOut.png "Try out yourself")](https://domaindrivenarchitecture.org/pages/dda-provision/c4k-mastodon-bot/)

Your input will stay in your browser. No server interaction is required.

You will also be able to try out on cli:
```
target/graalvm/c4k-mastodon-bot src/test/resources/valid-config.edn src/test/resources/valid-auth.edn | kubeval -
target/graalvm/c4k-mastodon-bot src/test/resources/valid-config.edn src/test/resources/valid-auth.edn | kubectl apply -f -
```
## Development & mirrors

Development happens at: https://repo.prod.meissa.de/meissa/c4k-mastodon-bot

Mirrors are:

* https://gitlab.com/domaindrivenarchitecture/c4k-mastodon-bot (issues and PR, CI)
* https://github.com/DomainDrivenArchitecture/c4k-mastodon-bot

For more details about our repository model see: https://repo.prod.meissa.de/meissa/federate-your-repos

## License

Copyright © 2024 meissa GmbH
Licensed under the [Apache License, Version 2.0](LICENSE) (the "License")
Pls. find licenses of our subcomponents [here](doc/SUBCOMPONENT_LICENSE)
