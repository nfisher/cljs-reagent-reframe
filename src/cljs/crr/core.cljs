(ns crr.core
  (:require [reagent.core :as reagent]))

(defn hello-world [name] ; accept name as a parameter
  [:h1 "Hello " name]) ; instead of JSX, Reagent uses Hiccup which are simply nested vectors.

(defn hello-world-container []
  ;; our container
  (let [name "World 3"]
    (fn []
      [hello-world name])))

(defn ^:export run
  []
  (reagent/render [hello-world-container ] ; hiccup that renders hello-world into page
                  (js/document.getElementById "app")))
