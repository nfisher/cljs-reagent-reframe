(ns crr.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]))

(def initial-state
  {:name "World 5"}) ; this is the map we want to initialise the app-db to.

(rf/reg-event-db
  :initialise
  (fn [db _]
    (merge db initial-state)))

(defn hello-world [name] ; accept name as a parameter
  [:h1 "Hello " name]) ; instead of JSX, Reagent uses Hiccup which are simply nested vectors.

(defn hello-world-container []
  ;; our container
  (let [name "World 4"]
    (fn []
      [hello-world name])))

(defn ^:export run
  []
  (rf/dispatch-sync [:initialise])
  (reagent/render [hello-world-container ] ; hiccup that renders hello-world into page
                  (js/document.getElementById "app")))
