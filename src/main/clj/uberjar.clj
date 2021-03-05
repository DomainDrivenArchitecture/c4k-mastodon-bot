(ns dda.k8s-mastodon-bot.uberjar
  (:require
   [clojure.spec.alpha :as s]
   [dda.k8s-mastodon-bot.core :as core])


(def usage
  "usage:
  
  k8s-mastodon-bot {your configuraton} {your authorization}")

(defn main [& args]
  (let [parsed-args (s/conform ::core/args args)]
    (if (= ::s/invalid parsed-args)
      (do (s/explain ::core/args args)
          (print (str "Bad commandline arguments\n" usage)))
      (let [{:keys [options config auth]} parsed-args]
        (cond
          (some #(= "-h" %) options)
          (print usage)
          :default
          (generate config auth))))))
