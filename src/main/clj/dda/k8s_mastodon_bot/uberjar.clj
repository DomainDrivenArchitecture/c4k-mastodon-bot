(ns dda.k8s-mastodon-bot.uberjar
  (:gen-class)
  (:require
   [clojure.spec.alpha :as s]
   [expound.alpha :as expound]
   [clojure.java.io :as io]
   [dda.k8s-mastodon-bot.core :as core]))

(alter-var-root #'s/*explain-out* (constantly expound/printer))

(s/def ::options (s/* #{"-h"}))
(s/def ::args (s/cat :options ::options))

(def usage
  "usage:
  
  k8s-mastodon-bot {your configuraton file} {your authorization file}")

(s/def ::filename string?)
(s/def ::cmd-args (s/cat :options ::options
                         :config ::filename
                         :auth ::filename))

(defn invalid-args-msg [spec args]
  (do (s/explain spec args)
      (println (str "Bad commandline arguments\n" usage))))

(defn -main [& cmd-args]
  (let [parsed-args-cmd (s/conform ::cmd-args cmd-args)]
    (if (= ::s/invalid parsed-args-cmd)
      (invalid-args-msg ::cmd-args cmd-args)
      (let [{:keys [options config auth]} parsed-args-cmd
            config-map (slurp config)
            auth-map (slurp auth)]
          (cond
            (some #(= "-h" %) options)
            (println usage)
            :default
            (println (core/generate config-map auth-map)))))))
