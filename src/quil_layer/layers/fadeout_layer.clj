(ns quil-layer.layers.fadeout-layer
  (:require [quil.core :as q])
  (:use [quil-layer.layer]))

(defn- setup []
  {:alpha 0})

(defn- update-state [state]
  (let [cur-alpha (:alpha state)
        alpha (if (>= cur-alpha 255) 255 (+ cur-alpha 1))]
  {:alpha alpha}))

(defn- draw-state [state]
  (q/no-stroke)
  (q/color-mode :hsb)
  (let [alpha (:alpha state)]
    (q/fill (.backgroundColor (q/current-graphics)) alpha))
  (q/rect 0 0 (q/width) (q/height)))

(defrecord FadeoutLayer [state]
  Layer
  (setup-layer-state [this]
                     (setup))
  (update-layer-state [this state]
                      (update-state state))
  (draw-layer-state [this state]
                    (draw-state state)))
