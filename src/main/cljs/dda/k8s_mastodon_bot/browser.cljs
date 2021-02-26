(ns dda.k8s-mastodon-bot.browser
  (:require
   [dda.k8s-mastodon-bot.core :as core]))

(defn config-from-document []
  (-> js/document
      (.getElementById "config")
      (.-value)))

(defn auth-from-document []
  (-> js/document
      (.getElementById "auth")
      (.-value)))

(defn render-to-document
  [input]
  (-> js/document
      (.getElementById "output")
      (.-value)
      (set! input)))

(defn init []
  (-> js/document
      (.getElementById "generate-button")
      (.addEventListener "click" 
                         #(-> (core/generate (config-from-document) (auth-from-document))
                              (render-to-document)))))