(defproject dda/k8s-mastodon-bot "0.1.0-SNAPSHOT"
  :description "provisioning mastodon-bot on k8s"
  :url "https://www.domaindrivenarchitecture.org"
  :license {:name "Apache License, Version 2.0"
            :url "https://www.apache.org/licenses/LICENSE-2.0.html"}
  :dependencies [[org.clojure/clojure "1.10.0"]]
  :source-paths ["main/src"]
  :resource-paths ["main/resources"]
  :repositories [["snapshots" :clojars]
                 ["releases" :clojars]]
  :deploy-repositories [["snapshots" :clojars]
                        ["releases" :clojars]]
  :profiles {:test {:test-paths ["test/src"]
                    :resource-paths ["test/resources"]
                    :dependencies [[dda/data-test "0.1.1"]]}})
