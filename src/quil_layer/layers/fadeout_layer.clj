(ns quil-layer.layers.fadeout-layer
  (:require [quil.core :as q])
  (:use [quil-layer.layer]))

(defn- setup []
  {:alpha 0
   :delta 0})

(defn- calc-delta [layer]
  (let [msec-to-fade (:msec-to-fade layer)
        sec-to-fade (/ msec-to-fade 1000.0)
        delta (/ (/ 255 sec-to-fade) (q/current-frame-rate))]
    delta))

(defn- update-state [layer state]
  (let [cur-delta (:delta state)
        delta (if (> cur-delta 0) cur-delta (calc-delta layer))
        cur-alpha (:alpha state)
        alpha (if (>= cur-alpha 255) 255 (+ cur-alpha delta))]
  {:alpha alpha
   :delta delta}))

(defn- draw-state [state]
  (q/no-stroke)
  (q/color-mode :hsb)
  (let [alpha (:alpha state)]
    (q/fill (.backgroundColor (q/current-graphics)) alpha))
  (q/rect 0 0 (q/width) (q/height)))

(defrecord FadeoutLayer [msec-to-fade state]
  Layer
  (setup-layer-state [this]
                     (setup))
  (update-layer-state [this state]
                      (update-state this state))
  (draw-layer-state [this state]
                    (draw-state state)))
