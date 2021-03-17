(ns dda.k8s-mastodon-bot.uberjar
  (:gen-class)
  (:require
   [clojure.spec.alpha :as s]
   [clojure.string :as cs]
   [clojure.java.io :as io]
   [dda.k8s-mastodon-bot.core :as core]))

(def usage
  "usage:
  
  k8s-mastodon-bot {your configuraton file} {your authorization file}")

(s/def ::options (s/* #{"-h"}))
(s/def ::filename (s/and string?
                              #(not (cs/starts-with? % "-"))))
(s/def ::cmd-args (s/cat :options ::options
                         :args (s/?
                                (s/cat :config ::filename
                                       :auth ::filename))))

(defn invalid-args-msg [spec args]
  (do (s/explain spec args)
      (println (str "Bad commandline arguments\n" usage))))

(defn -main [& cmd-args]
  (let [parsed-args-cmd (s/conform ::cmd-args cmd-args)]
    (if (= ::s/invalid parsed-args-cmd)
      (invalid-args-msg ::cmd-args cmd-args)
      (let [{:keys [options args]} parsed-args-cmd
            config-location (:config args)
            auth-location (:auth args)]
          (cond
            (some #(= "-h" %) options)
            (println usage)
            :default
            (println (core/generate (slurp config-location) (slurp auth-location))))))))
