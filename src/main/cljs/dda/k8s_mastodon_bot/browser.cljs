(ns dda.k8s-mastodon-bot.browser
  (:require
   [clojure.spec.alpha :as s]
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

(defn init []
  (-> js/document
      (.getElementById "generate-button")
      (.addEventListener "click" 
                         #(-> (core/generate (config-from-document) (auth-from-document))
                              (render-output-to-document)
                              (print "1"))))
  (-> js/document
      (.getElementById "config")
      (.addEventListener "blur"
                         #(-> (s/explain ::core/config (config-from-document))
                              (print))))
  )
