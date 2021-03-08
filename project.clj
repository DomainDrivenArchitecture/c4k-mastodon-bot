(defproject dda/k8s-mastodon-bot "0.1.0-SNAPSHOT"
  :description "common utils for dda config"
  :url "https://www.domaindrivenarchitecture.org"
  :license {:name "Apache License, Version 2.0"
            :url "https://www.apache.org/licenses/LICENSE-2.0.html"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [aero "1.1.6"]
                 [expound "0.8.4"]
                 [clj-commons/clj-yaml "0.7.106"]]
  :source-paths ["src/main/cljc"
                 "src/main/clj"]
  :resource-paths ["src/main/resources"]
  :repositories [["snapshots" :clojars]
                 ["releases" :clojars]]
  :deploy-repositories [["snapshots" :clojars]
                        ["releases" :clojars]]
  :profiles {:test {:test-paths ["src/test/cljc"]
                    :resource-paths ["src/test/resources"]
                    :dependencies [[dda/data-test "0.1.1"]]}
             :uberjar {:source-paths ["src/main/clj"]
                       :resource-paths ["src/main/resources"]
                       :aot :all
                       :main dda.k8s-mastodon-bot.uberjar
                       :uberjar-name "k8s-mastodon-bot-standalone.jar"
                       :dependencies [[org.clojure/tools.cli "1.0.194"]
                                      [ch.qos.logback/logback-classic "1.3.0-alpha5"]
                                      [org.slf4j/jcl-over-slf4j "2.0.0-alpha1"]]}})
