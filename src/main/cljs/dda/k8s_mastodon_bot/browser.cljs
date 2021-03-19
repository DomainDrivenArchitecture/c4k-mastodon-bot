(ns dda.k8s-mastodon-bot.browser
  (:require
   [clojure.spec.alpha :as s]
   [clojure.tools.reader.edn :as edn]
   [expound.alpha :as expound]
   [dda.k8s-mastodon-bot.core :as core]))

(defn print-debug [sth]
  (print "debug " sth)
  sth)

(defn config []
    (-> js/document
      (.getElementById "config")))

(defn set-config-validation! [input]
  (print-debug input)
  (-> js/document
      (.getElementById "config-validation")
      (.-innerHTML)
      (set! input)))

(defn form []
  (-> js/document
      (.getElementById "form")))

(defn config-from-document []
  (-> (config)
      (.-value)))

(defn auth-from-document []
  (-> js/document
      (.getElementById "auth")
      (.-value)))

(defn set-output!
  [input]
  (-> js/document
      (.getElementById "output")
      (.-value)
      (set! input)))

(defn render-validation-result-to-config
  [validation-result]
  (print-debug validation-result)
  (set-config-validation! validation-result)
  (-> (config)
      (.setCustomValidity validation-result))
  (-> (form)
      (.-classList)
      (.add "was-validated"))
  validation-result)

(defn expound-config [config]
  (expound/expound-str ::core/config config  {:print-specs? false}))

(defn validate-config! []
  (let [config (config-from-document)]
    (when-not (s/valid? ::core/config config)
      (-> config
          (edn/read-string)
          (expound-config)
          (render-validation-result-to-config)))))

(defn init []
  (-> js/document
      (.getElementById "generate-button")
      (.addEventListener "click"
                         #(-> (core/generate (config-from-document) (auth-from-document))
                              (set-output!))))
  (-> (config)
      (.addEventListener "blur"
                         #(validate-config!))))
