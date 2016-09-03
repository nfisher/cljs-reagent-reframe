(ns crr.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]))

(def initial-state
  {:name "World 5"}) ; this is the map we want to initialise the app-db to.

(rf/reg-event-db
  :initialise
  (fn [db _]
    (merge db initial-state)))

(rf/reg-sub
  :name
  (fn [db arg]
    (println "pirate sayz " arg) ; you should see the subscription vector in the console
    (:name db)))

(defn hello-world [name] ; accept name as a parameter
  [:h1 "Hello " name]) ; instead of JSX, Reagent uses Hiccup which are simply nested vectors.

(defn hello-world-container []
  ;; our container
  (let [name (rf/subscribe [:name])] ; the subscribe takes a vector allowing subscription to a deeply nested value in app-db.
    (fn []
      [hello-world @name]))) ; note we're de-referencing the atom here

(defn ^:export run
  []
  (rf/dispatch-sync [:initialise])
  (reagent/render [hello-world-container ] ; hiccup that renders hello-world into page
                  (js/document.getElementById "app")))
