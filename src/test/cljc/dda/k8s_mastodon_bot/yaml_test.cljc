(ns dda.k8s-mastodon-bot.yaml-test 
  (:require
   [clojure.test :refer [deftest is testing are]]
   [dda.k8s-mastodon-bot.yaml :as cut]))

(deftest should-parse-yaml-string
  (is (= {:hallo "welt"}
         (cut/from-string "hallo: welt"))))

(deftest should-generate-yaml-string
  (is (= "hallo: welt
"
         (cut/to-string {:hallo "welt"}))))
