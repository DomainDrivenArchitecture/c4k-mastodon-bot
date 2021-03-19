(ns dda.k8s-mastodon-bot.uberjar
  (:gen-class)
  (:require
   [clojure.spec.alpha :as s]
   [clojure.string :as cs]
   [clojure.tools.reader.edn :as edn]
   [expound.alpha :as expound]
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

(defn expound-config
  [config]
  (expound/expound ::core/config config))

(defn invalid-args-msg [spec args]
  (do (s/explain spec args)
      (println (str "Bad commandline arguments\n" usage))))

(defn -main [& cmd-args]
  (let [parsed-args-cmd (s/conform ::cmd-args cmd-args)]
    (if (= ::s/invalid parsed-args-cmd)
      (invalid-args-msg ::cmd-args cmd-args)
      (let [{:keys [options args]} parsed-args-cmd
            config (slurp (:config args))
            auth (slurp (:auth args))]
          (cond
            (some #(= "-h" %) options)
            (println usage)
            :default
            (let [config-edn (edn/read-string config)
                  auth-edn (edn/read-string auth)
                  config-valid? (= ::s/invalid (s/conform ::core/config config-edn))
                  auth-valid? (= ::s/invalid (s/conform ::core/config auth-edn))]
              (if (and config-valid? auth-valid?)
                (println (core/generate config auth))
                (do
                  (when (not config-valid?) (expound-config config-edn))
                  (when (not auth-valid?) (expound-config auth-edn))))))))))
