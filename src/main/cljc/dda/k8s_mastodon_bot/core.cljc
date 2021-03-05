(ns dda.k8s-mastodon-bot.core
  (:require
   [clojure.spec.alpha :as s]
   [clojure.string :as cs]
   [expound.alpha :as expound]
   [dda.k8s-mastodon-bot.yaml :as yaml]
   ))

(set! s/*explain-out* expound/printer)

(s/def ::options (s/* #{"-h"}))
(s/def ::config map?)
(s/def ::auth map?)
(s/def ::args (s/cat :options ::options
                     :config ::config
                     :auth ::auth))

(defn generate-config [my-config my-auth]
  (->
   (yaml/from-string (yaml/load-resource "config.yaml"))
   (assoc-in [:data :config.edn] (str my-config))
   (assoc-in [ :data :credentials.edn] (str my-auth))
   ))

(defn generate [my-config my-auth]
  (yaml/to-string (generate-config my-config my-auth)))


