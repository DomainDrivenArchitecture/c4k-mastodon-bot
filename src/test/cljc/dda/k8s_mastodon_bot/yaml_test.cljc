(ns dda.k8s-mastodon-bot.yaml-test 
  (:require
   [clojure.test :refer [deftest is testing are]]
   [dda.k8s-mastodon-bot.yaml :as cut]))

(deftest should-pars-yaml-string
  (is (= 43
         (cut/from-string "hallo: welt"))))
