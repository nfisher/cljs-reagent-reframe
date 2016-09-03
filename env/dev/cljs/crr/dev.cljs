(ns crr.dev
  (:require [crr.core :as crr]
            [figwheel.client :as fw]))

(fw/start {:on-jsload crr/run
           :websocket-url "ws://localhost:3449/figwheel-ws"})
