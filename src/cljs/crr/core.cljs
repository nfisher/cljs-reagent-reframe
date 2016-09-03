(ns crr.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]))

(def initial-state
  {:name "World 5"}) ; this is the map we want to initialise the app-db to.

(rf/reg-event-db
  :initialise
  (fn [db _]
    (merge db initial-state)))

(defn update-name [db [_ new-name]] ; re-frame examples use anonymous functions. Using a named function allows for easier testing.
  (merge db {:name new-name}))

(rf/reg-event-db
  :name-changed
  update-name)

(rf/reg-sub
  :name
  (fn [db arg]
    (println "pirate sayz " arg) ; you should see the subscription vector in the console
    (:name db)))

(defn change-name [evt]
  (rf/dispatch-sync [:name-changed (-> evt .-target .-value)]))

(defn hello-world [name] ; accept name as a parameter
  [:div
    [:input {:type :text :on-change change-name :value name}]
    [:h1 "Hello " name]]) ; instead of JSX, Reagent uses Hiccup which are simply nested vectors.

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
