(ns dda.k8s-mastodon-bot.yaml
  (:require 
   ["js-yaml" :as yaml]
   ))

(defn from-string [input]
  (js->clj (yaml/load input)
           :keywordize-keys true))

(defn to-string [edn]
  (yaml/dump (clj->js  edn)))