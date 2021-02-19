(defproject dda/k8s-mastodon-bot "0.1.0-SNAPSHOT"
  :description "common utils for dda config"
  :url "https://www.domaindrivenarchitecture.org"
  :license {:name "Apache License, Version 2.0"
            :url "https://www.apache.org/licenses/LICENSE-2.0.html"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [aero "1.1.6"]
                 [clj-commons/clj-yaml "0.7.106"]]
  :source-paths ["src/main"]
  :resource-paths ["resources/main"]
  :repositories [["snapshots" :clojars]
                 ["releases" :clojars]]
  :deploy-repositories [["snapshots" :clojars]
                        ["releases" :clojars]]
  :profiles {:dev {:source-paths ["integration/src"
                                  "test/src"
                                  "uberjar/src"]
                   :resource-paths ["integration/resources"
                                    "test/resources"]
                   :dependencies
                   [[org.clojure/test.check "0.10.0-alpha4"]
                    [ch.qos.logback/logback-classic "1.3.0-alpha4"]
                    [org.slf4j/jcl-over-slf4j "1.8.0-beta4"]
                    [dda/data-test "0.1.1"]]}
             :test {:test-paths ["src/test"]
                    :resource-paths ["resources/test"]
                    :dependencies [[dda/data-test "0.1.1"]]}}
  :local-repo-classpath true)
