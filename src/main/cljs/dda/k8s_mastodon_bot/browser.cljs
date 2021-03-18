(ns dda.k8s-mastodon-bot.browser
  (:require
   [clojure.tools.reader.edn :as edn]
   [expound.alpha :as expound]
   [dda.k8s-mastodon-bot.core :as core]))

(defn config-from-document []
  (-> js/document
      (.getElementById "config")
      (.-value)))

(defn auth-from-document []
  (-> js/document
      (.getElementById "auth")
      (.-value)))

(defn render-output-to-document
  [input]
  (-> js/document
      (.getElementById "output")
      (.-value)
      (set! input)))

(defn expound-config
  [config]
  (expound/expound ::core/config config))

(defn init []
  (-> js/document
      (.getElementById "generate-button")
      (.addEventListener "click" 
                         #(-> (core/generate (config-from-document) (auth-from-document))
                              (render-output-to-document))))
  (-> js/document
      (.getElementById "config")
      (.addEventListener "blur"
                         #(-> (config-from-document)
                              (edn/read-string)
                              (expound-config)
                              (print))))
)
