(ns dda.k8s-mastodon-bot.core-test
  (:require
   #?(:clj [clojure.test :refer [deftest is are testing run-tests]]
      :cljs [cljs.test :refer-macros [deftest is are testing run-tests]])
   [dda.k8s-mastodon-bot.core :as cut]))

(deftest should-generate-yaml
  (is (=  {:apiVersion "v1", :kind "ConfigMap"
           :metadata {:name "mastodon-bot", 
                      :labels {:app.kubernetes.io/name "k8s-mastodon-bot"}}, 
           :data {:config.edn "some-config-value\n", 
                  :credentials.edn "some-credentials-value\n"}}
          (cut/generate-config "some-config-value\n" "some-credentials-value\n"))))
