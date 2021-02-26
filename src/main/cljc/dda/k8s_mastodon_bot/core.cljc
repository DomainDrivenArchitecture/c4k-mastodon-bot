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

(def usage
  "usage:
  
  k8s-mastodon-bot {your configuraton} {your authorization}")

(defn main [& args]
  (let [parsed-args (s/conform ::args args)]
     (if (= ::s/invalid parsed-args)
      (do (s/explain ::args args)
          (print (str "Bad commandline arguments\n" usage)))
       (let [{:keys [options config auth]} parsed-args]
         (cond
           (some #(= "-h" %) options)
           (print usage)
           :default
           (yaml/to-string (generate-config config auth)))))))
