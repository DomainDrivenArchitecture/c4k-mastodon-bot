(ns dda.c4k-mastodon-bot.yaml-test
  (:require
   #?(:clj [clojure.test :refer [deftest is are testing run-tests]]
      :cljs [cljs.test :refer-macros [deftest is are testing run-tests]])
   [dda.c4k-mastodon-bot.yaml :as cut]))

(deftest should-parse-yaml-string
  (is (= {:hallo "welt"}
         (cut/from-string "hallo: welt"))))

(deftest should-generate-yaml-string
  (is (= "hallo: welt
"
         (cut/to-string {:hallo "welt"}))))

(deftest should-convert-config-yml-to-map
  (is (=  {:apiVersion "v1", :kind "ConfigMap"
           :metadata {:name "mastodon-bot", 
                      :labels {:app.kubernetes.io/name "c4k-mastodon-bot"}}, 
           :data {:config.edn "some-config-value\n", 
                  :credentials.edn "some-credentials-value\n"}}
          (cut/from-string (cut/load-resource "config.yaml")))))
