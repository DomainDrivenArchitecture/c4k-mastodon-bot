(ns dda.k8s-mastodon-bot.core
  (:require
   [clojure.spec.alpha :as s]
   [clojure.string :as cs]
   ;;[dda.k8s-mastodon-bot.yaml :as yaml]
   ))

(s/def ::options (s/* #{"-h"}))
(s/def ::config-location (s/? (s/and string?
                                     #(not (cs/starts-with? % "-")))))
(s/def ::args (s/cat :options ::options
                     :config-location ::config-location))

(defn main [& args]
  (let [parsed-args (s/conform ::args args)]
     (if (= ::s/invalid parsed-args)
      (do (s/explain ::args args)
          )
       (let [{:keys [options config-location]} parsed-args]
         "hallowelt"
       ))))
