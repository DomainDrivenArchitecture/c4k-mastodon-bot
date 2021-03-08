(ns dda.k8s-mastodon-bot.uberjar
  (:gen-class)
  (:require
   [clojure.spec.alpha :as s]
   [dda.k8s-mastodon-bot.core :as core]))


(def usage
  "usage:
  
  k8s-mastodon-bot '{your configuraton}' '{your authorization}'")

; Checks if a string validates a given spec after the string is transformed to Clojure data structure by clojure.edn/read-string.
(defn spec-string? [spec]
  (fn [str]
    (s/and
     (string? str)
     (s/valid? spec (clojure.edn/read-string str)))))

(s/def ::config-map-str (spec-string? ::core/config))
(s/def ::auth-map-str (spec-string? ::core/auth))
(s/def ::cmd-args (s/cat :options ::core/options
                         :config ::config-map-str
                         :auth ::config-map-str))

(defn invalid-args-msg [spec args]
  (do (s/explain spec args)
      (println (str "Bad commandline arguments\n" usage))))

(defn -main [& cmd-args]
  (let [parsed-args-cmd (s/conform ::cmd-args cmd-args)]
    (if (= ::s/invalid parsed-args-cmd)
      (invalid-args-msg ::cmd-args cmd-args)
      (let [{:keys [options config auth]} parsed-args-cmd
            config-map (clojure.edn/read-string config)
            auth-map (clojure.edn/read-string auth)]
          (cond
            (some #(= "-h" %) options)
            (println usage)
            :default
            (println (core/generate config-map auth-map)))))))
