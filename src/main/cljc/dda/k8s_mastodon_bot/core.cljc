(ns dda.k8s-mastodon-bot.core
  (:require
   [clojure.string :as cs]
   [clojure.spec.alpha :as s]
   [clojure.spec.test.alpha :as st]
   #?(:clj [orchestra.core :refer [defn-spec]]
      :cljs [orchestra.core :refer-macros [defn-spec]])
   [expound.alpha :as expound]
   [mastodon-bot.core-domain :as cd]
   [dda.k8s-mastodon-bot.yaml :as yaml]))

#?(:clj (alter-var-root #'s/*explain-out* (constantly expound/printer))
   :cljs (set! s/*explain-out* expound/printer))

(s/def ::config cd/config?)

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
  [my-config ::config
   my-auth cd/auth?] 
  (cs/join "\n" 
           [(yaml/to-string (generate-config my-config my-auth))
            "---"
            (yaml/to-string (generate-deployment))]))

(st/instrument 'dda.k8s-mastodon-bot.core)
