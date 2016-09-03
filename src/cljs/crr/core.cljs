(ns crr.core
  (:require [reagent.core :as reagent]))

(defn hello-world [name] ; accept name as a parameter
  [:h1 "Hello " name]) ; instead of JSX, Reagent uses Hiccup which are simply nested vectors.

(defn ^:export run
  []
  (reagent/render [hello-world "World 2"] ; hiccup that renders hello-world into page
                  (js/document.getElementById "app")))
