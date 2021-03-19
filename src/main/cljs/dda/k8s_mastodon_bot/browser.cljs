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

(defn auth []
  (-> js/document
      (.getElementById "auth")))

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
  (-> (auth)
      (.-value)))

(defn set-output!
  [input]
  (-> js/document
      (.getElementById "output")
      (.-value)
      (set! input)))

(defn set-config-validation-result!
  [validation-result]
  (print-debug validation-result)
  (set-config-validation! validation-result)
  (-> (config)
      (.setCustomValidity validation-result))
  (-> (form)
      (.-classList)
      (.add "was-validated"))
  validation-result)

(defn validate-config! []
  (let [config-str (config-from-document)
        config-map (edn/read-string config-str)]
    (if (s/valid? ::core/config config-map)
      (set-config-validation-result! "")
      (set-config-validation-result!
       (expound/expound-str ::core/config config-map {:print-specs? false})))))

(defn set-validated! []
  (-> (form)
      (.-classList)
      (.add "was-validated")))

(defn set-auth-validation-result!
  [validation-result]
  (-> js/document
       (.getElementById "auth-validation")
       (.-innerHTML)
       (set! validation-result))
  (-> (auth)
      (.setCustomValidity validation-result))
  validation-result)

(defn validate-auth! []
  (let [auth-str (auth-from-document)
        auth-map (edn/read-string auth-str)]
    (when-not (s/valid? ::core/auth auth-map)
      (set-auth-validation-result!
       (expound/expound-str ::core/auth auth-map {:print-specs? false})))))

(defn init []
  (-> js/document
      (.getElementById "generate-button")
      (.addEventListener "click"
                         #(-> (core/generate (config-from-document) (auth-from-document))
                              (set-output!))))
  (-> (config)
      (.addEventListener "blur"
                         #(do (validate-config!)
                              (set-validated!)))))
