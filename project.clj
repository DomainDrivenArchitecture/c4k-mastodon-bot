(defproject org.domaindrivenarchitecture/c4k-mastodon-bot "0.1.11-SNAPSHOT"
  :description "mastodon-bot c4k-installation package"
  :url "https://www.domaindrivenarchitecture.org"
  :license {:name "Apache License, Version 2.0"
            :url "https://www.apache.org/licenses/LICENSE-2.0.html"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clojure/tools.reader "1.3.6"]
                 [aero "1.1.6"]
                 [orchestra "2021.01.01-1"]
                 [expound "0.9.0"]
                 [clj-commons/clj-yaml "1.0.26"]
                 [dda/mastodon-bot "1.10.11"]]
  :target-path "target/%s/"
  :source-paths ["src/main/cljc"
                 "src/main/clj"]
  :resource-paths ["src/main/resources"]
  :repositories [["snapshots" :clojars]
                 ["releases" :clojars]]
  :deploy-repositories [["snapshots" {:sign-releases false :url "https://clojars.org/repo"}]
                        ["releases" {:sign-releases false :url "https://clojars.org/repo"}]]
  :profiles {:test {:test-paths ["src/test/cljc"]
                    :resource-paths ["src/test/resources"]
                    :dependencies [[dda/data-test "0.1.1"]]}
             :dev {:plugins [[lein-shell "0.5.0"]]}
             :uberjar {:aot :all
                       :main dda.c4k-mastodon-bot.uberjar
                       :uberjar-name "c4k-mastodon-bot-standalone.jar"
                       :dependencies [[org.clojure/tools.cli "1.0.214"]
                                      [ch.qos.logback/logback-classic "1.4.5"
                                       :exclusions [com.sun.mail/javax.mail]]
                                      [org.slf4j/jcl-over-slf4j "2.0.6"]]}}
  :release-tasks [["test"]
                  ["vcs" "assert-committed"]
                  ["change" "version" "leiningen.release/bump-version" "release"]
                  ["vcs" "commit"]
                  ["vcs" "tag" "--no-sign"]
                  ["change" "version" "leiningen.release/bump-version"]]
  :aliases {"native" ["shell"
                      "native-image"
                      "--report-unsupported-elements-at-runtime"
                      "--initialize-at-build-time"
                      "-jar" "target/uberjar/c4k-mastodon-bot-standalone.jar"
                      "-H:ResourceConfigurationFiles=graalvm-resource-config.json"
                      "-H:Log=registerResource"
                      "-H:Name=target/graalvm/${:name}"]})
