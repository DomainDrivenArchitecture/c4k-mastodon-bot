(ns dda.k8s-mastodon-bot.core
  (:require
   [clojure.string :as cs]
   [orchestra.core :refer-macros [defn-spec]]
   [mastodon-bot.core-domain :as cd]
   [dda.k8s-mastodon-bot.yaml :as yaml]
   ))

(defn generate-config [my-config my-auth]
  (->
   (yaml/from-string (yaml/load-resource "config.yaml"))
   (assoc-in [:data :config.edn] (str my-config))
   (assoc-in [ :data :credentials.edn] (str my-auth))
   ))

(defn generate-deployment []
  (->
   (yaml/from-string (yaml/load-resource "deployment.yaml"))))

(defn-spec generate any?
  [my-config cd/config?
   my-auth cd/auth?] 
  (cs/join "\n" 
           [(yaml/to-string (generate-config my-config my-auth))
            "---"
            (yaml/to-string (generate-deployment))]))


