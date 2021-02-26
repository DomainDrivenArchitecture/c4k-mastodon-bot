(ns dda.k8s-mastodon-bot.yaml
  (:require 
   ["js-yaml" :as yaml]
   [shadow.resource :as rc]
   ))

(def config (rc/inline "config.yaml"))

(defn load-resource [resource-name]
  config)

(defn from-string [input]
  (js->clj (yaml/load input)
           :keywordize-keys true))

(defn to-string [edn]
  (yaml/dump (clj->js  edn)))