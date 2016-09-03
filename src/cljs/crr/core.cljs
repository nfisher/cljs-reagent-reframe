(ns crr.core
  (:require [reagent.core :as reagent]))

(defn hello-world []
  [:h1 "Hello World"]) ; instead of JSX, Reagent uses Hiccup which are simply nested vectors.

(defn ^:export run
  []
  (reagent/render [hello-world] ; hiccup that renders hello-world into page
                  (js/document.getElementById "app")))
