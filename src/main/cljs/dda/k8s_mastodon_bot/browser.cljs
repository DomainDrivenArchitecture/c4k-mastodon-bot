(ns dda.k8s-mastodon-bot.browser
  (:require
   [dda.k8s-mastodon-bot.core :as core]))

(defn config-from-document []
  (-> js/document
      (.getElementById "config")
      (.-innerHTML)))

(defn auth-from-document []
  (-> js/document
      (.getElementById "auth")
      (.-innerHTML)))

(defn render-to-document
  [input]
  (-> js/document
      (.getElementById "output")
      (.-innerHTML)
      (set! input)))

(defn generate []
  (-> (dda.k8s-mastodon-bot.core/generate (config-from-document) (auth-from-document))
      (render-to-document)))

(defn init []
  (-> js/document
      (.getElementById "generate-button")
      (.addEventListener "click" generate)))